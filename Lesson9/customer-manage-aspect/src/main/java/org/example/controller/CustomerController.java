package org.example.controller;

import org.example.model.Customer;
import org.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/customers")  // Ensure the controller path starts with /customers
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // List customers with pagination
    @GetMapping
    public ModelAndView listCustomers(@PageableDefault(size = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("customer/customer-list");
        modelAndView.addObject("customers", customerService.getAllCustomers(pageable));
        return modelAndView;
    }

    // Show details of a specific customer
    @GetMapping("/{id}/show")
    public ModelAndView showCustomer(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            modelAndView.setViewName("customer/customer-show");
            modelAndView.addObject("customer", customer.get());
        } else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    // Display form to create a new customer
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    // Handle customer creation form submission
    @PostMapping("/create")
    public String createCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return "customer/create";
        }
        customerService.add(customer);
        return "redirect:/customers";
    }

    // Display form to update an existing customer
    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            modelAndView.setViewName("customer/update");
            modelAndView.addObject("customer", customer.get());
        } else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    // Handle customer update form submission
    @PostMapping("/update")
    public String updateCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return "customer/update";
        }
        customerService.update(customer);
        return "redirect:/customers";
    }

    @PostMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return "redirect:/customers";
    }
}
