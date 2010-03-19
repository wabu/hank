/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.model;

import de.javauni.jarcade.event.ListenerPool;
import de.javauni.jarcade.model.state.StateModelAccess;
import de.javauni.jarcade.model.state.StateModelEvent;
import de.javauni.jarcade.model.state.StateModelExport;
import de.javauni.jarcade.model.state.StateModelListener;
import de.javauni.utils.state.AbstractStates;
import de.javauni.utils.state.StatesBuilder;
import de.javauni.utils.state.StatesMap;
import static de.javauni.yarrish.model.ModelState.*;
import static de.javauni.yarrish.model.ModelAction.*;

/**
 *
 * @author wabu
 */
public class StateModelImpl
        extends AbstractStates<ModelState, ModelAction>
        implements StateModelAccess<ModelAction>, StateModelExport<ModelState>
{
    private final ListenerPool listeners = new ListenerPool();

    @Override
    protected StatesMap<ModelState, ModelAction> define(StatesBuilder<ModelState, ModelAction> builder) {
        return builder
                .from(Menu).on(Start).to(Map)
                           .on(Escape).to(Exit)
                .from(Map).on(Start).to(Level)
                .start(Menu);
    }

    public void doTransition(ModelAction trans) {
        super.doTransition(trans);
    }

    public void addListener(StateModelListener<ModelState> listener) {
        listeners.add(StateModelListener.class, listener);
    }

    public void removeListener(StateModelListener<ModelState> listener) {
        listeners.remove(StateModelListener.class, listener);
    }

    private void notifyListeners(ModelState tgt) {
        for(StateModelListener<ModelState> l : listeners.getListeners(StateModelListener.class)) {
            l.onModelStateChange(new StateModelEvent<ModelState>(tgt));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onTranstion(ModelAction trans, ModelState src, ModelState tgt) throws IllegalArgumentException {
        // create new model object
        switch(tgt) {
            case Menu:
                // use old menu model and invalidate others
            case Map:
                // create map or restore save state
            case Level:
                // where to get the level data?
        }

        notifyListeners(tgt);
    }
}
