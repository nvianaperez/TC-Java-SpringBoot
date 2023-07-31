package com.example.apollofy.service.dto;


import com.example.apollofy.domain.Track;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
public class PlaylistDTO {
    private Long id;
    @NotNull private String name;
    private String description;
    //boolean (tipo primitivo) --> valor por defecto es false. Entonces cuando creas el objeto el valor por defecto será false.
    //por esto es importante poner Boolean clase

    //librería Jackson convierte de Objeto Java a JSON --> se llama proceso de serialización, ya que todos los JSON están serializados (se sabae cuándo epiezan y cuándo acaban con los {}), mientras que un objeto Java son diferentes direcciones de memoria relacionadas con punteros
    //@JsonIgnore
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Boolean isPublic;

    public PlaylistDTO(Long id, String name, String description, Boolean isPublic) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
    }

    public PlaylistDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}

