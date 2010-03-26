/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.view;

import com.google.inject.Inject;
import de.javauni.jarcade.model.space.Entity;
import de.javauni.jarcade.model.space.Layer;
import de.javauni.jarcade.model.space.SpaceChangeListener;
import de.javauni.jarcade.model.space.SpacePhase;
import de.javauni.jarcade.model.state.StateListener;
import de.javauni.yarrish.model.level.LevelExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wabu
 */
public class LevelDummyView implements StateListener<SpacePhase>, SpaceChangeListener {
    private final Logger log = LoggerFactory.getLogger(LevelDummyView.class);

    @Inject
    public LevelDummyView(LevelExport e) {
        e.getSpaceChannel().addListener(this);
        e.getStateChannel().addListener(this);
    }

    public void onStateChange(SpacePhase state) {
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
