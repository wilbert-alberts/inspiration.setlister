package nl.popkoortheinspiration.setlister.setlist;

public class Break extends SetListItem {
	int durationInSeconds;

	public Break() {
		this(300);
	}

	public Break(int durationInSeconds) {
		this.durationInSeconds = durationInSeconds;
	}

	public int getDurationInSeconds() {
		return durationInSeconds;
	}

	public void setDurationInSeconds(int durationInSeconds) {
		this.durationInSeconds = durationInSeconds;
	}
}
