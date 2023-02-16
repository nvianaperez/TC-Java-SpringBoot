package com.example.apollofy.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

// primero el administrador de la aplicación crea los géneros, y luego los artistas crean sus tracks y le da una lista de géneros --> en track @manytomany a géneros
@Entity
@Data
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Genre(String name) {
        setName(name);
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }
}
