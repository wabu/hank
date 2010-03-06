package player;

public interface IControleable {
	/**
	 * animiert die implementierende Objektsteuerung, 
	 * das Objekt nach links laufen zu lassen
	 */
	void startMovingLeft();
	/**
	 * animiert die implementierende Objektsteuerung, 
	 * das Objekt nach rechts laufen zu lassen
	 */
	void startMovingRight();
	/**
	 * animiert die implementierende Objektsteuerung, 
	 * das Objekt bis zur Springhöhe nachoben springen zu lassen,
	 * anschließend wird es wieder herunter fallen
	 */
	void startJumping();
	/**
	 * stoppt die implementierende Objektsteuerung 
	 * beim nach links laufen lassen
	 */
	void stopMoveLeft();
	/**
	 * stoppt die implementierende Objektsteuerung 
	 * beim nach rechts laufen lassen
	 */
	void stopMoveRight();
}