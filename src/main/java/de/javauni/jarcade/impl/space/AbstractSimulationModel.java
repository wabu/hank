/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.impl.space;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.exceptions.IllegalAction;
import de.javauni.jarcade.impl.model.AbstractStateModel;
import de.javauni.jarcade.model.space.Entity;
import de.javauni.jarcade.model.space.SimulationAccess;
import de.javauni.jarcade.model.space.Space;
import de.javauni.jarcade.model.space.SpaceChangeListener;
import de.javauni.jarcade.model.space.SimulationExport;
import de.javauni.jarcade.model.space.SpaceEdit;
import de.javauni.jarcade.model.space.SpacePhase;
import de.javauni.jarcade.model.space.logic.EntityLogic;
import de.javauni.jarcade.model.space.logic.LogicManager;
import de.javauni.jarcade.model.state.StateListener;
import de.javauni.utils.UpdateLoop;
import java.io.IOException;

/**
 *
 * @author wabu
 */
public abstract class AbstractSimulationModel extends AbstractStateModel<SpacePhase> implements SimulationAccess, SimulationExport {
    private final LogicManager logic;
    private final UpdateLoop loop;

    private final SpaceEdit space;

    @Inject
    public AbstractSimulationModel(
            final Channel<StateListener<SpacePhase>> chan,
            final LogicManager logic,
            final SpaceEdit space,
            @Named("model-update-intervall") int intervall) {
        super(chan);
        this.logic = logic;
        this.loop = new UpdateLoop(new Function<Long, Void>() {
            public Void apply(Long f) {
                logic.updateStep(f);
                return null;
            }
        }, intervall);
        this.space = space;
    }

    protected void removeEntityLogic(Entity entity) {
        logic.removeEntityLogic(entity);
    }

    protected <E extends Entity> void addEntityLogic(E entity, EntityLogic<E> logic) {
        this.logic.addEntityLogic(entity, logic);
    }

    @Override
    protected void doStateTransition(SpacePhase src, SpacePhase tgt) throws IllegalAction {
        switch(tgt) {
            case loading:
                break;
            case initialized:
                loop.init();
                break;
            case warmup:
                break;
            case running:
                loop.start();
                break;
            case paused:
                loop.pause();
                break;
            case closed:
                loop.close();
                break;
            case intro:
            case outro:
            default:
                throw new UnsupportedOperationException(tgt+" not supported");
        }
    }
    
    public abstract void loadLevel(String ressources) throws IOException;

    public void initialize(String ressources) throws IllegalStateException, IOException {
        setState(SpacePhase.loading);

        Preconditions.checkState(
                getState() == SpacePhase.loading, "level allready initialized");
        loadLevel(ressources);

        setState(SpacePhase.initialized);
    }

    public Channel<SpaceChangeListener> getSpaceChannel() {
        return space.getSpaceChannel();
    }

    public Space getSpace() {
        return space;
    }
}