package output;

import com.google.inject.Guice;
import com.google.inject.Injector;


public class OutputTest {
	public static void main(String[] args) {
		Injector inj = Guice.createInjector(new OutputModule());
		Output out = inj.getInstance(OutputImpl.class);
		out.clear();
//		out.getGhostGraphics()
		out.getGhostGraphics().fillRect(50,50,100,100);
		out.repaint();
		
	}
}
