package com.codegym.controller;

import com.codegym.model.Cart;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DecimalFormat;
import java.util.List;

@Controller
public class CheckoutController {

    @Autowired
    private ProductService cartService;

    private String formatPrice(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
        return decimalFormat.format(price);
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        List<Cart> cartItems = cartService.getCartItems();

        for (Cart item : cartItems) {
            double price = item.getProduct().getDiscountPrice() > 0
                    ? item.getProduct().getDiscountPrice()
                    : item.getProduct().getPrice();
            item.setFormattedPrice(formatPrice(price));
            item.setFormattedTotal(formatPrice(item.getTotal()));
        }

        double total = cartService.calculateTotal();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", formatPrice(total));

        return "checkout"; // Trỏ tới checkout.html
    }
    @PostMapping("/checkout/complete")
    public String completeCheckout() {
        cartService.clearCart(); // Giả sử có hàm này
        return "redirect:/checkout/success";
    }
    @GetMapping("/checkout/success")
    public String checkoutSuccess() {
        return "thankyou";
    }

}
