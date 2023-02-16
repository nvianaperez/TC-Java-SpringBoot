package com.example.apollofy;

import com.example.apollofy.domain.Genre;
import com.example.apollofy.domain.Playlist;
import com.example.apollofy.domain.Track;
import com.example.apollofy.domain.User;
import com.example.apollofy.repository.GenreRepository;
import com.example.apollofy.repository.PlaylistRepository;
import com.example.apollofy.repository.TrackRepository;
import com.example.apollofy.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class ApollofyApplication implements CommandLineRunner {

	private final GenreRepository genreRepository;
	private final UserRepository userRepository;
	private final TrackRepository trackRepository;
	private final PlaylistRepository playlistRepository;

	public ApollofyApplication(GenreRepository genreRepository, UserRepository userRepository, TrackRepository trackRepository, PlaylistRepository playlistRepository) {
		this.genreRepository = genreRepository;
		this.userRepository = userRepository;
		this.trackRepository = trackRepository;
		this.playlistRepository = playlistRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApollofyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Genre rock = new Genre("Rock");
		Genre pop = new Genre("Pop");
		Genre heavy = new Genre("Heavy");
		genreRepository.save(rock);
		genreRepository.save(pop);
		genreRepository.save(heavy);

		User elena = new User ("Elena", "Ocaña", "elena@gmail.com");
		User nuria = new User ("Nuria", "Viana", "nvianaperez@gmail.com");
		User alfons = new User ("Alfons", "Rueda", "arueda@gmail.com");
		User gerard = new User ("Gerard", "Pou", "gerardpou@gmail.com");
		User susana = new User ("Susana", "Portobello", "sportobello@gmail.com");
		userRepository.save(elena);
		userRepository.save(nuria);
		userRepository.save(alfons);
		userRepository.save(gerard);
		userRepository.save(susana);

		Track track1 = new Track("Angel","Third single of Permanent Vacation", 350L, LocalDate.of(1987,01,04), elena);
		Track track2 = new Track("Beautiful Day","About a beautiful day in the beach",400L, LocalDate.of(1996,12,15), nuria);
		track2.addGenre(rock);
		track2.addGenre(heavy);
		Track track3 = new Track("My brown eye girl", "The song of my life",350L, LocalDate.of(1967,02,15), alfons);
		track3.addGenre(rock);
		track3.addGenre(pop);
		Track track4 = new Track("Somni", "Kid's song",350L, LocalDate.of(2022,02,15), gerard,
				Arrays.asList(new Genre[]{rock, pop, heavy}));
		Track track5 = new Track("Fear of the dark", "best Iron Maiden heavy song", 450L, LocalDate.of(1992,05,11), nuria,
				Arrays.asList(new Genre[]{heavy, rock}));
		Track track6 = new Track("Break my soul", "best Beyoncé pop song", 300L, LocalDate.of(2022,05,23), susana,
				Arrays.asList(new Genre[]{pop, rock}));
		Track track7 = new Track("Bohemian Rhapsody", "best classic rock song", 650L, LocalDate.of(1975, 05,15), nuria,
				Arrays.asList(new Genre[]{rock, pop}));
		Track track8 = new Track("Jo vull jugar", "my children's song", 250L, LocalDate.of(2009,05,14), nuria);
		track8.addGenre(pop);
		trackRepository.save(track1);
		trackRepository.save(track2);
		trackRepository.save(track3);
		trackRepository.save(track4);
		trackRepository.save(track5);
		trackRepository.save(track6);
		trackRepository.save(track7);
		trackRepository.save(track8);

		Playlist playlist1 = new Playlist("Relax", "pels meus moments", true);
		playlist1.addTrack(track1);
		playlist1.addTrack(track2);
		playlist1.addCollaborator(elena);
		playlistRepository.save(playlist1);



		//pero ahora falta especificar con qué base de datos trabajamos --> application.properties
		//googlear --> Spring Boot mysql application properties

		System.out.println("Finished");

	}
}
