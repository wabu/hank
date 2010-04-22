package de.javauni.yarrish.model.space.viewport;

import de.javauni.jarcade.model.space.Entity;
import java.util.List;

public interface ViewPortAccess extends ViewPort{
	List<Entity> getList();
}
