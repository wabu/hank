package world;


import java.awt.Graphics;

import controls.Keyboard;

import gui.Gui;
import gui.IGuiable;
import player.Player;
import utils.Schlaf;
import world.time.ITimer;
import world.time.Timer;

public class GlobalWorld {
	private static ITimer timer = new Timer();
	private static Player player = new Player(20,540,"Pics/Player/");
	private static IGuiable gui = new Gui("Hank",0,0,1000,700);
	private static Graphics gfx = gui.getGhostGraphics(); 
	public static void main(String[] args) {
		gui.addKeyListener(new Keyboard());
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(isGameGoingOn()){
				Schlaf.sleep(5);
				if(timer.getAkttime()%20==0)
				player.nextPic();
				
				gfx.clearRect(0,0,1000,700);
				gfx.drawImage(player.getPic(),player.getX(),player.getY(),null);
				gui.repaint();
				}
			}
		}).start();
	}
	public static int getDistanceToGround(int x, int y){
		System.out.println(player.getY());
		return 540-player.getY();
	}
	public static boolean isGameGoingOn(){
		return timer.isCountTime();
	}
	public static ITimer getTimer() {
		return timer;
	}

	public static Player getPlayer() {
		return player;
	}
	
}
