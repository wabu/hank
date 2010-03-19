/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.view;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.javauni.jarcade.model.menu.MenuModelExport;
import de.javauni.jarcade.model.state.StateListener;
import de.javauni.yarrish.model.main.MainModelExport;
import de.javauni.yarrish.model.main.MainState;
import de.javauni.yarrish.model.menu.MainMenuExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wabu
 */
public class DummyView implements StateListener<MainState> {
    private final Logger log = LoggerFactory.getLogger(DummyView.class);
    private final MainModelExport model;

    private final Provider<MainMenuExport> mmp;

    @Inject
    public DummyView(MainModelExport model, Provider<MainMenuExport> mmp) {
        this.model = model;
        this.mmp = mmp;

        model.getStateChannel().addListener(this);
    }

    public void onStateChange(MainState state) {
        assert state == model.getState();
        switch(state) {
            case Menu:
                log.info("showing menu");

                MainMenuExport mm = mmp.get();
                log.debug("got a menu model: "+mm);

                break;
            default:
                log.error("unimplemented view {}", state);
                break;
        }
    }
}
