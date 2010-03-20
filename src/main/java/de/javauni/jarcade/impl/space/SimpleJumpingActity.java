/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.impl.space;

import de.javauni.jarcade.model.space.act.Jumping;
import de.javauni.jarcade.model.space.props.ImpliedProperty;
import de.javauni.jarcade.model.space.props.Property;
import de.javauni.utils.geom.Box;

/**
 * @author wabu
 */
public class SimpleJumpingActity extends SimpleMovingActity implements Jumping {
    private final float jump;

    public SimpleJumpingActity(
            int id,
            @ImpliedProperty(name="position") Box pos,
            @ImpliedProperty(name="collision") Box collision,
            @Property(name="movement speed") float speed,
            @Property(name="jumping height") float jump
            ) {
        super(id, pos, collision, speed);
        this.jump = jump;
    }

    public float getJumpHeight() {
        return jump;
    }
}
