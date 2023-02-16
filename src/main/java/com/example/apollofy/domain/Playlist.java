package com.example.apollofy.domain;

import com.example.apollofy.service.dto.PlaylistDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean isPublic;

    @ManyToMany
    //Playlist tiene Tracks, por esto Playlist es la entidad fuerte o dominante, y la relación está en Playlist
    @OrderColumn //porque es importante mantener el orden de las tracks en la playlist
    private List<Track> tracks;

    @ManyToMany
    private List<User> collaborators;

    //sobrecarga de constructor
    public Playlist(String name, String description, Boolean isPublic) {
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
        this.tracks = new ArrayList<Track>();
        this.collaborators = new ArrayList<User>();
    }

    public Playlist(PlaylistDTO playlistDTO) {
        this.name = playlistDTO.getName();
        this.description = playlistDTO.getDescription();
        this.isPublic = playlistDTO.getIsPublic();
    }

    public void addTrack(Track track) {tracks.add(track);}
    public void addCollaborator(User collaborator) {collaborators.add(collaborator);}
}
