package com.codegym.controller;

import com.codegym.model.Song;
import com.codegym.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class SongController {

    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(".mp3", ".wav", ".ogg", ".m4p");
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("")
    public String uploadForm(Model model) {
        return "upload";
    }

    @PostMapping("upload")
    public String uploadSong(@RequestParam String name,
                             @RequestParam String artist,
                             @RequestParam String genre,
                             @RequestParam("file") MultipartFile file,
                             Model model) throws IOException {

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || ALLOWED_EXTENSIONS.stream().noneMatch(originalFilename.toLowerCase()::endsWith)) {
            model.addAttribute("error", "Invalid file type. Only MP3, WAV, OGG, M4P allowed.");
            return "upload";
        }

        String uploadPath = "/uploads/";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        String filePath = uploadPath + file.getOriginalFilename();
        file.transferTo(new File(filePath));

        Song song = new Song(name, artist, genre, "/uploads/" + file.getOriginalFilename());
        songService.addSong(song);

        return "redirect:/songs";
    }

    @GetMapping("/songs")
    public String listSongs(Model model) {
        model.addAttribute("songs", songService.getAllSongs());
        return "list";
    }
}
