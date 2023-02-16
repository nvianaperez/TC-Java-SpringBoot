package com.example.apollofy.rest;

import com.example.apollofy.domain.Genre;
import com.example.apollofy.domain.Track;
import com.example.apollofy.repository.GenreRepository;
import com.example.apollofy.repository.TrackRepository;
import com.example.apollofy.service.GenreService;
import com.example.apollofy.service.dto.GenreDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreRestController {
    private final GenreRepository genreRepository;
    private final GenreService genreService;
    private final TrackRepository trackRepository;

    public GenreRestController(GenreRepository genreRepository, GenreService genreService, TrackRepository trackRepository) {
        this.genreRepository = genreRepository;
        this.genreService = genreService;
        this.trackRepository = trackRepository;
    }

    //GET /api/genres
    @GetMapping()
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        //if (genres == null) throw new RuntimeException("There isn't any genre to return");
        if (genres == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(genres);
    }

    //ToDo: revisar que un findall nunca debe devolver un NOT FOUND

    //POST /api/genres
    @PostMapping
    public Genre createOneGenre(@RequestBody GenreDTO genreDto) {
        return genreService.createOneGenre(genreDto);
    }

    //PUT /api/genres
    @PatchMapping("/{id}")
    public Genre updateOneGenre(@PathVariable Long id, @RequestBody GenreDTO genreDto) {
        if (genreDto == null) throw new RuntimeException("Invalid id for genre: id null");
        if (id == null) throw new RuntimeException("Invalid id for searching the genre: id null");
        if (!genreRepository.existsById(id)) throw new RuntimeException("Entity Genre not found in data base: entity not found");
        //if (!Objects.equals(id, genreDto.getId())) throw new RuntimeException("The gender's id searched is not found in data base: id not found");
        if (!genreDto.equals(id)) throw new RuntimeException("The gender's id searched is not found in data base: id not found");
        return genreService.updateOneGenre(genreDto);
    }

    //GET /api/genres/{id}
    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable Long id) {
        //ToDo: duda: ¿de quién es responsabilidad revisar el Optional, del controller o del service?
        return genreService.getGenre(id);
        //puede encontrarlo o no, por esto un Optional
        //Optional<Genre> genreOptional = genreRepository.findById(id);
        //if(genreOptional.isEmpty()) throw new RuntimeException("The genre id " + id + " is invalid");
        //return genreOptional.get();
    }

    //DELETE /api/genres/{id}
    @DeleteMapping("/{id}")
    public void deleteOneGenre (@PathVariable Long id) {
        if (!genreRepository.existsById(id)) throw new RuntimeException("Entity Genre not found in data base: entity not found");
        genreService.deleteGenreById(id);
    }

    //GET /api/genres/{id}/tracks
    @GetMapping("/{id}/tracks")
    public List<Track> getTracksByGenreId (@PathVariable Long id) {
        return trackRepository.findByGenre(genreService.getGenre(id));
    }
    //ToDo: pasar del genreService al trackService y al trackRepository



}
