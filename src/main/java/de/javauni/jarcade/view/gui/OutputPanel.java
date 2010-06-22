package de.javauni.jarcade.view.gui;

import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OutputPanel extends JPanel{

	public OutputPanel(Integer x, Integer y, Integer width, Integer height) {
		this.setSize(width, height);
		setVisible(true);
		setPreferredSize(new Dimension(width, height));
		setLayout(null);
		this.setLocation(x, y);
	}

}
