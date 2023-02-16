package com.example.apollofy.utilites;

//Esta es la excepción de la capa de negocio, no tiene que ver con la API
public class PlaylistAlreadyExistsException extends RuntimeException {

    public PlaylistAlreadyExistsException(String playlistName) {
        super("The Playlist named " + playlistName + " already exists.");
    }

}
