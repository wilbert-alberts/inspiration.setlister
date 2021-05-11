package nl.popkoortheinspiration.setlister.setlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.cloud.Date;

import nl.popkoortheinspiration.setlister.song.Song;
import nl.popkoortheinspiration.setlister.song.SongRepository;
import nl.popkoortheinspiration.setlister.user.User;
import nl.popkoortheinspiration.setlister.user.UserRepository;

public class SetListTest {
	private SetListRepository repo;
	private UserRepository userRepo;
	private SongRepository songRepo;

	public SetListTest(SetListRepository repo, UserRepository userRepo, SongRepository songRepo) {
		this.repo = repo;
		this.userRepo = userRepo;
		this.songRepo = songRepo;
	}

	public void execute() {
		List<Song> songs = songRepo.readAll().stream().collect(Collectors.toList());
		Optional<User> author = userRepo.readAll().stream().findAny();

		if (songs.size() == 0) {
			System.err.println("No songs in repository.");
			return;
		}

		if (author.isEmpty()) {
			System.err.println("No author in user repository.");
			return;
		}

		SetList setList = repo.create();
		setList.setAuthorKey(author.get().getKey());

		Date today = Date.fromJavaUtilDate(new java.util.Date());
		setList.setDate(today);

		setList.setLocation("Raadhuis");
		setList.setTitle("Kerstoptreden 2022");

		ArrayList<SetListItem> setListItems = new ArrayList<SetListItem>();
		for (int i = 0; i < 10; i++) {
			SongRef item = new SongRef(songs.get(i % songs.size()).getKey());
			setListItems.add(item);

			if (i % 3 == 0) {
				Break br = new Break(600);
				setListItems.add(br);
			}
		}
		setList.setItems(setListItems);

		repo.update(setList);
	}
}
