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

package de.javauni.jarcade.utils.guice;

import com.google.inject.Key;
import com.google.inject.Provider;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
class ScopeMap {
    private final Map<Key<?>, Object> scoped = new HashMap<Key<?>, Object>();

    @SuppressWarnings("unchecked")
    public <T> T get(Key<T> key, Provider<T> prvdr) {
        if(!scoped.containsKey(key)) {
            scoped.put(key, prvdr.get());
        }
        return (T) scoped.get(key);
    }
}
