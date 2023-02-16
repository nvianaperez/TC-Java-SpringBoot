package com.example.apollofy.service;

import com.example.apollofy.domain.Genre;
import com.example.apollofy.domain.Track;
import com.example.apollofy.repository.GenreRepository;
import com.example.apollofy.repository.TrackRepository;
import com.example.apollofy.service.dto.GenreDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GenreService {
    private final GenreRepository genreRepository;
    private final TrackRepository trackRepository;

    public GenreService(GenreRepository genreRepository, TrackRepository trackRepository) {
        this.genreRepository = genreRepository;
        this.trackRepository = trackRepository;
    }

    public Genre createOneGenre(GenreDTO genreDTO) {
        Optional<Genre> genreOptional = genreRepository.findGenreByNameIgnoreCase(genreDTO.name());
        if(genreOptional.isPresent()) throw new RuntimeException("Genre named "+genreDTO.name()+" already exists");
        Genre genre = new Genre(genreDTO.name());
        return genreRepository.save(genre);
    }


    public Genre updateOneGenre(GenreDTO genreDTO) {
        Optional<Genre> genreOptional = genreRepository.findById(genreDTO.id());
        if(genreOptional.isEmpty()) throw new RuntimeException("Genre id "+genreDTO.id()+" doesn't exist");
        Genre genre = genreOptional.get();
        genre.setName(genreDTO.name());
        return genreRepository.save(genre);
    }

    public void deleteGenreById(Long id) {
        //programación funcional --> no necesita stream, por que el forEach ya tiene la lambda (callback)
        //trackRepository.findByGenre(getGenre(id)).forEach(track -> track.removeGenre(getGenre(id)));

        Genre genre = getGenre(id);

        List<Track> tracks = trackRepository.findByGenre(genre);

        for (Track track: tracks) {
            track.removeGenre(genre);
            //cuando estás en un contexto transaccional, si el flujo es correcto y all va bien, entonces se produce el cambio en la bbdd
            //pero si algo va mal, el @Transactional hace un roll back y no se produce ningún cambio en la bbdd, la bbdd queda intacta
            //es por esto que LOS SERVICIOS SUELEN TENER TRANSACTIONAL
            //porque suelen hacer cambios en los objetos de la bbdd
            //sino tendríamos que hacer persistente el cambio en bbdd con la linea 56
            //trackRepository.save(track);
        }
        genreRepository.delete(genre);

    }

    public Genre getGenre(Long id) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        if (genreOptional.isEmpty()) throw new RuntimeException("Genre id "+id+" doesn't exist");
        Genre genre = genreOptional.get();
        return genre;
    }

}
