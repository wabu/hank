package de.javauni.jarcade.model.entities;


public abstract class AbstractEntity implements Entity {
    private final int id;

    public AbstractEntity(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public int hashCode() {
        return id;
    };
}
