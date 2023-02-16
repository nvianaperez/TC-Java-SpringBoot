package com.example.apollofy.repository;

import com.example.apollofy.domain.Genre;
import com.example.apollofy.domain.Track;
import com.example.apollofy.service.dto.TrackDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface TrackRepository extends JpaRepository<Track,Long> {
    Optional<Track> findTracksByNameIgnoreCase(String name);

    @Query("select track from Track track where ?1 member of track.genres")
    List<Track> findByGenre (Genre genre);
/*
    @Query("""
        SELECT new com.example.apollofy.service.dto.TrackDTO(t.name,t.description)  FROM Track t 
        WHERE UPPER(t.name) LIKE UPPER(CONCAT('%', :q, '%')) OR 
        UPPER(t.description) LIKE UPPER(CONCAT('%', :q, '%'))         
    """)
*/
    @Query("""
        SELECT new com.example.apollofy.service.dto.TrackDTO(t.id, t.name,t.description)  FROM Track t 
        WHERE UPPER(t.name) LIKE UPPER(CONCAT('%', :q, '%')) OR 
        UPPER(t.description) LIKE UPPER(CONCAT('%', :q, '%'))         
    """)
    List<TrackDTO> searchTracks (@Param("q") String q, Pageable pageable);
}
