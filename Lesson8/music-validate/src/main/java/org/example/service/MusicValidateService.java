package org.example.service;

import org.example.model.MusicValidate;
import org.example.repository.IMusicValidateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MusicValidateService {
    @Autowired
    private IMusicValidateRepo musicValidateRepo;
    public void save(String name, String artist, String genre) {
        musicValidateRepo.save(new MusicValidate(0, name, artist, genre));
    }
    public void update(int id, String name, String artist, String genre) {
        Optional<MusicValidate> musicValidate = musicValidateRepo.findById(id);
        if (musicValidate.isPresent()) {
            MusicValidate m = musicValidate.get();
            m.setName(name);
            m.setArtist(artist);
            m.setGenre(genre);
            musicValidateRepo.save(m);
        } else {
            throw new RuntimeException("Không tìm thấy bài nhạc");
        }
    }
    public Iterable<MusicValidate> findAll() {
        return musicValidateRepo.findAll();
    }
    public MusicValidate findById(int id) {
        return musicValidateRepo.findById(id).orElse(null);
    }
}
