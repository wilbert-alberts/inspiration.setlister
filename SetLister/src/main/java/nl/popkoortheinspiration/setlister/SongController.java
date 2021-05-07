package nl.popkoortheinspiration.setlister;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nl.popkoortheinspiration.setlister.song.Song;
import nl.popkoortheinspiration.setlister.song.SongRepository;
import nl.popkoortheinspiration.setlister.song.Track;

@RestController
public class SongController {

	private final SongRepository repository;
	
	public SongController(SongRepository repository) {
		System.err.println("> SongController::SongController()");
		this.repository = repository;
	}

	@GetMapping("/songtest")
	void songTest() {
		System.err.println("> SongController::songTest()");
		Song s = new Song("TestSong");
		Track t1 = new Track("http://t1.mp3", Track.Kind.REHEARSE_ALL);
		Track t2 = new Track("http://t1.mp3", Track.Kind.REHEARSE_ALL);
		List<Track> ts = new ArrayList<Track>();
		ts.add(t1);
		ts.add(t2);
		s.setTracks(ts);
		repository.create(s);
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
		if (s != null)
			s.set(newSong);
	}

	@DeleteMapping("/songs/{id}")
	void delete(@PathVariable String id) {
		System.err.println("> SongController::delete()");
		repository.delete(id);
	}
}
