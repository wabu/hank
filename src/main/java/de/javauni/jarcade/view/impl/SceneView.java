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

package de.javauni.jarcade.view.impl;

import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;

import com.google.inject.Inject;

import de.javauni.jarcade.geom.Bound;

import de.javauni.jarcade.model.scene.Entity;
import de.javauni.jarcade.model.scene.Scene;
import de.javauni.jarcade.presenter.rendering.RendererMap;
import de.javauni.jarcade.utils.guice.ManagedScope;

import de.javauni.jarcade.view.RenderedView;

/**
 *
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
@ManagedScope
public class SceneView implements RenderedView<Scene, JavaGraphicsContext> {
    private final Scene scene;

    @Inject
    public SceneView(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void bindGraphics(JavaGraphicsContext gc) {
    }

    @Override
    public void updateGraphics(JavaGraphicsContext gc) {
    }

    @Override
    public void unbindGraphics() {
    }

    @Override
    public void render(JavaGraphicsContext gfx, RendererMap<JavaGraphicsContext> map, long delta) {
        Bound vb = scene.getViewport().getViewport();
        Bound rb = gfx.getBound();

        // TODO extract to helper class
        float vlx = vb.getLowerBound().x();
        float vux = vb.getUpperBound().x();
        float vly = vb.getLowerBound().y();
        float vuy = vb.getUpperBound().y();
        float vw = vux - vlx;
        float vh = vuy - vly;

        float rlx = rb.getLowerBound().x();
        float rux = rb.getUpperBound().x();
        float rly = rb.getLowerBound().y();
        float ruy = rb.getUpperBound().y();
        float rw = rux - rlx;
        float rh = ruy - rly;

        Graphics2D g = gfx.getGraphics();

        g.clearRect(0, 0, (int)rw, (int)rh);

        g.getTransform();
        // push viewport to level transformation
        g.translate(0, ruy);
        g.scale(rh/vh, -rw/vw);
        g.translate(0, -vly);

        for (Entity e : scene.getViewport()) {
            map.get(e).render(e, gfx, delta);
        }

        // XXX sould we really do the swap here or sould it be done for us
        gfx.swapBuffer();
    }
}