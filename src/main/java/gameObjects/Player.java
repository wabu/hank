package gameObjects;

import Game.GameLoop;
import physics.Gravity;
import physics.Position;

/**
 * All player characters are based on this class
 * 
 * @since 	02/05/2010
 * @version 02/12/2010
 * @author 	gd1008
 *
 */

public class Player extends Characters {
	
	/**
	 * constructor
	 * 
	 * @param pos
	 * @param spriteKey
	 * @param name
	 */
	public int jumpedFromHeight = getPosition().getY();
	private GameLoop.Playerstate lastState;
	
	
	public Player(Position pos, String spriteKey, String name)
	{
		super(pos, spriteKey, name);
	}
	
	public void jump(Gravity grav, float f)
	{
		// per definition: -1 can jump, 0 is falling, >0 jumps
		//if (grav.getJumpTime()==-1)
		
		// rise while not on max. jump height yet and only if not falling at the moment
		if (getPosition().getY() > jumpedFromHeight-GameLoop.PLAYER_MAX_HEIGHT && !(super.getJumpTime() == 0))
			super.setJumpTime(f);
	}
	
	public void goLeft()
	{
		this.setAcc(-ACCELERATION);
		super.setMyState(GameLoop.Playerstate.GOLEFT);
		lastState = GameLoop.Playerstate.GOLEFT;
	}
	
	public void goRight()
	{
		this.setAcc(ACCELERATION);
		super.setMyState(GameLoop.Playerstate.GORIGHT);
		lastState = GameLoop.Playerstate.GORIGHT;
	}
	
	public void idle()
	{
		this.setAcc(0);
		if (lastState == GameLoop.Playerstate.GORIGHT)
			super.setMyState(GameLoop.Playerstate.IDLERIGHT);
		else if (lastState == GameLoop.Playerstate.GOLEFT)
			super.setMyState(GameLoop.Playerstate.IDLELEFT);
		else
			super.setMyState(GameLoop.Playerstate.IDLE);
	}
	
	public void duck()
	{
		//to do
	}
}
