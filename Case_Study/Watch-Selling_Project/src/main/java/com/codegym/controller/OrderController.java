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

    // Hiển thị form thanh toán
    @GetMapping("/checkout")
    public String checkoutForm(@RequestParam(value = "watchId", required = false) Long watchId, Model model) {
        if (watchId != null) {
            Watch watch = watchService.findById(watchId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID = " + watchId));
            model.addAttribute("selectedWatch", watch);
        }
        model.addAttribute("paymentMethods", PaymentMethod.values());
        return "order/checkout";
    }

    // Xử lý đặt hàng
    @PostMapping("/checkout")
    public String processCheckout(@RequestParam String address,
                                  @RequestParam PaymentMethod paymentMethod,
                                  @RequestParam(value = "watchId", required = false) Long watchId,
                                  RedirectAttributes redirect) {

        Customer customer = customerService.getCurrentCustomer();
        Order order;

        if (watchId != null) {
            Watch watch = watchService.findById(watchId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID = " + watchId));

            if (watch.getQuantity() < 1) {
                redirect.addFlashAttribute("error", "Sản phẩm đã hết hàng.");
                return "redirect:/watches";
            }

            CartItem item = new CartItem(watch, 1);
            List<CartItem> items = new ArrayList<>();
            items.add(item);

            order = orderService.placeOrder(customer, items, address, paymentMethod);

            watch.setQuantity(watch.getQuantity() - 1);
            watchService.save(watch);

        } else {
            List<CartItem> items = new ArrayList<>(cartService.getAllItems());

            if (items.isEmpty()) {
                redirect.addFlashAttribute("error", "Giỏ hàng đang trống.");
                return "redirect:/cart/view";
            }

            order = orderService.placeOrder(customer, items, address, paymentMethod);

            for (CartItem item : items) {
                Watch watch = item.getWatch();
                watch.setQuantity(watch.getQuantity() - item.getQuantity());
                watchService.save(watch);
            }

            cartService.clear();
        }

        redirect.addFlashAttribute("success", "Đặt hàng thành công!");
        return "redirect:/order/status/" + order.getId();
    }

    // Hiển thị tiến trình đơn hàng
    @GetMapping("/status/{id}")
    public String orderStatus(@PathVariable Long id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);

        String status = order.getStatus().name();
        String text;
        String color;
        int percent;

        switch (status) {
            case "PENDING":
                text = "Đang xử lý";
                color = "bg-secondary";
                percent = 25;
                break;
            case "PROCESSING":
                text = "Đang tìm người giao";
                color = "bg-warning";
                percent = 50;
                break;
            case "SHIPPING":
                text = "Đang giao hàng";
                color = "bg-info";
                percent = 75;
                break;
            case "DELIVERED":
                text = "Đã giao thành công";
                color = "bg-success";
                percent = 100;
                break;
            case "CANCELLED":
                text = "Đã hủy";
                color = "bg-danger";
                percent = 100;
                break;
            default:
                text = "Không xác định";
                color = "bg-dark";
                percent = 0;
                break;
        }

        model.addAttribute("progressText", text);
        model.addAttribute("progressColor", color);
        model.addAttribute("progressPercent", percent);

        return "order/status";
    }

    // API trả tiến trình đơn hàng dạng JSON
    @GetMapping("/status/api/{id}")
    @ResponseBody
    public OrderStatusDto getStatus(@PathVariable Long id) {
        Order order = orderService.findById(id);
        String status = order.getStatus().name();
        String text;
        String color;
        int percent;

        switch (status) {
            case "PENDING":
                text = "Đang xử lý";
                color = "bg-secondary";
                percent = 25;
                break;
            case "PROCESSING":
                text = "Đang tìm người giao";
                color = "bg-warning";
                percent = 50;
                break;
            case "SHIPPING":
                text = "Đang giao hàng";
                color = "bg-info";
                percent = 75;
                break;
            case "DELIVERED":
                text = "Đã giao thành công";
                color = "bg-success";
                percent = 100;
                break;
            case "CANCELLED":
                text = "Đã hủy";
                color = "bg-danger";
                percent = 100;
                break;
            default:
                text = "Không xác định";
                color = "bg-dark";
                percent = 0;
                break;
        }

        return new OrderStatusDto(status, text, color, percent);
    }

    // Lịch sử đơn hàng
    @GetMapping("/history")
    public String history(Model model) {
        Customer customer = customerService.getCurrentCustomer();
        List<Order> orders = orderService.findOrdersByCustomer(customer);
        model.addAttribute("orders", orders);
        return "order/history";
    }

    // Mua ngay một sản phẩm
    @GetMapping("/buy/{id}")
    public String buyNow(@PathVariable Long id, RedirectAttributes redirect) {
        Watch watch = watchService.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID = " + id));

        if (watch.getQuantity() < 1) {
            redirect.addFlashAttribute("error", "Sản phẩm đã hết hàng.");
            return "redirect:/watches";
        }

        return "redirect:/order/checkout?watchId=" + id;
    }

    // Xóa đơn hàng đã giao
    @PostMapping("/delete/{id}")
    public String deleteCompletedOrder(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Order order = orderService.findById(id);

        if (order == null) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy đơn hàng.");
            return "redirect:/order/history";
        }

        if (order.getStatus().name().equals("DELIVERED") || order.getStatus().name().equals("CANCELLED")) {
            orderService.delete(order);
            redirectAttributes.addFlashAttribute("success", "Đã xóa đơn hàng thành công.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Không thể xóa được đơn hàng");
        }

        return "redirect:/order/history";
    }

    // Hiển thị form hủy đơn hàng
    @GetMapping("/cancel/{id}")
    public String showCancelForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Order order = orderService.findById(id);

        if (order == null ||
                (!order.getStatus().name().equals("PENDING") &&
                        !order.getStatus().name().equals("PROCESSING"))) {
            redirectAttributes.addFlashAttribute("error", "Không thể hủy đơn hàng này.");
            return "redirect:/order/status/" + id;
        }

        model.addAttribute("order", order);
        return "order/cancel";
    }

    // Xử lý hủy đơn hàng
    @PostMapping("/cancel/submit")
    public String submitCancelForm(@RequestParam Long orderId,
                                   @RequestParam String cancelReason,
                                   RedirectAttributes redirectAttributes) {
        Order order = orderService.findById(orderId);

        if (order == null) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy đơn hàng.");
            return "redirect:/order/history";
        }

        order.setStatus(OrderStatus.CANCELLED);
        order.setCancelReason(cancelReason);
        orderService.save(order);

        redirectAttributes.addFlashAttribute("success", "Đơn hàng đã được hủy thành công.");
        return "redirect:/order/status/" + orderId;
    }
}
