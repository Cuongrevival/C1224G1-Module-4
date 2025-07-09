package com.codegym.controller;

import com.codegym.model.Watch;
import com.codegym.service.CartService;
import com.codegym.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cartService")
public class CartController {

    @Autowired
    private WatchService watchService;

    @Autowired
    private CartService cartService;

    @GetMapping("/view")
    public String viewCart(Model model) {
        model.addAttribute("items", cartService.getAllItems());
        model.addAttribute("total", cartService.getTotal());
        return "cart/view";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, RedirectAttributes redirect) {
        Watch watch = watchService.findById(id).orElse(null);
        if (watch == null || watch.getQuantity() <= 0) {
            redirect.addFlashAttribute("error", "Sản phẩm không tồn tại hoặc đã hết hàng.");
            return "redirect:/watches";
        }
        cartService.addItem(watch);
        return "redirect:/cart/view";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {
        cartService.removeItem(id);
        return "redirect:/cart/view";
    }
}

