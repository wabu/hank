package de.javauni.jarcade.utils.visit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static de.javauni.jarcade.utils.visit.FooBarVisitAdapter.*;

public class FooBarVisitTest {
    private FooBarVisitImpl baz;
    private Foo foo;
    private Bar bar;

    @Before
    public void setUp() throws Exception {
        this.baz = new FooBarVisitImpl();
        this.foo = new Foo(){};
        this.bar = new Bar(){};
    }

    @Test
    public void testAccpet() {
        baz.set(foo);
        String f = baz.accept(new FooBarVisitor<String>() {
            public String visit(Bar b) {
                fail("visiting bar with foo");
                throw new IllegalStateException("fail should have failed!!!");
            };

            public String visit(Foo f) {
                assertEquals("visited with element foo", foo, f);
                return "foo";
            };
        });
        assertEquals("value returned", "foo", f); 

        baz.set(bar);
        String b = baz.accept(new FooBarVisitor<String>() {
            public String visit(Foo f) {
                fail("visiting foo with bar");
                throw new IllegalStateException("fail should have failed!!!");
            };

            public String visit(Bar b) {
                assertEquals("visited with element bar", bar, b);
                return "bar";
            };

        });
        assertEquals("value returned", "bar", b); 
    }
}
