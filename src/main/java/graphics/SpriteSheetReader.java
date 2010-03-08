package graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SpriteSheetReader
{
		// Übergeben wird das Bild, wie viele Spalten das Sprite Sheet hat und vie viele Reihen es hat.
		public static BufferedImage[] splitImage(BufferedImage img, int cols, int rows)
		{
			int w = img.getWidth()/cols;
			int h = img.getHeight()/rows;
			int num = 0;
			
			BufferedImage character[] = new BufferedImage[w*h];
			// Die Aufspaltung erfolgt durch zwei For-Schleifen
			for(int y = 0; y < rows; y++)
			{
				for(int x = 0; x < cols; x++)
				{
					// Die Teilbilder werden in einem BufferedImage-Array gespeichert.
					character[num] = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
					
					Graphics2D g = character[num].createGraphics();
					g.drawImage(img,0,0,w,h,w*x,h*y,w*x+w,h*y+h,null);
					g.dispose();
					num++;
				}
		}
		return character;
	}
}