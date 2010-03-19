/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javauni.jarcade.exceptions;

/**
 *
 * @author wabu
 */
public class IllegalAction extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public IllegalAction(Throwable cause) {
        super(cause);
    }

    public IllegalAction(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAction(String s) {
        super(s);
    }

    public IllegalAction() {
        super();
    }
}
