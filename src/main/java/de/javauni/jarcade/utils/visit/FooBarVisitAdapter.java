package de.javauni.jarcade.utils.visit;

public interface FooBarVisitAdapter {
    interface FooBarVisitor<R> {
        R visit(Foo foo);
        R visit(Bar bar);
    }

    <R> R accept(FooBarVisitor<R> visitor);
}
