package com.codegym.service;

import com.codegym.dao.MusicDAO;
import com.codegym.model.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HibernateMusicService{
    @Autowired
    private MusicDAO musicDAO;

    public void save(Music music) {
        musicDAO.save(music);
    }

    public List<Music> findAll() {
        return musicDAO.findAll();
    }

    public Music findById(int id) {
        return musicDAO.findById(id);
    }

    public void update(Music music) {
        musicDAO.update(music);
    }

    public void delete(int id) {
        musicDAO.delete(id);
    }
}
