package de.javauni.jarcade.utils.visit;

public class FooBarVisitImpl extends AbstractVisitAdapter<FooBarVisitor<?>> {

    private Adapter<?, FooBarVisitor<?>> baz;
    protected AbstractVisitAdapter.Adapter<?,FooBarVisitor<?>> get() {
        return baz;
    };

    public void set(Bar bar) {
        baz = new Adapter<Bar, FooBarVisitor<?>>(bar) {
            protected <R> Visit<? extends FooBarVisitor<R>,Bar,R> fix() {
                return new Visit<FooBarVisitor<R>, Bar, R>() {
                    public R apply(FooBarVisitor<R> v, Bar e) {
                        return v.visit(e);
                    }
                };
            }
        };
    }

    public void set(Foo foo) {
        baz = new Adapter<Foo, FooBarVisitor<?>>(foo) {
            protected <R> Visit<? extends FooBarVisitor<R>,Foo,R> fix() {
                return new Visit<FooBarVisitor<R>, Foo, R>() {
                    public R apply(FooBarVisitor<R> v, Foo e) {
                        return v.visit(e);
                    }
                };
            }
        };
    }
}

