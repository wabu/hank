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

package de.javauni.jarcade.view.gui.Level;

import com.google.inject.Inject;
import de.javauni.jarcade.control.AbstractControl;
import de.javauni.jarcade.control.playercontrol.PlayerControl;

import de.javauni.jarcade.model.control.CharacterControl;
import de.javauni.jarcade.model.entities.Entity;
import de.javauni.jarcade.model.scene.Layer;
import de.javauni.jarcade.model.scene.Scene;
import de.javauni.jarcade.model.scene.event.SceneChangeListener;
import de.javauni.jarcade.utils.guice.ManagedScope;
import de.javauni.jarcade.view.render.RendererFactory;
import de.javauni.jarcade.view.render.RendererMap;
import de.javauni.jarcade.view.render.RenderingLoop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.javauni.yarrish.model.level.CharacterControlListener;
import de.javauni.yarrish.model.level.LevelExport;

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
@ManagedScope
public class LevelView implements StateListener<ScenePhase>, 
       SceneChangeListener, CharacterControlListener {
    private final Logger log = LoggerFactory.getLogger(LevelView.class);
    private final RenderingLoop loop;
    private final RendererMap map;
    private final RendererFactory fac;
    private final PlayerControl kbCtl;

    @Inject
    public LevelView(LevelExport e, 
            RenderingLoop loop, 
            RendererMap map, 
            RendererFactory fac,
            PlayerControl kbCtl) {
        e.getScene().getSceneChannel().addListener(this);
        e.getStateChannel().addListener(this);
        e.getCharacterChannel().addListener(this);
        this.loop = loop;
        this.map = map;
        this.fac = fac;
        this.kbCtl = kbCtl;
    }

    @Override
    public void onStateChange(ScenePhase state) {
        log.debug("level is now {}",state);
        switch(state) {
            case initialized:
                loop.init();
                break;
            case intro:
            case warmup:
            case running:
            case outro:
                loop.start();
                break;
            case paused:
                loop.pause();
            case closed:
                loop.close();
                break;
        }
    }

    @Override
    public void onLayerAdded(Layer layer) {
    }

    @Override
    public void onEntitySpawned(Entity e, Layer layer) {
        log.debug("new entity {}",e);
        if(fac.isRenderable(e)) {
            map.put(e, fac.getRenderer(e));
        }
    }

    @Override
    public void onEntityRemoved(Entity e, Layer layer) {
        log.debug("entity {} removed",e);
    }

    @Override
    public void onEntityLayerChange(Entity e, Layer before, Layer layer) {
        log.debug("entity {} channged layer", e);
    }

    @Override
    public void newCharacterControlCreated(CharacterControl ctl, int num) {
        kbCtl.registerControl(ctl, num);
    }
}
