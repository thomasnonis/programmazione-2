package minesweeper;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Bomb cell
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Bomb extends Cell{
    /**
     * The background color of the circle representing the bomb
     */
    private static final Color BOMB_BG = Color.RED;
    
    Bomb(){}
    
    /**
     * Triggers the bomb
     * @param mainRef The reference to the main class
     */
    public void trigger(Minesweeper mainRef){
        super.trigger(mainRef);
        
        this.getChildren().add( new Circle(SIZE / 2 * 0.75, BOMB_BG) );
        
        mainRef.cellsLeft--;
        
        if(!mainRef.peekMode){
            new Prompt(Prompt.Status.LOSS);
            //Based on the interpretation, choose one:
            Platform.exit();
            //mainRef.reset();
        }
    }
}
