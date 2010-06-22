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

package de.javauni.jarcade.model.event;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/**
 * @param <L> listener type
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
class ChannelImpl<L extends Listener> implements Channel<L> {
    private final List<L> ls = new CopyOnWriteArrayList<L>();
    private final Executor exec;

    @Inject
    ChannelImpl(@Named("channel-broadcast-executor") Executor exec) {
        this.exec = exec;
    }

    public void addListener(L l) {
        ls.add(l);
    }

    public void removeListener(L l) {
        ls.remove(l);
    }

    public void broadcast(final Broadcastor<L> bc) {
        for(L l : ls) {
            bc.apply(l);
        }
        // XXX channel in same or other thread
        /*exec.execute(new Runnable() {
            public void run() {
            }
        });*/
    }

}
