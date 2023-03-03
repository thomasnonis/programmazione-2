package EscapeGame;

import javafx.scene.paint.Color;

public class User extends Ball {
    private static final Color COLOR = Color.ORANGE;

    User(){
        super();
        setColor(COLOR);
    }

    public boolean checkCollision(Enemy e){
        return this.distanceFrom(e) < this.getRadius() + e.getRadius();
    }

}
