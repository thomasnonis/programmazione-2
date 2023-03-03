package esame;

import javafx.scene.shape.Polygon;

/**
 * Isosceles triangle
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Triangle extends Polygon{

    /**
     * Creates a new isosceles triangle, given its base and height
     * @param base The length of the base
     * @param height The height of the triangle
     */
    public Triangle(double base, double height) {
        super();
        this.getPoints().addAll(new Double[]{
            base/2, 0.0,
            0.0, height,
            base, height
        });
    }

    
    
}
