package com.inspiration.setlister.setlist;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.inspiration.setlister.user.User;

@Entity
public class SetList {
	@Id
	@GeneratedValue
	Long id;
	User author;
	String date;
	String Location;
	String remarks;
	List<SetListItem> setListItems;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<SetListItem> getSetListItems() {
		return setListItems;
	}

	public void setSetListItems(List<SetListItem> setListItems) {
		this.setListItems = setListItems;
	}
}
