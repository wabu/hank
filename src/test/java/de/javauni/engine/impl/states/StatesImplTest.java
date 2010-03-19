/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javauni.engine.impl.states;

import de.javauni.yarrish.engine.impl.states.StatesMap;
import de.javauni.yarrish.engine.impl.states.AbstractStates;
import de.javauni.yarrish.engine.impl.states.StatesBuilder;
import de.javauni.yarrish.engine.model.States;
import org.junit.Test;
import static org.junit.Assert.*;
import static de.javauni.engine.impl.states.StatesImplTest.S.*;
import static de.javauni.engine.impl.states.StatesImplTest.T.*;

/**
 *
 * @author wabu
 */
public class StatesImplTest {

    enum S {

        a, b, c
    }

    enum T {

        x, y
    }

    static abstract class TestStates extends AbstractStates<S, T> {
        @Override
        protected void onTranstion(T trans, S src, S tgt) {
            // TODO wirte test to check calls on transition
        }
    }
    private States<S, T> states;

    private void assertState(String msg, S s) {
        assertEquals(msg, states.getCurrentState(), s);
    }

    private void assertDefaultStates() {
        assertState("-> a", a);
        states.send(x);
        assertState("a >x> a", a);
        states.send(x);
        assertState("a >x> a", a);
        states.send(y);
        assertState("a >y> b", b);
        states.send(x);
        assertState("b >x> c", c);
        states.send(x);
        assertState("c >x> c", c);
        states.send(y);
        assertState("c >y> a", a);
        states.send(x);
        assertState("a >x> a", a);
    }

    @Test
    public void simpleStatesTest() {
        states = new TestStates() {
            @Override
            protected StatesMap<S, T> define(StatesBuilder<S, T> builder) {
                return builder
                        .from(a).on(x).to(a)
                        .from(a).on(y).to(b)
                        .from(b).on(x).to(c)
                        .from(b).on(y).to(c)
                        .from(c).on(x).to(c)
                        .from(c).on(y).to(a).
                start(a);
            }
        };
        assertDefaultStates();
    }

    @Test
    public void simplifyedSyntaxTest() {
        states = new TestStates() {
            @Override
            protected StatesMap<S, T> define(StatesBuilder<S, T> builder) {
                return builder
                        .from(a).on(x).to(a)
                                .on(y).to(b)
                        .from(b, c).on(x).to(c)
                        .from(b).on(y).to(c)
                        .from(c).on(y).to(a)
                        .start(a);
            }
        };
        assertDefaultStates();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotADFA() {
        new TestStates() {
            @Override
            protected StatesMap<S, T> define(StatesBuilder<S, T> builder) {
                return builder
                        .from(a).on(x).to(a)
                                .on(x).to(b).start(a);
            }
        };
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnknownTransition() {
        states = new TestStates() {
            @Override
            protected StatesMap<S, T> define(StatesBuilder<S, T> builder) {
                return builder
                        .from(a).on(x).to(a)
                                .on(y).to(b)
                                .start(a);
            }
        };

        assertState("-> a", a);
        states.send(x);
        assertState("a >x> a", a);
        states.send(x);
        assertState("a >x> a", a);
        states.send(y);
        assertState("a >y> b", b);

        states.send(x); // no transition form b
    }
}
