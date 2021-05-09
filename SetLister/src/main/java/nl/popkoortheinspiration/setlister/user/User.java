package nl.popkoortheinspiration.setlister.user;

import nl.popkoortheinspiration.setlister.repository.Entity;

public class User extends Entity {
	public enum Role {
		ADMIN, EDITOR, PUBLIC
	}

	String username;
	String password;
	Role role;

	public User() {
		this("_null_", null, Role.PUBLIC);
	}

	public User(String n, String p) {
		this(n, p, Role.PUBLIC);
	}

	public User(String n, String p, Role r) {
		this(null, n, p, r);
	}

	public User(String key, String n, String p, Role r) {
		super(key);
		setUsername(n);
		setPassword(p);
		setRole(r);
	}

	public void set(User other) {
		setUsername(other.getUsername());
		setPassword(other.getPassword());
		setRole(other.getRole());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String toString() {
		return "{ " + getUsername() + ", " + getPassword() + ", " + getRole().toString() + " }";
	}
}
