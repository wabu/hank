package player;

import objects.IPositionable;

public interface IMoveable extends IPositionable {
	/**
	 * jump Up with speed of Object
	 */
	void jump();

	/**
	 * fell down with speed of Object
	 */
	void fall();

	/**
	 * move left with speed of Object
	 */
	void moveLeft();

	/**
	 * move right with speed of Object
	 */
	void moveRight();

	/**
	 * sets the Speed of Object
	 */
	void setSpeed(int speed);

	/**
	 * returns the Speed of Object
	 */
	int getSpeed();
}
