package ViewPort;

import java.util.List;
import java.util.Vector;

import renderer.Entity.Entity;

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
