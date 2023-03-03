package esame;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * Abstract superclass for all cells
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public abstract class Cell extends StackPane{
    /**
     * The size of the cell, in pixels
     */
    protected static final double CELL_SIZE = 50.0;
    
    /**
     * The thickness of the border, in pixels
     */
    private static final double BORDER_SIZE = 0.5;
    
    /**
     * The color of the border
     */
    private static final Color BORDER_COLOR = Color.GREY;
    
    /**
     * The rectangle graphically representing the cell
     */
    protected Rectangle cell;

    /**
     * Creates the cell as a Rectangle in a StackPane
     */
    public Cell() {
        cell = new Rectangle(CELL_SIZE, CELL_SIZE);
        cell.setStroke(BORDER_COLOR);
        cell.setStrokeWidth(BORDER_SIZE);;
        
        this.getChildren().add(cell);
    }
}
