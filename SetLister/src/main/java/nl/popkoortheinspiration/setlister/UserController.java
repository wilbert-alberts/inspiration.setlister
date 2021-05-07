package nl.popkoortheinspiration.setlister;

import java.util.Collection;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nl.popkoortheinspiration.setlister.user.User;
import nl.popkoortheinspiration.setlister.user.UserRepository;

@RestController
public class UserController {

	private final UserRepository repository;

	public UserController(UserRepository repository) {
		System.err.println("> UserController::UserController()");
		this.repository = repository;
	}

	@PostMapping("/users")
	User create(@RequestBody User newUser) {
		System.err.println("> UserController::create()");
		return repository.create(newUser);
	}

	@GetMapping("/users")
	Collection<User> readAll() {
		System.err.println("> UserController::readAll()");
		Collection<User> result = repository.readAll();
		return result;
	}

	@GetMapping("/users/{id}")
	User read(@PathVariable String id) {
		System.err.println("> UserController::read()");
		return repository.read(id);
	}

	@PutMapping("/users/{id}")
	void update(@RequestBody User newUser, @PathVariable String id) {
		System.err.println("> UserController::update()");
		User s = repository.read(id);
		if (s != null)
			s.set(newUser);
	}

	@DeleteMapping("/users/{id}")
	void delete(@PathVariable String id) {
		System.err.println("> UserController::delete()");
		repository.delete(id);
	}
}
