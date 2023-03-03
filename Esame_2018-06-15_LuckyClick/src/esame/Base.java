/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import java.util.Random;
import javafx.scene.paint.Color;

/**
 * It is the standard cell. It has a random value, which is graphically represented in it.
 * When it is clicked, the trigger() method returns the current game points + the value of the cell
 * @author Thomas
 */
public class Base extends Cella{
    /**
     * The background color of the cell after it has been clicked.
     */
    private static final Color BG = Color.WHITE;
    /**
     * The value of the cell. It is added to the current game points when the cell is clicked.
     */
    private int value;

    /**
     * Default constructor. <br>
     * It calls its superclass constructor, and generates the random value.
     */
    public Base() {
        super();
        value = (new Random().nextInt(10) + 1) * 100;
        active = true;
    }
    
    
    /**
     * This method is called when the cell is clicked.
     * It sets the new background color, the text and deactivates the cell.
     * @return The current game points + its value
     */
    @Override
    public int trigger() {
        if(active){
            setFill(BG);
            setText(String.valueOf(value));
            
            active = false;
            return LuckyClick.getPts() + value;
        }
        return LuckyClick.getPts();
    }
    

    
    
    
}
