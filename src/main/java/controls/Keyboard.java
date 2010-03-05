package controls;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import player.IControleable;
import world.GlobalWorld;

/**
 * @author Michael Kmoch
 * @category controls/Keyboard
 * @
 * Diese Klasse nimmt die TastenEvents entgegen, 
 * und spricht die jeweilige Operation der Spiellogik an
 */

public class Keyboard extends KeyAdapter{
	
	/**
	 * @since 20.02.2010
	 * Dieses Interface ist direkt mit dem Spieler1 gekoppelt,
	 * soll heiﬂen die Klasse Spieler implementiert dieses Interface
	 */	
	private IControleable controls = GlobalWorld.getPlayer();
	
	public void keyPressed(KeyEvent arg0) {
		performAction(arg0);
		arg0.consume();
	}
	private void performAction(KeyEvent arg0) {
		switch(arg0.getKeyCode()){
		case 'W': case 38: controls.jump();  break;
//		case 'S': case 40: controls.moveDown(); break;
		case 'A': case 37: controls.moveLeft(); break;
		case 'D': case 39: controls.moveRight(); break;
		}	
	}
	private void stopAction(KeyEvent arg0) {
		switch(arg0.getKeyCode()){
//		case 'W': case 38: controls.stopJumping();  break;
//		case 'S': case 40: controls.stopMovingDown(); break;
		case 'A': case 37: controls.stopMoveLeft(); break;
		case 'D': case 39: controls.stopMoveRight(); break;
		}	
	}
	public void keyReleased(KeyEvent arg0) {
		stopAction(arg0);
		arg0.consume();
	}
}
