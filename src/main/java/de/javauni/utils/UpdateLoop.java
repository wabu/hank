/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.utils;

import com.google.common.base.Function;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 *
 * @author wabu
 */
public class UpdateLoop implements Runnable {
    private final Thread thread;
    private final Function<Long, Void> task;
    private long lastTime;
    private long calcTime;
    private final long intervall;

    private final Object mutex = new Object();
    private boolean paused = false;

    /**
     * calls a task periodically. it will give the time since the last call
     * to as a argument to the task.
     * @param task task to be executed periodically
     * @param intervall aspired time intervall in ms between the calls
     * @param tf factory used to create a thread
     */
    public UpdateLoop(Function<Long, Void> task, int intervall, ThreadFactory tf) {
        this.task = task;
        this.intervall = intervall;
        this.thread = tf.newThread(this);
    }

    /**
     * calls a task periodically. it will give the time since the last call
     * to as a argument to the task.
     * @param task task to be executed periodically
     * @param intervall aspired time intervall in ms between the calls
     */
    public UpdateLoop(Function<Long, Void> task, int intervall) {
        this(task, intervall, Executors.defaultThreadFactory());
    }

    /**
     * calls a task periodically. it will give the time since the last call
     * to as a argument to the task.
     * @param task task to be executed periodically
     * @param tf factory used to create a thread
     */
    public UpdateLoop(Function<Long, Void> task, ThreadFactory tf) {
        this(task, 0, tf);
    }

    /**
     * calls a task periodically. it will give the time since the last call
     * to as a argument to the task.
     * @param task task to be executed periodically
     * it will try to
     */
    public UpdateLoop(Function<Long, Void> task) {
        this(task, 0, Executors.defaultThreadFactory());
    }

    public void start() throws IllegalStateException {
        try {
            lastTime = System.currentTimeMillis();
            thread.start();
        } catch(IllegalThreadStateException e) {
            throw new IllegalStateException(e);
        }
    }

    public void pause() throws IllegalStateException {
        if(!thread.isAlive()) {
            throw new IllegalStateException("can't pause, not running");
        }
        paused = true;
    }

    public void resume() throws IllegalStateException {
        if(!thread.isAlive()) {
            throw new IllegalStateException("can't resume, not running");
        }
        synchronized(mutex) {
            paused = false;
            mutex.notifyAll();
        }
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

    protected void update() {
        long time = System.currentTimeMillis();
        long delta = time - lastTime;
        lastTime = time;
        task.apply(delta);
        calcTime = System.currentTimeMillis() - lastTime;
    }
}
