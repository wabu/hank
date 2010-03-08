package gameObjects;

import Game.GameLoop;
import physics.Position;

public class StaticImage extends StaticObjects
{
	public StaticImage(Position pos, String spriteKey, String type)
	{
		super(pos, spriteKey, type);
	}
	
	public static StaticImage makeTitle(int x, int y)
	{
		return new StaticImage(new Position(x, y, (int)(995*GameLoop.SCALEFACTOR), (int)(442*GameLoop.SCALEFACTOR), 0f), "Title01", "TheMainTitle");
	}
	
	public static StaticImage makeStartButton(int x, int y)
	{
		return new StaticImage(new Position(x, y, (int)(135*GameLoop.SCALEFACTOR), (int)(40*GameLoop.SCALEFACTOR), 0f), "StartButton01", "Start");
	}

	public static StaticImage makeExitButton(int x, int y)
	{
		return new StaticImage(new Position(x, y, (int)(392*GameLoop.SCALEFACTOR), (int)(41*GameLoop.SCALEFACTOR), 0f), "ExitButton01", "Exit");
	}
}
