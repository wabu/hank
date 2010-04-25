package de.javauni.jarcade.impl.view.gui;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class testJButton {
	public static void main(String[] args) throws IOException {
		OutputImpl impl = new OutputImpl("test", 0, 0, 500, 600);
		JButton btn = new JButton("Test",new ImageIcon("test2.jpg"));
		btn.setSize(200, 300);
		btn.setLocation(100, 100);
		btn.setVisible(true);
		impl.add(btn);
	}
}
