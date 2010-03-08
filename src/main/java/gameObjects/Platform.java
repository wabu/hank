package gameObjects;

import Game.GameLoop;
import physics.Position;

/**
 * Platform contains the methods to create the different ground-tiles
 *  
 * @since	02/05/2010
 * @version 02/12/2010
 * @author 	gd1008
 */
public class  Platform extends StaticObjects {
	
	/**
	 * Platform is the constructor
	 * 
	 * @param pos
	 * @param spriteKey
	 * @param type
	 */
	public Platform(Position pos, String spriteKey, String type)
	{
		super(pos, spriteKey, type);
	}
	
	/**
	 * platform1X1 creates a new Platform object with x an y Position and fix width, height, velocity, spriteKey and type
	 * 
	 * @param x
	 * @param y
	 */
	public static Platform platform1X1(int x, int y)
	{
		return new Platform(new Position(x, y, (int)(128*GameLoop.SCALEFACTOR), (int)(24*GameLoop.SCALEFACTOR), 0f), "platform1X1", "platform");
	}
}