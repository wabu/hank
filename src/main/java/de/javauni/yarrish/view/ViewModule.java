/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.view;

import com.google.inject.AbstractModule;

/**
 *
 * @author wabu
 */
public class ViewModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DummyView.class);
        bind(LevelDummyView.class);
    }

}
