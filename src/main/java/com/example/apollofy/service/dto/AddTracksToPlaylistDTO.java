package com.example.apollofy.service.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AddTracksToPlaylistDTO(
        @NotNull List<Long> idTracks,
        Integer position) {
}
