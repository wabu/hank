package gui;

import java.awt.Graphics;
import java.awt.event.KeyListener;

public interface IGuiable {
	/**
	 * animiert die Gui zum updaten der Grafiken
	 */
	void repaint();
	/**
	 * erlaubt den Zugriff von außen
	 * @return gibt das Bild der Ausgabe zurück, 
	 * um darauf Veränderungen zu ermöglichen
	 */
	Graphics getGhostGraphics();
	/**
	 * Fügt der GUI einen KeyListener hinzu,
	 * @param kl - KEyListener, das hinzuzufügende KeyListenerObject
	 */
	void addKeyListener(KeyListener kl);
}
