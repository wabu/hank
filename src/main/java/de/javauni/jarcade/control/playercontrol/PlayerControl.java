package de.javauni.jarcade.control.playercontrol;

import de.javauni.jarcade.control.AbstractControl;
import de.javauni.jarcade.model.control.CharacterControl;

/**
 * @author Michael Kmoch, Simon Lang
 */


public interface PlayerControl extends AbstractControl {
    public void registerControl(CharacterControl ctl, int playerNo);
}
