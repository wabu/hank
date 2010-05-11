package de.javauni.jarcade.model.control;

public abstract class AbstractNeuron implements Neuron {
    private final float minVal;
    private final float maxVal;
    private final float step;

    public AbstractNeuron(float minVal, float maxVal, float step) {
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.step = step;
    }

    public AbstractNeuron() {
        this(-1f, 1f, 0.1f);
    }

    public AbstractNeuron(boolean allowNegative) {
        this(allowNegative ? -1:0, 1f, 0.1f);
    }

    protected abstract float apply();
    protected abstract void apply(float val);
    
    protected void bound(float val) {
        if(val < minVal) {
            val = minVal;
        }
        if(apply() > maxVal) {
            val = maxVal;
        }
        apply(val);
    }

    public float get() {
        return apply();
    }

    @Override
    public void decrease() {
        set(apply()-step);
    }

    @Override
    public void increase() {
        set(apply()+step);
    }

    @Override
    public void reset() {
        set(0f);
    }

    @Override
    public void set(float val) {
        bound(val);
    }

    @Override
    public void setHalfNegative() {
        set(minVal/2);
    }

    @Override
    public void setHalfPositive() {
        set(maxVal/2);
    }

    @Override
    public void setMaxNegative() {
        set(minVal);
    }

    @Override
    public void setMaxPositive() {
        set(maxVal);
    }
}
