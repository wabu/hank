package gameObjects;

import Game.GameLoop;
import physics.Position;

/**
 * Chest contains the methods to create the different ground-tiles
 *  
 * @since	02/05/2010
 * @version 02/12/2010
 * @author 	gd1008
 */
public class Chest extends StaticObjects {
	
	/**
	 * Chest is the constructor
	 * 
	 * @param pos
	 * @param spriteKey
	 * @param type
	 */
	public Chest(Position pos, String spriteKey, String type)
	{
		super(pos, spriteKey, type);
	}
	
	/**
	 * chest1X1 creates a new ground object with x an y Position and fix width, height, velocity, spriteKey and type
	 * 
	 * @param x
	 * @param y
	 */
	public static Chest chest1X1(int x, int y)
	{
		return new Chest(new Position(x, y, (int)(96*GameLoop.SCALEFACTOR), (int)(96*GameLoop.SCALEFACTOR), 0f), "chest1X1", "small chest");
	}
}
