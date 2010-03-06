package controls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import player.IControleable;
import world.GlobalWorld;

/**
 * @author Michael Kmoch
 * @category controls/Keyboard @ Diese Klasse nimmt die TastenEvents entgegen,
 *           und spricht die jeweilige Operation der Spiellogik an
 */

public class Keyboard extends KeyAdapter {

	/**
	 * @since 20.02.2010 Dieses Interface ist direkt mit dem Spieler1 gekoppelt,
	 *        soll heißen die Klasse Playercontrole vom Player1 implementiert
	 *        dieses Interface
	 */
	private final IControleable controls = GlobalWorld.getPlayer()
			.getControle();

	@Override
	/**
	 * starts equivalent move
	 * 38=KEY_UP
	 * 37=KEY_LEFT
	 * 39=KEY_RIGHT 
	 */
	public void keyPressed(final KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case 'W':
		case 38:
			controls.startJumping();
			break;
		case 'A':
		case 37:
			controls.startMovingLeft();
			break;
		case 'D':
		case 39:
			controls.startMovingRight();
			break;
		}
		arg0.consume();
	}

	@Override
	/**
	 * if KeyReleased stop equivalent Move
	 * 37 = KEY_LEFT
	 * 39 = KEY_RIGHT
	 */
	public void keyReleased(final KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case 'A':
		case 37:
			controls.stopMoveLeft();
			break;
		case 'D':
		case 39:
			controls.stopMoveRight();
			break;
		}
		arg0.consume();
	}
}
