package com.codegym.controller;

import com.codegym.model.*;
import com.codegym.service.OrderService;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ProductService productService;
    @Autowired private OrderService orderService;

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        Optional<Product> product = productService.findById(id);
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        cart.addProduct(product.get());
        session.setAttribute("cart", cart);
        return "redirect:/products";
    }

    @GetMapping
    public String showCart(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam Long id, @RequestParam int quantity, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.updateQuantity(id, quantity);
        }
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.removeProduct(id);
        }
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && !cart.getItems().isEmpty()) {
            Order order = new Order();
            List<OrderItem> orderItems = new ArrayList<>();

            for (CartItem ci : cart.getItems()) {
                OrderItem oi = new OrderItem();
                oi.setProduct(ci.getProduct());
                oi.setQuantity(ci.getQuantity());
                oi.setPrice(ci.getProduct().getPrice());
                orderItems.add(oi);
            }

            order.setItems(orderItems);
            order.setTotal(cart.getTotalPrice());
            orderService.save(order);
            cart.clear();
            session.setAttribute("cart", cart);
            model.addAttribute("order", order);
        }
        return "checkout";
    }
}
