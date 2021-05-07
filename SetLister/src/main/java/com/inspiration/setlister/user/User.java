package com.inspiration.setlister.user;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	private @Id @GeneratedValue Long id;
	private String username;
	private String password;

	User() {
	}

	User(String un, String pw) {
		username = un;
		password = pw;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.username, this.password);
	}

	public boolean compare(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;
		User other = (User) o;
		return Objects.equals(this.id, other.id) && Objects.equals(this.username, other.username)
				&& Objects.equals(this.password, other.password);
	}
}
