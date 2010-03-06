package animation;

import java.awt.Image;

public interface IAnimation {
	/**
	 * 
	 * @return gibt das nächst folgende Bild mit Verzögerung durch speed zurück
	 */
	Image getNextPicture();
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed);
}
