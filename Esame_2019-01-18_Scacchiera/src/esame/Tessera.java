package esame;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

/**
 * Abstract class that represents all cards
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public abstract class Tessera extends StackPane{
    /**
     * Holds the status of the card
     */
    protected boolean covered = true;
    
    /**
     * Size of the single cell (in pixels)
     */
    private final double CELL_SIZE = 50.0;
    
    /**
     * Size of the cell's border
     */
    private final double BORDER_SIZE = 0.5;
    
    /**
     * Color of the cell's border
     */
    private final Color BORDER_COLOR = Color.BLACK;
    
    /**
     * Background color of the cell
     */
    private final Color BG_COLOR = Color.YELLOW;
    
    /**
     * The rectangle that represents the card
     */
    private Rectangle card;
    
    /**
     * The label displayed in the card
     */
    private Label txt;
    
    /**
     * Sets all card and text properties and creates the StackPane in the intended form
     */
    public Tessera() {
        super();
        card = new Rectangle(CELL_SIZE, CELL_SIZE, BG_COLOR);
        card.setStroke(BORDER_COLOR);
        card.setStrokeWidth(BORDER_SIZE);
        
        txt = new Label(); 
        txt.setMaxSize(CELL_SIZE, CELL_SIZE);
        txt.setWrapText(true);
        txt.setTextAlignment(TextAlignment.CENTER);
        txt.setAlignment(Pos.CENTER);
        
        this.getChildren().addAll(card, txt);
        
    }
    
    /**
     * Sets the text that is displayed on the card while it is uncovered
     * @param txt The text to display
     */
    public void setText(String txt){
        this.txt.setText(txt);
    }
    
    /**
     * This method should be called whenever a card is clicked
     */
    public void trigger(){
        if(covered){
            uncover();
            return;
        }
        cover();
    }
    
    /**
     * Sets the card to its covered status
     */
    protected abstract void cover();
    
    /**
     * Sets the card to  its uncovered status
     */
    protected abstract void uncover();
    
    public abstract String toString();
    
}
