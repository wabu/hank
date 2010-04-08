package teil3;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

public class HalloSagerModul implements Module {
		public void configure(Binder binder) {
                binder.bind(IAnzeige.class).to(HalloSager.class);
                binder.bind(Integer.class).annotatedWith(Names.named("intwert")).toInstance(42);
        }
}