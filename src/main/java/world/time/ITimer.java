package world.time;

public interface ITimer {
	/**
	 * @return gibt aktuelle Zeit zurück, 1 Zeiteinheit = 5ms
	 */
	public int getAkttime();
	/**
	 * zählt der Zähler weiter?
	 */
	public boolean isCountTime();
	/**
	 * stopt den Zähler
	 */
	public void stopCountTime();

}
