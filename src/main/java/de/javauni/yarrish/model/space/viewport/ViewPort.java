package de.javauni.yarrish.model.space.viewport;

import de.javauni.jarcade.model.space.Entity;

public interface ViewPort {
	void entityEntersViewport(Entity enty);
	void entityLeavesViewport(Entity enty);
}
