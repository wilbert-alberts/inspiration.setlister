package com.inspiration.setlister.user;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

public class UserRepository {
	static Map<Long, User> users = init();

	static private Map<Long, User> init() {
		Map<Long, User> result = new HashMap<Long, User>();
		User u;

		u = new User(1, "Wilbert", "Wilbert");
		result.put(u.getId(), u);

		u = new User(2, "Marielle", "Marielle");
		result.put(u.getId(), u);
		return result;
	}

	static IUser createUser(IUser user) {
		Optional<Long> maxID = users.keySet().stream().max(Long::compare);
		Long newId = 1 + maxID.orElse(0L);
		User newUser = new User(newId, user.getUsername(), user.getPassword());
		users.put(newId, newUser);
		return newUser;
	}

	static IUser readUser(Long id) {
		IUser result = users.get(id);
		if (result == null)
			throw new IllegalArgumentException("readUser, user not found with id: " + id);
		return result;
	}

	static Collection<IUser> readUsers() {
		HashSet<IUser> result = new HashSet<>();
		result.addAll(users.values());
		return result;
	}

	static void updateUser(IUser newUser) {
		IUser result = users.get(newUser.getId());
		if (result == null)
			throw new IllegalArgumentException("readUser, user not found with id: " + newUser.getId());
		result.setPassword(newUser.getPassword());
		result.setUsername(newUser.getUsername());
	}

	static void deleteUser(Long id) {
		IUser result = users.get(id);
		if (result == null)
			throw new IllegalArgumentException("readUser, user not found with id: " + id);
		users.remove(id);
	}
}
