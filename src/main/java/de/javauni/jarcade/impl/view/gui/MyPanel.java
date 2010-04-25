package de.javauni.jarcade.impl.view.gui;

import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyPanel extends JPanel{

	public MyPanel(Integer x, Integer y, Integer width, Integer height) {
		this.setSize(width, height);
		setVisible(true);
		setPreferredSize(new Dimension(width, height));
		setLayout(null);
		this.setLocation(x, y);
	}

}
