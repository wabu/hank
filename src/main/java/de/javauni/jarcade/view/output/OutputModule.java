package de.javauni.jarcade.view.output;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

public class OutputModule implements Module{
	private final static String NAME="Hank";
	private final static int X=0;
	private final static int Y=0;
	private final static int WIDTH=800;
	private final static int HEIGHT=600;

	@Override
	public void configure(Binder binder) {
		binder.bind(Output.class).to(OutputImpl.class);

		binder.bind(String.class).annotatedWith(Names.named("name")).toInstance(NAME);
		binder.bind(Integer.class).annotatedWith(Names.named("x")).toInstance(X);
		binder.bind(Integer.class).annotatedWith(Names.named("y")).toInstance(Y);
		binder.bind(Integer.class).annotatedWith(Names.named("height")).toInstance(HEIGHT);
		binder.bind(Integer.class).annotatedWith(Names.named("width")).toInstance(WIDTH);

	}
	
}
