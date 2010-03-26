/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.model.level;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.impl.space.SpaceImpl;
import de.javauni.jarcade.model.space.LayerEdit;
import de.javauni.jarcade.model.space.SpaceChangeListener;
import de.javauni.utils.geom.Box;
import de.javauni.utils.guice.ManagedScope;

/**
 *
 * @author wabu
 */
@ManagedScope
public class LevelSpace extends SpaceImpl {
    @Inject
    public LevelSpace(Channel<SpaceChangeListener> chan,
            @Named("level-size")
            Box size,
            @Named("zero-layer")
            LayerEdit zero) {
        super(chan, size, zero);
    }

}
