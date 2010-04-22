package renderer.rendererThread;

import java.awt.Graphics2D;

import output.Output;
import renderer.Entity.Entity;
import renderer.rendererMap.RendererMap;
import ViewPort.ViewPortAccess;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class RendererThreadImpl extends Thread implements RendererThread{
	public static final int defaultFPS = 60;
	
	private boolean running = true;
	private long fps=60;
	private RendererMap rendererMap;
	private Output output;
	private ViewPortAccess viewPortAccess;
	@Inject public RendererThreadImpl(	@Named("fps") long fps,
										@Named("rendererMap") RendererMap rendererMap,
										@Named("output") Output output,
										@Named("viewPortAccess") ViewPortAccess viewPortAccess
									 ){
		if(fps==0){
			fps=60;
		}
		this.rendererMap=rendererMap;
		this.output=output;
		this.viewPortAccess=viewPortAccess;
		this.fps=fps;
	}
	
	@Override
	public void run() {
		while(running){
			try {
				Thread.sleep((long) (1E3/fps));
				output.clear();
				for(Entity e : viewPortAccess.getList()){
					rendererMap.get(e).render(e, (Graphics2D) output.getGhostGraphics(), 5);					
				}
				output.repaint();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@Override
	public void stopIt() {
		running=false;
		interrupt();
	}

	@Override
	public void setFramePerSecond(long fps) {
		if(fps==0)throw new IllegalArgumentException("fps can't be zero");
		this.fps=fps;		
	}

	@Override
	public long getFramePerSecond() {
		return this.fps;		
	}

}
