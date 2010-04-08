/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.impl.space;

import de.javauni.jarcade.model.space.CollidableEntity;
import de.javauni.jarcade.model.space.props.ImpliedProperty;
import de.javauni.utils.geom.Box;

/**
 *
 * @author wabu
 */
public class SimpleCollidableEntity extends SimpleEntity implements CollidableEntity {
    private final Box collision;

    public SimpleCollidableEntity(int id, Box pos) {
        super(id, pos);
        collision = pos;
    }

    public SimpleCollidableEntity(int id,
            @ImpliedProperty(name="position") Box pos,
            @ImpliedProperty(name="collision") Box collision) {
        super(id, pos);
        this.collision = collision;
    }

    public Box getCollisionBox() {
        return collision;
    }
}
