package renderer.rendererThread;

import output.Output;
import renderer.rendererMap.RendererMap;

import ViewPort.ViewPortAccess;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

public class RendererThreadModul implements Module{
	final long FPS;
	private RendererMap rendererMap;
	private Output output;
	private ViewPortAccess viewPortAccess;

	public RendererThreadModul(long FPS, RendererMap rendererMap, Output output, ViewPortAccess viewPortAccess) {
		this.FPS=FPS;
		this.viewPortAccess=viewPortAccess;
		this.output=output;
		this.rendererMap=rendererMap;
	}
	
	public RendererThreadModul() {
		this.FPS=60;
	}

	@Override
	public void configure(Binder binder) {
		binder.bind(RendererThread.class).annotatedWith(Names.named("RendererThread")).to(RendererThreadImpl.class);
		binder.bind(Long.class).annotatedWith(Names.named("fps")).toInstance(FPS);
		binder.bind(RendererMap.class).annotatedWith(Names.named("rendererMap")).toInstance(rendererMap);
		binder.bind(Output.class).annotatedWith(Names.named("output")).toInstance(output);
		binder.bind(ViewPortAccess.class).annotatedWith(Names.named("viewPortAccess")).toInstance(viewPortAccess);	
	}

}
