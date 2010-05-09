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

package de.javauni.jarcade.view;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




import de.javauni.jarcade.model.StateListener;
import de.javauni.jarcade.model.scene.Entity;
import de.javauni.jarcade.model.scene.Layer;
import de.javauni.jarcade.model.scene.SceneModelExport;
import de.javauni.jarcade.model.scene.ScenePhase;
import de.javauni.jarcade.model.scene.event.SceneChangeListener;
import de.javauni.jarcade.utils.guice.ManagedScope;
import de.javauni.jarcade.view.render.RendererFactory;
import de.javauni.jarcade.view.render.RendererMap;
import de.javauni.jarcade.view.render.RenderingLoop;

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
@ManagedScope
public class LevelView implements StateListener<ScenePhase>, SceneChangeListener {
    private final Logger log = LoggerFactory.getLogger(LevelView.class);
    private final RenderingLoop thread;
    private final RendererMap map;
    private final RendererFactory fac;

    @Inject
    public LevelView(SceneModelExport e, 
            RenderingLoop thread, RendererMap map, RendererFactory fac) {
        e.getScene().getSceneChannel().addListener(this);
        e.getStateChannel().addListener(this);
        this.thread = thread;
        this.map = map;
        this.fac = fac;
    }

    public void onStateChange(ScenePhase state) {
        log.debug("level is now {}",state);
        switch(state) {
            case initialized:
                thread.init();
                break;
            case intro:
            case outro:
            case warmup:
            case running:
                thread.start();
                break;
            case paused:
                thread.pause();
                break;
            case closed:
                thread.close();
                break;
        }
    }

    public void onLayerAdded(Layer layer) {
    }

    public void onEntitySpawned(Entity e, Layer layer) {
        log.debug("new entity {}",e);
        if(fac.isRenderable(e)) {
            map.put(e, fac.getRenderer(e));
            log.debug("added renderer {} for {}",map.get(e),e);
        }
    }

    public void onEntityRemoved(Entity e, Layer layer) {
        log.debug("entity {} removed",e);
    }

    public void onEntityLayerChange(Entity e, Layer before, Layer layer) {
        log.debug("entity {} channged layer", e);
    }

}
