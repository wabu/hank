package de.javauni.jarcade.control;

import de.javauni.jarcade.model.control.CharacterControl;

// we only have to declare the interface for the factory
public interface KeyboardControlFactory {
    KeyboardControl create(CharacterControl ctl, int playNo);
}
