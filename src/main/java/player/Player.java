package player;

import objects.GameAnimatedObject;
import world.GlobalWorld;

public class Player extends GameAnimatedObject 
				implements IControleable
{
	private boolean jumping,moveLeft,moveRight;
	public Player(int x, int y, String pfad) {
		super(x, y, pfad);
		new PlayerMovingThread(this).start();
	}
	@Override
	public boolean isJumping() {
		return jumping;
	}
	@Override
	public boolean isMovingLeft() {
		return moveLeft;
	}
	@Override
	public boolean isMovingRight() {
		return moveRight;
	}
	@Override
	public void jump() {
		if(GlobalWorld.getDistanceToGround(x, y)==0){
			jumping=true;	
		}
	}
	@Override
	public void stopJumping() {
		jumping=false;	
	}
	@Override
	public void moveLeft() {
			moveLeft=true;	
	}
	@Override
	public void moveRight() {
			moveRight=true;
	}
	@Override
	public void stopMoveLeft() {
		moveLeft=false;	
	}
	@Override
	public void stopMoveRight() {
		moveRight=false;	
	}
	@Override
	public void setX(int x) {
		this.x=x;	
	}
	@Override
	public void setY(int y) {
		this.y=y;
	}
}
