package com.example.apollofy.service.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.example.apollofy.domain.Genre} entity
 */
/*
//Serializable --> en serie 0 y 1 para transmitir a través de la red, entre dos objetos Java. Así nos servirá para múltiples escenarios, para enviar a través de HTTP y a través de internet
@Data
public class GenreDTO implements Serializable {
    private final Long id;
    private final String name;
}
*/

public record GenreDTO (
        @NotNull List<Long> idTracks,
        @Id Long id,
        String name
    ) {
}