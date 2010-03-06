package world;

import gui.Gui;
import gui.IGuiable;

import java.awt.Graphics;

import player.Player;
import world.time.ITimer;
import world.time.Timer;
import controls.Keyboard;

public class GlobalWorld {
	private static ITimer timer = new Timer();
	private static Player player = new Player(20, 540, "Pics\\Player\\");
	private static IGuiable gui = new Gui("Hank", 0, 0, 1000, 700);
	private static Graphics gfx = gui.getGhostGraphics();

	public static void main(final String[] args) {
		gui.addKeyListener(new Keyboard());
		new GameLoop(player, gui, gfx).start();

	}

	/**
	 * @deprecated muss überarbeitet werden,
	 * aber noch gibt es keine Kollsisionen 
	 * @param x
	 *            X-Koord vom abgefragten Objekt
	 * @param y
	 *            Y-Koord vom abgefragten Objekt
	 * @return die Distanz zwichen Boden und Objekt
	 */
	public static int getDistanceToGround(final int x, final int y) {
		return 540 - player.getY();
	}
	/**
	 * 
	 * @return gibt true zurück, wenn das Spiel weiter läuft,
	 * alle Threads die persistent laufen, hören auf diesen Counter
	 * z.B. PlayerControleThread
	 */
	public static boolean isGameGoingOn() {
		return timer.isCountTime();
	}
	/**
	 * 
	 * @return gibt 
	 */
	public static int getTime() {
		return timer.getAkttime();
	}

	public static Player getPlayer() {
		return player;
	}

}
