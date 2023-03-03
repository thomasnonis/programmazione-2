package EscapeGame;

import javafx.scene.paint.Color;

import java.util.Random;

/**
 * A EscapeGame.Striker is an enemy that moves in a single direction,
 * randomly generated at instantiation,
 * for the whole duration of the game
 */
public class Striker extends Enemy {
    /**
     * Represents the color of all Strikers
     */
    private static final Color COLOR = Color.BLUE;

    Striker(){
        super();
        setColor(COLOR);
        setDirection();
    }

    private void setDirection(){
        int xDir, yDir;
        //Set direction
        //TODO add following to superclass
        do {
            xDir = getRanDir();
            yDir = getRanDir();
        }while(xDir == 0 && yDir == 0);

        //TODO if diagonal, distance is square root
        if(xDir > 0){
            byX = delta;
        }else if(xDir < 0){
            byX = -delta;
        }else{
            byX = 0;
        }

        if(yDir > 0){
            byY = delta;
        }else if(yDir < 0){
            byY = -delta;
        }else{
            byY = 0;
        }
    }

    public void move(){
        moveBy(byX, byY);
    }
}
