/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.model.level;

import de.javauni.jarcade.impl.space.SimpleActiveEntity;
import de.javauni.jarcade.model.space.act.Movement;
import de.javauni.jarcade.model.space.act.Moving;
import de.javauni.utils.geom.Box;
import de.javauni.utils.geom.Coord;

/**
 * @author wabu
 */
public class HankEntity extends SimpleActiveEntity<Movement> implements Moving {
    public final static float DEFAULT_HANK_W = 0.8f;
    public final static float DEFAULT_HANK_H = 1.8f;

    public HankEntity(int id, Coord coord) {
        this(id, new Box(coord,
                DEFAULT_HANK_W, DEFAULT_HANK_H));
    }
    public HankEntity(int id, Box pos) {
        super(id, pos, Movement.none);
    }

    public float getMovementSpeed() {
        // TODO extract
        return 1f;
    }
}
