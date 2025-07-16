package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/profile")
    public String showProfile(Model model) {
        Customer customer = customerService.getCurrentCustomer();
        model.addAttribute("customer", customer);
        return "customer/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute("customer") Customer updatedCustomer,
                                RedirectAttributes redirect) {
        Customer current = customerService.getCurrentCustomer();

        current.setFullName(updatedCustomer.getFullName());
        current.setPhone(updatedCustomer.getPhone());

        customerService.save(current);

        redirect.addFlashAttribute("success", "Cập nhật thông tin thành công!");
        return "redirect:/customer/profile";
    }

}
