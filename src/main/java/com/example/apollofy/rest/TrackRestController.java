package com.example.apollofy.rest;

import com.example.apollofy.domain.Track;
import com.example.apollofy.repository.TrackRepository;
import com.example.apollofy.service.TrackService;
import com.example.apollofy.service.UserService;
import com.example.apollofy.service.dto.TrackDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tracks")
public class TrackRestController {
    private final TrackRepository trackRepository;
    private final TrackService trackService;
    private final UserService userService;

    public TrackRestController(TrackRepository trackRepository, TrackService trackService, UserService userService) {
        this.trackRepository = trackRepository;
        this.trackService = trackService;
        this.userService = userService;
    }

    //GET /api/tracks
    @GetMapping()
    public List<Track> getAllTracks () {
        return trackRepository.findAll();
    }


    //POST /api/tracks
    @PostMapping("/users/{userId}/tracks")
    public Track createTrack (@PathVariable Long userId, @RequestBody TrackDTO trackDto) {
        return trackService.createOneTrack(userId, trackDto);
    }

    //PUT /api/tracks
    @PatchMapping("/{id}")
    public Track partialUpdateOneTrack(@PathVariable Long id, @RequestBody TrackDTO trackDto) {
        if(id == null) throw new RuntimeException("Invalid id for track: id " +id+ " null");
        if(!trackRepository.existsById(id)) throw new RuntimeException("Entity Track not found in data base: entity not found");
        if(trackDto == null) throw new RuntimeException("The track.dto id is not valid");
        //if (!Objects.equals(id, trackDto.getId())) throw new RuntimeException("The track's id searched is not found in data base: id not found");
        if (!trackDto.equals(id)) throw new RuntimeException("The track's id searched does not match track.dto id");
        return trackService.partialUpdateOneTrack(trackDto);


    }

    //GET /api/tracks/{id}
    @GetMapping("/{id}")
    public Track getTrackById(@PathVariable Long id) {
        Optional<Track> trackOptional = trackRepository.findById(id);
        if (trackOptional.isEmpty()) {
            throw new RuntimeException("The track id " + id + " is invalid");
        } else {
            return trackOptional.get();
        }
    }


    //DELETE /api/tracks/{id}
    @DeleteMapping("/{id}")
    public void deleteOneTrack (@PathVariable Long id) {
        //Next message doesn't have sense because in the path we always have an id
        //if(id == null) throw new RuntimeException("Invalid id for searching the track: id null");
        trackService.deleteTrackById(id);
    }
}

