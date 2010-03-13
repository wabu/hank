/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.model;

import de.javauni.jarcade.impl.states.AbstractStates;
import de.javauni.jarcade.impl.states.StatesBuilder;
import de.javauni.jarcade.impl.states.StatesMap;
import de.javauni.yarrish.model.MainStates.Action;
import de.javauni.yarrish.model.MainStates.State;
import static de.javauni.yarrish.model.MainStates.State.*;
import static de.javauni.yarrish.model.MainStates.Action.*;

/**
 *
 * @author wabu
 */
public class MainStates extends AbstractStates<State,Action> {
    public enum State {
        Intro, Menu
    }
    public enum Action {
        Escape, Finish
    }

    @Override
    protected StatesMap<State,Action> define(StatesBuilder<State, Action> builder) {
        return builder
               .from(Intro).on(Escape,Finish).to(Menu)
               .start(Intro);
    }

    @Override
    protected void onTranstion(Action trans, State src, State tgt) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
