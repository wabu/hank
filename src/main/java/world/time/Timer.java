package world.time;

public class Timer implements Runnable, ITimer {
	private int aktTime = 0;
	private boolean countTime = true;

	public Timer() {
		new Thread(this).start();
	}

	@Override
	/**
	 * @return gibt aktuelle Zeit zurück, 1 Zeiteinheit = 5ms
	 */
	public int getAkttime() {
		return aktTime;
	}

	@Override
	/**
	 * is the Timer ticking?
	 */
	public boolean isCountTime() {
		return countTime;
	}

	@Override
	/**
	 * stops counting
	 */
	public void stopCountTime() {
		countTime = false;
	}

	@Override
	public void run() {
		while (countTime) {
			aktTime++;
			try {
				Thread.sleep(5);
			} catch (final InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
