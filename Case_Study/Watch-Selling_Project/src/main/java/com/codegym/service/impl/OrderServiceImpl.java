package com.codegym.service.impl;

import com.codegym.model.*;
import com.codegym.repository.IOrderRepo;
import com.codegym.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private IOrderRepo orderRepo;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
    private final ConcurrentMap<Long, ScheduledFuture<?>> tasks = new ConcurrentHashMap<>();

    @Override
    public Order placeOrder(Customer customer, List<CartItem> cartItems, String address, PaymentMethod method) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setShippingAddress(address);
        order.setPaymentMethod(method);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        for (CartItem cartItem : cartItems) {
            OrderItem item = new OrderItem();
            item.setWatch(cartItem.getWatch());
            item.setQuantity(cartItem.getQuantity());
            item.setOrder(order);
            order.getItems().add(item);

            // Giảm số lượng đồng hồ
            int currentQty = cartItem.getWatch().getQuantity();
            cartItem.getWatch().setQuantity(currentQty - cartItem.getQuantity());
        }

        Order saved = orderRepo.save(order);
        scheduleOrderStatusUpdates(saved.getId());
        return saved;
    }

    private void scheduleOrderStatusUpdates(Long orderId) {
        ScheduledFuture<?> task = scheduler.schedule(() -> updateStatus(orderId, OrderStatus.PROCESSING), 5, TimeUnit.SECONDS);
        tasks.put(orderId, task);

        scheduler.schedule(() -> updateStatus(orderId, OrderStatus.SHIPPING), 10, TimeUnit.SECONDS);
        scheduler.schedule(() -> updateStatus(orderId, OrderStatus.DELIVERED), 15, TimeUnit.SECONDS);
    }

    private void updateStatus(Long orderId, OrderStatus newStatus) {
        Optional<Order> optionalOrder = orderRepo.findById(orderId);
        optionalOrder.ifPresent(order -> {
            if (order.getStatus() == OrderStatus.CANCELLED || order.getStatus() == OrderStatus.DELIVERED) {
                return;
            }

            // Nếu trạng thái hiện tại đã cao hơn trạng thái mới, không cập nhật
            if (order.getStatus().ordinal() >= newStatus.ordinal()) {
                return;
            }

            order.setStatus(newStatus);
            orderRepo.save(order);
        });
    }


    @Override
    public List<Order> findOrdersByCustomer(Customer customer) {
        return orderRepo.findByCustomerOrderByCreatedAtDesc(customer);
    }

    @Override
    public Order findById(Long id) {
        return orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public void delete(Order order) {
        orderRepo.delete(order);
    }
    @Override
    public void save(Order order) {
        orderRepo.save(order);
    }


}
