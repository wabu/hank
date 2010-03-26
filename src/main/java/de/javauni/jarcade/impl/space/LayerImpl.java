/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.impl.space;

import de.javauni.jarcade.model.space.Entity;
import de.javauni.jarcade.model.space.LayerEdit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author wabu
 */
public class LayerImpl implements LayerEdit {
    private final int index;
    private final int distance;

    private final List<Entity> entities
            = new ArrayList<Entity>();

    public LayerImpl(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    public int getIndex() {
        return index;
    }

    public int getDistance() {
        return distance;
    }

    public Iterator<Entity> iterator() {
        return entities.iterator();
    }

    public void add(Entity e) {
        entities.add(e);
    }

    public void remove(Entity e) throws NoSuchElementException {
        if(!entities.remove(e)){
            throw new NoSuchElementException("layer contains no entity "+e.getId());
        }
    }
}
