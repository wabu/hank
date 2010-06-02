package de.javauni.jarcade.control;

/**
 * @author Michael Kmoch, Simon Lang
 */

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;

import de.javauni.jarcade.control.controlmanagement.ControlManagement;
import de.javauni.jarcade.control.controlmanagement.ControlManagementImpl;
import de.javauni.jarcade.control.playercontrol.PlayerControl;
import de.javauni.jarcade.control.playercontrol.PlayerControlImpl;
import de.javauni.jarcade.control.playercontrol.PlayerControlMap;
import de.javauni.jarcade.control.playercontrol.PlayerControlMapImpl;
import de.javauni.jarcade.model.main.MainModelExport;
import de.javauni.jarcade.model.main.MainModelImpl;

public class ControlModule implements Module {

    @Override
    public void configure(Binder binder) {
        // we bind only the factory interface
        binder.bind(PlayerControl.class)
            .to(PlayerControlImpl.class);
        binder.bind(PlayerControlMap.class)
            .to(PlayerControlMapImpl.class).in(Scopes.SINGLETON);
        binder.bind(ControlManagement.class)
                .to(ControlManagementImpl.class);
    }

}
