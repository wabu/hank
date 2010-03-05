package player;

import utils.Schlaf;
import world.GlobalWorld;

public class PlayerMovingThread extends Thread {
	private IControleable player;

	public PlayerMovingThread(IControleable player) {
		this.player = player;
	}

	@Override
	public void run() {
		super.run();
		while (GlobalWorld.isGameGoingOn()) {
			Schlaf.sleep(20);
			// System.out.println("X:"+player.getX()+"Y:"+player.getY());

			if (player.isMovingLeft())
				player.setX(player.getX() - 5);
			if (player.isMovingRight())
				player.setX(player.getX() + 5);
			if (player.isJumping()){
				player.setY(player.getY()-5);
				if(GlobalWorld.getDistanceToGround(player.getX(), player.getY()) >= 80){
					player.stopJumping();
					
				}
			}else if(GlobalWorld.getDistanceToGround(player.getX(), player.getY()) > 0){
				player.setY(player.getY()+5);
				
			}
		}
	}
}
