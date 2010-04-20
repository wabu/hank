package ViewPort;

import java.util.List;

import renderer.Entity.Entity;

public interface ViewPortAccess extends ViewPort{
	List<Entity> getList();
}
