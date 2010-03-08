package gameObjects;

import Game.GameLoop;
import physics.Position;

/**
 * All non-player-characters are based on this class
 * 
 * @since	02/05/2010
 * @version	02/12/2010
 * @author	Flo
 *
 */
public class AI extends Characters{
	private GameLoop.Playerstate lastState;
	
	/**
	 * Constructor
	 * 
	 * @param pos
	 * @param name
	 */
	public AI(Position pos, String spriteKey, String name)
	{
		super(pos, spriteKey, name);
	}
	
	public GameLoop.Playerstate getLastState(){
		return lastState;
	}
	
	public void setLastState(GameLoop.Playerstate ls){
		lastState = ls;
	}	
}
