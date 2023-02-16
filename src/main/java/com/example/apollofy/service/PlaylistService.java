package com.example.apollofy.service;

import com.example.apollofy.domain.Playlist;
import com.example.apollofy.domain.Track;
import com.example.apollofy.repository.PlaylistRepository;
import com.example.apollofy.service.dto.AddTracksToPlaylistDTO;
import com.example.apollofy.service.dto.PlaylistDTO;
import com.example.apollofy.utilites.ElementNotFoundException;
import com.example.apollofy.utilites.PlaylistAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private TrackService trackService;

    public PlaylistService(PlaylistRepository playlistRepository, TrackService trackService) {
        this.playlistRepository = playlistRepository;
        this.trackService = trackService;
    }

    public Playlist createOnePlaylist(PlaylistDTO playlistDTO) {
        Optional<Playlist> playlistOptional = playlistRepository.findPlaylistByNameIgnoreCase(playlistDTO.getName());
        if(playlistOptional.isPresent()) throw new PlaylistAlreadyExistsException(playlistDTO.getName());
        Playlist playlist = new Playlist(playlistDTO);
        return playlistRepository.save(playlist);
    }

    public Playlist updateOnePlaylist(PlaylistDTO playlistDTO) {
        Playlist playlist = getPlaylist(playlistDTO.getId());
        playlist.setName(playlistDTO.getName());
        playlist.setDescription(playlistDTO.getDescription());
        playlist.setIsPublic(playlistDTO.getIsPublic());
        return playlistRepository.save(playlist);
    }

    public Playlist patchPlaylist(PlaylistDTO playlistDTO) {
        Optional<Playlist> playlistOptional = playlistRepository.findById(playlistDTO.getId());
        if(playlistOptional.isEmpty()) throw new RuntimeException("Playlist id "+playlistDTO.getId()+" doesn't exist");
        Playlist playlist = playlistOptional.get();
        if(playlistDTO.getName() != null ) playlist.setName(playlistDTO.getName());
        if(playlistDTO.getDescription() != null ) playlist.setDescription(playlistDTO.getDescription());
        if(playlistDTO.getIsPublic() != null) playlist.setIsPublic(playlistDTO.getIsPublic());
        return playlistRepository.save(playlist);
    }

    public Playlist addTracks(Long id, AddTracksToPlaylistDTO addTracksToPlaylistDTO) {
        Playlist playlist = playlistRepository.findById(id).get();
        var currentTracks = playlist.getTracks();

        List<Track> tracksToAdd = new ArrayList<>();

        for (Long idTrack : addTracksToPlaylistDTO.idTracks()) {
            Track trackToAdd = trackService.findById(idTrack);
            tracksToAdd.add(trackToAdd);
        }

        if(addTracksToPlaylistDTO.position() != null) {
            currentTracks.addAll(addTracksToPlaylistDTO.position(),tracksToAdd);
        } else currentTracks.addAll(tracksToAdd);
        return playlist;
    }

    public Playlist getPlaylist (Long id) {
        Optional<Playlist> playlistOptional = playlistRepository.findById(id);
        if(playlistOptional.isEmpty()) throw new ElementNotFoundException(Playlist.class,id);
        Playlist playlist = playlistOptional.get();
        return playlist;
    }
}
