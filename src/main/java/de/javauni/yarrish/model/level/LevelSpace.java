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
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
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
