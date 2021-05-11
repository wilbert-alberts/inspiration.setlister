package nl.popkoortheinspiration.setlister;

import java.util.Collection;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nl.popkoortheinspiration.setlister.song.Song;
import nl.popkoortheinspiration.setlister.song.SongRepository;
import nl.popkoortheinspiration.setlister.song.SongTest;

@RestController
public class SongController {

	private final SongRepository repository;
	
	public SongController(SongRepository repository) {
		System.err.println("> SongController::SongController()");
		this.repository = repository;
	}

	@GetMapping("/songs/test")
	void songTest() {
		System.err.println("> SongController::songTest()");
		SongTest tst = new SongTest(repository);
		tst.execute();
	}

	
	@PostMapping("/songs")
	Song create(@RequestBody Song newSong) {
		System.err.println("> SongController::create()");
		return repository.create(newSong);
	}

	@GetMapping("/songs")
	Collection<Song> readAll() {
		System.err.println("> SongController::readAll()");
		Collection<Song> result = repository.readAll();
		return result;
	}

	@GetMapping("/songs/{id}")
	Song read(@PathVariable String id) {
		System.err.println("> SongController::read()");
		return repository.read(id);
	}

	@PutMapping("/songs/{id}")
	void update(@RequestBody Song newSong, @PathVariable String id) {
		System.err.println("> SongController::update()");
		Song s = repository.read(id);
		if (s != null) {
			s.set(newSong);
			repository.update(s);
		}
	}

	@DeleteMapping("/songs/{id}")
	void delete(@PathVariable String id) {
		System.err.println("> SongController::delete()");
		repository.delete(id);
	}
}
