package physics;

import gameObjects.CollisionObjects;
import gameState.GameState;

public interface Collision 
{
	public boolean checkCollision (CollisionObjects obj, GameState gState); 
}
