/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.utils.guice;

import com.google.inject.ScopeAnnotation;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 * scope annotiation for instances which lifetime is manually managed
 * @author wabu
 * @see InstanceScopeManager
 */
@Target({ TYPE, METHOD })
@Retention(RUNTIME)
@ScopeAnnotation
public @interface ManagedScope {
}

