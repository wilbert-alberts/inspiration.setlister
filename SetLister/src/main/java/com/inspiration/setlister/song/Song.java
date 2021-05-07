package com.inspiration.setlister.song;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.inspiration.setlister.score.Score;
import com.inspiration.setlister.track.Track;
import com.inspiration.setlister.track.Track.Kind;

@Entity
public class Song {
	public class DuplicateTrackKindException extends RuntimeException {
		public DuplicateTrackKindException(Song song, Set<Kind> duplicateKinds) {
			super("Trying to associate multiple tracks with common kind ("
					+ duplicateKinds.stream().map(k -> k.toString()).collect(Collectors.joining(", ", "{ ", " }"))
					+ ") to song (" + song.getTitle() + ")");
		}
	}

	@Id
	@GeneratedValue
	Long id;
	String title;
	String[] hitBy;
	String[] hitIn;
	String[] arrangmentBy;
	String[] composers;
	String inRepertoireSince;
	String[] notes;
	Duration duration;
	Track[] tracks;
	Score score;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getHitBy() {
		return hitBy;
	}

	public void setHitBy(String[] hitBy) {
		this.hitBy = hitBy;
	}

	public String[] getHitIn() {
		return hitIn;
	}

	public void setHitIn(String[] hitIn) {
		this.hitIn = hitIn;
	}

	public String[] getArrangmentBy() {
		return arrangmentBy;
	}

	public void setArrangmentBy(String[] arrangmentBy) {
		this.arrangmentBy = arrangmentBy;
	}

	public String[] getComposers() {
		return composers;
	}

	public void setComposers(String[] composers) {
		this.composers = composers;
	}

	public String getInRepertoireSince() {
		return inRepertoireSince;
	}

	public void setInRepertoireSince(String inRepertoireSince) {
		this.inRepertoireSince = inRepertoireSince;
	}

	public String[] getNotes() {
		return notes;
	}

	public void setNotes(String[] notes) {
		this.notes = notes;
	}

	public Track[] getTracks() {
		return tracks;
	}

	public void setTracks(Track[] tracks) {
		Map<Track.Kind, List<Track>> tmp = Arrays.stream(tracks).collect(Collectors.groupingBy(t -> t.getKind()));
		Set<Track.Kind> duplicateKinds = tmp.keySet().stream().filter(k -> tmp.size() > 1).collect(Collectors.toSet());
		if (duplicateKinds.size() > 0)
			throw new DuplicateTrackKindException(this, duplicateKinds);
		this.tracks = tracks;
	}

	Track getTrack(Track.Kind kind) {
		Optional<Track> result = Arrays.stream(tracks).filter(t -> t.getKind() == kind).findAny();
		return result.orElseGet(null);
	}

	Score getScore() {
		return score;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}
}
