package nl.popkoortheinspiration.setlister.repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

public class Repository<T extends Entity> {
	Firestore db;

	String collectionName;

	public Repository(String collectionName) {
		db = FirestoreHelper.getDB();
		this.collectionName = collectionName;
	}

	public T create(T newT) {
		DocumentReference ref = db.collection(collectionName).document();
		try {
			newT.setKey(ref.getId());
			ref.create(newT).get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newT;
	}

	public T read(String key, T obj) {
		DocumentReference ref = db.collection(collectionName).document(key);
		ApiFuture<DocumentSnapshot> result = ref.get();
		DocumentSnapshot snapshot;
		T t = null;
		try {
			snapshot = result.get();
			t = (T) snapshot.toObject(obj.getClass());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	public Collection<T> readAll(T obj) {
		CollectionReference ref = db.collection(collectionName);
		Set<T> result = new HashSet<T>();
		for (DocumentReference docRef : ref.listDocuments()) {
			DocumentSnapshot snapshot;
			try {
				snapshot = docRef.get().get();
				if (snapshot.exists()) {
					T t = (T) snapshot.toObject(obj.getClass());
					result.add(t);
				}
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public void update(T t) {
		DocumentReference ref = db.collection(collectionName).document(t.getKey());
		ref.set(t);
	}

	public void delete(String key) {
		DocumentReference ref = db.collection(collectionName).document(key);
		ref.delete();
		ref.get();
	}
}
