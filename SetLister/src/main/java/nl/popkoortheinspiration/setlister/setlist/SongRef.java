package nl.popkoortheinspiration.setlister.setlist;

public class SongRef extends SetListItem {
	String songKey;
	
	public SongRef() {
		this(null);
	}

	public SongRef(String key) {
		this.songKey = key;
	}

	public String getSongKey() {
		return songKey;
	}

	public void setSongKey(String songKey) {
		this.songKey = songKey;
	}
}
