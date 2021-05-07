package nl.popkoortheinspiration.setlister.repository;

import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

public class FirestoreHelper {
	private static final String PROJECTID = "setlister";
	private static Firestore db;

	static Firestore getDB() {
		if (db == null) {
			FirestoreOptions firestoreOptions;
			try {
				firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder().setProjectId(PROJECTID)
						.setCredentials(GoogleCredentials.getApplicationDefault()).build();
				Firestore db = firestoreOptions.getService();
				return db;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
