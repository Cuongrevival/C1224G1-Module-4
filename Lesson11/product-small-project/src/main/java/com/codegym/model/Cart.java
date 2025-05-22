package com.codegym.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Long, CartItem> items = new HashMap<>();

    public void addProduct(Product product) {
        CartItem item = items.get(product.getId());
        if (item == null) {
            items.put(product.getId(), new CartItem(product, 1));
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }
    }

    public void removeProduct(Long productId) {
        items.remove(productId);
    }

    public void updateQuantity(Long productId, int quantity) {
        CartItem item = items.get(productId);
        if (item != null) {
            if (quantity <= 0) items.remove(productId);
            else item.setQuantity(quantity);
        }
    }

    public double getTotalPrice() {
        return items.values().stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();
    }

    public void clear() {
        items.clear();
    }

    public Collection<CartItem> getItems() {
        return items.values();
    }
}
