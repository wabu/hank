/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.model.level;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.name.Names;
import de.javauni.jarcade.impl.space.LayerImpl;
import de.javauni.jarcade.model.space.LayerEdit;
import de.javauni.utils.geom.Box;

/**
 *
 * @author wabu
 */
public class LevelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LevelAccess.class).to(LevelModelImpl.class);
        bind(LevelExport.class).to(LevelModelImpl.class);

        bind(Integer.class).annotatedWith(Names.named("level-update-intervall"))
                .toInstance(20);
        bind(Box.class).annotatedWith(Names.named("level-size"))
                .toInstance(new Box(0, 0, 1000, 1000));
        
        // TODO new module
        bind(LayerEdit.class).annotatedWith(Names.named("zero-layer"))
                .toProvider(new Provider<LayerEdit>(){
            public LayerEdit get() {
                return new LayerImpl(0, 100);
            }
        });

    }
}
