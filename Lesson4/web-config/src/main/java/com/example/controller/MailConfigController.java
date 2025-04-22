package com.example.controller;

import com.example.model.MailConfig;
import com.example.service.MailConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/config")
public class MailConfigController {

    private final MailConfigService service;
    @Autowired
    public MailConfigController(MailConfigService service) {
        this.service = service;
    }

    @GetMapping
    public String viewConfig(Model model) {
        model.addAttribute("config", service.getConfig());
        return "view";
    }

    @GetMapping("/edit")
    public String editForm(Model model) {
        model.addAttribute("config", service.getConfig());
        model.addAttribute("languages", new String[]{"English", "Vietnamese", "Japanese", "Chinese"});
        model.addAttribute("pageSizes", new int[]{5, 10, 15, 25, 50, 100});
        return "edit";
    }

    @PostMapping("/update")
    public String updateConfig(@ModelAttribute MailConfig config) {
        service.updateConfig(config);
        return "redirect:/config";
    }
}
