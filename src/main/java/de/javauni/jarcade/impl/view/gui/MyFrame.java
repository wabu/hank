package de.javauni.jarcade.impl.view.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MyFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2715856747193391407L;

	public MyFrame(final String fenstername, final int x, final int y,
			final int width, final int height) {
		super(fenstername);
		this.setSize(width, height);
		setVisible(true);
		setPreferredSize(new Dimension(width, height));
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(x, y);

	}
}
