package EscapeGame;

import java.util.Random;

public abstract class Enemy extends Ball {
    protected final double delta = 10; //px
    protected double byX;
    protected double byY;

    Enemy(){
        super();
    }

    public int getRanDir(){
        Random random = new Random();
        double n = random.nextDouble()*3;
        if(n > 2){
            return 1;
        }else if(n > 1){
            return -1;
        }
        return 0;
    }

    public abstract void move();

}
