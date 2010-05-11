package de.javauni.jarcade.model.control;

public interface Neuron {
    float get();
    void set(float val);

    void increase();
    void decrease();

    void reset();

    void setMaxPositive();
    void setMaxNegative();
    void setHalfPositive();
    void setHalfNegative();
}
