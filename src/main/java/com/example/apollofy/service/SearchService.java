package com.example.apollofy.service;

import com.example.apollofy.domain.Search;

import com.example.apollofy.repository.PlaylistRepository;
import com.example.apollofy.repository.SearchRepository;
import com.example.apollofy.repository.TrackRepository;
import com.example.apollofy.repository.UserRepository;
import com.example.apollofy.service.dto.PlaylistDTO;
import com.example.apollofy.service.dto.SearchDTO;
import com.example.apollofy.service.dto.TrackDTO;
import com.example.apollofy.service.dto.UserDTO;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Transactional
public class SearchService {
    private final TrackRepository trackRepository;
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    private final SearchRepository searchRepository;

    public SearchService(TrackRepository trackRepository,
                         PlaylistRepository playlistRepository,
                         UserRepository userRepository, SearchRepository searchRepository) {
        this.trackRepository = trackRepository;
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.searchRepository = searchRepository;
    }

    public SearchDTO findByKeyword(String q, Pageable pageable) {
        //guardamos las búsquedas en bbdd
        searchRepository.save(new Search(q));

        SearchDTO dto = new SearchDTO();
        for (String word: q.split("\\s+")) {
            searchWord(word,dto,pageable);
        }

        return dto;
    }

    private void searchWord(String q, SearchDTO searchDTO, Pageable pageable) {
        List<PlaylistDTO> playlistListSearched =  playlistRepository.searchPlaylists(q, pageable);
        List<TrackDTO> trackListSearched = trackRepository.searchTracks(q, pageable);
        List<UserDTO> userListSearched = userRepository.searchUsers(q, pageable);

        searchDTO.addTracks(trackListSearched);
        searchDTO.addPlaylist(playlistListSearched);
        searchDTO.addUsers(userListSearched);
    }
}
