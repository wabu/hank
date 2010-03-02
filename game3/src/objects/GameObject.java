package objects;

import java.awt.Image;

import utils.LoadHandler;

public class GameObject implements IDrawable{
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
	 * @param x - linke Kante des Objects
	 * @param y - obere Kante des Objects
	 * @param pfad - gibt an, aus welchem Ordner das Bild geladen wird
	 */
	public GameObject(int x, int y, String pfad) {
		this.x=x;
		this.y=y;
		pic=LoadHandler.loadPic(pfad);
	}
	@Override
	/**
	 * gibt die X-Koordiante zur�ck
	 */
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	/**
	 * gibt die Y-Koordiante zur�ck
	 */
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}
	@Override
	/**
	 * gibt das Bild zur�ck
	 */
	public Image getPic() {
		// TODO Auto-generated method stub
		return pic;
	}
	
	

}
