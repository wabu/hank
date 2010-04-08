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

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.assistedinject.FactoryProvider;
import com.google.inject.name.Names;
import de.javauni.jarcade.event.ChannelImpl;
import de.javauni.jarcade.impl.space.EntityHandlerFactory;
import de.javauni.jarcade.impl.space.EntityHandlerImpl;
import de.javauni.jarcade.impl.space.LayerImpl;
import de.javauni.jarcade.model.space.LayerChangeListener;
import de.javauni.jarcade.model.space.LayerEdit;
import de.javauni.utils.geom.Box;
import java.util.concurrent.Executors;

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public class LevelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LevelAccess.class).to(LevelModelImpl.class);
        bind(LevelExport.class).to(LevelModelImpl.class);

        bind(Integer.class).annotatedWith(Names.named("level-update-intervall"))
                .toInstance(20);
        bind(Box.class).annotatedWith(Names.named("level-size"))
                .toInstance(new Box(0, 0, 1000, 1000));
        
        // TODO new module
        bind(LayerEdit.class).annotatedWith(Names.named("zero-layer"))
                .toProvider(new Provider<LayerEdit>(){
            public LayerEdit get() {
                // FIXME get channel implementation or remove this code
                return new LayerImpl(0, 100, new ChannelImpl<LayerChangeListener>(Executors.newSingleThreadExecutor()));
            }
        });
        bind(EntityHandlerFactory.class).toProvider(
                FactoryProvider.newFactory(EntityHandlerFactory.class, EntityHandlerImpl.class));

    }
}
