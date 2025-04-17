package com.codegym.quanlikhachhang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class NewCustomerController {
    private CustomerDAO customerDAO = new CustomerDAO();

    @GetMapping("/customers")
    public String showCustomer(Model model) {
      List<Customer> customerList = customerDAO.getAllCustomers();
      model.addAttribute("customerList", customerList);
      return "list";
    }
    // Hiển thị form chỉnh sửa khách hàng
    @GetMapping("/customers/edit")
    public String showEditForm(@RequestParam("id") int id, Model model) {
        Customer customer = customerDAO.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "edit";
    }

    // Xử lý dữ liệu chỉnh sửa sau khi submit
    @PostMapping("/customers/update")
    public String updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerDAO.updateCustomer(customer);
        return "redirect:/customers";
    }

}
