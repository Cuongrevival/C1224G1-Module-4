package com.codegym.service;

import com.codegym.model.CartItem;
import com.codegym.model.Watch;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
public class CartService {
    private final Map<Long, CartItem> cart = new HashMap<>();

    public void addItem(Watch watch) {
        cart.compute(watch.getId(), (id, item) ->
                (item == null) ? new CartItem(watch, 1) : new CartItem(watch, item.getQuantity() + 1));
    }

    public void removeItem(Long id) {
        cart.remove(id);
    }

    public Collection<CartItem> getAllItems() {
        return cart.values();
    }

    public double getTotal() {
        return cart.values().stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public void clear() {
        cart.clear();
    }
}

