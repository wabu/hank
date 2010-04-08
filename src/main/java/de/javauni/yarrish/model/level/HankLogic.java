/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.model.level;

import de.javauni.jarcade.model.space.Entity;
import de.javauni.jarcade.model.space.act.Movement;
import de.javauni.jarcade.model.space.logic.EntityLogic;

/**
 *
 * @author covin
 */
public class HankLogic implements EntityLogic<HankEntity>{
    public void update(HankEntity entity, long timeDelta) {
        switch(entity.getCurrentActivity()) {
            case left:
                float x = entity.getPositionBox().getX();
                // TODO something
                break;
        }
    }
}
