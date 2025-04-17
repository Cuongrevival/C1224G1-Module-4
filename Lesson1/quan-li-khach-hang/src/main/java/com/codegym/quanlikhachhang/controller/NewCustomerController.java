package com.codegym.quanlikhachhang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class NewCustomerController {
    @GetMapping("/customer")
    public String showCustomer(Model model) {
        List<Integer> customerId = new ArrayList<>();
        customerId.add(1);
        customerId.add(2);
        List<String> customerName = new ArrayList<>();
        customerName.add("Nguyễn Hồng Hà");
        customerName.add("Hoàng Văn Cừ");
        Map<Integer, String> customerInfo = new HashMap<>();
        customerInfo.put(customerId.get(0), customerName.get(0));
        customerInfo.put(customerId.get(1), customerName.get(1));
        model.addAttribute("customerInfo", customerInfo);
        return "list";
    }
}
