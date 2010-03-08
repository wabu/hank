package gameObjects;

import java.awt.Image;

import physics.Position;

/**
 * All Counter in the game are based on this class
 * 
 * @since	02/05/2010
 * @version	02/12/2010
 * @author	Flo
 *
 */
public abstract class Counter extends InterfaceObjects{

	/**
	 * constructor
	 * 
	 * @param pos
	 * @param spriteKey
	 */
	public Counter(Position pos, String spriteKey)
	{
		super(pos, spriteKey);
	}
	
	public Image getImage(){	
		return null;
	}	
}
