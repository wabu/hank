/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.jarcade.impl.space;

import de.javauni.jarcade.model.space.Actity;
import de.javauni.jarcade.model.space.ActityAccess;
import de.javauni.utils.geom.Box;

/**
 * @param <A> activity enum type
 * @author wabu
 */
public class SimpleActiveEntity<A extends Enum<A>>
        extends SimpleCollidableEntity implements Actity<A>, ActityAccess<A> {

    private A activity;

    public SimpleActiveEntity(int id, Box pos, Box collision, A activity) {
        super(id, pos, collision);
        this.activity = activity;
    }

    public SimpleActiveEntity(int id, Box pos, A activity) {
        super(id, pos);
        this.activity = activity;
    }

    public A getCurrentActivity() {
        return activity;
    }

    public void setActity(A activity) {
        this.activity = activity;
    }

}
