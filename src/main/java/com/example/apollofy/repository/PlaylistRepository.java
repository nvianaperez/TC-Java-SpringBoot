package com.example.apollofy.repository;

import com.example.apollofy.domain.Playlist;
import com.example.apollofy.service.dto.PlaylistDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Optional<Playlist> findPlaylistByNameIgnoreCase(String name);

    @Query("""
            SELECT new com.example.apollofy.service.dto.PlaylistDTO(p.name,p.description) FROM Playlist p
            WHERE UPPER(p.name) LIKE UPPER(CONCAT('%', :q, '%')) OR 
            UPPER(p.description) LIKE UPPER(CONCAT('%', :q, '%')) AND 
            p.isPublic=true
    """)
    List<PlaylistDTO> searchPlaylists (@Param("q") String q, Pageable pageable);
}
/*
    @Query("SELECT new com.thorben.janssen.spring.jpa.projections.dto.AuthorSummaryDTO(a.firstName, a.lastName) FROM Author a WHERE a.firstName = :firstName")
    List<AuthorSummaryDTO> findByFirstName(String firstName);

 */

