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

import com.google.common.base.Function;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.impl.space.AbstractManagedModel;
import de.javauni.jarcade.impl.space.EntityHandlerFactory;
import de.javauni.jarcade.impl.space.LayerImpl;
import de.javauni.jarcade.impl.space.SimpleCollidableEntity;
import de.javauni.jarcade.model.scene.entity.Entity;
import de.javauni.jarcade.model.scene.ScenePhase;
import de.javauni.jarcade.model.StateListener;
import de.javauni.utils.geom.Box;
import de.javauni.utils.guice.ManagedScope;
import java.io.IOException;

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
@ManagedScope
public class LevelModelImpl extends AbstractManagedModel implements LevelAccess, LevelExport {
    private final LevelSpace space;

    @Inject
    public LevelModelImpl(Channel<StateListener<ScenePhase>> chan,
            EntityHandlerFactory ehFactory,
            LevelSpace space,
            @Named("level-update-intervall") int intervall) {
        super(chan, ehFactory, space, intervall);
        this.space = space;
    }

    @Override
    public void loadLevel(String ressources) throws IOException {
        // XXX layer channel foo
        space.getWorldBox().setBox(0,0, 100, 500);
        space.addLayer(new LayerImpl(0, 0, null));

        space.addEntity(new Function<Integer, Entity>() {
            public Entity apply(Integer f) {
                return new SimpleCollidableEntity(f, new Box(0, 200, 200, 100));
            }
        }, 0);
        space.addEntity(new Function<Integer, Entity>() {
            public Entity apply(Integer f) {
                return new HankEntity(f, new Box(20, 180, 20, 10));
            }
        }, 0);
        // TODO behavior, perhaps in super classes
    }

    public LevelSpace getSpace() {
        return space;
    }
}
