/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javauni.jarcade.impl.states;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import de.javauni.jarcade.model.States;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.CheckForNull;

/**
 *
 * @author wabu
 */
public abstract class AbstractStatesBuilder<S extends Enum<S>, T extends Enum<T>>
        implements StatesTransitionBuilder<S, T>, TargetBuilder<S, T> {

    private final Map<S, Map<T, S>> maps = new HashMap<S, Map<T, S>>();
    @CheckForNull
    private S[] froms;
    private T[] ons;
    private final ImmutableMap.Builder<T, S> current = null;

    public TransitionBuilder<S, T> from(S... src) {
        Preconditions.checkArgument(src.length > 0,
                "from must not be empty");
        froms = src;
        return this;
    }

    public TargetBuilder<S, T> on(T... trans) {
        Preconditions.checkArgument(trans.length > 0,
                "on must not be empty");
        ons = trans;
        return this;
    }

    public StatesTransitionBuilder<S, T> to(S tgt) {
        assert froms != null;
        assert ons != null;

        for (S f : froms) {
            if (!maps.containsKey(f)) {
                maps.put(f, new HashMap<T, S>());
            }
            Map<T, S> m = maps.get(f);
            for (T o : ons) {
                if (m.containsKey(o)) {
                    throw new IllegalArgumentException(
                            "their's already an transition from " + f + " on "
                            + o);
                }
                m.put(o, tgt);
            }
        }

        return this;
    }

    protected Map<S, Map<T, S>> getMap() {
        return maps;
    }

    public abstract States<S, T> start(S start);
}
