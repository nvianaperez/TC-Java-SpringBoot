package com.example.apollofy.repository;

import com.example.apollofy.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre,Long> {


    Optional<Genre> findGenreByNameIgnoreCase(String name);
}
