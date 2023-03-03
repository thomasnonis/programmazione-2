package esame;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * Abstract superclass of all cells. It is a subclass of StackPane, so it can be used as a graphical element
 * @author Thomas
 */
public abstract class Cella extends StackPane{
    /**
     * If a cell has been clicked, it is false. By default it should be set true in the constructor
     */
    protected boolean active;
    /**
     * The width of the single cell in pixels
     */
    private static final double WIDTH = 50.0; //px
    /**
     * The height of the single cell in pixels
     */
    private static final double HEIGHT = 50.0; //px
    /**
     * The size of the outside borders of the single cell
     */
    private static final double BORDER_SIZE = 0.5; //px
    /**
     * The color of the outside borders
     */
    private static final Color BORDER_COLOR = Color.BLACK;
    /**
     * The default background color of an active (not clicked yet) cell
     */
    private static final Color DEF_BG = Color.YELLOW;
    
    
    /**
     * The rectangle that represents the background and the border of the cell
     */
    private Rectangle rectangle;
    /**
     * The text inside the cell
     */
    private Label txt;
    
    /**
     * Default constructor.<br>
     * It sets the default settings and creates the StackPane combining the rectangle and the text
     */
    Cella(){
        rectangle = new Rectangle(WIDTH, HEIGHT);
        setFill(DEF_BG);
        rectangle.setStroke(BORDER_COLOR);
        rectangle.setStrokeWidth(BORDER_SIZE);
        txt = new Label();
        this.getChildren().addAll(rectangle, txt);
        this.setMaxHeight(HEIGHT);
        this.setMaxWidth(WIDTH);
    }
    
    /**
     * Sets the text inside the cell
     * @param text The text
     */
    protected void setText(String text){
        txt.setText(text);
    }
    
    /**
     * Sets the color of the cell
     * @param value The color of the cell
     */
    protected void setFill(Paint value){
        rectangle.setFill(value);
    }
    
    /**
     * Abstract method that gets called when a cell is clicked.
     * @return The updated game points
     */
    public abstract int trigger();
    
    /**
     * Returns true if a cell is active, which means it has not been clicked yet.
     * It returns false otherwise
     * @return The cell's state
     */
    public boolean isActive(){
        return active;
    }
 
}
