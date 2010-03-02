package gui;

import java.awt.Graphics;
import java.awt.event.KeyListener;

public interface IGuiable {
	/**
	 * animiert die Gui zum updaten der Grafiken
	 */
	void repaint();
	/**
	 * erlaubt den Zugriff von au�en
	 * @return gibt das Bild der Ausgabe zur�ck, 
	 * um darauf Ver�nderungen zu erm�glichen
	 */
	Graphics getGhostGraphics();
	/**
	 * F�gt der GUI einen KeyListener hinzu,
	 * @param kl - KEyListener, das hinzuzuf�gende KeyListenerObject
	 */
	void addKeyListener(KeyListener kl);
}
