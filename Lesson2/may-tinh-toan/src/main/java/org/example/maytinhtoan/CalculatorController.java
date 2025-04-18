package org.example.maytinhtoan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class CalculatorController {

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping(value = "/calculate")
    public String calculate(@RequestParam("a") double a,
                            @RequestParam("b") double b,
                            @RequestParam("operator") String operator,
                            Model model) {
        double result = 0;
        String message = "";

        switch (operator) {
            case "+":
                result = a + b;
                message = "Cộng";
                break;
            case "-":
                result = a - b;
                message = "Trừ";
                break;
            case "*":
                result = a * b;
                message = "Nhân";
                break;
            case "/":
                if (b == 0) {
                    message = "Không thể chia cho 0";
                    model.addAttribute("error", message);
                    return "index";
                } else {
                    result = a / b;
                    message = "Chia";
                }
                break;
        }

        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("operator", operator);
        model.addAttribute("message", message);
        model.addAttribute("result", result);
        return "index";
    }
}
