package com.codegym.controller;

import com.codegym.model.*;
import com.codegym.service.CartService;
import com.codegym.service.CustomerService;
import com.codegym.service.OrderService;
import com.codegym.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private WatchService watchService;

    @GetMapping("/checkout")
    public String checkoutForm(Model model) {
        model.addAttribute("paymentMethods", PaymentMethod.values());
        return "order/checkout";
    }

    @PostMapping("/checkout")
    public String processCheckout(@RequestParam String address,
                                  @RequestParam PaymentMethod paymentMethod,
                                  RedirectAttributes redirect) {

        List<CartItem> items = new ArrayList<>(cartService.getAllItems());
        if (items.isEmpty()) {
            redirect.addFlashAttribute("error", "Giỏ hàng đang trống.");
            return "redirect:/cart/view";
        }

        Customer customer = customerService.getCurrentCustomer();
        Order order = orderService.placeOrder(customer, items, address, paymentMethod);

        // Trừ số lượng sản phẩm còn lại
        for (CartItem item : items) {
            Watch watch = item.getWatch();
            watch.setQuantity(watch.getQuantity() - item.getQuantity());
            watchService.save(watch);
        }

        cartService.clear();

        redirect.addFlashAttribute("success", "Đặt hàng thành công!");
        return "redirect:/order/status/" + order.getId();
    }

    // Trang xử lý đơn hàng (BƯỚC 7)
    @GetMapping("/status/{id}")
    public String orderStatus(@PathVariable Long id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return "order/status";
    }

    // Lịch sử đơn hàng
    @GetMapping("/history")
    public String history(Model model) {
        Customer customer = customerService.getCurrentCustomer();
        List<Order> orders = orderService.findOrdersByCustomer(customer);
        model.addAttribute("orders", orders);
        return "order/history";
    }
}

