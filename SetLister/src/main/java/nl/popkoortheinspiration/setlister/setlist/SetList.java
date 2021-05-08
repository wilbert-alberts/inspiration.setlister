package nl.popkoortheinspiration.setlister.setlist;

import java.util.ArrayList;
import java.util.List;

import com.google.cloud.Date;

import nl.popkoortheinspiration.setlister.repository.Entity;

public class SetList extends Entity {
	String title;
	String location;
	String authorKey;
	Date date;
	List<SetListItem> items;

	public SetList() {
		this("_null_", "_null_", "_null_", Date.fromJavaUtilDate(new java.util.Date()));
	}

	public SetList(String t, String l, String a, Date d) {
		this(null, t, l, a, d, new ArrayList<SetListItem>());
	}

	public SetList(String title, String location, String author, Date date, List<SetListItem> items) {
		this(null, title, location, author, date, items);
	}

	public SetList(String key, String t, String l, String a, Date d, List<SetListItem> i) {
		super(key);
		items = new ArrayList<SetListItem>();
		setTitle(t);
		setLocation(l);
		setAuthorKey(a);
		setDate(d);
		setItems(i);
	}

	public void set(SetList other) {
		setTitle(other.getTitle());
		setLocation(other.getLocation());
		setAuthorKey(other.getAuthorKey());
		setDate(other.getDate());
		setItems(other.getItems());
	}
	
	public String getAuthorKey() {
		return authorKey;
	}

	public void setAuthorKey(String authorKey) {
		this.authorKey = authorKey;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<SetListItem> getItems() {
		ArrayList<SetListItem> result = new ArrayList<SetListItem>(items);
		return result;
	}

	public void setItems(List<SetListItem> items) {
		this.items.clear();
		this.items.addAll(items);
	}

}
