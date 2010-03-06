package animation;

import java.awt.Image;
import java.util.Vector;

import utils.LoadHandler;
import utils.Schlaf;

public class Animation implements IAnimation {

	private final Vector<Image> pics = new Vector<Image>();
	private int aktnr = -1;
	private int speed;

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Animation(int speed, final String pfad) {
		this.speed = speed;
		LoadHandler.loadPics(pfad, pics);
	}

	public static void main(final String[] args) {
		final IAnimation ani = new Animation(5, "Pics\\Player\\");
		for (int i = 0; i < 25; i++) {
			System.out.println(ani.getNextPicture());
		}
	}

	@Override
	public Image getNextPicture() {
		Schlaf.sleep(speed);
		aktnr = (aktnr + 1) % ((pics.size() * speed));
		return pics.get(aktnr / speed);
	}
}
