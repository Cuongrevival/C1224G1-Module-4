package com.codegym.service;

import com.codegym.model.CartItem;
import com.codegym.model.Customer;
import com.codegym.model.Order;
import com.codegym.model.PaymentMethod;

import java.util.List;

public interface OrderService {
    Order placeOrder(Customer customer, List<CartItem> cartItems, String address, PaymentMethod method);
    List<Order> findOrdersByCustomer(Customer customer);
    Order findById(Long id);
}
