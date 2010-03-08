package physics;

import Game.GameLoop;
import gameObjects.Chest;
import gameObjects.Ground;
import gameObjects.Hank;
import gameObjects.Opponent;
//import gameObjects.Opponent1;
//import gameObjects.Tube;
import gameState.GameState;


public class Level 
{
	
	private GameState gState;

	public Level(GameState gState)
	{
		this.gState = gState;
		createLevel();
	}
	
	public void createLevel()
	{
		Hank CptHank = Hank.makeHank((int)(200*GameLoop.SCALEFACTOR), (int)(469*GameLoop.SCALEFACTOR), 0f);
//		Tube tube	 = Tube.tube1X1((int)(850*GameLoop.SCALEFACTOR), (int)(425*GameLoop.SCALEFACTOR));
		
		// rat, don't delete!
		Opponent opp = Opponent.makeAI((int)(650*GameLoop.SCALEFACTOR), (int)(200*GameLoop.SCALEFACTOR), 0f);
		opp.insertWayPoint(new Position((int)(400*GameLoop.SCALEFACTOR), (int)(300*GameLoop.SCALEFACTOR), 0, 0, -1f));
		opp.insertWayPoint(new Position((int)(700*GameLoop.SCALEFACTOR), (int)(300*GameLoop.SCALEFACTOR), 0, 0,  1f));

		gState.insertGameObject(CptHank);
		
//		gState.insertBackgroundObject(n)

//		gState.insertGameObject(Ground.ground1X1flipped((int)(0*GameLoop.SCALEFACTOR), 	(int)(680*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1flipped((int)(128*GameLoop.SCALEFACTOR), 	(int)(680*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1flipped((int)(256*GameLoop.SCALEFACTOR), 	(int)(680*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1flipped((int)(384*GameLoop.SCALEFACTOR), 	(int)(680*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1flipped((int)(512*GameLoop.SCALEFACTOR), 	(int)(680*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1flipped((int)(640*GameLoop.SCALEFACTOR), 	(int)(680*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1flipped((int)(768*GameLoop.SCALEFACTOR), 	(int)(680*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1flipped((int)(896*GameLoop.SCALEFACTOR), 	(int)(680*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1flipped((int)(1024*GameLoop.SCALEFACTOR), 	(int)(680*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1flipped((int)(1024*GameLoop.SCALEFACTOR), 	(int)(680*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1flipped((int)(1152*GameLoop.SCALEFACTOR), 	(int)(680*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1flipped((int)(1280*GameLoop.SCALEFACTOR), 	(int)(680*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1flipped((int)(1408*GameLoop.SCALEFACTOR), 	(int)(680*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1flipped((int)(1536*GameLoop.SCALEFACTOR), 	(int)(680*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1flipped((int)(1664*GameLoop.SCALEFACTOR), 	(int)(680*GameLoop.SCALEFACTOR)));
		
//		gState.insertGameObject(Ground.ground1X1((int)(0*GameLoop.SCALEFACTOR), 	(int)(550*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(128*GameLoop.SCALEFACTOR), 	(int)(550*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(256*GameLoop.SCALEFACTOR), 	(int)(550*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(384*GameLoop.SCALEFACTOR), 	(int)(550*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(512*GameLoop.SCALEFACTOR), 	(int)(550*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(640*GameLoop.SCALEFACTOR), 	(int)(550*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(768*GameLoop.SCALEFACTOR), 	(int)(550*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(896*GameLoop.SCALEFACTOR), 	(int)(550*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(1024*GameLoop.SCALEFACTOR), 	(int)(550*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(1024*GameLoop.SCALEFACTOR), 	(int)(550*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(1152*GameLoop.SCALEFACTOR), 	(int)(550*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(1280*GameLoop.SCALEFACTOR), 	(int)(550*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(1408*GameLoop.SCALEFACTOR), 	(int)(550*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(1536*GameLoop.SCALEFACTOR), 	(int)(550*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(1664*GameLoop.SCALEFACTOR), 	(int)(550*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(1792*GameLoop.SCALEFACTOR), 	(int)(612*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(1920*GameLoop.SCALEFACTOR), 	(int)(612*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(1920*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(1920*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(2048*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(2176*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(2304*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(2432*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(2560*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(2688*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(2816*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(2944*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(3072*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(3200*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(3328*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(3456*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Chest.chest1X1((int)(1140*GameLoop.SCALEFACTOR), 	(int)(388*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Chest.chest1X1((int)(2976*GameLoop.SCALEFACTOR), 	(int)(388*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Chest.chest1X1((int)(3072*GameLoop.SCALEFACTOR), 	(int)(388*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Chest.chest1X1((int)(3072*GameLoop.SCALEFACTOR), 	(int)(292*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(1472*GameLoop.SCALEFACTOR), 	(int)(164*GameLoop.SCALEFACTOR)));
		//gState.insertGameObject(Ground.ground1X1((int)(1664*GameLoop.SCALEFACTOR), 	(int)(228*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(3584*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(3712*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(3840*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(3840*GameLoop.SCALEFACTOR), 	(int)(612*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(3968*GameLoop.SCALEFACTOR), 	(int)(612*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(4096*GameLoop.SCALEFACTOR), 	(int)(612*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(4096*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(4224*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(4352*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(4480*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(4608*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(4736*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(4864*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(4992*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(5120*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(5248*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(5376*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(5504*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(5632*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(5760*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(5888*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(6012*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(6144*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(6144*GameLoop.SCALEFACTOR), 	(int)(612*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(6272*GameLoop.SCALEFACTOR), 	(int)(612*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(6400*GameLoop.SCALEFACTOR), 	(int)(612*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(6400*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(6528*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(6656*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(6784*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(6912*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(6012*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Chest.chest1X1((int)(5664*GameLoop.SCALEFACTOR), 	(int)(388*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(7040*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(7168*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(7296*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(7424*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(7552*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(7680*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(7808*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(7936*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(8064*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(8192*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(8320*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(8448*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(8576*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(8704*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(8832*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(8960*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(9088*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(9216*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(9344*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(9472*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(9600*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(9728*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(9856*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(9984*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(10112*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(10240*GameLoop.SCALEFACTOR), 	(int)(484*GameLoop.SCALEFACTOR)));
		/*gState.insertGameObject(Ground.ground1X1((int)(10240*GameLoop.SCALEFACTOR), 	(int)(284*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Ground.ground1X1((int)(10240*GameLoop.SCALEFACTOR), 	(int)(84*GameLoop.SCALEFACTOR)));*/
		//gState.insertGameObject(Ground.ground1X1((int)(8192*GameLoop.SCALEFACTOR), 	(int)(356*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Chest.chest1X1((int)(8480*GameLoop.SCALEFACTOR), 	(int)(388*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Chest.chest1X1((int)(8576*GameLoop.SCALEFACTOR), 	(int)(388*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Chest.chest1X1((int)(8576*GameLoop.SCALEFACTOR), 	(int)(292*GameLoop.SCALEFACTOR)));
		gState.insertGameObject(Chest.chest1X1((int)(8567*GameLoop.SCALEFACTOR), 	(int)(169*GameLoop.SCALEFACTOR)));
		
		//gState.insertGameObject(tube);
		gState.insertGameObject(opp);
		
		/*Opponent1 opponent = Opponent1.makeAI((int)(600*GameLoop.SCALEFACTOR), (int)(469*GameLoop.SCALEFACTOR), 0f);
		opponent.insertWayPoint(new Position((int)(600*GameLoop.SCALEFACTOR),(int)(469*GameLoop.SCALEFACTOR),(int)(50*GameLoop.SCALEFACTOR),(int)(65*GameLoop.SCALEFACTOR),-3f));
		gState.insertGameObject(opponent);*/
	}
}
