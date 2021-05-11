package nl.popkoortheinspiration.setlister.user;

import nl.popkoortheinspiration.setlister.user.User.Role;

public class UserTest {
	private final UserRepository repo;
	
	public UserTest(UserRepository repo) {
		this.repo = repo;
	}
	
	public void execute() {
		User u = new User("Wilbert", "wilbertje");
		repo.create(u);
		u.setRole(Role.ADMIN);
		repo.update(u);
	}
}
