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

	@GetMapping("/users")
	Collection<IUser> getAllUsers() {
		Collection<IUser> result = UserRepository.readUsers();
		return result;
	}

	@GetMapping("/users/{id}")
	IUser getUserById(@PathVariable Long id) {
		return UserRepository.readUser(id);
	}

	@PutMapping("/users/{id}")
	void getUserById(@RequestBody User newUser) {
		System.err.println("Entering update");
		UserRepository.updateUser(newUser);
	}

	@PostMapping("/users")
	IUser createUser(@RequestBody User newUser) {
		return UserRepository.createUser(newUser);
	}

	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable Long id) {
		System.err.println("Entering delete");
		UserRepository.deleteUser(id);
	}
}
