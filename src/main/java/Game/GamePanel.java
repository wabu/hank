package Game;

import gameObjects.BackgroundObjects;
import gameObjects.CollisionObjects;
import gameObjects.InterfaceObjects;
import gameState.GameState;
import graphics.Sprites;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * this class draws the objects to the panel
 * 
 * @see		GameState
 * @since	02/11/2010
 * @version	02/11/2010 v0.2
 * @author 	Arne Balks, Eugen Belozerov, Mirko Berns-Relotic, Marco Block-Berlitz
 *
 */

@SuppressWarnings("serial")
public class GamePanel extends JPanel
{
	private GameState myGState;
	private Sprites sprites;
	
	/**
	 * Constructor. The size is being set and gamestate is fetched
	 * @param w the width of the panel
	 * @param h the height of the panel
	 * @param gState the gameState object that will be used
	 */
	public GamePanel(int w, int h, GameState gState, Sprites sprites) 
	{
		this.setSize(w, h);
		myGState = gState;
		this.sprites = sprites;
	}
	
	/**
	 * overriding the update method
	 */
	@Override
	public void update(Graphics g)
	{
	}
	/**
	 * overriding the paintComponent method. The games rendering is being controlled here
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.white);
		g.fillRect(0, 0, GameLoop.PANEL_WIDTH, GameLoop.PANEL_HEIGHT);
		
		BufferedImage image;
		
		InterfaceObjects iO;
		CollisionObjects cO;
		BackgroundObjects bO;
		
		// readout all BackgroundObjects...
		for (int i = 0; i < myGState.getBackgroundObjectSize(); i++) {
			bO = myGState.getBackgroundObject(i);
			
			if (bO==null)
				continue;
			
			image = sprites.getObject(bO);
			// draw CollisionObject on Panel
			try {
				g.drawImage(image.getScaledInstance((int)(bO.getPosition().getWidth()*GameLoop.SCALEFACTOR), (int)(bO.getPosition().getHeight()*GameLoop.SCALEFACTOR), Image.SCALE_FAST), (int)(bO.getPosition().getX()*GameLoop.SCALEFACTOR), (int)(bO.getPosition().getY()*GameLoop.SCALEFACTOR), null);
			} catch (Exception e) {}
		}
						
		// GameObjects 
		for (int i = 0; i < myGState.getCollisionObjectSize(); i++) {
			cO = myGState.getCollisionObject(i);
			
			if (cO==null)
				continue;
			
			image = sprites.getObject(cO);
			// draw CollisionObject on Panel
			try {
				g.drawImage(image.getScaledInstance((int)(cO.getPosition().getWidth()*GameLoop.SCALEFACTOR), (int)(cO.getPosition().getHeight()*GameLoop.SCALEFACTOR), Image.SCALE_FAST), (int)(cO.getPosition().getX()*GameLoop.SCALEFACTOR), (int)(cO.getPosition().getY()*GameLoop.SCALEFACTOR), null);
			} catch (Exception e) {}
		}

		//		System.out.println("SIZE: "+myGState.getInterfaceObjectSize());
		for (int i = 0; i < myGState.getInterfaceObjectSize(); i++) {
			iO = myGState.getInterfaceObject(i);
			
			if (iO==null)
				continue;
			
			System.out.println("Objekt: " + i + " X:"+iO.getPosition().getX()+" Y:"+iO.getPosition().getY());
			// draw InterfaceObjects on Panel
			g.drawImage(iO.getImage().getScaledInstance((int)(iO.getPosition().getWidth()*GameLoop.SCALEFACTOR), (int)(iO.getPosition().getHeight()*GameLoop.SCALEFACTOR), Image.SCALE_FAST), (int)(iO.getPosition().getX()*GameLoop.SCALEFACTOR), (int)(iO.getPosition().getY()*GameLoop.SCALEFACTOR), (int)(iO.getPosition().getWidth()*GameLoop.SCALEFACTOR), (int)(iO.getPosition().getHeight()*GameLoop.SCALEFACTOR), this);
		}
		g.setColor(Color.black);
		// draw FPS-display
		g.drawString("FPS: " + Long.toString(myGState.getFps()), (int)(50*GameLoop.SCALEFACTOR), (int)(50*GameLoop.SCALEFACTOR));

		// if mode is change, we want to print text informations...
		if (!(myGState.getMsg().equals("")))
		{
			g.setColor(new Color(0, 0, 0, 200));
			g.fillRect(0, 0, 1024, 768);
			g.setColor(Color.white);
			g.drawString(myGState.getMsg(), 450, 300);
		}		
	}
	
}
