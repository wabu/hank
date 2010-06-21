package de.javauni.jarcade.utils.visit;

public interface VisitAdapter {
    interface Visitor<R> {};

    <R> R accept(Visitor<R> visitor);
}
