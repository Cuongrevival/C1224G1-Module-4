package org.example.model;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "music_validate")
public class MusicValidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    @Size(max = 800)
    @Pattern(regexp = "^[0-9a-zA-Z\\s]+$", message = "Name can only contain letters and spaces")
    private String name;
    @NotEmpty
    @Size(max = 300)
    @Pattern(regexp = "^[0-9a-zA-Z\\s]+$", message = "Artist can only contain letters and spaces")
    private String artist;
    @NotEmpty
    @Size(max = 1000)
    @Pattern(regexp = "^[0-9a-zA-Z\\s,]+$", message = "Genre can only contain letters and spaces")
    private String genre;

    public MusicValidate(int id, String name, String artist, String genre) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.genre = genre;
    }

    public MusicValidate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
