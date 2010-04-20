package output;

import java.awt.Graphics;

public interface Output {

	void repaint();
	Graphics getGhostGraphics();
	void clear();
}
