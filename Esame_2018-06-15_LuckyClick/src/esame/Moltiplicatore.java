/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import javafx.scene.paint.Color;

/**
 * When it is clicked, the trigger() method returns the current game points
 * multiplied by the MULTIPLY_BY value of the cell
 * @author Thomas
 */
public class Moltiplicatore extends Cella{
    /**
     * The background color of the cell after it has been clicked.
     */
    private static final Color BG = Color.RED;
    /**
     * The value of the cell. The current game points are multiplied by this number.
     */
    private static final int MULTIPLY_BY = 2;

    /**
     * Default constructor.
     */
    public Moltiplicatore() {
        super();
        active = true;
    }
    
    
    /**
     * This method is called when the cell is clicked.
     * It sets the new background color, the text and deactivates the cell.
     * @return The current game points multiplied by the cell's coefficient
     */
    @Override
    public int trigger() {
        if(active){
            setFill(BG);
            setText("x" + MULTIPLY_BY);
            
            active = false;
            return LuckyClick.getPts() * MULTIPLY_BY;
        }
        return LuckyClick.getPts();
    }
}
