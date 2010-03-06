package world;

import gui.IGuiable;

import java.awt.Graphics;

import objects.IDrawable;
import utils.Schlaf;

public class GameLoop extends Thread {
	private final IDrawable player;
	private final Graphics gfx;
	private final IGuiable gui;

	public GameLoop(final IDrawable player, final IGuiable gui,
			final Graphics gfx) {
		super();
		this.player = player;
		this.gfx = gfx;
		this.gui = gui;
	}

	@Override
	public void run() {
		while (GlobalWorld.isGameGoingOn()) {
			Schlaf.sleep(5);
			gfx.clearRect(0, 0, 1000, 700);
			gfx.drawImage(player.getPic(), player.getX(), player.getY(), null);
			gui.repaint();
		}
	}
}
