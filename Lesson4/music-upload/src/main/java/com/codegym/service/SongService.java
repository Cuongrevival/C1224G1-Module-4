package com.codegym.service;

import com.codegym.model.Song;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {
    private List<Song> songList = new ArrayList<>();

    public void addSong(Song song) {
        songList.add(song);
    }

    public List<Song> getAllSongs() {
        return songList;
    }
}

