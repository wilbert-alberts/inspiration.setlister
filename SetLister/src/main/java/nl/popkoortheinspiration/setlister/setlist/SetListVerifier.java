package nl.popkoortheinspiration.setlister.setlist;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.popkoortheinspiration.setlister.song.Song;
import nl.popkoortheinspiration.setlister.song.SongRepository;
import nl.popkoortheinspiration.setlister.user.User;
import nl.popkoortheinspiration.setlister.user.UserRepository;

public class SetListVerifier {
	private final UserRepository userRepo;
	private final SongRepository songRepo;
	private final SetListRepository setListRepo;

	public SetListVerifier(SetListRepository setlistRepo, UserRepository userRepo, SongRepository songRepo) {
		this.userRepo = userRepo;
		this.songRepo = songRepo;
		this.setListRepo = setlistRepo;
	}

	public boolean verify(SetList sl) {
		Collection<Song> songs = songRepo.readAll();
		Collection<User> users = userRepo.readAll();

		boolean validUser = users.stream().anyMatch(u -> u.getKey() == sl.getAuthorKey());

		Stream<SongRef> songsInSetList = sl.getItems().stream().filter(s -> s instanceof SongRef).map(s -> (SongRef) s);
		boolean validSongs = songsInSetList
				.allMatch(sisl -> songs.stream().anyMatch(sir -> sir.getKey() == sisl.getSongKey()));

		return validUser && validSongs;
	}

	public void fixRepo() {
		Collection<Song> songs = songRepo.readAll();
		Collection<User> users = userRepo.readAll();

		Collection<SetList> setLists = setListRepo.readAll();
		setLists.forEach(sl -> fixSetList(sl, songs, users));
	}

	private void fixSetList(SetList sl, Collection<Song> songs, Collection<User> users) {
		boolean updateNeeded = !verify(sl);
		if (updateNeeded) {
			if (users.stream().noneMatch(u -> u.getKey() == sl.getAuthorKey())) {
				sl.setAuthorKey(null);
			}
			List<SetListItem> validItems = sl.getItems().stream()
					.filter(i -> (!(i instanceof SongRef))
							|| (songs.stream().anyMatch(s -> s.getKey() == ((SongRef) i).getSongKey())))
					.collect(Collectors.toList());
			sl.setItems(validItems);
			setListRepo.update(sl);
		}
	}
}
