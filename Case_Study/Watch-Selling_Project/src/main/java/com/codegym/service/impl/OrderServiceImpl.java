package com.codegym.service.impl;

import com.codegym.model.*;
import com.codegym.repository.IOrderItemRepo;
import com.codegym.repository.IOrderRepo;
import com.codegym.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired private IOrderRepo orderRepository;
    @Autowired private IOrderItemRepo orderItemRepository;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    public Order placeOrder(Customer customer, List<CartItem> cartItems, String address, PaymentMethod method) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setShippingAddress(address);
        order.setPaymentMethod(method);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> items = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem item = new OrderItem();
            item.setWatch(cartItem.getWatch());
            item.setQuantity(cartItem.getQuantity());
            item.setOrder(order);
            items.add(item);
        }
        order.setItems(items);
        Order saved = orderRepository.save(order);

        // Bắt đầu chuỗi cập nhật trạng thái
        startOrderProcess(saved);

        return saved;
    }

    private void startOrderProcess(Order order) {
        long delay = 5; // phút
        scheduler.schedule(() -> updateStatus(order.getId(), OrderStatus.PROCESSING), delay, TimeUnit.MINUTES);
        scheduler.schedule(() -> updateStatus(order.getId(), OrderStatus.SHIPPING), delay * 2, TimeUnit.MINUTES);
        scheduler.schedule(() -> updateStatus(order.getId(), OrderStatus.DELIVERED), delay * 3, TimeUnit.MINUTES);
    }

    private void updateStatus(Long orderId, OrderStatus status) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        optionalOrder.ifPresent(o -> {
            o.setStatus(status);
            orderRepository.save(o);
        });
    }

    @Override
    public List<Order> findOrdersByCustomer(Customer customer) {
        return orderRepository.findByCustomerOrderByCreatedAtDesc(customer);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng có ID: " + id));

    }
}


