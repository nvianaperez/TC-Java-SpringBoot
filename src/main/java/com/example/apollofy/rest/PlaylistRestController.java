package com.example.apollofy.rest;

import com.example.apollofy.domain.Playlist;
import com.example.apollofy.repository.PlaylistRepository;
import com.example.apollofy.service.PlaylistService;
import com.example.apollofy.service.dto.AddTracksToPlaylistDTO;
import com.example.apollofy.service.dto.PlaylistDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistRestController {
    private final PlaylistRepository playlistRepository;
    private PlaylistService playlistService;

    public PlaylistRestController(PlaylistRepository playlistRepository, PlaylistService playlistService) {
        this.playlistRepository = playlistRepository;
        this.playlistService = playlistService;
    }


    //GET api/playlists
    @GetMapping()
    public List<Playlist> getAllPlaylists () {
        return playlistRepository.findAll();
    }

    //POST api/playlists
    @PostMapping()
    //en el caso de POST ha de devolver 201
    @ResponseStatus(HttpStatus.CREATED)
    public Playlist createOnePlaylist (@RequestBody PlaylistDTO playlistDTO) {
        return playlistService.createOnePlaylist(playlistDTO);
    }

    //PUT api/playlist
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Playlist updateOnePlaylist (@RequestBody PlaylistDTO playlistDTO,
                                       @PathVariable(required = true) Long id) {
        if (playlistDTO.getId() == null) throw new RuntimeException("Invalid id for playlist: id null");
        //if (!Objects.equals(id, genreDto.getId())) throw new RuntimeException("The gender's id searched is not found in data base: id not found");
        if (!playlistDTO.getId().equals(id)) throw new RuntimeException("The playlist's id searched is not found in data base: id not found");
        return playlistService.updateOnePlaylist(playlistDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Playlist patchOnePlaylist (@RequestBody PlaylistDTO playlistDTO,
                                      @PathVariable(required = true) Long id) {
        if (playlistDTO.getId() == null) throw new RuntimeException("Invalid id for playlist: id null");
        //if (!Objects.equals(id, genreDto.getId())) throw new RuntimeException("The gender's id searched is not found in data base: id not found");
        if (!playlistDTO.getId().equals(id)) throw new RuntimeException("The playlist's id searched is not found in data base: id not found");
        return playlistService.patchPlaylist(playlistDTO);
    }

    @PostMapping("/{id}/tracks")
    public Playlist addTracksToPlaylist (@PathVariable Long id
            , @RequestBody AddTracksToPlaylistDTO addTracksToPlaylistDTO) {
        return playlistService.addTracks(id, addTracksToPlaylistDTO);
    }

    @GetMapping("/{id}")
    public Playlist getPlaylist (@PathVariable Long id) {
        return playlistService.getPlaylist(id);
    }
}

//ToDo: utilizar dto's
//TODO: personalizar NotFoundException
//TODO: empezar Playlist
