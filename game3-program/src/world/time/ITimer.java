package world.time;

public interface ITimer {
	/**
	 * @return gibt aktuelle Zeit zur�ck, 1 Zeiteinheit = 5ms
	 */
	public int getAkttime();
	/**
	 * z�hlt der Z�hler weiter?
	 */
	public boolean isCountTime();
	/**
	 * stopt den Z�hler
	 */
	public void stopCountTime();

}
