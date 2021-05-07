package com.inspiration.setlister.setlist;

import com.inspiration.setlister.song.Duration;

public class Break extends SetListItem {
	class IllegalDurationException extends RuntimeException {

		public IllegalDurationException(int newDuration) {
			super("Durations of setlistitems need to be larger then zero. (Got: " + newDuration + ")");
		}

	}

	Duration duration; 

	Break(Duration duration) {
		setDuration(duration);
	}

	Break() {
		// Default break of 10 minutes
		this(new Duration(600));
	}

	void setDuration(Duration newDuration) {
		this.duration = newDuration;
	}

	@Override
	Duration getDuration() {
		return duration;
	}

	@Override
	String getTxt() {
		return "Break (" + duration + ")";
	}

}
