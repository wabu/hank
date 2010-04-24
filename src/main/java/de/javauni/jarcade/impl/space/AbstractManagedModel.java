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
import de.javauni.jarcade.model.scene.ManagedModelAccess;
import de.javauni.jarcade.model.scene.Scene;
import de.javauni.jarcade.model.scene.SceneChangeListener;
import de.javauni.jarcade.model.scene.ManagedModelExport;
import de.javauni.jarcade.model.scene.SceneEdit;
import de.javauni.jarcade.model.scene.ScenePhase;
import de.javauni.jarcade.model.space.logic.EntityHandler;
import de.javauni.jarcade.model.StateListener;
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
                AbstractStateModel<ScenePhase> implements ManagedModelAccess,
                ManagedModelExport {
    private final EntityHandler logic;
    private final UpdateLoop loop;

    private final SceneEdit space;

    @Inject
    public AbstractManagedModel(
            final Channel<StateListener<ScenePhase>> chan,
            final EntityHandlerFactory ehFactory,
            final SceneEdit space,
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
    protected void doStateTransition(ScenePhase src, ScenePhase tgt) throws IllegalAction {
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
        Preconditions.checkState(getState().ordinal() < ScenePhase.loading.ordinal(),
                "level allready initialized");

        setState(ScenePhase.loading);
        loadLevel(ressources);

        setState(ScenePhase.initialized);
    }

    public Channel<SceneChangeListener> getSpaceChannel() {
        return space.getSpaceChannel();
    }

    public Scene getSpace() {
        return space;
    }
}
