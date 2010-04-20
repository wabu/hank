package ViewPort;

import renderer.Entity.Entity;

public interface ViewPort {
	void entityEntersViewport(Entity enty);
	void entityLeavesViewport(Entity enty);
}
