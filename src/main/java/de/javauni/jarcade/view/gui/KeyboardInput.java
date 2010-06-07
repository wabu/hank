package de.javauni.jarcade.view.gui;

/**
 * @author Michael Kmoch, Simon Lang
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;

import de.javauni.jarcade.control.AbstractControl;
import de.javauni.jarcade.control.gamecontrol.GameControl;
import de.javauni.jarcade.control.playercontrol.PlayerControl;
import de.javauni.jarcade.model.StateListener;
import de.javauni.jarcade.model.main.MainState;

public class KeyboardInput extends KeyAdapter implements StateListener<MainState> {

    private AbstractControl control;
    private Map<MainState, AbstractControl> stateControlMap;

    @Inject
    public KeyboardInput(PlayerControl playerControl,
    		GameControl gameControl) {
        this.control = playerControl;
        stateControlMap = new HashMap<MainState, AbstractControl>();
        stateControlMap.put(MainState.Level, playerControl);
        stateControlMap.put(MainState.Game, gameControl);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	control.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	control.keyReleased(e);
    }

	@Override
	public void onStateChange(MainState state) {
		this.control = stateControlMap.get(state);
	}

}
