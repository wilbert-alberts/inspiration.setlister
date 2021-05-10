package nl.popkoortheinspiration.setlister.song;

import java.util.ArrayList;
import java.util.List;

import nl.popkoortheinspiration.setlister.repository.Entity;

public class Song extends Entity {
	String title;
	List<Track> tracks;
	int durationInSeconds;
	private String scoreURL;
	
	public Song() {
		this("_null_");
	}

	public Song(String title) {
		this(null, title);
	}

	public Song(String key, String title) {
		this(key, title, 180);
	}

	public Song(String key, String title, int durationInSeconds) {
		super(key);
		setTitle(title);
		setDurationInSeconds(durationInSeconds);
		tracks = new ArrayList<Track>();
	}

	public void set(Song other) {
		setTitle(other.title);
		setDurationInSeconds(other.getDurationInSeconds());
		setTracks(other.getTracks());
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Track> getTracks() {
		ArrayList<Track> r = new ArrayList<Track>(this.tracks);
		return r;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks.clear();
		this.tracks.addAll(tracks);
	}

	public int getDurationInSeconds() {
		return durationInSeconds;
	}

	public void setDurationInSeconds(int durationInSeconds) {
		this.durationInSeconds = durationInSeconds;
	}

	public String getScoreURL() {
		return scoreURL;
	}

	public void setScoreURL(String scoreURL) {
		this.scoreURL = scoreURL;
	}
	
}
