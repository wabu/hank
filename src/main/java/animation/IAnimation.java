package animation;

import java.awt.Image;

public interface IAnimation {
	/**
	 * 
	 * @return gibt das n�chst folgende Bild mit Verz�gerung durch speed zur�ck
	 */
	Image getNextPicture();
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed);
}
