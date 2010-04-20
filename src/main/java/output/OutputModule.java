package output;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

public class OutputModule implements Module{
	private final String NAME="Hank";
	private final int X=0;
	private final int Y=0;
	private final int WIDTH=800;
	private final int Height=600;
	@Override
	public void configure(Binder binder) {
		binder.bind(Output.class).annotatedWith(Names.named("Hallo-Sager")).to(OutputImpl.class);
		binder.bind(String.class).annotatedWith(Names.named("name")).toInstance(NAME);
		binder.bind(Integer.class).annotatedWith(Names.named("x")).toInstance(X);
		binder.bind(Integer.class).annotatedWith(Names.named("y")).toInstance(Y);
		binder.bind(Integer.class).annotatedWith(Names.named("height")).toInstance(Height);
		binder.bind(Integer.class).annotatedWith(Names.named("width")).toInstance(WIDTH);
	}
	
}
