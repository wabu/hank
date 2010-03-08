package gameObjects;

import physics.Position;

/**
 * the GameObject is the basic object for all objects in the game
 * 
 * @since 	02/05/2010
 * @version	02/12/2010
 * @author 	Flo
 */

public abstract class GameObject 
{
	private Position pos;
	private String spriteKey;
	
	/**
	 * constructor
	 * 
	 * @param pos
	 * @param spriteKey
	 */
	public GameObject (Position pos, String spriteKey)
	{
		this.pos = pos;
		this.spriteKey = spriteKey;
	}
	
	// getter and setter
	public Position getPosition()
	{
		return pos;
	}
	
	public void setPosition(Position pos)
	{
		this.pos = pos;
	}
	
	public String getSpriteKey()
	{
		return spriteKey;
	}
	
	public void setSpriteKey(String key)
	{
		this.spriteKey = key;
	}
}
