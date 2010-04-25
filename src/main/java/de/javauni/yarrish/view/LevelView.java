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

package de.javauni.yarrish.view;

import com.google.inject.Inject;
import de.javauni.jarcade.model.scene.entity.Entity;
import de.javauni.jarcade.model.scene.Layer;
import de.javauni.jarcade.model.scene.SceneChangeListener;
import de.javauni.jarcade.model.scene.ScenePhase;
import de.javauni.jarcade.model.StateListener;
import de.javauni.yarrish.model.level.LevelExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public class LevelView implements StateListener<ScenePhase>, SceneChangeListener {
    private final Logger log = LoggerFactory.getLogger(LevelView.class);

    @Inject
    public LevelView(LevelExport e) {
        e.getScene().getSceneChannel().addListener(this);
        e.getStateChannel().addListener(this);
    }

    public void onStateChange(ScenePhase state) {
        log.debug("level state is now {}",state);
    }

    public void onEntitySpawned(Entity e, Layer layer) {
        log.debug("new entity {}",e);
    }

    public void onEntityRemoved(Entity e, Layer layer) {
        log.debug("entity {} removed",e);
    }

    public void onEntityLayerChange(Entity e, Layer before, Layer layer) {
        log.debug("entity {} channged layer", e);
    }

}
