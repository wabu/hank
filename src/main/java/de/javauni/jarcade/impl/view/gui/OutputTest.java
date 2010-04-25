package de.javauni.jarcade.impl.view.gui;

import java.awt.Color;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.javauni.jarcade.impl.OutputModule;
import de.javauni.jarcade.view.render.Output;


public class OutputTest {
	public static void main(String[] args) throws InterruptedException {
		Injector inj = Guice.createInjector(new OutputModule());
		Output out = inj.getInstance(OutputImpl.class);
		out.clear();
//		out.getGhostGraphics()
		for(int i=0; i<1000; i++){
			Thread.sleep(1);
			out.getGhostGraphics().setColor(Color.black);
			out.getGhostGraphics().fillRect(50,50,100,100);
			out.repaint();
			Thread.sleep(1);
			out.getGhostGraphics().setColor(Color.red);
			out.getGhostGraphics().fillRect(50,50,100,100);
			out.repaint();
		}
//		out.clear();
//		out.repaint();
		
	}
}
