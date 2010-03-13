/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.model;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.event.ChannelImpl;
import de.javauni.utils.guice.DynamicProiver;
import de.javauni.jarcade.impl.states.AbstractStates;
import de.javauni.jarcade.impl.states.StatesBuilder;
import de.javauni.jarcade.impl.states.StatesMap;
import de.javauni.yarrish.model.MainStates.Action;
import de.javauni.yarrish.model.MainStates.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static de.javauni.yarrish.model.MainStates.State.*;
import static de.javauni.yarrish.model.MainStates.Action.*;

/**
 *
 * @author wabu
 */
public class MainStates extends AbstractStates<State,Action> {
    public enum State {
        Splash, Menu, Map, Lvl
    }
    public enum Action {
        Escape, Finish, Start
    }

    @Override
    protected StatesMap<State,Action> define(StatesBuilder<State, Action> builder) {
        return builder
               .from(Splash).on(Start,Finish)   .to(Menu)
               .from(Menu)  .on(Start)          .to(Lvl)
                            .on(Finish, Escape) .to(Splash)
               .from(Lvl)   .on(Escape)         .to(Menu)
                            .on(Finish)         .to(Lvl)

               .start(Splash);
    }

    private final Logger log = LoggerFactory.getLogger(MainStates.class);
    private final Channel<State> chan = new ChannelImpl<State>();
    private final Provider<LvlModel> lvls;

    @Inject
    public MainStates(Provider<LvlModel> lvls) {
        super();
        this.lvls = lvls;
    }

    @Inject
    protected void init(DynamicProiver<Channel<State>> p) {
        log.debug("binding dynamic channel");
        p.setInstance(chan);
    }

    @Override
    protected void onTranstion(Action trans, State src, State tgt) throws IllegalArgumentException {
        log.debug(">{}> {}", trans, tgt);
        // TODO manage lifecircle of submodels
        switch(tgt) {
            case Splash:
                System.exit(0);
                break;
            case Menu:
                log.info("showing menu ...");
                break;
            case Lvl:
                log.info("loading level ...");
                lvls.get();
                break;
        }
        chan.broadcast(tgt);
    }
}
