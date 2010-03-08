package gameObjects;

import Game.GameLoop;
import physics.Position;

/**
 * abstract class for all characters
 * 
 * @since 	02/05/2010
 * @version	02/12/2010
 * @author 	Flo
 */
public abstract class Characters extends CollisionObjects {
	protected String name;
	protected final float ACCELERATION = 1f; 
	protected float myAcc;
	
	private GameLoop.Playerstate myState;
	
	/**
	 * constructor
	 * 
	 * @param pos
	 * @param spriteKey
	 * @param name
	 */
	public Characters(Position pos, String spriteKey, String name)
	{
		super(pos, spriteKey);
		this.name 	= name;
		myState 	= GameLoop.Playerstate.IDLE;
	}

	public float getAcc()
	{
		return myAcc;
	}
	
	public void setAcc(float acc)
	{
		myAcc = acc;
	}
	
	public GameLoop.Playerstate getMyState()
	{
		return myState;
	}
	
	public void setMyState(GameLoop.Playerstate state)
	{
		myState = state;
	}
}
