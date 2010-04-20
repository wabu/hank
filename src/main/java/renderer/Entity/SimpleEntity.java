/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package renderer.Entity;


/**
 * @author wabu
 */
public class SimpleEntity implements Entity {
    private final Box pos;
    private final int id;

    public SimpleEntity(int id,
            @ImpliedProperty(name="position") Box pos) {
        this.pos = pos;
        this.id = id;
    }

    public Box getPositionBox() {
        return pos;
    }
    public int getId() {
        return id;
    }
}
