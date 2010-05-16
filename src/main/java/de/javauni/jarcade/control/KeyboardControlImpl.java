package de.javauni.jarcade.control;

import java.awt.event.KeyEvent;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import de.javauni.jarcade.model.control.CharacterControl;
import de.javauni.jarcade.utils.Pair;

/**
 * @author Michael Kmoch, Simon Lang
 */


public class KeyboardControlImpl implements KeyboardControl {

    private final Logger log = LoggerFactory.getLogger(KeyboardControlImpl.class);
    private KeyboardControlMap kbdMap;
    private Map<Integer, CharacterControl> ctlMap;

    @Inject
    public KeyboardControlImpl(KeyboardControlMap kbdMap) {
        this.kbdMap = kbdMap;
    }

    public void registerControl(CharacterControl ctl, int playerNo) {
        ctlMap.put(playerNo, ctl);
    }

    public void keyPressed(KeyEvent e) {
        Pair<Integer, ControlEvent> event = kbdMap.get(e.getKeyCode());

        switch (event.snd) {
        case Jump:
            log.debug("Jump key pressed");
            ctlMap.get(event.fst).neuronalJump().setMaxPositive();
            break;
        case MoveLeft:
            log.debug("MoveLeft key pressed");
            ctlMap.get(event.fst).neuronalDirection().setMaxNegative();
            break;
        case MoveRight:
            log.debug("MoveRight key pressed");
            ctlMap.get(event.fst).neuronalDirection().setMaxPositive();
            break;
        }
    }

    public void keyReleased(KeyEvent e) {
        Pair<Integer, ControlEvent> event = kbdMap.get(e);

        switch (event.snd) {
        case Jump:
            log.debug("Jump released");
            break;
        case MoveLeft:
        case MoveRight:
            ctlMap.get(event.fst).neuronalDirection().reset();
            log.debug("Move released");
            break;
        case Stop:
            log.debug("Key released");
            break;
        }

    }
}
