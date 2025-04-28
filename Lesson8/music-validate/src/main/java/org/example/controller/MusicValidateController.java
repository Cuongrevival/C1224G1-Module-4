package org.example.controller;

import org.example.model.MusicValidate;
import org.example.service.MusicValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/") // Thêm vào đây để không cần lặp lại "/create", "/update" nhiều lần
public class MusicValidateController {

    @Autowired
    private MusicValidateService musicValidateService;

    @GetMapping
    public ModelAndView listMusic() {
        Iterable<MusicValidate> music = musicValidateService.findAll();
        ModelAndView mv = new ModelAndView("music");
        mv.addObject("music", music);
        return mv;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        return new ModelAndView("create", "music", new MusicValidate());
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("music") MusicValidate musicValidate,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create";
        }
        musicValidateService.save(musicValidate.getName(), musicValidate.getArtist(), musicValidate.getGenre());
        return "redirect:/";
    }

    @GetMapping("/{id}/update")
    public ModelAndView showUpdateForm(@PathVariable int id) {
        MusicValidate music = musicValidateService.findById(id);
        if (music == null) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("update", "music", music);
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("music") MusicValidate musicValidate,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update";
        }
        musicValidateService.update(musicValidate.getId(), musicValidate.getName(), musicValidate.getArtist(), musicValidate.getGenre());
        return "redirect:/";
    }
}
