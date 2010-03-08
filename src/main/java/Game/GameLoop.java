/**
 * Gameloop creates the frame for the panel and holds the main loop of the game
 * 
 * @see		CollisionObjects, Hank, Opponent, Player, GameState, Sprites, Gravity, Level, Position
 * @since	02/05/2010
 * @version	02/25/2010 v0.5
 * @author 	Mirko Berns-Relotic, Robin R�dai
 *
 */

package Game;

import gameObjects.BackgroundObjects;
import gameObjects.CollisionObjects;
import gameObjects.Hank;
import gameObjects.Opponent;
import gameObjects.Player;
import gameObjects.StaticImage;
import gameState.GameState;
import graphics.Sprites;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

import javax.swing.JFrame;

import physics.Gravity;
import physics.Level;
import physics.Position;


@SuppressWarnings("serial")
public class GameLoop extends JFrame implements KeyListener
{
	// ************************************************************
	// Game properties
	public static final float SCALEFACTOR_FRAME	= 1f;
	public static final float SCALEFACTOR  		= 1f;
	
	public static final int PANEL_WIDTH  = (int)(1018*SCALEFACTOR_FRAME);
	public static final int PANEL_HEIGHT = (int)(742*SCALEFACTOR_FRAME);	
	
	// Look & feel properties
	public static final int   PLAYER_MAX_HEIGHT 			= (int)(250*SCALEFACTOR);
	public static final float GRAVITY_MAX_VELOCITY_LEFT		= -6.0f*SCALEFACTOR;
	public static final float GRAVITY_MAX_VELOCITY_RIGHT	= 6.0f*SCALEFACTOR;
	public static final float GRAVITATION					= 0.5f*SCALEFACTOR;
	public static final float GRAVITY_ACC					= 1f*SCALEFACTOR;
	public static final int   GRAVITY_CENTER_X				= (int)(512*SCALEFACTOR_FRAME);
	// ************************************************************
	
	// ************************************************************
	// Level and world properties, start position for hank
	public static int WORLD_WIDTH;
	public static int WORLD_HEIGHT;
	
	public static int LEVEL_START_X;
	public static int LEVEL_START_Y;
	public static int LEVEL_WIDTH;
	public static int LEVEL_HEIGHT;
	
	public static int VIEW_START_X;
	public static int VIEW_START_Y;	
	public static int VIEW_WIDTH  = (int)(1024*SCALEFACTOR_FRAME);
	public static int VIEW_HEIGHT = (int)(768*SCALEFACTOR_FRAME);
	
	public static int HANK_START_X;
	public static int HANK_START_Y;
	// ************************************************************
	
	// ************************************************************
	// Player properties
	public static enum Playerstate 
	{
		IDLE, IDLELEFT, IDLERIGHT, GOLEFT, GORIGHT, JUMPLEFT, JUMPRIGHT
	};
	// ************************************************************
	
	private File backgroundMusicFile;
	private File[] musicSnippetFile;
	MusicPlayer myMusicPlayer;
	private AudioClip sound;
	private AudioClip[] musicSnippet;
	final int COUNT_SOUNDSNIPPETS = 5;
	private StaticImage menuTitle, menuStart, menuExit;
	private BackgroundObjects background;
	
	private enum LoopState {
		INIT, INTRO, LEVEL, OUTRO, PAUSE, END, EXIT, MENU
	};
	
	private LoopState state;
	private boolean levelIsRunning, pauseReady, upReady, downReady;
	private boolean leftPressed, rightPressed, upPressed, downPressed, escapePressed, enterPressed, pPressed;
	private long delta, last;
	private GameState gState;
	private GamePanel gPanel;
	private Player cptHank;
	@SuppressWarnings("unused")
	private Level level;
	private Sprites sprites;
	private LoadHelper loader;
	private Gravity gravity;
	private Vector<CollisionObjects> allCollisionObjects;
	
	public static boolean readyToJump;
	/**
	 * Constructor. It shows the frame and does initialisations
	 */
	public GameLoop()
	{
		last = System.nanoTime();
		
		this.setSize(VIEW_WIDTH, VIEW_HEIGHT);
		this.setLocation(0,0);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gState = new GameState();
		sprites = new Sprites();
		
		gPanel = new GamePanel(PANEL_WIDTH, PANEL_HEIGHT, gState, sprites);
		this.add(gPanel);
		this.addKeyListener(this);

		state = LoopState.MENU;
		upReady = true;
		downReady = true;
		
		gravity = new Gravity();
		loader = LoadHelper.getInstance();
		loader.loadGameContent(sprites);
		
		// MusicPlayer wird geladen und mit dem Gameloop verkn�pft
		myMusicPlayer 	    = new MusicPlayer(this);
		
		// Hintergrundmusik wird geladen
        backgroundMusicFile = new File("res/soundfiles/SuperMarioWorldLevel1.mid"); 
        
		// Musiksnippets werden geladen
		musicSnippetFile 	= new File[COUNT_SOUNDSNIPPETS];
		musicSnippetFile[0]	= new File("res/soundfiles/Mario Riff.wav"); 
		musicSnippetFile[1]	= new File("res/soundfiles/Mario Jump.wav"); 
		musicSnippetFile[2]	= new File("res/soundfiles/DEATH.wav"); 
		musicSnippetFile[3]	= new File("res/soundfiles/Power Up.wav"); 
		musicSnippetFile[4]	= new File("res/soundfiles/Pause.wav"); 
		
		musicSnippet = new AudioClip[COUNT_SOUNDSNIPPETS];
        try
        {
            sound = Applet.newAudioClip(backgroundMusicFile.toURI().toURL());
			for (int i=0; i<musicSnippet.length; i++)
				musicSnippet[i] = Applet.newAudioClip(musicSnippetFile[i].toURI().toURL());
        }
        catch(Exception ex) {}
        
		this.setVisible(true);
	}
	/**
	 * create all objects needed for the main menu
	 */
	private void createMenu()
	{
		menuTitle = StaticImage.makeTitle(10, 50);
		menuStart = StaticImage.makeStartButton(425, 520);
		menuExit = StaticImage.makeExitButton(300, 600);
		gState.insertGameObject(menuTitle);
		gState.insertGameObject(menuStart);
		gState.insertGameObject(menuExit);
	}
	
	/**
	 * create all objects needed for the level
	 */
	private void createLevel()
	{
		levelIsRunning = true;
		gState.reset();
		
		
		level = new Level(gState);
		
		// set level properties
		WORLD_WIDTH		= (int)(1500*SCALEFACTOR_FRAME);
		WORLD_HEIGHT	= (int)(750*SCALEFACTOR_FRAME);
		
		LEVEL_START_X	= (int)(0*SCALEFACTOR_FRAME);
		LEVEL_START_Y	= (int)(0*SCALEFACTOR_FRAME);
		LEVEL_WIDTH		= (int)(10240*SCALEFACTOR_FRAME);
		LEVEL_HEIGHT	= (int)(750*SCALEFACTOR_FRAME);
		
		VIEW_START_X	= (int)(0*SCALEFACTOR_FRAME);
		VIEW_START_Y	= (int)(0*SCALEFACTOR_FRAME);	
		
		HANK_START_X	= (int)(50*SCALEFACTOR_FRAME);
		HANK_START_Y	= (int)(100*SCALEFACTOR_FRAME);
		
		Vector<CollisionObjects> allCollisionObjects = gState.getCollisionObjects();			
		for (int i = 0; i<allCollisionObjects.size(); i++)
		{
			CollisionObjects co = allCollisionObjects.get(i);
			if (co instanceof Hank) {
				Position pos2 = allCollisionObjects.elementAt(i).getPosition();
				pos2.setX(HANK_START_X);
				pos2.setY(HANK_START_Y);
			} 			
		}
		
		cptHank = ((Player)gState.getCollisionObject(0));
		
		background = BackgroundObjects.makeBackground(0, 0);
		gState.insertBackgroundObject(background);
	}
	
	/**
	 * delta is the duration of the last loop. It is needed for displaying smooth motions for every situation/workload
	 */
	private void calcDelta()
	{
		delta = System.nanoTime() - last;
		last = System.nanoTime();
		gState.setFps(((long) 1e9)/delta);
	}

	// ******************************************************************
	// States
	/**
	 * game state for the main menu.
	 */
	private void state_menu()
	{
		System.out.println("Menu phase started");
		
		createMenu();
		
		while (true)
		{	
			repaint();
			if (enterPressed) 
			{
				if (Sprites.getButtonIndex() == 0)
				{
					state = LoopState.EXIT;
				}else state = LoopState.INIT;
				break;
			}
			else if ((upPressed) && (upReady) && (Sprites.getButtonIndex() == 0))
			{
				if (Sprites.getButtonIndex() == 1) 
				{
					Sprites.setButtonIndex(0);
				}
				else Sprites.setButtonIndex(1);
				
				upReady = false;
			}else if ((downPressed) && (downReady) && (Sprites.getButtonIndex() == 1))
			{
				if (Sprites.getButtonIndex() == 1) 
				{
					Sprites.setButtonIndex(0);
				}
				else Sprites.setButtonIndex(1);
				
				downReady = false;
			}
			try 
			{
				Thread.sleep(50);
			} catch (Exception e) {}
		}
	}
	
	/**
	 * game state for initialisations. The level creation is started here
	 */
	private void state_init() 
	{
		System.out.println("Init Phase started");
		createLevel();
		gState.setMsg("");
		pauseReady = true;
		repaint();
	}
	
	/**
	 * game state for playing an intro
	 */
	private void state_intro() 
	{
		System.out.println("Intro phase started");
	}
	
	/**
	 * game state while a level is being played.
	 * For the prototype the loop with rendering calls, movement and control handling is within
	 */
	private void state_level() 
	{
		System.out.println("Level Phase started");
		myMusicPlayer.soundLoop(sound);
		
		// rat
		allCollisionObjects = gState.getCollisionObjects();
		Hank myHank 	= null;
		Opponent myRat	= null;

		for (CollisionObjects co:allCollisionObjects)
		{
			if (co instanceof Opponent)
			{
				myRat = (Opponent)co;
				myRat.startPatroullie(1);
				myRat.setMyState(GameLoop.Playerstate.GORIGHT);
			}
			if (co instanceof Hank)
			{
				myHank = (Hank)co;
			}
		}
		

		while (levelIsRunning)
		{
			calcDelta();
			
			//***************************************************
			//Pause handling
			if ((pPressed) && (pauseReady))
			{
				System.out.println("Pause pressed ... ");
				levelIsRunning = false;
				pauseReady = false;
				state = LoopState.PAUSE;
				break;
			}
			else gState.setPaused(false);
			
			//***************************************************
			//Control handling
			if (rightPressed) 
			{
				cptHank.goRight();
			}
			else if (leftPressed) 
			{
				cptHank.goLeft();
			}
			else
				cptHank.idle();
			
			if (upPressed)
			{
				// only allow jump when standing on the ground OR already in the air, to rise higher
				if (readyToJump || myHank.getJumpTime() > 0)
				{
					// Hank is standing on the ground
					if (myHank.getJumpTime() == -1)
					{
						// play the jump sound and save from which height he jumps to calculate max. jump height
						cptHank.jumpedFromHeight = cptHank.getPosition().getY();
						myMusicPlayer.playSound(musicSnippet[1]);
					}
					// make Hank jump and consequently not ready to jump, till he reached ground again
					cptHank.jump(gravity, 10.f);
					readyToJump = false;
				}
			}
			else if (downPressed) 
				cptHank.duck();
			
			// UP button released and Hank standing on the floor
			if (!upPressed && myHank.getJumpTime() <= 0)
				readyToJump = true;
			
			//***************************************************
			
			//***************************************************			
			// Movement calculation
			
			// first hank:
			if (myHank.getPosition().getY()>800)
			{
				if (gState.getLife() <= 0)
				{
					levelIsRunning = false;
					state = LoopState.OUTRO;
				} else
				{
					gState.setLife(gState.getLife() - 1);
					levelIsRunning = false;
					state = LoopState.INIT;
				}
			}
		
			boolean hasMoved = gravity.calcNewPosition(myHank, myHank.getAcc(), gState);
			if (!hasMoved)
			{
				if (myHank.getJumpTime()>0)
					myHank.setJumpTime(0);
				if (myHank.getFallTime()>0)
					myHank.setFallTime(0);
				myHank.getPosition().setVelocity(0);
			}
				
			// rat:
			@SuppressWarnings("unused")
			boolean hasMovedRat = gravity.calcNewPosition(myRat, myRat.getWayPoint().getVelocity(), gState);
			// check for new waypoint
			//if (!hasMovedRat){
				//if (myRat.getPosition().getX())
			//}
			
			//***************************************************
			try 
			{
				Thread.sleep(10);
			} catch (Exception e) {}
			
			repaint();
		}
	}
	
	/**
	 * game state for pausing the game
	 */
	private void state_pause() 
	{	
		System.out.println("Pause phase started");
		myMusicPlayer.playSound(musicSnippet[4]);
		gState.setMsg("Pause - hit \"P\" to continue or Escape to end");
		repaint();
		
		if (!gState.isPaused()) 
		{
			pPressed = false;
			gState.setPaused(true);
		}
		while (true)
		{	
			if (pPressed)
			{
				gState.clearMsg();
				state = LoopState.LEVEL;
				levelIsRunning = true;
				break;
			}			
			else if (escapePressed)
			{
				state = LoopState.EXIT;
				gState.clearMsg();
				break;				
			}
			try 
			{
				Thread.sleep(50);
			} catch (Exception e) {}
		}
	}
	
	/**
	 * game state for playing an outro
	 */
	private void state_outro() 
	{
		System.out.println("Outro phase started");
		gState.setMsg("Game Over");
		repaint();
		myMusicPlayer.soundStop(sound);
		myMusicPlayer.playSound(musicSnippet[2]);
		try 
		{
			Thread.sleep(3000);
		} catch (Exception e) {}
	}
	
	/**
	 * game state for something when the game ends
	 */
	private void state_end() 
	{
		System.out.println("End phase started");
		gState.setMsg("Press Enter to restart... Escape to end.");
		repaint();
		
		while (true)
		{		
			if (enterPressed) 
			{
				state = LoopState.INIT;
				break;
			}			
			else if (escapePressed)
			{
				state = LoopState.EXIT;
				break;				
			}
			try 
			{
				Thread.sleep(50);
			} catch (Exception e) {}
		}
	}

	// ******************************************************************

	
	/**
	 * the game cycles through the gamestates
	 */
	protected void gameStart() 
	{
		last = System.nanoTime();	    
		
	    boolean finished = false;
	    
	    while (!finished)
	    {
	    	switch (state) 
	    	{
	    	case MENU:
	    		state_menu();
	    		break;
	    	case INIT:
	    		state_init();
	    		state = LoopState.INTRO;
	    		break;
	    	case INTRO:
	    		state_intro();
	    		state = LoopState.LEVEL;
	    		break;
	    	case LEVEL:
	    		state_level();
	    		break;
	    	case PAUSE:
	    		state_pause();
	    		break;
	    	case OUTRO:
	    		state_outro();
	    		state = LoopState.END;
	    		break;
	    	case END:
	    		state_end();
	    		try 
	    		{
	    			Thread.sleep(1000);
	    		} catch (Exception e) {}
	    		break;
	    	case EXIT:
	    		finished = true;
	    		break;
	    	default:
	    		break;			
	    	}
	    }
	    System.out.println("We finished!");
	    System.exit(0);

	}
	
	// ******************************************************************
	// Controller for handling the key inputs
	@Override
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();
		switch(key)
		{
			case KeyEvent.VK_UP:
				upPressed 		= true;
				break;
			case KeyEvent.VK_LEFT:
				leftPressed 	= true;
				break;
			case KeyEvent.VK_DOWN:
				downPressed 	= true;
				break;
			case KeyEvent.VK_RIGHT:
				rightPressed 	= true;
				break;
			case KeyEvent.VK_ENTER:
				enterPressed 	= true;
				break;
			case KeyEvent.VK_ESCAPE:
				escapePressed 	= true;
				break;
			case KeyEvent.VK_P:
				if (pauseReady)
				{
					pPressed 	= true;
				}
				break;	
			default:
				break;
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{	
		int key = e.getKeyCode();
		switch(key)
		{
			case KeyEvent.VK_UP:
				upPressed 		= false;
				upReady = true;
				break;
			case KeyEvent.VK_LEFT:
				leftPressed 	= false;
				break;
			case KeyEvent.VK_DOWN:
				downPressed 	= false;
				downReady = true;
				break;
			case KeyEvent.VK_RIGHT:
				rightPressed 	= false;
				break;
			case KeyEvent.VK_ENTER:
				enterPressed 	= false;
				break;
			case KeyEvent.VK_ESCAPE:
				escapePressed 	= false;
				break;				
			case KeyEvent.VK_P:
				pPressed 	= false;
				pauseReady	= true;
				break;
			default:
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
	}
	
	// ******************************************************************
	// main method for creating and starting the game
	public static void main(String[] args)
	{
		GameLoop gL = new GameLoop();
		gL.gameStart();
	}
}
