package nl.popkoortheinspiration.setlister;

import java.util.Collection;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nl.popkoortheinspiration.setlister.setlist.SetList;
import nl.popkoortheinspiration.setlister.setlist.SetListRepository;
import nl.popkoortheinspiration.setlister.song.SongRepository;
import nl.popkoortheinspiration.setlister.user.UserRepository;

@RestController
public class SetListController {

	private final SetListRepository repository;
	private final UserRepository userRepo;
	private final SongRepository songRepo;

	public SetListController(SetListRepository repository, UserRepository userRepo, SongRepository songRepo) {
		System.err.println("> SetListController::SetListController()");
		this.repository = repository;
		this.userRepo = userRepo;
		this.songRepo = songRepo;
	}

	@PostMapping("/setlists")
	SetList create(@RequestBody SetList newSetList) {
		System.err.println("> SetListController::create()");
		return repository.create(newSetList);
	}

	@GetMapping("/setlists")
	Collection<SetList> readAll() {
		System.err.println("> SetListController::readAll()");
		Collection<SetList> result = repository.readAll();
		return result;
	}

	@GetMapping("/setlists/{id}")
	SetList read(@PathVariable String id) {
		System.err.println("> SetListController::read()");
		return repository.read(id);
	}

	@PutMapping("/setlists/{id}")
	void update(@RequestBody SetList newSetList, @PathVariable String id) {
		System.err.println("> SetListController::update()");
		SetList s = repository.read(id);
		if (s != null)
			s.set(newSetList);
	}

	@DeleteMapping("/setlists/{id}")
	void delete(@PathVariable String id) {
		System.err.println("> SetListController::delete()");
		repository.delete(id);
	}
}
