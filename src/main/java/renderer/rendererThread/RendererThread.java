package renderer.rendererThread;

public interface RendererThread extends Runnable{
	void stopIt();
	void setFramePerSecond(long fps) throws IllegalArgumentException;
	long getFramePerSecond();
}
