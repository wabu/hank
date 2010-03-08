package gameObjects;

import physics.Position;

public abstract class StaticObjects extends CollisionObjects{

	String type;
	
	public StaticObjects(Position pos, String spriteKey, String type)
	{
		super(pos, spriteKey);
		this.type = type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
