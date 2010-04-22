package de.javauni.jarcade.renderer.Thread;

public interface RendererThread extends Runnable{
	void stopIt();
	void setFramePerSecond(long fps) throws IllegalArgumentException;
	long getFramePerSecond();
}
