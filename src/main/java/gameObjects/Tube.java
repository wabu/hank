package gameObjects;

import Game.GameLoop;
import physics.Position;

/**
 * Tube contains the methods to create the different tubes
 *  
 * @since	02/05/2010
 * @version 02/12/2010
 * @author 	gd1008
 */
public class Tube extends StaticObjects{

	/**
	 * Tube is the constructor
	 * 
	 * @param pos
	 * @param spriteKey
	 * @param type
	 */
	public Tube(Position pos, String spriteKey, String type)
	{
		super(pos, spriteKey, type);
	}
	
	/**
	 * tube1x1 creates a new ground object with x an y Position and fix width, height, velocity, spriteKey and type
	 * 
	 * @param x
	 * @param y
	 */
	public static Tube tube1X1( int x, int y)
	{
		return new Tube(new Position(x, y, (int)(145*GameLoop.SCALEFACTOR), (int)(175*GameLoop.SCALEFACTOR), 0f), "Tube1x1", "tube");
	}
}
