package gameObjects;

import Game.GameLoop;
import physics.Position;

/**
 * Hank is a player character. The class contains the constructor 
 * 
 * @since	02/05/2010
 * @version 02/12/2010
 * @author 	Flo
 */
public class Hank extends Player {
	
	/**
	 * this is the standard constructor for Cpt. Hank
	 * 
	 * @param pos
	 * @param spriteKey
	 * @param name
	 */
	public Hank(Position pos, String spriteKey, String name)
	{
		super(pos, spriteKey ,name);
	}
	
	/**
	 * makeHank creates a new Cpt. Hank object with x, y Position and velocity and fix width, height, spriteKey and name
	 * 
	 * @param x
	 * @param y
	 * @param velocity
	 */
	public static Hank makeHank(int x, int y, float velocity)
	{
		return new Hank(new Position(x, y, (int)(100*GameLoop.SCALEFACTOR), (int)(130*GameLoop.SCALEFACTOR), velocity), "Hank01", "Cpt. Hank");
	}
}
