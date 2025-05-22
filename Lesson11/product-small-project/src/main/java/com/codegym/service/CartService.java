package com.codegym.service;


import com.codegym.model.Cart;
import com.codegym.model.Product;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final Cart cart = new Cart();

    public Cart getCart() {
        return cart;
    }


    public void addToCart(Product product) {
        cart.addProduct(product);
    }


    public void removeFromCart(Long productId) {
        cart.removeProduct(productId);
    }


    public void updateQuantity(Long productId, int quantity) {
        cart.updateQuantity(productId, quantity);
    }


    public void clearCart() {
        cart.clear();
    }
}

