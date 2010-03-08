package physics;

import java.util.Vector;

import Game.GameLoop;
import gameObjects.*;
import gameState.GameState;

/**
 * Gravity calculates the new Position of an CollisionObject based on the velocity
 * 
 * @see		CollisionObjects, GameState
 * @since	02/05/2010
 * @version	02/05/2010 v0.1
 * @author 	Caspar Eiting
 *
 */

public class Gravity 
{	
	/** 
	 * calculate new position based on GRAVITATION and velocity
	 * 
	 * @param go
	 * @param acceleration
	 * @param gState
	 * @return newPosition
	 */
	private Position calcPotentialNewPosition (CollisionObjects go, float acceleration, GameState gState )
	{
		Position newPosition = new Position(go.getPosition());
		
		// if the jumpTime is greater than 0 ...
		if (go.getJumpTime() > 0)
		{
			//System.out.println("jump!");
			// ... let him jump
			go.setFallTime(0);
			go.setJumpTime(go.getJumpTime()-1); 
			newPosition.setY(newPosition.getY() - Math.round(go.getJumpTime() * GameLoop.GRAVITATION));			
			
			if (acceleration>0){
				((Characters)go).setMyState(GameLoop.Playerstate.JUMPRIGHT);
				if (newPosition.getVelocity()+1<GameLoop.GRAVITY_MAX_VELOCITY_RIGHT)
					newPosition.setVelocity(newPosition.getVelocity()+GameLoop.GRAVITY_ACC);
			} else if (acceleration<0) {
				((Characters)go).setMyState(GameLoop.Playerstate.JUMPLEFT);
				if (newPosition.getVelocity()-1>GameLoop.GRAVITY_MAX_VELOCITY_LEFT)
					newPosition.setVelocity(newPosition.getVelocity()-GameLoop.GRAVITY_ACC);
			} else {
				if (newPosition.getVelocity()>0)
					newPosition.setVelocity(newPosition.getVelocity()-GameLoop.GRAVITY_ACC);
				else if (newPosition.getVelocity()<0)
					newPosition.setVelocity(newPosition.getVelocity()+GameLoop.GRAVITY_ACC);
			}
		}
		// if GameObject has no collision with a ground ...
		else if (!checkCollisionGround(go, gState))
		{
			//System.out.println("fall!: "+go.getFallTime());
			// ... let him fall
			go.setFallTime(go.getFallTime()+1);			
			newPosition.setY(newPosition.getY() + Math.round(go.getFallTime() * GameLoop.GRAVITATION));	
		}
		// if GameObject has a collision with a ground ...
		else
		{
			//System.out.println("walk");
			go.setFallTime(0);
			// ... calculate position based on velocity
			if (acceleration>0){
				if (newPosition.getVelocity()+1<GameLoop.GRAVITY_MAX_VELOCITY_RIGHT)
					newPosition.setVelocity(newPosition.getVelocity()+1);
			} else if (acceleration<0) {
				if (newPosition.getVelocity()-1>GameLoop.GRAVITY_MAX_VELOCITY_LEFT)
					newPosition.setVelocity(newPosition.getVelocity()-1);
			} else {
				if (newPosition.getVelocity()>0)
					newPosition.setVelocity(newPosition.getVelocity()-1);
				else if (newPosition.getVelocity()<0)
					newPosition.setVelocity(newPosition.getVelocity()+1);
			}		
			go.setJumpTime(-1);
		}
		// set new X position
		newPosition.setX(newPosition.getX() + Math.round(newPosition.getVelocity()));
		return newPosition;
	}
	
	
	/**
	 * calculate a new Position and check if the Position is valid through checkCollision
	 * 
	 * @param go
	 * @param acceleration
	 * @param gState
	 * @return
	 */
	public boolean calcNewPosition (CollisionObjects go, float acceleration, GameState gState )
	{
		Position potentialPosition 	= calcPotentialNewPosition(go, acceleration, gState);
		
		CollisionObjects goTest = null;
		if (go instanceof Hank)
			goTest 	= new Hank(potentialPosition, "spriteKey", "myHank");
		
		if (go instanceof Opponent) 
			goTest 	= new Opponent(potentialPosition, "spriteKey", "myRat");
		
		//System.out.println("OldPos X:"+go.getPosition().getX() +" Y:"+go.getPosition().getY());
		//System.out.println("NewPos X:"+potentialPosition.getX()+" Y:"+potentialPosition.getY());

		AABB coll = new AABB();
		
		if (coll.checkCollision(goTest, gState)){
			//System.out.println("JA! CAPTAIN HANK HATTE RECHT");
			// if GameObject has collision set jumpTime to zero to stop 
			// the jump			
			
			// **************************************************************
			// FIXED BIG-BUG
			if (checkCollisionGround(goTest, gState)) {
				go.setFallTime(0);
				go.setJumpTime(-1);
			}
			// **************************************************************
			
			// if we are in jump, simulate the collision and negate the velocity
			if (!checkCollisionGround(go, gState)) {
				//System.out.println("SCHON WIEDER");
				go.setPosition(new Position(go.getPosition().getX(), 
												go.getPosition().getY(),
												go.getPosition().getWidth(),
												go.getPosition().getHeight(),
											   -go.getPosition().getVelocity()));
			
				// collision power
				if (go.getJumpTime()>=2)
					go.setJumpTime(go.getJumpTime()-2); 
			} else {				
				go.setJumpTime(-1);
				go.setFallTime(0);	
			}
			return false;
		}	
		else
		{		
			if (go instanceof Hank){
				int diffx = go.getPosition().getX() - potentialPosition.getX();
				if ((go.getPosition().getX() >= GameLoop.GRAVITY_CENTER_X-20)&&(go.getPosition().getX() <= GameLoop.GRAVITY_CENTER_X+20))
				{							
					if (((go.getPosition().getVelocity()>0) && (GameLoop.VIEW_START_X + GameLoop.VIEW_WIDTH + 5 <= GameLoop.LEVEL_START_X + GameLoop.LEVEL_WIDTH)) ||						 
						((go.getPosition().getVelocity()<0) && (GameLoop.VIEW_START_X - 5 >= 0))) {
						// we are on central position 				
						Vector<CollisionObjects> allCollisionObjects = gState.getCollisionObjects();			
						for (int i = 0; i<allCollisionObjects.size(); i++)
						{
							CollisionObjects co = allCollisionObjects.get(i);
							
							// ***************************************************************************
							// don't touch me!
							//if (!(co instanceof Hank)) {
							//	Position pos = allCollisionObjects.elementAt(i).getPosition();
							//	pos.setX(pos.getX()+diffx);						
							//	allCollisionObjects.elementAt(i).setPosition(pos);
							//}
							// ***************************************************************************
											
							Position pos2 = allCollisionObjects.elementAt(i).getPosition();
							if (co instanceof Hank) 
							{
								pos2.setVelocity(potentialPosition.getVelocity());
								pos2.setY(potentialPosition.getY());
							} else 
								pos2.setX(pos2.getX() + diffx);						
						}
						
						// translate the view also
						GameLoop.VIEW_START_X -= diffx;
						
						return true;
					} 
					else 
					{
						// we are not at the central position
						go.setPosition(potentialPosition);
						return true;
					}				
				} 
				else 
				{
					// we are not at the central position					
					if ((GameLoop.VIEW_START_X + potentialPosition.getX()+go.getPosition().getWidth() < GameLoop.LEVEL_START_X + GameLoop.LEVEL_WIDTH) &&
						(potentialPosition.getX()>0)) 
					{
						// new position set
						go.setPosition(potentialPosition);
						return true;
					} else 
						return false;
				}
			} 
			else 
			{
				// new position set
				go.setPosition(potentialPosition);
				
				// waypoint found?
				if (dist(potentialPosition, ((Opponent)go).getWayPoint()) <= 10){
					((Opponent)go).nextWayPoint();
					if (((Opponent)go).getWayPoint().getVelocity()>0){
						((AI)go).setLastState(((Characters)go).getMyState());
						((Characters)go).setMyState(GameLoop.Playerstate.GORIGHT);
					}
					else{
						((AI)go).setLastState(((Characters)go).getMyState());
						((Characters)go).setMyState(GameLoop.Playerstate.GOLEFT);
					}
				}
				return true;
			}
		}
	}
	
	private int dist(Position p1, Position p2)
	{
		int sum = Math.abs(p1.getX()-p2.getX());
		return sum;
	}
	
	/**
	 * check if the GameObject has a collision at the bottom (stand on something)
	 * 
	 * @param go
	 * @param gState
	 * @return
	 */
	private boolean checkCollisionGround(CollisionObjects go, GameState gState)
	{
		Position newP = new Position(go.getPosition());
		newP.setY(newP.getY()+2);				
		
		CollisionObjects goTest = null;
		
		if (go instanceof Hank)
			goTest = new Hank(newP, "spriteKey", "myHank");
		else if (go instanceof Opponent){
			//System.out.println("Oppi");
			goTest = new Opponent(newP, "spriteKey", "myOpp");
		}
		
		AABB coll = new AABB();
		
		if (coll.checkCollision(goTest, gState))
			return true;
		else
			return false;
	}
}
