package de.javauni.jarcade.model.entities;

import de.javauni.jarcade.model.control.AbstractNeuron;
import de.javauni.jarcade.model.control.CharacterControl;
import de.javauni.jarcade.model.control.Neuron;

public abstract class AbstractControlableEntity extends AbstractEntity implements CharacterControl {
    private float direction = 1;
    private float jump = 0;
    private float crouch = 0;

    public AbstractControlableEntity(int id) {
        super(id);
    }

    @Override
    public Neuron neuronalDirection() {
        return new AbstractNeuron() {
            protected float apply() {
                return direction;
            };
            protected void apply(float val) {
                direction = val;
            };
        };
    }

    @Override
    public Neuron neuronalJump() {
        return new AbstractNeuron(false) {
            protected float apply() {
                // XXX just a test
                if(System.currentTimeMillis()%3000<=2000)
                    return 1;
                else
                    return jump;
            };
            protected void apply(float val) {
                jump = val;
            };
        };
    }

    @Override
    public Neuron neuronalCrouch() {
        return new AbstractNeuron(false) {
            protected float apply() {
                return crouch;
            };
            protected void apply(float val) {
                crouch = val;
            };
        };
    }

    public float getDirection(){
        return this.direction;
    }

    public float getJump(){
        return this.jump;
    }

    public float getCrouch(){
        return this.crouch;
    }
}
