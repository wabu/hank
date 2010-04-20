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

package de.javauni.utils.guice;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.google.inject.internal.Nullable;
import javax.annotation.CheckForNull;

public class ManagedScopeImpl implements Scope {
    @CheckForNull private ScopeMap scope;

    public <T> Provider<T> scope(final Key<T> key, final Provider<T> prvdr) {
        return new Provider<T>() {
            public T get() {
                ScopeMap sm = scope;
                if(sm == null) {
                    return prvdr.get();
                } else {
                    return sm.get(key, prvdr);
                }
            }
        };
    }

    public void setScope(@Nullable ScopeMap scope) {
        this.scope = scope;
    }
}

