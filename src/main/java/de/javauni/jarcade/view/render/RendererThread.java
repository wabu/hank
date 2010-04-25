package de.javauni.jarcade.view.render;

public interface RendererThread extends Runnable{
    void start();
	void stop();

	void setFramePerSecond(long fps) throws IllegalArgumentException;
	long getFramePerSecond();
}
