package com.inspiration.setlister.user;

import java.util.Collection;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private final UserRepository repository;
	
	public UserController(UserRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/users")
	Collection<User> getAllUsers() {
		Collection<User> result = repository.findAll();
		return result;
	}

	@GetMapping("/users/{id}")
	User getUserById(@PathVariable Long id) {
		return repository.findById(id).get();
	}

	@PutMapping("/users/{id}")
	User getUserById(@RequestBody User newUser, @PathVariable Long id) {
		System.err.println("Entering update");
		return repository.findById(id).map(u -> {
			u.setId(newUser.getId());
			u.setPassword(newUser.getPassword());
			return repository.save(u);
		}).orElseGet(() -> {
			newUser.setId(id);
			return repository.save(newUser);
		});
	}

	@PostMapping("/users")
	User createUser(@RequestBody User newUser) {
		return repository.save(newUser);
	}

	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable Long id) {
		System.err.println("Entering delete");
		repository.deleteById(id);
	}
}
