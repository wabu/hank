package gameState;

import gameObjects.BackgroundObjects;
import gameObjects.CollisionObjects;
import gameObjects.InterfaceObjects;

import java.util.Vector;

public class GameState 
{
	private long fps;
	private Vector<CollisionObjects> allCollisionObjectsInVector;
	private Vector<InterfaceObjects> allInterfaceObjectsInVector;
	private Vector<BackgroundObjects> allBackgroundObjectsInVector;
	private String msg;
	private boolean isPaused;
	private int life, points;
	
	public GameState()
	{
		allCollisionObjectsInVector 	= new Vector<CollisionObjects>();
		allInterfaceObjectsInVector 	= new Vector<InterfaceObjects>();
		allBackgroundObjectsInVector 	= new Vector<BackgroundObjects>();
		reset();
	}

	public void reset(){
		allCollisionObjectsInVector.clear();
		allInterfaceObjectsInVector.clear();
		allBackgroundObjectsInVector.clear();
		msg    		= "";
		fps 		= 0;
		points 		= 0;
		life		 = 3;
		setPaused(false);
	}

	// ***************************************************************
	// Methods for the gameloop
	public void setFps(long fps)
	{
		this.fps = fps;
	}
	// ***************************************************************
	
	// ***************************************************************
	// parameters relevant for GUI
	public long getFps()
	{
		return fps;
	}
	
	public Vector<InterfaceObjects> getInterfaceObjects()
	{
		return allInterfaceObjectsInVector;
	}
	// ***************************************************************
	
	public void insertInterfaceObject (InterfaceObjects io)
	{
		allInterfaceObjectsInVector.add(io);
	}	
	//****************************************************************
	
	public Vector<BackgroundObjects> getBackgroundObjects()
	{
		return allBackgroundObjectsInVector;
	}	
	
	public void insertBackgroundObject (BackgroundObjects bo)
	{
		allBackgroundObjectsInVector.add(bo);
	}
	
	public void insertGameObject (CollisionObjects go)
	{
		allCollisionObjectsInVector.add(go);
	}
	
	public Vector<CollisionObjects> getCollisionObjects()
	{
		return allCollisionObjectsInVector;
	}
	
	public int getCollisionObjectSize()
	{
		return allCollisionObjectsInVector.size();
	}
	
	public int getInterfaceObjectSize()
	{
		return allInterfaceObjectsInVector.size();
	}
	
	public int getBackgroundObjectSize()
	{
		return allBackgroundObjectsInVector.size();
	}
	
	public CollisionObjects getCollisionObject(int index)
	{
		if ((index>=0)&&(index<allCollisionObjectsInVector.size()))
			return allCollisionObjectsInVector.elementAt(index);
		return null;
	}
	
	public InterfaceObjects getInterfaceObject(int index)
	{
		if ((index>=0)&&(index<allInterfaceObjectsInVector.size()))
			return allInterfaceObjectsInVector.elementAt(index);
		return null;
	}
	
	public BackgroundObjects getBackgroundObject(int index)
	{
		if ((index>=0)&&(index<allBackgroundObjectsInVector.size()))
			return allBackgroundObjectsInVector.elementAt(index);
		return null;
	}
	
	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	public String getMsg() 
	{
		return msg;
	}
	
	public void clearMsg()
	{
		this.msg = "";
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPoints() {
		return points;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getLife() {
		return life;
	}	
}
