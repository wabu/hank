package objects;

import java.awt.Image;
import java.util.Vector;

import utils.LoadHandler;

public class GameAnimatedObject extends GameObject implements IAnimationable{
	/**
	 * Vector<Image> pics beinhaltet alle Bilder
	 */
	private Vector<Image> pics = new Vector<Image>();
	/**
	 * gibt immer an, welches das aktuelle Bild ist
	 */
	private int aktPic=0;
	/**
	 * 
	 * @param x - linke Kante des Objects
	 * @param y - obere Kante des Objects
	 * @param pfad - gibt an, aus welchem Ordner die Bilder geladen werden
	 */
	public GameAnimatedObject(int x, int y,String pfad) {
		super(x, y, pfad);
		LoadHandler.loadPics(pfad, pics);
	}
	
	@Override
	/**
	 * setzt das aktuelle Bild auf das nächste
	 * Funktionsweise
	 * Modulo size, führt dazu das das Zählinterval
	 * auf die Anzahl der size begrenz ist, d.h.
	 * z.B. 3 Bilder(0,1,2): 0,1,2,0,1,2,0,1,2
	 */
	public void nextPic() {
		aktPic++;
		aktPic=aktPic%pics.size();
		pic=pics.get(aktPic);
	}
	
}
