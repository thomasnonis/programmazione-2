/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import javafx.scene.paint.Color;

/**
 * When it is clicked, the trigger() method returns the current game points
 * divided by the DIVIDE_BY value of the cell
 * @author Thomas
 */
public class Divisore extends Cella{
    /**
     * The background color of the cell after it has been clicked.
     */
    private static final Color BG = Color.GREEN;
    /**
     * The value of the cell. The current game points are divided by this number.
     */
    private static final int DIVIDE_BY = 2;

    /**
     * Default constructor.
     */
    public Divisore() {
        super();
        active = true;
    }
    
    
    /**
     * This method is called when the cell is clicked.
     * It sets the new background color, the text and deactivates the cell.
     * @return The current game points divided by the cell's coefficient
     */
    @Override
    public int trigger() {
        if(active){
            setFill(BG);
            setText("/" + DIVIDE_BY);
            
            active = false;
            return LuckyClick.getPts() / DIVIDE_BY;
        }
        return LuckyClick.getPts();
    }
}
