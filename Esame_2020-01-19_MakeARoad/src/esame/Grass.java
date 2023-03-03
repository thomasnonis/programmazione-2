package esame;

import javafx.scene.paint.Color;

/**
 * Grass cell
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Grass extends Cell{
    /**
     * Background color for the Grass cell
     */
    private static final Color BACKGROUND_COLOR = Color.GREEN;

    /**
     * Creates a Grass cell
     */
    public Grass() {
        super();
        cell.setFill(BACKGROUND_COLOR);
    }
    
    
}
