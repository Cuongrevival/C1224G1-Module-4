package org.example.controller;

import org.example.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {
    @GetMapping
    public ModelAndView showLoginForm(){
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
    @PostMapping("/validate_user")
    public ModelAndView validateUser(@Validated @ModelAttribute("user") User user,
                               BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            return new ModelAndView("login");
        }
        return new ModelAndView("result");
    }
}
