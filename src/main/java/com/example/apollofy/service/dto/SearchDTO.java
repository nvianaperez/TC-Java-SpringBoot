package com.example.apollofy.service.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class SearchDTO {
    private List<TrackDTO> trackList;
    private List<PlaylistDTO> playlistList;
    private List<UserDTO> userList;

    public SearchDTO() {
        trackList = new ArrayList<>();
        playlistList = new ArrayList<>();
        userList = new ArrayList<>();
    }

    public void addTracks(List<TrackDTO> trackListSearched) {
        trackList.addAll(trackListSearched);
    }

    public void addPlaylist(List<PlaylistDTO> playlistListSearched) {
        playlistList.addAll(playlistListSearched);
    }

    public void addUsers(List<UserDTO> userListSearched) {
        userList.addAll(userListSearched);
    }
}
