package org.example.controller;

import org.example.model.Phone;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class PhoneController {
    @GetMapping
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("input");
        modelAndView.addObject("phone", new Phone());
        return modelAndView;
    }
    @PostMapping("/validate_phone")
    public String checkPhone(@Valid @ModelAttribute("phone") Phone phone,
                                   BindingResult bindingResult, Model model) {
        new Phone().validate(phone, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "input";
        } else {
            model.addAttribute("phone", phone);
            return "result";
        }
    }
}
