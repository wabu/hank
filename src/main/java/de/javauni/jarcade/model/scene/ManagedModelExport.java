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

package de.javauni.jarcade.model.scene;

import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.model.StateModelExport;

/**
 *
<<<<<<< HEAD:src/main/java/de/javauni/jarcade/model/space/ManagedModelExport.java
 *
 * @author wabu
=======
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
>>>>>>> added license: gpl with cp ex:src/main/java/de/javauni/jarcade/model/space/SimulationExport.java
 */
public interface ManagedModelExport extends StateModelExport<ScenePhase>{
    Channel<SceneChangeListener> getSpaceChannel();
    Scene getSpace();
}
