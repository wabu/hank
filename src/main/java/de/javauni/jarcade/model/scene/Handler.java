package de.javauni.jarcade.model.scene;

import de.javauni.jarcade.model.entities.Entity;

public interface Handler<E1 extends Entity, E2 extends Entity> {
    void handle(E1 entity1, E2 entity2);
}
