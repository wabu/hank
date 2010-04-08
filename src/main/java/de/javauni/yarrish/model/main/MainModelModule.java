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

package de.javauni.yarrish.model.main;

import com.google.inject.AbstractModule;
import de.javauni.utils.guice.ScopeManagerModule;

/**
 * guice submodule for the main state
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public class MainModelModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ScopeManagerModule());
        bind(MainModelAccess.class).to(MainModelImpl.class);
        bind(MainModelExport.class).to(MainModelImpl.class);
    }

}
