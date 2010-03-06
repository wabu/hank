package objects;

import java.awt.Image;
import java.io.IOException;
import java.util.List;

import utils.ResourceHelper;

public class GameAnimatedObject extends GameObject {
	/**
	 * Vector<Image> pics beinhaltet alle Bilder
	 */
	private final List<Image> pics;

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
	public GameAnimatedObject(final int x, final int y, final String pfad) throws IOException {
		super(x, y);
        pics = ResourceHelper.loadImageList(pfad);
	}

	protected GameAnimatedObject(final int x, final int y) {
		super(x, y);
        pics = null;
	}
}
