package com.codegym.controller;

import com.codegym.model.Declaration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DeclarationController {
    @GetMapping("form")
    public String showForm(Model model) {
        model.addAttribute("declaration", new Declaration());
        return "form";
    }

    @PostMapping("/control")
    public String processForm(@ModelAttribute("declaration") Declaration declaration, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("declaration", declaration);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String showList(Model model) {
        return "list";
    }
}
