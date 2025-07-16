package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Role;
import com.codegym.model.Watch;
import com.codegym.service.CustomerService;
import com.codegym.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private WatchService watchService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/watches")
    public String manageWatches(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                Model model) {
        model.addAttribute("watches", watchService.findAllPaged(page, size));
        return "admin/watch-list";
    }

    @GetMapping("/watches/create")
    public String createWatchForm(Model model) {
        model.addAttribute("watch", new Watch());
        return "admin/watch-form";
    }

    @PostMapping("/watches/save")
    public String saveWatch(@ModelAttribute Watch watch) {
        watchService.save(watch);
        return "redirect:/admin/watches";
    }

    @GetMapping("/watches/edit/{id}")
    public String editWatch(@PathVariable Long id, Model model) {
        model.addAttribute("watch", watchService.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy đồng hồ")));
        return "admin/watch-form";
    }

    @GetMapping("/watches/delete/{id}")
    public String deleteWatch(@PathVariable Long id) {
        watchService.findById(id).ifPresent(watchService::delete);
        return "redirect:/admin/watches";
    }

    @GetMapping("/users")
    public String manageUsers(Model model) {
        model.addAttribute("users", customerService.findAll());
        return "admin/user-list";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id, Principal principal, RedirectAttributes redirectAttributes) {
        Customer userToDelete = customerService.findById(id);

        // Ngăn xóa admin
        if (userToDelete.getRole() == Role.ADMIN) {
            redirectAttributes.addFlashAttribute("error", "Không thể xóa tài khoản quản trị viên.");
            return "redirect:/admin/users";
        }

        customerService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Xóa người dùng thành công.");
        return "redirect:/admin/users";
    }

}

