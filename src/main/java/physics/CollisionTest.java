/*package physics;

import gameObjects.Ground;
import gameObjects.Player;
import gameState.GameState;

public class CollisionTest {

	public static void main (String [] args) {
		GameState gs = new GameState();
		Player cptHank = new Player(new Position(100, 718, 20, 20, 0.0f), "Hank");
//		Player cptEvil = new Player(new Position(10, 10, 20, 20, 0.0f), "Evil");
		
		Ground[] g1 = new Ground[11];
		g1[0] = new Ground(new Position(0, 718, 100, 50, 0f), "Grass");
		g1[1] = new Ground(new Position(100, 718, 100, 50, 0f), "Grass");
		g1[2] = new Ground(new Position(200, 718, 100, 50, 0f), "Grass");
		g1[3] = new Ground(new Position(300, 718, 100, 50, 0f), "Grass");
		g1[4] = new Ground(new Position(400, 718, 100, 50, 0f), "Grass");
		g1[5] = new Ground(new Position(500, 718, 100, 50, 0f), "Grass");
		g1[6] = new Ground(new Position(600, 718, 100, 50, 0f), "Grass");
		g1[7] = new Ground(new Position(700, 718, 100, 50, 0f), "Grass");
		g1[8] = new Ground(new Position(800, 718, 100, 50, 0f), "Grass");
		g1[9] = new Ground(new Position(900, 718, 100, 50, 0f), "Grass");
		g1[10] = new Ground(new Position(300, 668, 100, 50, 0f), "Grass");
				
//		gs.insertGameObject(cptHank);
		
		for (int i = 0; i < 11; i++)
		{
			gs.insertGameObject(g1[i]);
		}
		
//		gs.insertGameObject(g1);
		AABB aabb = new AABB();
		if (aabb.checkCollision(cptHank, gs))
			System.out.println("Kollision Ja!");
		else System.out.println("Kollision Nein!");
	}
}
*/