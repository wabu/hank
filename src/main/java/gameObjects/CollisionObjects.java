package gameObjects;

import physics.Position;

public class CollisionObjects extends GameObject{	
	private float jumpTime;
	private int fallTime;
	
	public CollisionObjects(Position pos, String spriteKey){
		super(pos, spriteKey);
		jumpTime	= 0.f;
		fallTime	= 0;
	}
	
	public Position getPosition(){
		return super.getPosition();
	}
	
	public void setPosition(Position p){
		super.setPosition(p);
	}
	
	public float getJumpTime()
	{
		return jumpTime;
	}
	
	public void setJumpTime(float j)
	{
		this.jumpTime = j;
	}
	
	public int getFallTime()
	{
		return fallTime;
	}
	
	public void setFallTime(int f)
	{
		this.fallTime = f;
	}	
}
