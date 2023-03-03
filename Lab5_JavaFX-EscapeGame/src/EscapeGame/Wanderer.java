package EscapeGame;

import javafx.scene.paint.Color;

public class Wanderer extends Enemy {
    private static final Color COLOR = Color.DARKBLUE;
    private int iteration = 0;
    private final int CHANGE_EVERY = 5;

    Wanderer(){
        super();
        setColor(COLOR);
    }


    public void move(){
        if(iteration == 0){
            //TODO add following to superclass
            int xDir, yDir;
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
        moveBy(byX, byY);
        iteration = (++iteration) % CHANGE_EVERY;
//        System.out.println("Iteration: " + iteration);
    }
}
