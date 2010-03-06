package animation;

import java.awt.Image;
import java.io.IOException;
import java.util.List;

import utils.ResourceHelper;
import utils.Schlaf;

public class Animation implements IAnimation {

	private final List<Image> pics;
	private int aktnr = -1;
	private int speed;

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Animation(int speed, final String pfad) throws IOException {
		this.speed = speed;
		pics = ResourceHelper.loadImageList(pfad);
	}

	public static void main(final String[] args) throws IOException {
		final IAnimation ani = new Animation(5, "Pics/Player/mario%02d.jpg");
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
