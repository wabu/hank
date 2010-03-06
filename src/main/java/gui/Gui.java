package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import utils.MyFrame;

/**
 * 
 * @author Michael Kmoch
 * @category Gui Diese GUI ist durch ein im Endeffekt JFrame realisiert, welches
 *           jediglich durch den Constructor von MYFrame spezialisert wird.
 *           Unsere GUI dient jediglich als Ausgabe und besitzt keine Logik,
 *           damit benötigen wir eine Schnittstelle, die wir hier als IGuiable
 *           benannt haben
 */
public class Gui extends MyFrame implements IGuiable {
	private static final long serialVersionUID = -9173088758061285566L;
	/**
	 * Wir verwenden ghost-imaging bzw. doublebuffering
	 */
	private final Image img_ghost;
	private final Graphics g_ghost;

	/**
	 * 
	 * @param fenstername
	 *            - Der Name der oben in der Titelleiste angezeigt wird
	 * @param x
	 *            - Die linke Kante, an der das Fenster beim Aufruf starten soll
	 * @param y
	 *            - Die obere Kante, an der das Fenster beim Aufruf starten soll
	 * @param width
	 *            - Die Breite mit der das Fenster starten soll
	 * @param height
	 *            - Die Höhe mit der das Fenster starten soll
	 */
	public Gui(final String fenstername, final int x, final int y,
			final int width, final int height) {
		super(fenstername, x, y, width, height);
		img_ghost = createImage(width, height);
		g_ghost = img_ghost.getGraphics();
		g_ghost.setXORMode(Color.white);

	}

	@Override
	/**
	 * @return gibt das Grafikobjekt zurück auf dem, 
	 * von außen gemalt werden kann
	 */
	public Graphics getGhostGraphics() {
		return g_ghost;
	}

	@Override
	/**
	 * überschreibt die Paint-Methode, nötig für Double-buffering
	 */
	public void paint(final Graphics g) {
		update(g);
	}

	@Override
	/**
	 * überschreibt die Update-Methode, nötig für Double-buffering
	 */
	public void update(final Graphics g) {
		g.drawImage(img_ghost, 0, 0, Color.white, this);
	}
}
