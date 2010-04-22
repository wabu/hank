package output;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class OutputImpl extends MyFrame implements Output{ 

	private final Image img_ghost;
	private final Graphics g_ghost;
	private final MyPanel panel;
	@Inject OutputImpl(	@Named("name") String fenstername,
						@Named("x") Integer x,
						@Named("y") Integer y,
						@Named("width") Integer width,
						@Named("height") Integer height) 
	{
		super(fenstername, x, y, width, height);
		panel=new MyPanel(x, y, width, height);
		this.add(panel);
		img_ghost = panel.createImage(width, height);
		g_ghost = img_ghost.getGraphics();
	}

	@Override
	/**
	 * @return gibt das Grafikobjekt zurueck auf dem 
	 * von aussen gemalt werden kann
	 */
	public Graphics getGhostGraphics() {
		return g_ghost;
	}
	
	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		
		paint(getGraphics());
		super.repaint();
	}
	@Override
	/**
	 * ueberschreibt die Paint-Methode, noetig fuer Double-buffering
	 */
	public void paint(final Graphics g) {
		update(g);
	}

	@Override
	/**
	 * ueberschreibt die Update-Methode, noetig fuer Double-buffering
	 */
	public void update(final Graphics g) {
		g.drawImage(img_ghost, 0, 0, this);
	}

	@Override
	public void clear() {
		g_ghost.clearRect(0, 0, getWidth(), getHeight());
	
	}
}