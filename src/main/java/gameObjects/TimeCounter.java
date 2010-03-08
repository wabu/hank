package gameObjects;

import java.awt.*;
import java.awt.image.BufferedImage;

import physics.Position;

/**
 * This is the TimeCounter class. It can count Time
 * 
 * @since	02/05/2010
 * @version 02/12/2010
 * @author	Flo
 */
public class TimeCounter extends Counter{
	
	public TimeCounter(Position pos, String spriteKey)
	{
		super(pos, spriteKey);
	}
	
	/**
	 * creates a BufferedImage with Position - fillrect is a placeholder - and returns the BufferedImage
	 */
	public BufferedImage getImage(){
		BufferedImage bufImg = new BufferedImage(this.getPosition().getWidth(), this.getPosition().getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		Graphics localG = bufImg.getGraphics();
		localG.setColor(Color.green);
		localG.fillRect(getPosition().getX(), getPosition().getY(), getPosition().getWidth(), getPosition().getHeight());
		return bufImg;
	}
}
