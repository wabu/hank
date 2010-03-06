package objects;

import java.awt.Image;
import java.util.Vector;

import utils.LoadHandler;

public class GameAnimatedObject extends GameObject {
	/**
	 * Vector<Image> pics beinhaltet alle Bilder
	 */
	private final Vector<Image> pics = new Vector<Image>();

	/**
	 * gibt immer an, welches das aktuelle Bild ist
	 */

	/**
	 * 
	 * @param x
	 *            - linke Kante des Objects
	 * @param y
	 *            - obere Kante des Objects
	 * @param pfad
	 *            - gibt an, aus welchem Ordner die Bilder geladen werden
	 */
	public GameAnimatedObject(final int x, final int y, final String pfad) {
		super(x, y, pfad);
		LoadHandler.loadPics(pfad, pics);
	}

	public GameAnimatedObject(final int x, final int y) {
		super(x, y);
	}
}
