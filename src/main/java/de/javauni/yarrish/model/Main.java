/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.javauni.yarrish.model.states.ModelState;
import de.javauni.yarrish.view.DummyView;

/**
 *
 * @author wabu
 */
public class Main {
    public static void main(String[] args) {
        Injector inj = Guice.createInjector(new YarrishModule());
        YarrishModel ym = inj.getInstance(YarrishModel.class);
        inj.getInstance(DummyView.class);

        ym.setState(ModelState.Menu);
        ym.setState(ModelState.Map);
        ym.setState(ModelState.Menu);
    }
}
