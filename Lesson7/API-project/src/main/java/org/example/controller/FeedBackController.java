package org.example.controller;

import org.example.exception.BadWordException;
import org.example.model.Feedback;
import org.example.service.BadWordService;
import org.example.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@Controller
public class FeedBackController {

    private final FeedBackService feedBackService;
    private final BadWordService badWordService;

    @Autowired
    public FeedBackController(FeedBackService feedBackService, BadWordService badWordService) {
        this.feedBackService = feedBackService;
        this.badWordService = badWordService;
    }

    @GetMapping("/")
    public String listFeedback(@PageableDefault(size = 5) Pageable pageable, Model model) {
        Page<Feedback> feedbackPage = feedBackService.findAll(pageable);
        model.addAttribute("feedbackList", feedbackPage.getContent());
        model.addAttribute("page", feedbackPage);
        return "list";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        Feedback feedback = new Feedback();
        model.addAttribute("feedback", feedback);

        model.addAttribute("grades", Arrays.asList(1, 2, 3, 4, 5));

        return "home";
    }

    @PostMapping("/save")
    public String saveFeedback(@ModelAttribute("feedback") Feedback feedback) {
        if (feedback.getDate() == null) {
            feedback.setDate(LocalDate.now());
        } else if (badWordService.containsBadWords(feedback.getComment())) {
            return "bad-word";
        }
        feedBackService.save(feedback);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Feedback fb = feedBackService.findById(id);
        List<Integer> grades = Arrays.asList(1, 2, 3, 4, 5);
        model.addAttribute("grades", grades);
        model.addAttribute("feedback", fb);
        return "home";
    }

    @GetMapping("/delete/{id}")
    public String deleteFeedback(@PathVariable Long id) {
        feedBackService.delete(feedBackService.findById(id));
        return "redirect:/";
    }

    @GetMapping("/like/{id}")
    public String like(@PathVariable Long id) {
        Feedback fb = feedBackService.findById(id);
        fb.setLikes(fb.getLikes() + 1);
        feedBackService.save(fb);
        return "redirect:/";
    }

    @ExceptionHandler(BadWordException.class)
    public ModelAndView handleBadWordException() {
        ModelAndView mav = new ModelAndView("bad-word");
        mav.addObject("message", "Feedback của bạn chứa từ ngữ không phù hợp.");
        return mav;
    }

}
