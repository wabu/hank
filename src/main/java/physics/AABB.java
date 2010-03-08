package physics;

import gameObjects.CollisionObjects;
import gameObjects.Hank;
import gameObjects.Opponent;
import gameState.GameState;
import java.util.Vector;
import java.awt.*;
/**
 * this class checks if two objects intersect
 * @since 02/05/2010
 * @version 02/05/2010 v0.1
 * @author Arne Balks, Florian Häckh
 *
 */
public class AABB implements Collision{
	
		//collide provides rectangles build from the x,y, height and width variable from the collisionobjects, it uses the interna rectangle version "intersect>" to test them for collision
	/**
	 * creation of the hitbox-recatngles
	 * @param CollisionObjects o1, CollisionObjects o2
	 * @return boolean
	 */
	private boolean collide (CollisionObjects o1, CollisionObjects o2){
		//	creation and names of both rectangles (ro1, ro2)	
		Rectangle ro1 = new Rectangle(o1.getPosition().getX(), o1.getPosition().getY(), o1.getPosition().getWidth(), o1.getPosition().getHeight());
		Rectangle ro2 = new Rectangle(o2.getPosition().getX(), o2.getPosition().getY(), o2.getPosition().getWidth(), o2.getPosition().getHeight());
		// test for intersect of both rectangles. If collision occurs return will be true otherwise false
		if (ro1.intersects(ro2))
			return true;
		else
			return false;
	}
	/**
	 * collision test for all objects
	 * @param CollisionObjects obj, GameState gState
	 * @return boolean
	 */
	public boolean checkCollision (CollisionObjects obj, GameState gState){
		//collisionObjects from gState are loaded into vector "callObjListe"
		Vector<CollisionObjects> collObjListe = gState.getCollisionObjects();
		
		//this happens via for-loop, that the vector-methode "size()" knows how much steps it has to pass
		for (int i = 0; i < collObjListe.size(); i++)
		{	
			if (obj instanceof Hank) 
			{
				//System.out.println("Hank found");
				if (collObjListe.elementAt(i) instanceof Hank) 
				{
					//System.out.println("Hank coll with Hank");
					continue;
				}
			}

			if (obj instanceof Opponent)
			{
				//System.out.println("Rat found");
				if (collObjListe.elementAt(i) instanceof Opponent) 
				{ 
					//System.out.println("Rat coll with Rat");
					continue;
				}
			}
				
			//collide methode testing all objects in a row
			if (collide(obj, collObjListe.elementAt(i)))
				return true;
		}
		
		return false;
	}
}
