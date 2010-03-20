/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.impl.space;

import de.javauni.jarcade.model.space.Entity;
import de.javauni.jarcade.model.space.logic.EntityLogic;
import de.javauni.jarcade.model.space.logic.LogicManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author wabu
 */
public class LogicManagerImpl implements LogicManager {
    private final Map<EntityLogic<Entity>, Set<Entity>> lmap
            = new HashMap<EntityLogic<Entity>, Set<Entity>>();
    private final Set<Entity> flaged = new HashSet<Entity>();

    public <E extends Entity> void addEntityLogic(E entity, EntityLogic<E> logic) {
        @SuppressWarnings("unchecked")
        EntityLogic<Entity> l = (EntityLogic<Entity>) logic;
        if(!lmap.containsKey(l)) {
            lmap.put(l, new HashSet<Entity>());
        }
        lmap.get(l).add(entity);
    }

    public void removeEntityLogic(Entity entity) {
        flaged.add(entity);
    }

    public void updateStep(long timeDelta) {
        for(Map.Entry<EntityLogic<Entity>, Set<Entity>> lm : lmap.entrySet()) {
            for(Entity e : lm.getValue()) {
                if(flaged.contains(e)) {
                    flaged.remove(e);
                    lm.getValue().remove(e);
                } else {
                    lm.getKey().update(e);
                }
            }
        }
    }

}
