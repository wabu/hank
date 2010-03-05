package world;

public class Init {
	
	
	public static void main(String[] args) throws InterruptedException {
		GlobalWorld world = new GlobalWorld();
		
		while(GlobalWorld.getTimer().getAkttime()<100){
			Thread.sleep(20);
			System.out.println(GlobalWorld.getTimer().getAkttime());
		}
		GlobalWorld.getTimer().stopCountTime();
		
	}
}
