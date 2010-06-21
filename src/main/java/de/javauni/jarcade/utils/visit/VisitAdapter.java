package de.javauni.jarcade.utils.visit;

import static de.javauni.jarcade.utils.visit.VisitAdapter.*;

public interface VisitAdapter<V extends Visitor<?,V>> {
    interface Visitor<R, V extends Visitor<R,V>> extends Fix<R, V> {}

    interface Fix<R, V extends Visitor<R,V>> {}

    <R, VR extends Fix<R, ? extends V>> R accept(VR visitor);
}
