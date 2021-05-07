package com.inspiration.setlister.song;

public class Duration {
	public class IllegalDurationException extends RuntimeException {

		public IllegalDurationException(int duration) {
			super("Illegal duration in seconds: " + duration + " (should be at least 0)");
		}

		public IllegalDurationException(int minutes, int seconds) {
			super("Illegal duration in minuytes:seconds (" + minutes + ":" + seconds + ")");
		}

	}

	int durationInSeconds;

	public void setDurationInSeconds(int durationInSeconds) {
		if (durationInSeconds < 0)
			throw new IllegalDurationException(durationInSeconds);
		this.durationInSeconds = durationInSeconds;
	}

	public void setDuration(int minutes, int seconds) {
		if (minutes < 0)
			throw new IllegalDurationException(minutes, seconds);
		if (seconds < 0)
			throw new IllegalDurationException(minutes, seconds);
		if (seconds > 59)
			throw new IllegalDurationException(minutes, seconds);

		durationInSeconds = minutes * 60 + seconds;
	}

	public String toString(int duration) {
		if (duration < 0)
			throw new IllegalDurationException(duration);
		int minutes = duration / 60;
		int seconds = duration % 60;
		String minutesStr = Integer.toString(minutes);
		String secondsStr = Integer.toString(seconds);
		if (seconds < 10)
			secondsStr = "0" + secondsStr;
		String result = minutesStr + ":" + secondsStr;
		return result;
	}

	public int getDurationInSeconds() {
		return durationInSeconds;
	}

	public Duration(int seconds) {
		this.setDurationInSeconds(seconds);
	}

	public Duration(int minutes, int seconds) {
		this.setDuration(minutes, seconds);
	}

}
