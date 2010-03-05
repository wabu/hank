package player;

import objects.IPositionable;


public interface IControleable extends IPositionable {
	/**
	 * Direkter Zugriff auf x,y Koordinaten
	 */
	void setX(int x);
	void setY(int y);
	
	
	void moveLeft();
	void moveRight();
	void jump();
	void stopMoveLeft();
	void stopMoveRight();
	void stopJumping();
	boolean isMovingLeft();
	boolean isMovingRight();
	boolean isJumping();
}
