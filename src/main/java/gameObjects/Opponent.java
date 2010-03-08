package gameObjects;

import java.util.Vector;
import physics.Position;
import Game.GameLoop;

public class Opponent extends AI{
	private enum AIstate 
	{
		IDLE, WALK, DIE, COLLIDE
	};
	@SuppressWarnings("unused")
	private AIstate state;
	private int wayPointIndex;
	private Vector<Position> myWayPoints;
	
	/**
	 * Constructor
	 * 
	 * @param pos
	 * @param name
	 */
	public Opponent(Position pos, String spriteKey, String name)
	{
		super(pos, spriteKey, name);
		myWayPoints = new Vector<Position>();
		state 		= AIstate.IDLE;
	}
	
	public static Opponent makeAI(int x, int y, float velocity )
	{
		return new Opponent(new Position(x, y, (int)(96*GameLoop.SCALEFACTOR), (int)(48*GameLoop.SCALEFACTOR), velocity), "Rat", "DummyRat");
	}
	
	public void insertWayPoint(Position p)
	{
		myWayPoints.add(p);
	}
	
	public Position getWayPoint()
	{
		return myWayPoints.get(wayPointIndex);
	}
	
	public void nextWayPoint()
	{
		wayPointIndex += 1;
		wayPointIndex %= myWayPoints.size();
	}
	
	public void startPatroullie(int index)
	{
		wayPointIndex = index;
		state = AIstate.WALK;		
	}
}
