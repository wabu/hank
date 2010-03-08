package Game;

import java.applet.AudioClip;

/*
	Gameloop-Musik Beispiel
	von Marco Block
	Januar 2010
*/
public class MusicPlayer implements Runnable {

    private AudioClip sound;
	private boolean playSoundLoop;
	
    public MusicPlayer(GameLoop game)
    {
	    playSoundLoop = false;
    }

    public void run()
    {
    }

    public void start()
    {
    }

    public void soundStop(AudioClip audio)
    {
        if (audio!=null)
            audio.stop();
		playSoundLoop = false;
    }

	public void playSound(AudioClip audio) {
		if (!playSoundLoop) {
			sound = audio;
			soundStop(sound);
			if (audio!=null)
				sound.play();
		} 
    }
	
    public void soundLoop(AudioClip audio) {
        sound 			= audio;
		playSoundLoop 	= true;
        soundStop(sound);
        if (audio!=null)
            sound.loop();
    }
}
