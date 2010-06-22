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

package de.javauni.jarcade.model.scene.impl;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;

import de.javauni.jarcade.model.StateModel;

import de.javauni.jarcade.model.impl.AbstractStateModel;

import de.javauni.jarcade.model.impl.event.Channel;
import de.javauni.jarcade.model.scene.Scene;
import de.javauni.jarcade.model.scene.SceneModel;
import de.javauni.jarcade.model.scene.impl.operate.SceneUpdateLoop;

public abstract class SceneModelImpl extends AbstractStateModel<SceneModel.Phase> implements SceneModel {
    private final Scene.Edit scene;
    private final SceneUpdateLoop loop;

    @Inject
    public SceneModelImpl(
            final Channel<StateModel.ChangeListener<SceneModel.Phase>> chan,
            final Scene.Edit space,
            final SceneUpdateLoop loop) {
        super(chan, SceneModel.Phase.loading);
        this.loop = loop;
        this.scene = space;
    }

    @Override
    protected void doStateTransition(SceneModel.Phase src, SceneModel.Phase tgt) 
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
    
    public abstract void loadLevel(String ressources);

    @Override
    public void initialize(String ressources) {
        Preconditions.checkState(getState().ordinal() <= SceneModel.Phase.loading.ordinal(),
                "level allready initialized");

        setState(SceneModel.Phase.loading);
        loadLevel(ressources);

        setState(SceneModel.Phase.initialized);
    }

    @Override
    public Scene getScene() {
        return scene;
    }
}
