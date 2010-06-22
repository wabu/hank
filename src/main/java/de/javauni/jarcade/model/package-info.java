/**
 * interfaces and abstract classes to a domainspecific model sit inside the model package.
 * a model consists of
 * <ul>
 * <li>its private, implementation specific data,</li>
 * <li>a public exported interface and</li>
 * <li>a public access interface</li>
 * <ul>
 * the export interface is used by views to "show" the model
 * and the access is used by the controls to interact with the model
 */
@DefaultAnnotation(Nonnull.class)
package de.javauni.jarcade.model;

import edu.umd.cs.findbugs.annotations.DefaultAnnotation; //NOPMD
import javax.annotation.Nonnull; //NOPMD

