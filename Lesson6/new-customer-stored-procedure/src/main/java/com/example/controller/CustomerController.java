package com.example.controller;

import com.example.model.Customer;
import com.example.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final ICustomerService customerService;
    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "list";
    }


    @GetMapping("/create")
    public String showAddForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "create";
    }
    @GetMapping("/{id}/update")
    public String showUpdateForm(Model model,@PathVariable long id) {
        model.addAttribute("customer", customerService.findById(id));
        return "update";
    }
    @PostMapping("/update")
    public String update(Customer customer) {
        customerService.update(customer);
        return "redirect:/customers";
    }
    @PostMapping("/save")
    public String save(Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(Customer customer, RedirectAttributes redirect) {
        customerService.delete(customer);
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/customers";
    }
    @GetMapping("/{id}/view")
    public String view(@PathVariable("id") long id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "view";
    }
    @PostMapping("/save-proc")
    public String saveUsingProcedure(Customer customer, RedirectAttributes redirect) {
        customerService.insertCustomerByProcedure(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhone(),
                customer.getAddress()
        );
        redirect.addFlashAttribute("success", "Inserted customer via stored procedure successfully!");
        return "redirect:/customers";
    }

}
