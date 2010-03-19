/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.utils.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wabu
 */
public class EnummeratedProviderTest {
    enum E {
        a,b
    }
    interface I {
        char i();
    }
    static class A implements I {
        public char i() {
            return 'a';
        }
    }
    static class B implements I {
        public char i() {
            return 'b';
        }
    }

    static class Module extends AbstractModule {
        @Override
        protected void configure() {
            bind(I.class).annotatedWith(Names.named("a")).to(A.class);
            bind(I.class).annotatedWith(Names.named("b")).to(B.class);

            bind(new TypeLiteral<EnumeratedProvider<I, E>>(){})
                    .toInstance(new EnumeratedProviderImpl<I, E>(I.class, E.values()));
        }
    }

    private EnumeratedProvider<I, E> factory;

    @Before
    public void setUp() {
        Injector inj = Guice.createInjector(new Module());
        factory = inj.getInstance(
                Key.get(new TypeLiteral<EnumeratedProvider<I, E>>(){}));
    }

    @Test
    public void testTypes() {
        I i = factory.get(E.a);
        assertTrue("i is an A", i instanceof A);
        assertEquals("i returns a", 'a', i.i());

        I j = factory.get(E.b);
        assertTrue("j is an B", i instanceof A);
        assertEquals("j returns b", 'b', j.i());

        A a = factory.get(E.a);
        assertTrue("a is an A", i instanceof A);
        assertEquals("a returns a", 'a', a.i());

        B b = factory.get(E.b);
        assertTrue("b is an B", i instanceof A);
        assertEquals("b returns b", 'b', b.i());
    }

    @Test(expected=ClassCastException.class) 
    public void testFail() {
        B b = factory.get(E.a);

        // we sould not get here
        assertEquals("this should not happen, but a B should return b!",
                'b', b.i());
    }
}