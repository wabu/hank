/*
 *  Copyright (C) 2010 Daniel Waeber <wabu@inf.fu-berlin.de>
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package de.javauni.jarcade.impl.space;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.exceptions.IllegalAction;
import de.javauni.jarcade.impl.model.AbstractStateModel;
import de.javauni.jarcade.model.space.ManagedModelAccess;
import de.javauni.jarcade.model.space.Space;
import de.javauni.jarcade.model.space.SpaceChangeListener;
import de.javauni.jarcade.model.space.ManagedModelExport;
import de.javauni.jarcade.model.space.SpaceEdit;
import de.javauni.jarcade.model.space.SpacePhase;
import de.javauni.jarcade.model.space.logic.EntityHandler;
import de.javauni.jarcade.model.state.StateListener;
import de.javauni.utils.UpdateLoop;
import java.io.IOException;

/**
 * An AbstractMangedModel is...very useful
 *
 * What a ManagedModel does is the following:
 * initiates
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public abstract class AbstractManagedModel extends
                AbstractStateModel<SpacePhase> implements ManagedModelAccess,
                ManagedModelExport {
    private final EntityHandler logic;
    private final UpdateLoop loop;

    private final SpaceEdit space;

    @Inject
    public AbstractManagedModel(
            final Channel<StateListener<SpacePhase>> chan,
            final EntityHandlerFactory ehFactory,
            final SpaceEdit space,
            @Named("model-update-intervall") int intervall) {
        super(chan);
        this.logic = ehFactory.create(space.getZeroLayer());
        this.loop = new UpdateLoop(new Function<Long, Void>() {
            public Void apply(Long f) {
                logic.updateEntities(f);
                return null;
            }
        }, intervall);
        this.space = space;
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
        Preconditions.checkState(getState().ordinal() < SpacePhase.loading.ordinal(),
                "level allready initialized");

        setState(SpacePhase.loading);
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