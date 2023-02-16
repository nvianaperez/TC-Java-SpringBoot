package com.example.apollofy.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

//A DTO for the {@link com.example.apollofy.domain.Track} entity


@Data
@NoArgsConstructor
public class TrackDTO implements Serializable {
    //@JsonIgnore
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private String name;
    private String description;
    //@JsonIgnore
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Long duration;
    //@JsonIgnore
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private LocalDate releasedDate;


    public TrackDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

/*
public record TrackDTO (
        Long id,
        @NotNull String name,
        @NotNull String description,
        Long duration,
        LocalDate releaseDate
    ) {
}
*/


