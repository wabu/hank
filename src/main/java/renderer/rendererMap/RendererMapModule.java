package renderer.rendererMap;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

public class RendererMapModule implements Module
{

	@Override
	public void configure(Binder binder)
	{
		binder.bind(RendererMap.class).annotatedWith(Names.named("RendererMap")).to(RendererMapImpl.class);	
	}

}
