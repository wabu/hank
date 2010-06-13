package de.javauni.jarcade.utils.visit;

import static de.javauni.jarcade.utils.visit.FooBarVisitAdapter.*;

public interface FooBarVisitAdapter extends VisitAdapter<FooBarVisitor<?>> {
    interface FooBarVisitor<R> extends Visitor<R, FooBarVisitor<R>> {
        R visit(Foo foo);
        R visit(Bar bar);
    }
}
