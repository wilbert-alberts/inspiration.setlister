package com.inspiration.setlister.user;

public class User implements IUser {
	private Long id;
	private String username;
	private String password;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User(long l, String un, String pw) {
		id = l;
		username = un;
		password = pw;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}
}
