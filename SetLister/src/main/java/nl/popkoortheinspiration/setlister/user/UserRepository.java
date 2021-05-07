package nl.popkoortheinspiration.setlister.user;

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
public class UserRepository extends Repository<User> {

	public UserRepository() {
		super("User");
	}

	public User create() {
		User obj = new User();
		return super.create(obj);
	}

	public User read(String key) {
		User obj = new User();
		return super.read(key, obj);
	}

	public Collection<User> readAll() {
		User obj = new User();
		return super.readAll(obj);
	}
}
