package nl.popkoortheinspiration.setlister.song;

import java.util.ArrayList;
import java.util.List;

public class SongTest {

	private final SongRepository repo;

	public SongTest(SongRepository repo) {
		this.repo = repo;
	}
	
	public void execute() {
		Song s = new Song("TestSong");
		Track t1 = new Track("http://t1.mp3", Track.Kind.REHEARSE_ALL);
		Track t2 = new Track("http://t2.mp3", Track.Kind.ACCOMPANIMENT);
		List<Track> ts = new ArrayList<Track>();
		ts.add(t1);
		ts.add(t2);
		s.setTracks(ts);
		s.setDurationInSeconds(260);
		s.setScoreURL("http://myscoreurl.html");
		repo.create(s);
	}
}
