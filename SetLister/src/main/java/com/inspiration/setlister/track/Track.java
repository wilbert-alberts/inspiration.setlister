package com.inspiration.setlister.track;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Track {
	public enum Kind {
		ACCOMPANYMENT, PRACTICE_ALL, PRACTICE_SOPRANO, PRACTICE_ALTO, PRACTICE_TENOR, EMPTY
	}

	@Id
	@GeneratedValue
	Long id;
	String link;
	Kind kind;
	String[] notes = null;

	Track() {
		this("");
	}

	Track(String link) {
		this(link, Kind.EMPTY);
	}

	Track(String link, Kind kind) {
		setLink(link);
		setKind(kind);
	}

	Track(String link, Kind kind, String note) {
		this(link, kind);
		setNotes(note);
	}

	Track(String link, Kind kind, String[] notes) {
		this(link, kind);
		setNotes(notes);
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public String[] getNotes() {
		return notes;
	}

	public void setNotes(String note) {
		this.notes = new String[1];
		this.notes[0] = note;
	}

	public void setNotes(String[] notes) {
		this.notes = notes;
	}


}
