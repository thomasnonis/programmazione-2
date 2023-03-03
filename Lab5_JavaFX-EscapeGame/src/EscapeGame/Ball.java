package EscapeGame;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.Random;

/**
 * Abstract superclass that defines the common properties of the game entities
 * @author Thomas Nonis
 */
public abstract class Ball{
    /**
     * Represents the default radius of a ball
     */
    private static double defaultRadius = 20; //px

    /**
     * Is the actual circle represented by EscapeGame.Ball
     */
    public Circle circle;

    Ball(){
        circle = new Circle(randomX(), randomY(), defaultRadius);
    }

    /**
     * Sets the center of the ball at coordinates (x,y) with respect to the top-left corner of the stage
     * @param x position on the x-axis
     * @param y position on the y-axis
     */
    protected void setPosition(double x, double y){
        circle.setCenterX(x);
        circle.setCenterY(y);
    }

    /**
     * Generates a random position on the x-axis, such that the ball will spawn with at least 1 pixel in the stage
     * @return random x-position
     */
    private double randomX(){
        Random random = new Random();
        double x = random.nextDouble() * Game.width;
        if(x < -defaultRadius){
            x = -defaultRadius + 1;
        }
        if(x > Game.width + defaultRadius){
            x = Game.width + defaultRadius - 1;
        }
        return x;
    }

    /**
     * Generates a random position on the y-axis, such that the ball will spawn with at least 1 pixel in the stage
     * @return random y-position
     */
    private double randomY(){
        Random random = new Random();
        double y = random.nextDouble() * Game.height;
        if(y < -defaultRadius){
            y = -defaultRadius + 1;
        }
        if(y > Game.height + defaultRadius){
            y = Game.height + defaultRadius - 1;
        }
        return y;
    }

    /**
     * Sets the color of the ball
     * @param color The color to set the ball to
     */
    public void setColor(Paint color){
        circle.setFill(color);
    }

    /**
     *
     * @return Returns the current radius of the ball
     */
    public double getRadius(){
        return circle.getRadius();
    }

    /**
     * Sets the center of the ball to the center of the stage
     */
    public void reset(){
        setPosition(Game.width / 2, Game.height / 2);
    }

    /**
     * Calculates and returns the distance between the center of the current ball and the argument one
     * @param b Ball to measure the distance from
     * @return The distance between the centers of two Balls
     */
    public double distanceFrom(Ball b){
        double distance = Math.pow(circle.getCenterX() - b.circle.getCenterX(), 2);
        distance += Math.pow(circle.getCenterY() - b.circle.getCenterY(), 2);
        distance = Math.sqrt(distance);
        return distance;
    }

    /**
     * Moves the ball along the x and y directions, by the amount passed as arguments
     * @param byX The amount to move the ball by, on the X axis.
     *            Positive values move the ball downwards, negative values move the ball upwards
     * @param byY The amount to move the ball by, on the Y axis.
     *            Positive values move the ball to the right, negative values move the ball to the left
     */
    public void moveBy(double byX, double byY){
        setPosition(circle.getCenterX() + byX, circle.getCenterY() + byY);

        if(circle.getCenterY() < 0){
            circle.setCenterY(Game.height);
        }
        else if(circle.getCenterY() > Game.height){
            circle.setCenterY(0);
        }
        if(circle.getCenterX() < 0){
            circle.setCenterX(Game.width);
        }
        else if(circle.getCenterX() > Game.width){
            circle.setCenterX(0);
        }
    }
}
