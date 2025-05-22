package com.codegym.quanlikhachhang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/customers")
public class NewCustomerController {
    @Autowired
    private CustomerDAO customerDAO;

    @GetMapping("/list")
    public String showCustomer(Model model) {
        List<Customer> customerList = customerDAO.getAllCustomers();
        model.addAttribute("customerList", customerList);
        return "list";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") int id, Model model) {
        Customer customer = customerDAO.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "edit";
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerDAO.updateCustomer(customer);
        return "redirect:/customers";
    }

}
