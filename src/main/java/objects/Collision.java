package objects;

import java.awt.Rectangle;

public class Collision {

	public static boolean collidate(final ICollisionable col1,
			final ICollisionable col2) {
		final Rectangle r1 = new Rectangle(col1.getX(), col1.getY(), col1
				.getHeight(), col1.getWidth());
		final Rectangle r2 = new Rectangle(col2.getX(), col2.getY(), col2
				.getHeight(), col2.getWidth());
		return r1.intersects(r2);
	}
}
