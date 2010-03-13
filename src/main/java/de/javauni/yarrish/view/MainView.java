/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.view;

import com.google.inject.Inject;
import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.event.Listener;
import de.javauni.yarrish.model.MainStates;
import de.javauni.yarrish.model.MainStates.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wabu
 */
public class MainView implements Listener<MainStates.State>{
    private final Logger log = LoggerFactory.getLogger(MainView.class);

    @Inject
    public MainView(Channel<MainStates.State> chan) {
        chan.suscribe(this);
    }

    public void on(State event) {
        log.info("viewing "+event);
    }
}
