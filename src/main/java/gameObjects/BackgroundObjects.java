package gameObjects;

import Game.GameLoop;
import physics.Position;

/**
 * All backgroundObjects are based on this class. BackgroundObjects have no collision
 * 
 * @since	02/05/2010
 * @version	02/12/2010
 * @author	Flo
 */
public class BackgroundObjects extends GameObject{
	int layer;
	
	/**
	 * constructor
	 * 
	 * @param pos
	 * @param spriteKey
	 * @param layer
	 */
	public BackgroundObjects(Position pos, String spriteKey, int layer)
	{
		super(pos, spriteKey);
		this.layer = layer;
	}

	public static BackgroundObjects makeBackground(int x, int y)
	{
		return new BackgroundObjects(new Position(x, y, (int)(1024*GameLoop.SCALEFACTOR), (int)(750*GameLoop.SCALEFACTOR), 0f), "BG01", 0);
	}
	
	public int getLayer()
	{
		return layer;
	}
}

