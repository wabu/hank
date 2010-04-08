/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.model.level;

import com.google.common.base.Function;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.impl.space.AbstractManagedModel;
import de.javauni.jarcade.impl.space.EntityHandlerFactory;
import de.javauni.jarcade.impl.space.SimpleCollidableEntity;
import de.javauni.jarcade.model.space.Entity;
import de.javauni.jarcade.model.space.SpacePhase;
import de.javauni.jarcade.model.state.StateListener;
import de.javauni.utils.geom.Box;
import de.javauni.utils.guice.ManagedScope;
import java.io.IOException;

/**
 *
 * @author wabu
 */
@ManagedScope
public class LevelModelImpl extends AbstractManagedModel implements LevelAccess, LevelExport {
    private final LevelSpace space;

    @Inject
    public LevelModelImpl(Channel<StateListener<SpacePhase>> chan,
            EntityHandlerFactory ehFactory,
            LevelSpace space,
            @Named("level-update-intervall") int intervall) {
        super(chan, ehFactory, space, intervall);
        this.space = space;
    }

    @Override
    public void loadLevel(String ressources) throws IOException {
        space.addEntity(new Function<Integer, Entity>() {
            public Entity apply(Integer f) {
                return new SimpleCollidableEntity(f, new Box(0, 200, 200, 100));
            }
        }, 0);
        space.addEntity(new Function<Integer, Entity>() {
            public Entity apply(Integer f) {
                return new HankEntity(f, new Box(20, 180, 20, 10));
            }
        }, 0);
        // TODO behavior, perhaps in super classes
    }
}
