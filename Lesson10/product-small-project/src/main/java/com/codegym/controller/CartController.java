package com.codegym.controller;

import com.codegym.model.Cart;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private ProductService cartService;

    // Định dạng giá trị tiền
    private String formatPrice(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
        return decimalFormat.format(price);
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        // Lấy danh sách sản phẩm trong giỏ hàng
        List<Cart> cartItems = cartService.getCartItems();

        // Định dạng giá trị của từng sản phẩm và tổng giỏ hàng
        for (Cart item : cartItems) {
            double price = item.getProduct().getDiscountPrice() > 0 ? item.getProduct().getDiscountPrice() : item.getProduct().getPrice();
            item.setFormattedPrice(formatPrice(price));
            item.setFormattedTotal(formatPrice(item.getTotal()));
        }

        // Tính tổng giỏ hàng và định dạng
        double total = cartService.calculateTotal();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", formatPrice(total)); // Định dạng tổng cộng

        return "cart"; // Trả về file cart.html
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity) {
        cartService.addToCart(productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/update")
    public String updateCart(@RequestParam List<Long> productIds,
                             @RequestParam List<Integer> quantities) {
        for (int i = 0; i < productIds.size(); i++) {
            cartService.updateQuantity(productIds.get(i), quantities.get(i));
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeItem(@PathVariable Long id) {
        cartService.removeItem(id);
        return "redirect:/cart";
    }
}
