package objects;

import java.awt.Image;
import java.io.IOException;

import utils.ResourceHelper;

public class GameObject implements IDrawable {
	/**
	 * gibt die linke Kante an
	 */
	protected int x;
	/**
	 * gibt die obere Kante an
	 */
	protected int y;
	/**
	 * das Bild vom Object
	 */
	protected Image pic;

	/**
	 * 
	 * @param x
	 *            - linke Kante des Objects
	 * @param y
	 *            - obere Kante des Objects
	 * @param pfad
	 *            - gibt an, aus welchem Ordner das Bild geladen wird
	 */
	public GameObject(final int x, final int y, final String pfad) throws IOException {
		this.x = x;
		this.y = y;
		pic = ResourceHelper.loadImage(pfad);
	}

	protected GameObject(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	/**
	 * gibt die X-Koordiante zurÃ¼ck
	 */
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	/**
	 * gibt die Y-Koordiante zurÃ¼ck
	 */
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	/**
	 * gibt das Bild zurÃ¼ck
	 */
	public Image getPic() {
		// TODO Auto-generated method stub
		return pic;
	}

}
