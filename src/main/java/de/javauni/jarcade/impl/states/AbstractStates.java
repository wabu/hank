/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javauni.jarcade.impl.states;

import de.javauni.jarcade.model.States;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * To implement a state machine, you can define your state machine in the
 * define() method using the DFA builder internal DSL.
 * heres a simple example of using the builder:
 * <pre>
 * return builder
 *   .from(a,c).on(x).to(b)
 *             .on(y).to(d)
 *   .form(b).on(x,y).to(d)
 *   .from(d).on(x).to(a)
 *           .on(y).to(c)
 *   .start(a);
 * </pre>
 * the onTransition() method will be called before a transtion takes place.
 *
 * @see StatesBuilderImpl
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
     * @param start
     * @param map
     */
    public AbstractStates(S start, Map<S, Map<T, S>> map) {
        this.state = start;
        this.map = map;
        this.smap = map.get(state);
    }

    public AbstractStates(StatesMap<S,T> map) {
        this(map.getStart(), map.getMap());
    }
    
    public AbstractStates() {
        StatesMap<S,T> m = define(new StatesBuilderImpl<S, T>());
        this.state = m.getStart();
        this.map = m.getMap();
        this.smap = map.get(state);
    }

    abstract protected StatesMap<S,T> define(StatesBuilder<S,T> builder);

    public S getCurrentState() {
        return state;
    }

    public synchronized void send(T action) {
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
