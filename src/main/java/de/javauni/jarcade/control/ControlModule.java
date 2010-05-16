package de.javauni.jarcade.control;

/**
 * @author Michael Kmoch, Simon Lang
 */

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryProvider;

public class ControlModule implements Module {

    @Override
    public void configure(Binder binder) {
        // we bind only the factory interface
        binder.bind(KeyboardControl.class)
            .to(KeyboardControlImpl.class);

        binder.bind(KeyboardControlMap.class)
            // don't use @Named Annotations if the type is non-ambiguous
                // (for anything not String, Int, Long)
            /*.annotatedWith(Names.named("KeyboardControlMap")) */
            .to(KeyboardControlMapImpl.class);
    }

}
