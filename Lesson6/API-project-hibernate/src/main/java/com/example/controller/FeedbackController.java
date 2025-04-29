package com.example.controller;

import com.example.model.Feedback;
import com.example.service.FeedbackDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
public class FeedbackController {
    private final FeedbackDAO feedbackDAO;

    @Autowired
    public FeedbackController(FeedbackDAO feedbackDAO) {
        this.feedbackDAO = feedbackDAO;
    }

    @GetMapping("/list")
    public String listFeedback(Model model) {
        model.addAttribute("feedbackList", feedbackDAO.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        Feedback feedback = new Feedback();
        model.addAttribute("feedback", feedback);
        List<Integer> grades = Arrays.asList(1, 2, 3, 4, 5);
        model.addAttribute("grades", grades);

        return "home";
    }

    @PostMapping("/save")
    public String saveFeedback(@ModelAttribute("feedback") Feedback feedback) {
        if (feedback.getDate() == null) {
            feedback.setDate(LocalDate.now());
        }
        feedbackDAO.save(feedback);
        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Feedback fb = feedbackDAO.findById(id);
        List<Integer> grades = Arrays.asList(1, 2, 3, 4, 5);
        model.addAttribute("grades", grades);
        model.addAttribute("feedback", fb);
        return "home";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        feedbackDAO.delete(id);
        return "redirect:/list";
    }

    @GetMapping("/like/{id}")
    public String like(@PathVariable int id) {
        Feedback fb = feedbackDAO.findById(id);
        fb.setLikes(fb.getLikes() + 1);
        feedbackDAO.save(fb);
        return "redirect:/list";
    }

}