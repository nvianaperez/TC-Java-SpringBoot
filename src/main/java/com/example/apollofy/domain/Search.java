package com.example.apollofy.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor

//una entity no es obligatoria, es solo cuando quieres hacer persistente en una tabla de bbdd
//con esto recordaremos las búsquedas
public class Search {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String q;

    public Search(String keyword) {
        this.q = keyword;
    }
}
