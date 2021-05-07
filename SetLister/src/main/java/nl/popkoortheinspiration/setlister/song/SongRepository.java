package nl.popkoortheinspiration.setlister.song;

import java.beans.JavaBean;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Component;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import nl.popkoortheinspiration.setlister.repository.Repository;

@Component
public class SongRepository extends Repository<Song> {

	public SongRepository() {
		super("Song");
	}

	public Song create() {
		Song obj = new Song();
		return super.create(obj);
	}
	
	public Song read(String key) {
		Song obj = new Song();
		return super.read(key, obj);
	}
	
	public Collection<Song> readAll() {
		Song obj = new Song();
		return super.readAll(obj);
	}
}
