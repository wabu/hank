package world.time;

public class Timer implements Runnable, ITimer{
	private int aktTime=0; 
	private boolean countTime=true;
	public Timer() {
		new Thread(this).start();
	}
	@Override
	public int getAkttime() {
		return aktTime;
	}
	@Override
	public boolean isCountTime() {
		return countTime;
	}
	@Override
	public void stopCountTime(){
		countTime=false;
	}
	@Override
	public void run() {
		while(countTime){
			aktTime++;
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
