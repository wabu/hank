package de.javauni.jarcade.model.control;

public class NeuronImpl extends AbstractNeuron {
    private float val;

    public NeuronImpl(float minVal, float maxVal, float step) {
        super(minVal, maxVal, step);
        this.val = 0;
    }

    @Override
    protected float apply() {
        return val;
    }

    @Override
    protected void apply(float val) {
        this.val = val;
    }
}
