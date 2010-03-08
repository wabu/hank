package gameObjects;

import Game.GameLoop;
import physics.Position;

/**
 * Rat is a non-player-character. The class contains the constructor 
 * 
 * @since
 * @version
 * @author Flo
 *
 */
public class Rat extends Characters {
	
	/**
	 * this is the standard constructor for Rat
	 * 
	 * @param pos
	 * @param spriteKey
	 * @param name
	 */
	public Rat(Position pos, String spriteKey, String name)
	{
		super(pos, spriteKey, name);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param velocity
	 */
	public static Rat makeRat (int x, int y, float velocity)
	{
		return new Rat( new Position(x, y, (int)(96*GameLoop.SCALEFACTOR), (int)(48*GameLoop.SCALEFACTOR), velocity), "Rat01", "Ratte");
	}
}
