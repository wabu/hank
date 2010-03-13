/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javauni.yarrish.engine.impl.states;

import de.javauni.yarrish.engine.model.States;
import java.util.Map;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * To implement a state machine, you can use the AbstractStatesBuilder 
 * to create the DFA; the onTransition() method will be called before
 * a transtion takes place.
 * <pre>
 * states = builder
 *   .from(a,c).on(x).to(b)
 *             .on(y).to(d)
 *   .form(b).on(x,y).to(d)
 *   .from(d).on(x).to(a)
 *           .on(y).to(c)
 *   .start(a);
 * </pre>
 * @see AbstractStatesBuilder
 * @param <S> states type
 * @param <T> transition type
 * @author wabu
 */
@NotThreadSafe
public abstract class AbstractStates<S extends Enum<S>, T extends Enum<T>>
        implements States<S, T> {

    private S state;
    private Map<T, S> smap;
    private final Map<S, Map<T, S>> map;

    /**
     * @param state
     * @param map
     */
    public AbstractStates(S state, Map<S, Map<T, S>> map) {
        this.state = state;
        this.map = map;
        this.smap = map.get(state);
    }

    public S getCurrentState() {
        return state;
    }

    public void send(T action) {
        if (smap == null || !smap.containsKey(action)) {
            throw new IllegalArgumentException(
                    "current state " + state + " has not transition with "
                    + action);
        }
        S tgt = smap.get(action);
        onTranstion(action, state, tgt);
        state = tgt;
        smap = map.get(tgt);
    }

    protected abstract void onTranstion(T trans, S src, S tgt)
            throws IllegalArgumentException;
}
