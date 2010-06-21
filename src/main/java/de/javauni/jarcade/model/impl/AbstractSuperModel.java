package de.javauni.jarcade.model.impl;

import java.util.HashMap;
import java.util.Map;

import java.util.Map.Entry;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;

import de.javauni.jarcade.model.StateModel;
import de.javauni.jarcade.model.SuperModel;
import de.javauni.jarcade.model.impl.event.Channel;
import de.javauni.jarcade.utils.visit.VisitAdapter;

public abstract class AbstractSuperModel<S extends Enum<S>, V extends VisitAdapter<?>>
        extends AbstractStateModel<S> implements SuperModel<S, V> {
    private final Map<S, Provider<? extends V>> providerMap;
    private V sub = null;

    public AbstractSuperModel(Channel<StateModel.ChangeListener<S>> channel, 
            S initial) {
        super(channel, initial);
        this.providerMap = new HashMap<S, Provider<? extends V>>();
    }

    protected abstract Map<S, Key<? extends V>> getSubModelMap();

    @Inject
    void initialize(Injector inj) {
        for (Entry<S, Key<? extends V>> e : getSubModelMap().entrySet()) {
            providerMap.put(e.getKey(), inj.getProvider(e.getValue()));
        }
    }

    @Override
    protected void doStateTransition(S src, S tgt) throws IllegalArgumentException {
        // TODO scope management
        sub = providerMap.get(tgt).get();
    }
}
