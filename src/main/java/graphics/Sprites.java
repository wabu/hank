package graphics;

import gameObjects.*;

import java.util.HashMap;
import java.awt.image.BufferedImage;

import Game.GameLoop;

/** 
 * Class saves objects and assigns it to a string.
 * 
 * @since 		2010/02/11
 * @version		v0.1
 * @author 		Sandra, Caspar, Gerd
 *
 */

public class Sprites 
{
	HashMap <String, BufferedImage[]> spriteRegister;
	
	// ***********
	// captain dirty hank
	private int isIdleIndex;
	private int isIdleIndexMAX 			 	= 0;
	private int isIdleIndexSTART 			= 0;
	private int isIdleLeftIndex;
	private int isIdleLeftIndexMAX 			= 5;
	private int isIdleLeftIndexSTART 		= 4;
	private int isIdleRightIndex;
	private int isIdleRightIndexMAX 		= 7;
	private int isIdleRightIndexSTART 		= 6;
	private static int buttonIndex 			= 1;
	
	private int goLeftIndex;
	private int goLeftIndexMAX 		= 9;
	private int goLeftIndexSTART	= 8;
	private int goRightIndex;
	private int goRightIndexMAX 	= 11;
	private int goRightIndexSTART	= 10;
	private int jumpLeftIndex;
	private int jumpLeftIndexMAX 	= 2;
	private int jumpLeftIndexSTART	= 2;
	private int jumpRightIndex;
	private int jumpRightIndexMAX 	= 3;
	private int jumpRightIndexSTART	= 3;
	
	// rat
	private int ratOffset = 10;
	private int goLeftRatIndex;
	private int goLeftRatIndexSTART	= 0;
	private int goLeftRatIndexMAX 	= 4;
	private int goRightRatIndex;
	private int goRightRatIndexSTART= 5;
	private int goRightRatIndexMAX 	= 9;
	// ***********
	
	public Sprites()
	{
		spriteRegister = new HashMap<String, BufferedImage[]>();

		// ***********
		// dirty
		isIdleIndex 	= isIdleIndexSTART;
		goLeftIndex 	= goLeftIndexSTART;
		goRightIndex 	= goRightIndexSTART;
		// ***********
	}
	
	/**
	 * function is called by GameLoop(); to initiate all Objects
	 * 
	 * @param keyWord string which describes the position of the wanted BufferedImage
	 * @param spriteObject container which holds the final BufferedImage 
	 * 
	 */
	public void setObject(String keyWord , BufferedImage[] spriteObject)
	{
		spriteRegister.put(keyWord, spriteObject);
	}
	
	/**
	 * function gets BufferedImageobject according to keyword
	 * 
	 * @param keyWord
	 * @return BufferedImage Object
	 */
	public BufferedImage getObject(GameObject gO)
	{
		String keyWord 			= gO.getSpriteKey();
		BufferedImage retImage 	= null;
		int temp 				= ratOffset;
		
		if (gO instanceof Hank) {
			GameLoop.Playerstate myState = ((Characters)gO).getMyState();
			
			if (myState == GameLoop.Playerstate.IDLE){
				retImage = spriteRegister.get(keyWord)[isIdleIndex];
				isIdleIndex++;
				if (isIdleIndex>isIdleIndexMAX)
					isIdleIndex = isIdleIndexSTART;
			} else if (myState == GameLoop.Playerstate.IDLERIGHT){
				retImage = spriteRegister.get(keyWord)[isIdleRightIndex];
				isIdleRightIndex++;
				if (isIdleRightIndex>isIdleRightIndexMAX)
					isIdleRightIndex = isIdleRightIndexSTART;
			} else if (myState == GameLoop.Playerstate.IDLELEFT){
				retImage = spriteRegister.get(keyWord)[isIdleLeftIndex];
				isIdleLeftIndex++;
				if (isIdleLeftIndex>isIdleLeftIndexMAX)
					isIdleLeftIndex = isIdleLeftIndexSTART;
			} else if (myState == GameLoop.Playerstate.GORIGHT){
				retImage = spriteRegister.get(keyWord)[goRightIndex];
				goRightIndex++;
				if (goRightIndex>goRightIndexMAX)
					goRightIndex = goRightIndexSTART;				
			} else if (myState == GameLoop.Playerstate.GOLEFT){
				retImage = spriteRegister.get(keyWord)[goLeftIndex];
				goLeftIndex++;
				if (goLeftIndex>goLeftIndexMAX)
					goLeftIndex = goLeftIndexSTART;				
			} else if (myState == GameLoop.Playerstate.JUMPLEFT){
				retImage = spriteRegister.get(keyWord)[jumpLeftIndex];
				jumpLeftIndex++;
				if (jumpLeftIndex>jumpLeftIndexMAX)
					jumpLeftIndex = jumpLeftIndexSTART;				
			} else if (myState == GameLoop.Playerstate.JUMPRIGHT){
				retImage = spriteRegister.get(keyWord)[jumpRightIndex];
				jumpRightIndex++;
				if (jumpRightIndex>jumpRightIndexMAX)
					jumpRightIndex = jumpRightIndexSTART;				
			}
		} else if (gO instanceof Opponent)
		{
			GameLoop.Playerstate myState = ((Characters)gO).getMyState();
			GameLoop.Playerstate myLastState = ((AI)gO).getLastState();
			
			if ((myState == GameLoop.Playerstate.GOLEFT) && (myLastState == GameLoop.Playerstate.GOLEFT)){
				retImage = spriteRegister.get(keyWord)[goLeftRatIndex/ratOffset];
				goLeftRatIndex++;
				if (goLeftRatIndex>goLeftRatIndexMAX*ratOffset)
					goLeftRatIndex = goLeftRatIndexSTART;
			} else if ((myState == GameLoop.Playerstate.GOLEFT) && (myLastState != GameLoop.Playerstate.GOLEFT)){
				retImage = spriteRegister.get(keyWord)[goLeftRatIndex];
				goLeftRatIndex++;
				if (goLeftRatIndex>goLeftRatIndexMAX)
					goLeftRatIndex = goLeftRatIndexSTART;		
			} else if ((myState == GameLoop.Playerstate.GORIGHT)&&(myLastState == GameLoop.Playerstate.GORIGHT)){
				retImage = spriteRegister.get(keyWord)[goRightRatIndex/ratOffset];
				goRightRatIndex++;
				if (goRightRatIndex>goRightRatIndexMAX*ratOffset)
					goRightRatIndex = goRightRatIndexSTART;			
			} else if ((myState == GameLoop.Playerstate.GORIGHT)&&(myLastState != GameLoop.Playerstate.GORIGHT)){
				retImage = spriteRegister.get(keyWord)[goRightRatIndex];
				goRightRatIndex++;
				if (goRightRatIndex>goRightRatIndexMAX)
					goRightRatIndex = goRightRatIndexSTART;			
			}
		} else if (gO instanceof StaticImage)
		{
			if (((StaticImage) gO).getType().equals("Start"))
			{
				if (buttonIndex == 0) retImage = spriteRegister.get(keyWord)[buttonIndex + 1];
				else retImage = spriteRegister.get(keyWord)[buttonIndex - 1];
			} else if (((StaticImage) gO).getType().equals("Exit"))
			{
				retImage = spriteRegister.get(keyWord)[buttonIndex];
			} else retImage = spriteRegister.get(keyWord)[0];
		} else
		{
			try {
				retImage = spriteRegister.get(keyWord)[0];
			} catch (Exception e){
				retImage = null;
			}
		}
		ratOffset = temp;
		return retImage;
	}
	
	public static int getButtonIndex()
	{
		return buttonIndex;
	}
	
	public static void setButtonIndex(int value)
	{
		buttonIndex = value;
	}
}
