package player;

import utils.Schlaf;
import world.GlobalWorld;

public class PlayerControleThread implements IControleable, Runnable {
	/**
	 * um den Sprung in der Luft zu vermeiden, muss mann auf die Position
	 * zugreifen können
	 */
	private final IMoveable player;

	public PlayerControleThread(final IMoveable playerposition) {
		super();
		player = playerposition;
		new Thread(this).start();
	}

	/**
	 * Is the Player jumping?
	 */
	private boolean jumping;
	/**
	 * Is the Player moving left?
	 */
	private boolean moveLeft;
	/**
	 * Is the Player moving right?
	 */
	private boolean moveRight;

	@Override
	public void startJumping() {
		if (GlobalWorld.getDistanceToGround(player.getX(), player.getY()) == 0) {
			jumping = true;
		}
	}

	@Override
	public void startMovingLeft() {
		moveLeft = true;
	}

	@Override
	public void startMovingRight() {
		moveRight = true;
	}

	@Override
	public void stopMoveLeft() {
		moveLeft = false;
	}

	@Override
	public void stopMoveRight() {
		moveRight = false;
	}

	@Override
	public void run() {
		while (GlobalWorld.isGameGoingOn()) {
			Schlaf.sleep(40);
			if (moveLeft) {
				player.moveLeft();
			}
			if (moveRight) {
				player.moveRight();
			}
			/**
			 * nur zu Testzwecken, überarbeiten!
			 */
			if (jumping) {
				player.jump();
				if (GlobalWorld.getDistanceToGround(player.getX(), player
						.getY()) >= 80) {
					jumping = false;
				}
			} else if (GlobalWorld.getDistanceToGround(player.getX(), player
					.getY()) > 0) {
				player.fall();

			}
		}
	}
}
