package nl.popkoortheinspiration.setlister.song;

public class Track {
	public enum Kind {
		UNSPECIFIED, ACCOMPANIMENT, REHEARSE_ALL, REHEARSE_SOPRANO, REHEARSE_ALTO, REHEARSE_TENOR
	}

	String url;
	Kind kind;

	public Track() {
		this("<null", Kind.UNSPECIFIED);
	}
	
	public Track(String title) {
		this(title, Kind.UNSPECIFIED);
	}
	
	public Track(String title, Kind kind) {
		setKind(kind);
		setUrl(title);
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}
}
