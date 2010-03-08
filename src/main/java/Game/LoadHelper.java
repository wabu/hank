package Game;

import graphics.SpriteSheetReader;
import graphics.Sprites;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Class helps to load Images
 * 
 * @since		2010/02/12
 * @version 	ver0.1
 * @author 		Sandra
 *
 */

public class LoadHelper 
{
	private static LoadHelper theInstance;
	
	/**
	 * Constructor
	 */
	private LoadHelper()
	{
		
	}
	
	/**
	 * creates an instance of LoadHelper and limits the number of possible created objects to one
	 * @return 	theInstance
	 */
	public static LoadHelper getInstance()
	{
		if(theInstance == null)
			theInstance = new LoadHelper();
		return theInstance;
	}
	
	/**
	 * loads images
	 */
	public void loadGameContent(Sprites sprites)
	{
		try
		{
			// read Image and send it to SpriteSheetReader. Add SpriteKey and returned BufferedImage[] to hashmap
			sprites.setObject("BG01", SpriteSheetReader.splitImage(ImageIO.read(new File("res/himmel.png")), 1, 1));
			sprites.setObject("Ground1X1", SpriteSheetReader.splitImage(ImageIO.read(new File("res/Ground.png")), 1, 1));
			sprites.setObject("Ground1X1flipped", SpriteSheetReader.splitImage(ImageIO.read(new File("res/Groundflipped.png")), 1, 1));
			sprites.setObject("Hank01", SpriteSheetReader.splitImage(ImageIO.read(new File("res/spriteSheetHank.png")), 4, 4));
			sprites.setObject("Tube1x1", SpriteSheetReader.splitImage(ImageIO.read(new File("res/Tube.png")), 1, 1));
			sprites.setObject("Rat", SpriteSheetReader.splitImage(ImageIO.read(new File("res/rat.png")), 4, 3));
			sprites.setObject("Title01", SpriteSheetReader.splitImage(ImageIO.read(new File("res/menu/hauptmenue_schriftzug.png")), 1, 1));
			sprites.setObject("StartButton01", SpriteSheetReader.splitImage(ImageIO.read(new File("res/menu/hauptmenue_start.png")), 2, 1));
			sprites.setObject("ExitButton01", SpriteSheetReader.splitImage(ImageIO.read(new File("res/menu/hauptmenue_windows.png")), 2, 1));
			sprites.setObject("chest1X1", SpriteSheetReader.splitImage(ImageIO.read(new File("res/kiste.png")), 1, 1));
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}			
	}
}
