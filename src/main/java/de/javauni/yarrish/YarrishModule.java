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

package de.javauni.yarrish;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import de.javauni.yarrish.model.level.LevelModule;
import de.javauni.yarrish.model.main.MainModelModule;
import de.javauni.yarrish.model.menu.MenuModule;
import de.javauni.yarrish.view.ViewModule;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * guice module to configure the game and bind all submodules
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public class YarrishModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Executor.class)
                .annotatedWith(Names.named("channel-broadcast-executor"))
                .toInstance(Executors.newSingleThreadExecutor());

        install(new MainModelModule());
        install(new MenuModule());
        install(new LevelModule());

        install(new ViewModule());
    }

}
