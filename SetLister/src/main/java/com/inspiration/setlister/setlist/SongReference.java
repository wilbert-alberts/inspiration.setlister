package com.inspiration.setlister.setlist;

import com.inspiration.setlister.song.Duration;
import com.inspiration.setlister.song.Song;

public class SongReference extends SetListItem {
	Song songRef;

	SongReference(Song Song) {
		this.songRef = Song;
	}

	@Override
	Duration getDuration() {
		if (songRef == null)
			return null;
		else
			return songRef.getDuration();
	}

	@Override
	String getTxt() {
		if (songRef == null)
			return "<Empty>";
		else
			return songRef.getTitle() + " (" + songRef.getDuration().toString() + ")"; 
	}

}
