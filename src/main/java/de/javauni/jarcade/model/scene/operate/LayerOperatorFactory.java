package de.javauni.jarcade.model.scene.operate;

import de.javauni.jarcade.model.scene.Layer;


public interface LayerOperatorFactory<O extends Operator<Layer>> {
    O create(Layer l);
}
