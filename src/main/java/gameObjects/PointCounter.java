package gameObjects;

import java.awt.Image;

import physics.Position;

/**
 * This is the PointCounter class. It can count every sort of Point
 * 
 * @since	02/05/2010
 * @version 02/12/2010
 * @author	Flo
 */
public class PointCounter extends Counter {

	/**
	 * constructor
	 * 
	 * @param pos
	 * @param spriteKey
	 */
	public PointCounter(Position pos, String spriteKey)
	{
		super(pos, spriteKey);
	}
	
	public Image getImage()
	{	
		return null;
	}
}
