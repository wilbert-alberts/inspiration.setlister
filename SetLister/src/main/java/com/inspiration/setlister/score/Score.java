package com.inspiration.setlister.score;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Score {
	@Id
	@GeneratedValue
	Long id;
	String link;

	Score() {
	}

	Score(String link) {
		this.link = link;
	}

	Score(Long id, String link) {
		this.id = id;
		this.link = link;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
