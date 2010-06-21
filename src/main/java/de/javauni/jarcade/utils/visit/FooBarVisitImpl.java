package de.javauni.jarcade.utils.visit;

import static de.javauni.jarcade.utils.visit.FooBarVisitAdapter.*;

public class FooBarVisitImpl implements FooBarVisitAdapter {
    static class VisitorAdapter<R> {
        private final FooBarVisitor<R> visitor;
        public VisitorAdapter(FooBarVisitor<R> visitor) {
            this.visitor = visitor;
        }

        R visit(FooAdapter foo) {
            return visitor.visit(foo.get());
        }
        R visit(BarAdapter bar) {
            return visitor.visit(bar.get());
        }
    }

    static abstract class Adapter<E> {
        private final E e;
        Adapter(E e) {
            this.e = e;
        }

        E get() {
            return e;
        }

        abstract <R> R accept(FooBarVisitor<R> visitor);
    }

    static class FooAdapter extends Adapter<Foo> {
        FooAdapter(Foo foo) {
            super(foo);
        }
        <R> R accept(FooBarVisitor<R> visitor) {
            return new VisitorAdapter<R>(visitor).visit(this);
        }
    }

    static class BarAdapter extends Adapter<Bar> {
        BarAdapter(Bar bar) {
            super(bar);
        }
        <R> R accept(FooBarVisitor<R> visitor) {
            return new VisitorAdapter<R>(visitor).visit(this);
        }
    }

    private Adapter<?> baz;

    public void set(Bar bar) {
        baz = new BarAdapter(bar);
    }
    public void set(Foo foo) {
        baz = new FooAdapter(foo);
    }


    @Override
    public <R, VR extends VisitAdapter.Fix<R,? extends FooBarVisitAdapter.FooBarVisitor<?>>> R accept(VR visitor) {
        return baz.accept((FooBarVisitor<R>)visitor);
    };
}

