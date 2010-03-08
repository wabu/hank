package gameObjects;

import java.awt.Image;

import physics.Position;

/**
 * All interfaceObjects are based on this class. interfaceObjects are not part of the level
 * 
 * @since	02/05/2010
 * @version	02/12/2010
 * @author	Flo
 */
public class InterfaceObjects extends GameObject{

	/**
	 * constructor
	 * 
	 * @param pos
	 * @param spriteKey
	 */
	public InterfaceObjects(Position pos, String spriteKey){
		super(pos, spriteKey);
	}
	
	public Position getPosition(){
		return super.getPosition();
	}
	
	public void setPosition(Position p){
		super.setPosition(p);
	}
	
	public Image getImage(){	
		System.out.println("Verfluchte Axt!");
		return null;
	}
}
