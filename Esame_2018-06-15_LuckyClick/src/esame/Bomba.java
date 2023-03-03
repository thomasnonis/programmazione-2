package esame;

import javafx.scene.paint.Color;

/**
 * When this cell is triggered, it should trigger all the cells that are on its column and its row
 * @author Thomas
 */
public class Bomba extends Cella{
    /**
     * The background color of the cell after it has been clicked.
     */
    private static final Color BG = Color.BLUE;

    /**
     * Default constructor.
     */
    public Bomba() {
        super();
        active = true;
    }
    
    
    /**
     * This method is called when the cell is clicked.
     * It sets the new background color, the text and deactivates the cell.
     * @return The current game points unchanged
     */
    @Override
    public int trigger() {
        if(active){
            setFill(BG);
            setText("BOOM!");
            
            active = false;
            return LuckyClick.getPts();
        }
        return LuckyClick.getPts();
        }
    
}
