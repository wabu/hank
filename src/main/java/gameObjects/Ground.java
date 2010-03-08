package gameObjects;

import Game.GameLoop;
import physics.Position;

/**
 * Ground contains the methods to create the different ground-tiles
 *  
 * @since	02/05/2010
 * @version 02/12/2010
 * @author 	Flo
 */
public class Ground extends StaticObjects {
	
	/**
	 * Ground is the constructor
	 * 
	 * @param pos
	 * @param spriteKey
	 * @param type
	 */
	public Ground(Position pos, String spriteKey, String type)
	{
		super(pos, spriteKey, type);
	}
	
	/**
	 * ground1X1 creates a new ground object with x an y Position and fix width, height, velocity, spriteKey and type
	 * 
	 * @param x
	 * @param y
	 */
	public static Ground ground1X1(int x, int y)
	{
		return new Ground(new Position(x, y, (int)(130*GameLoop.SCALEFACTOR), (int)(130*GameLoop.SCALEFACTOR), 0f), "Ground1X1", "ground");
	}
	
	public static Ground ground1X1flipped(int x, int y)
	{
		return new Ground(new Position(x, y, (int)(130*GameLoop.SCALEFACTOR), (int)(130*GameLoop.SCALEFACTOR), 0f), "Ground1X1flipped", "groundf");
	}
}
