package nl.popkoortheinspiration.setlister.setlist;

import java.util.Collection;

import org.springframework.stereotype.Component;

import nl.popkoortheinspiration.setlister.repository.Repository;

@Component
public class SetListRepository extends Repository<SetList>{

	public SetListRepository() {
		super("SetList");
	}
	public SetList create() {
		SetList obj = new SetList();
		return super.create(obj);
	}

	public SetList read(String key) {
		SetList obj = new SetList();
		return super.read(key, obj);
	}

	public Collection<SetList> readAll() {
		SetList obj = new SetList();
		return super.readAll(obj);
	}
}
