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

package de.javauni.jarcade.model.scene;

import java.io.IOException;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;


import de.javauni.jarcade.model.AbstractStateModel;
import de.javauni.jarcade.model.StateListener;
import de.javauni.jarcade.model.event.Channel;
import de.javauni.jarcade.model.scene.operate.SceneUpdateLoop;


/**
 * the scene model manages a scene and its operation
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public abstract class SceneModelImpl extends
                AbstractStateModel<ScenePhase> implements SceneModelAccess,
                SceneModelExport {
    private final SceneEdit scene;
    private final SceneUpdateLoop loop;

    @Inject
    public SceneModelImpl(
            final Channel<StateListener<ScenePhase>> chan,
            final SceneEdit space,
            final SceneUpdateLoop loop) {
        super(chan, ScenePhase.loading);
        this.loop = loop;
        this.scene = space;
    }

    @Override
    protected void doStateTransition(ScenePhase src, ScenePhase tgt) 
            throws IllegalArgumentException {
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

    @Override
    public void initialize(String ressources) throws IllegalStateException, IOException {
        Preconditions.checkState(getState().ordinal() <= ScenePhase.loading.ordinal(),
                "level allready initialized");

        setState(ScenePhase.loading);
        loadLevel(ressources);

        setState(ScenePhase.initialized);
    }

    @Override
    public Scene getScene() {
        return scene;
    }
}
