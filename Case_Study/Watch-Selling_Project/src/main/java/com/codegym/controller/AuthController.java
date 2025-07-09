package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute Customer customer,
                                  @RequestParam String confirmPassword,
                                  @RequestParam(defaultValue = "false") boolean agree,
                                  RedirectAttributes redirect) {
        if (!agree) {
            redirect.addFlashAttribute("error", "Bạn phải đồng ý với điều khoản.");
            return "redirect:/register";
        }

        if (!customer.getPassword().equals(confirmPassword)) {
            redirect.addFlashAttribute("error", "Mật khẩu xác nhận không khớp.");
            return "redirect:/register";
        }

        customerService.register(customer);
        redirect.addFlashAttribute("success", "Đăng ký thành công! Mời bạn đăng nhập.");
        return "redirect:/login";
    }
}

