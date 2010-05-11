package de.javauni.jarcade.model.phys;

import org.jbox2d.collision.shapes.CircleDef;
import org.jbox2d.collision.shapes.PolygonDef;
import org.jbox2d.collision.shapes.Shape;

import org.jbox2d.common.Vec2;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

import de.javauni.jarcade.geom.Bound;
import de.javauni.jarcade.geom.Vec;

import de.javauni.jarcade.model.entities.AbstractControlableEntity;

public class ControlableBody extends AbstractControlableEntity implements Physical {
    private Body body;
    private Shape head, torso, feet;
    private Vec2 vertical;
    private final Bound bound;

    public ControlableBody(int id, Bound bound) {
        super(id);
        this.bound = bound;
    }

    @Override
    public void addTo(World w) {
        Vec pos = bound.mid();
        Vec size = bound.size();
        float r = size.x()/2;
        float h = size.y()/2-r;
        body = createBody(w, 
                new Vec2(pos.x(),pos.y()-h), new Vec2(pos.x(),pos.y()+h), r);
    }

    @Override
    public void removeFrom(World w) {
    }

    @Override
    public de.javauni.jarcade.geom.Shape getShape() {
        return Phys.from(body);
    }

    public Body getBody(){
        return this.body;
    }

    public Shape getHead(){
        return this.head;
    }

    public Shape getTorso(){
        return this.torso;
    }

    public Shape getFeet(){
        return this.feet;
    }

    public Vec2 getVertical(){
        return this.vertical;
    }

    private Body createBody(World w, Vec2 p0, Vec2 p1, float radius) {
        vertical = p1.sub(p0);
        BodyDef bd = new BodyDef();
        bd.fixedRotation = true; // Typical character bodies do not rotate
        Body body1 = w.createBody(bd);
        
        Vec2 feetToHeadDir = p1.sub(p0);
        feetToHeadDir.normalize();
        
        Vec2 feetDir = Vec2.cross(feetToHeadDir, 1.0f);
        
        //Head
        CircleDef cd = new CircleDef();
        cd.localPosition.set(p1);
        cd.density = 1.0f;
        cd.radius = radius;
        cd.friction = 0.0f; 
        //If we use non-zero friction, player will stick to walls, which is not what we want
        cd.restitution = 0.0f;
        head = body1.createShape(cd);
        
        //Torso
        PolygonDef pd = new PolygonDef();
        pd.density = 1.0f;
        pd.friction = 0.0f;
        pd.restitution = 0.0f;
        pd.addVertex(p0.add(feetDir.mul(radius*.98f)));
        pd.addVertex(p1.add(feetDir.mul(radius*.98f)));
        pd.addVertex(p1.sub(feetDir.mul(radius*.98f)));
        pd.addVertex(p0.sub(feetDir.mul(radius*.98f)));
        torso = body1.createShape(pd);
        
        //Feet
        cd = new CircleDef();
        cd.localPosition.set(p0);
        cd.density = 1.0f;
        cd.radius = radius;
        cd.friction = 0.0f;
        cd.restitution = 0.0f; 
        //Feet should have zero restitution so player doesn't bounce after jumping
        feet = body1.createShape(cd);
        
        body1.setMassFromShapes();
        return body1;
    }
}
