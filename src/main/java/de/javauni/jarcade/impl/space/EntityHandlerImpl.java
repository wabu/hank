/*
 *  Copyright (C) 2010 Daniel Waeber <wabu@inf.fu-berlin.de>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package de.javauni.jarcade.impl.space;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import de.javauni.jarcade.model.scene.entity.Entity;
import de.javauni.jarcade.model.scene.Layer;
import de.javauni.jarcade.model.scene.LayerChangeListener;
import de.javauni.jarcade.model.space.logic.EntityLogic;
import de.javauni.jarcade.model.space.logic.EntityHandler;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author wabu
 */
public class EntityHandlerImpl implements EntityHandler, LayerChangeListener {
    private final Map<EntityLogic<Entity>, Set<Entity>> lmap 
        = new HashMap<EntityLogic<Entity>, Set<Entity>>();
    private final Set<Entity> flaged = new HashSet<Entity>();
    private final Layer layer;
    private final EntityLogicFactory elFactory;

    @Inject
    protected EntityHandlerImpl(@Assisted Layer layer, EntityLogicFactory factory) {

        this.layer = layer;
        this.elFactory = factory;

        for (Entity e : layer) {
            this.onEntityAdded(e);
        }
        // subscribe this handler to associated layer,
        // so we can track all entity changes
        layer.getLayerChannel().addListener(this);
    }

    public <E extends Entity> void addEntityLogic(E entity, EntityLogic<E> logic) {
        @SuppressWarnings("unchecked")
        EntityLogic<Entity> l = (EntityLogic<Entity>) logic;
        if (!lmap.containsKey(l)) {
            lmap.put(l, new HashSet<Entity>());
        }
        lmap.get(l).add(entity);
    }

    public void removeEntityLogic(Entity entity) {
        flaged.add(entity);
    }

    public void updateEntities(long timeDelta) {
        /*
         * simple movemant: we call specific entity logic of each entity
         */
        for (Map.Entry<EntityLogic<Entity>, Set<Entity>> lm : lmap.entrySet()) {
            for (Entity e : lm.getValue()) {
                if (flaged.contains(e)) {
                    flaged.remove(e);
                    lm.getValue().remove(e);
                } else {
                    lm.getKey().update(e, timeDelta);
                }
            }
        }
        // TODO: Collision detetection
    }

    public void onEntityAdded(Entity e) {
        EntityLogic<Entity> logic = elFactory.getEntityLogic(e);
        if (logic != null) {
            addEntityLogic(e, logic);
        }
    }

    public void onEntityRemoved(Entity e) {
        removeEntityLogic(e);
    }
}

