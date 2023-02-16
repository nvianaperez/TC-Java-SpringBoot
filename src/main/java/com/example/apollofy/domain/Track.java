package com.example.apollofy.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long duration;
    private LocalDate releasedDate;

    @ManyToOne(optional = false) //mandatory para crear una track, porque optional está a false
    //mejor así que @OneToMany desde User a Tracks. Porque es mejor desde la foreign key hacia la primary key
    private User artist;

    @ManyToMany
    private List<Genre> genres;

    public Track(String name, String description, Long duration, LocalDate releasedDate, User artist) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.releasedDate = releasedDate;
        this.artist = artist;
        //constructor que permite pasar géneros con valor null
        this.genres = new ArrayList<Genre>();

    }

    public Track(String name, String description, Long duration, LocalDate releasedDate, User artist, List<Genre> genres) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.releasedDate = releasedDate;
        this.artist = artist;
        this.genres = genres;
    }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }
}
