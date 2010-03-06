package player;

import java.awt.Image;
import java.io.IOException;

import objects.GameAnimatedObject;
import objects.ICollisionable;
import animation.Animation;
import animation.IAnimation;

public class Player extends GameAnimatedObject implements IMoveable,
		ICollisionable {

	private final IAnimation animation;
	private final IControleable controle = new PlayerControleThread(this);
	private int speed = 5;

	public Player(final int x, final int y, final String pfad) throws IOException {
		super(x, y);
		animation = new Animation(20, pfad);
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed
	 *            the speed to set
	 */
	public void setSpeed(final int speed) {
		this.speed = speed;
	}

	@Override
	/**
	 * @return - gibt das nächste Bild der Animation zurück
	 */
	public Image getPic() {
		return animation.getNextPicture();
	}

	/**
	 * @return gibt ein Controleable Interface zurück, welches Methoden zum
	 *         Starten von Bewegungen dient, aber kein direkten Zugriff erlaubt
	 */
	public IControleable getControle() {
		return controle;
	}

	@Override
	public void jump() {
		y -= speed;
	}

	@Override
	public void moveLeft() {
		x -= speed;
	}

	@Override
	public void moveRight() {
		x += speed;
	}

	@Override
	public void fall() {
		y += speed;
	}

	@Override
	public int getHeight() {
		return 80;
	}

	@Override
	public int getWidth() {
		return 40;
	}
}
