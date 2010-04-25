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

package de.javauni.yarrish.model;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryProvider;
import com.google.inject.name.Names;
import de.javauni.jarcade.impl.scene.EntityHandlerFactory;
import de.javauni.jarcade.impl.scene.EntityHandlerImpl;
import de.javauni.jarcade.model.scene.Scene;
import de.javauni.yarrish.model.level.LevelAccess;
import de.javauni.yarrish.model.level.LevelExport;
import de.javauni.yarrish.model.level.LevelModelImpl;

import de.javauni.yarrish.model.level.LevelScene;

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public class LevelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LevelAccess.class).to(LevelModelImpl.class);
        bind(LevelExport.class).to(LevelModelImpl.class);
        bind(Scene.class).to(LevelScene.class);

        bind(Integer.class).annotatedWith(Names.named("level-update-intervall"))
                .toInstance(20);
        
        bind(EntityHandlerFactory.class).toProvider(
                FactoryProvider.newFactory(EntityHandlerFactory.class, EntityHandlerImpl.class));

    }
}
