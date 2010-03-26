/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.view;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.javauni.jarcade.model.state.StateListener;
import de.javauni.yarrish.model.level.LevelExport;
import de.javauni.yarrish.model.main.MainModelExport;
import de.javauni.yarrish.model.main.MainState;
import de.javauni.yarrish.model.menu.MainMenuExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * dummy view that shows how everything can be combined
 * @author wabu
 */
public class DummyView implements StateListener<MainState> {
    private final Logger log = LoggerFactory.getLogger(DummyView.class);
    private final MainModelExport model;

    private final Provider<LevelDummyView> lvlProvider;

    // use constructor to get access to the model export
    // and providers, if you want to get submodles/subview when the main state changes
    @Inject
    public DummyView(MainModelExport model, Provider<LevelDummyView> lvlProvider) {
        this.model = model;
        this.lvlProvider = lvlProvider;

        model.getStateChannel().addListener(this);
    }

    public void onStateChange(MainState state) {
        assert state == model.getState();

        switch(state) {
            case Level:
                log.info("huhu level");
                LevelDummyView lvl = lvlProvider.get();
                log.debug("lvl view crated: "+lvl);

                break;
            default:
                log.error("unimplemented view {}", state);
                break;
        }
    }
}
