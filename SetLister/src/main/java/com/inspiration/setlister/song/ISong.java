package com.inspiration.setlister.song;

import com.inspiration.setlister.score.IScore;
import com.inspiration.setlister.track.ITrack;
import com.inspiration.setlister.track.TrackKind;

public interface ISong {
	String getTitle();
	String[] hitBy();
	String[] hitIn();
	String[] arrangmentBy();
	String[] composers();
	String   inRepertoireSince();
	String[] getNotes();
	
	ITrack getTrack(TrackKind kind);
	IScore getScore();
}
