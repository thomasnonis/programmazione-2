package minesweeper;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Abstract class for all cells
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public abstract class Cell extends StackPane{
    /**
     * The size of the cell
     */
    protected static final double SIZE = 50.0;
    
    /**
     * The background color of an unclicked cell
     */
    private static final Color DEF_BG = Color.BLUE;
    
    /**
     * The background color of a clicked cell
     */
    private static final Color SHOW_BG = Color.YELLOW;
    
    /**
     * The rectangle that acts as the background
     */
    private Rectangle background;
    
    Cell(){
        background = new Rectangle(SIZE, SIZE, DEF_BG);
        
        this.getChildren().add(background);
    }
    
    /**
     * Triggers the cell
     * @param mainRef The reference to the main class
     */
    public void trigger(Minesweeper mainRef){
        background.setFill(SHOW_BG);
    }
}
