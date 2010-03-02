package utils;
import java.awt.Dimension;

import javax.swing.JFrame;



public class MyFrame extends JFrame {

	public MyFrame(String fenstername,int x, int y, int width, int height) {
		super(fenstername);
		this.setSize(width, height);
		this.setVisible(true);
		this.setPreferredSize(new Dimension(width,height));
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(x, y);
		
	}
}
