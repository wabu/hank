/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.impl.space;

import de.javauni.jarcade.model.space.act.Movement;
import de.javauni.jarcade.model.space.act.Moving;
import de.javauni.jarcade.model.space.props.ImpliedProperty;
import de.javauni.jarcade.model.space.props.Property;
import de.javauni.utils.geom.Box;

/**
 *
 * @author wabu
 */
public class SimpleMovingActity extends SimpleActity<Movement> implements Moving {
    private final float speed;

    public SimpleMovingActity(
            int id,
            @ImpliedProperty(name="position") Box pos,
            @ImpliedProperty(name="collision") Box collision,
            @Property(name="movement speed") float speed) {
        super(id, pos, collision, Movement.none);
        this.speed = speed;
    }

    public float getMovementSpeed() {
        return speed;
    }
}
