package de.javauni.yarrish.model.space.viewport;

import de.javauni.jarcade.model.space.Entity;
import java.util.List;
import java.util.Vector;

public class ViewPortImpl implements ViewPort, ViewPortAccess {
	private List<Entity> list = new Vector<Entity>();
	
	@Override
	public List<Entity> getList() {
		return list;
	}
	
	@Override
	public void entityEntersViewport(Entity enty) {
		list.add(enty);
	}
	@Override
	public void entityLeavesViewport(Entity enty) {
		list.remove(enty);
	}

}
