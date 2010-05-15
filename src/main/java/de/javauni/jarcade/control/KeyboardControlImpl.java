package de.javauni.jarcade.control;

/**
 * @author Michael Kmoch, Simon Lang
 */

import java.awt.event.KeyEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import de.javauni.jarcade.model.control.CharacterControl;
import de.javauni.jarcade.utils.Pair;

public class KeyboardControlImpl implements KeyboardControl {

    private final Logger log = LoggerFactory.getLogger(KeyboardControlImpl.class);
    private KeyboardControlMap kbdMap;

    // use @Assisted annotation, the factory will assist
    @Inject
    public KeyboardControlImpl(
            @Assisted CharacterControl ctl,
            @Assisted int playerNo, 
            KeyboardControlMap kbdMap) {
        this.kbdMap = kbdMap;
    }

    public void keyPressed(KeyEvent e) {
        Pair<Integer, ControlEvent> event = kbdMap.get(e.getKeyCode());

        switch (event.snd) {
        case Jump:
            log.info("Jump key pressed");
            break;
        case MoveLeft:
            log.info("MoveLeft key pressed");
            break;
        case MoveRight:
            log.info("MoveRight key pressed");
            break;
        }
    }

    public void keyReleased(KeyEvent e) {
        Pair<Integer, ControlEvent> event = kbdMap.get(e);

        switch (event.snd) {
        case Stop:
            log.info("Key released");
            break;
        }

    }
}
