package de.javauni.jarcade.view;

import de.javauni.jarcade.impl.space.SimpleEntity;
import java.awt.Color;
import java.awt.Graphics2D;

import de.javauni.utils.geom.Box;

public class SimpleEntityRenderer implements Renderer<SimpleEntity> {

    @Override
    public void render(SimpleEntity entity, Graphics2D gfx, long timeDelta, long levelTime) {
		Box box = entity.getPositionBox();
		gfx.setColor(Color.black);
		gfx.fillRect((int)box.getX(), (int)box.getY(), (int)box.getH(), (int)box.getW());
    }

}
