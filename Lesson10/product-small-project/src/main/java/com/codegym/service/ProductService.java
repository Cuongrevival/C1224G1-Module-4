package com.codegym.service;

import com.codegym.model.Cart;
import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepository;

    private final Map<Long, Cart> cart = new HashMap<>();

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // Lấy chi tiết sản phẩm theo ID
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Thêm sản phẩm vào giỏ hàng
    public void addToCart(Long productId, int quantity) {
        Product product = findById(productId);
        if (product != null) {
            cart.merge(productId, new Cart(product, quantity),
                    (oldItem, newItem) -> {
                        oldItem.setQuantity(oldItem.getQuantity() + newItem.getQuantity());
                        return oldItem;
                    });
        }
    }

    // Cập nhật số lượng
    public void updateQuantity(Long productId, int quantity) {
        Cart item = cart.get(productId);
        if (item != null) {
            item.setQuantity(quantity);
        }
    }

    // Xoá khỏi giỏ hàng
    public void removeItem(Long productId) {
        cart.remove(productId);
    }

    // Lấy danh sách item trong giỏ hàng
    public List<Cart> getCartItems() {
        return new ArrayList<>(cart.values());
    }

    // Tính tổng giá tiền
    public double calculateTotal() {
        return cart.values().stream()
                .mapToDouble(Cart::getTotal)
                .sum();
    }

    public void clearCart() {
        cart.clear();
    }

}
