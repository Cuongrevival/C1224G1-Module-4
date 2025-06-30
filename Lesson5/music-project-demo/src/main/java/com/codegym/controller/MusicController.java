package com.codegym.controller;

import com.codegym.model.Music;
import com.codegym.service.HibernateMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class MusicController {

    private HibernateMusicService musicService;
    @Autowired
    public void setMusicService(HibernateMusicService musicService) {
        this.musicService = musicService;
    }
    @Value("${music_file_path}")
    public String filePath;
    @GetMapping("/music")
    public String list(Model model) {
        List<Music> musics = musicService.findAll();
        model.addAttribute("musics", musics);
        return "list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("music", new Music());
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Music music,
                         @RequestParam("file") MultipartFile file,
                         HttpServletRequest request) throws IOException {
        File uploadDir = new File(filePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String fileName = file.getOriginalFilename();
        file.transferTo(new File(filePath + fileName));

        music.setFilePath("/" + fileName);
        musicService.save(music);
        return "redirect:/music";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Music music = musicService.findById(id);
        model.addAttribute("music", music);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Music music) {
        musicService.update(music);
        return "redirect:/music";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        musicService.delete(id);
        return "redirect:/music";
    }

    @GetMapping("/listen/{id}")
    public String listen(@PathVariable int id, Model model) {
        Music music = musicService.findById(id);
        model.addAttribute("music", music);
        return "listen";
    }
}
