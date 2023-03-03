package EscapeGame;

import javafx.scene.paint.Color;

import java.util.Random;

public class Bubbler extends Wanderer {
    private static final Color COLOR = Color.LIGHTBLUE;

    Bubbler(){
        super();
        setColor(COLOR);
    }



    public void move(){
        super.move();
        //10% probability
        if(new Random().nextDouble() < 0.1){
            circle.setRadius(circle.getRadius() * 1.2);
        }
    }
}
