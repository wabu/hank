/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.view;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.javauni.jarcade.model.MenuModel;
import de.javauni.jarcade.model.state.StateListener;
import de.javauni.jarcade.model.state.StateModelExport;
import de.javauni.yarrish.model.states.ModelState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wabu
 */
public class DummyView implements StateListener<ModelState> {
    private final Logger log = LoggerFactory.getLogger(DummyView.class);
    private final StateModelExport<ModelState> model;

    private final Provider<MenuModel> mmp;

    @Inject
    public DummyView(StateModelExport<ModelState> model, Provider<MenuModel> mmp) {
        this.model = model;
        this.mmp = mmp;

        model.getStateChannel().addListener(this);
    }

    public void onStateChange(ModelState state) {
        switch(state) {
            case Menu:
                log.info("showing menu");

                MenuModel mm = mmp.get();
                log.debug("got a menu model: "+mm);

                break;
            default:
                log.error("unimplemented view {}", state);
                break;
        }
    }
}
