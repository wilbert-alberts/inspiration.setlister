package nl.popkoortheinspiration.setlister.repository;

public class Entity {
	String key;

	public Entity() {
		key = null;
	}

	public Entity(String key) {
		this.key = key;
	}

	public void setKey(String key) {
		if ((this.key != null) && (this.key.compareTo(key) != 0)) {
			System.err.println("Changing key of Entity that has already one. Old: " + this.key + ", new: " + key);
		}
		this.key = key;
	}

	public String getKey() {
		return key;
	}

}
