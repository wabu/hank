/**
 * 
 */
package de.javauni.jarcade.utils.visit;

import de.javauni.jarcade.utils.visit.VisitAdapter.FixV;

public interface FooBarVisitor<R> extends FixV<R, FooBarVisitor<R>> {
    R visit(Foo foo);
    R visit(Bar bar);
}
