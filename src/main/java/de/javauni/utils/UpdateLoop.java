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

package de.javauni.utils;

import java.util.concurrent.ThreadFactory;

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public abstract class UpdateLoop implements Runnable {
    private final Thread thread;
    private long lastTime;
    private long calcTime;
    private final long intervall;

    private final Object mutex = new Object();
    private boolean paused = true;

    /**
     * calls a task periodically. it will give the time since the last call
     * to as a argument to the task.
     * @param intervall aspired time intervall in ms between the calls
     * @param tf factory used to create a thread
     */
    protected UpdateLoop(int intervall, ThreadFactory tf) {
        this.intervall = intervall;
        this.thread = tf.newThread(this);
    }

    public void init() throws IllegalStateException {
        try {
            thread.start();
        } catch(IllegalThreadStateException e) {
            throw new IllegalStateException(e);
        }
    }

    public void start() throws IllegalStateException {
        if(!thread.isAlive()) {
            throw new IllegalStateException("can't resume, not running");
        }
        synchronized(mutex) {
            lastTime = System.currentTimeMillis();
            paused = false;
            mutex.notifyAll();
        }
    }

    public void pause() throws IllegalStateException {
        if(!thread.isAlive()) {
            throw new IllegalStateException("can't pause, not running");
        }
        paused = true;
    }

    public void close() {
        thread.interrupt();
    }

    public void run() {
        while(!Thread.interrupted()) {
            while(!Thread.interrupted() && !paused) {
                try {
                    long s = intervall - calcTime;
                    if(s > 0) {
                        Thread.sleep(s);
                    }
                } catch (InterruptedException ex) {
                    return;
                }
                update();
            }
            try {
                synchronized(mutex) {
                    while(paused) {
                        mutex.wait();
                    }
                }
            } catch (InterruptedException ex) {
                return;
            }
        }
    }
    
    protected abstract void update(long delta);

    protected void update() {
        long time = System.currentTimeMillis();
        long delta = time - lastTime;
        lastTime = time;
        update(delta);
        calcTime = System.currentTimeMillis() - lastTime;
    }
}
