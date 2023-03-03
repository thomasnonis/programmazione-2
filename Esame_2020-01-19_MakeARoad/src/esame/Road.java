package esame;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Road cell
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Road extends Cell{
    /**
     * Background color of the Road cell
     */
    private static final Color BACKGROUND_COLOR = Color.DARKGRAY;
    
    /**
     * Background color of the direction indicator
     */
    private static final Color DIR_IND_COLOR = Color.BLACK;
    
    /**
     * Border color of the direction indicator
     */
    private static final Color DIR_IND_BORDER_COLOR = Color.YELLOW;
    
    /**
     * Thickness direction indicator's border
     */
    private static final double DIR_IND_BORDER_SIZE = 1.5;
    
    /**
     * Width of the car
     */
    private static final double CAR_WIDTH = CELL_SIZE / 2;
    
    /**
     * Length of the car
     */
    private static final double CAR_LENGTH = CELL_SIZE / 5;
    
    /**
     * Color of the car
     */
    private static final Color CAR_COLOR = Color.WHITE;
    
    /**
     * Graphical element representing the direction indicator
     */
    private Circle dirInd;
    
    /**
     * Graphical element representing the car
     */
    private Rectangle car;
    
    /**
     * The direction of the road cell
     */
    private Dir direction;
    
    /**
     * Creates a Road Cell, oriented in the desired direction
     * @param direction The direction of the Road Cell
     */
    Road(Dir direction){
        super();
        
        this.direction = direction;
        cell.setFill(BACKGROUND_COLOR);
        dirInd = new Circle(CELL_SIZE / 10, DIR_IND_COLOR);
        dirInd.setStroke(DIR_IND_BORDER_COLOR);
        dirInd.setStrokeWidth(DIR_IND_BORDER_SIZE);
        
        car = new Rectangle(CAR_WIDTH, CAR_LENGTH);
        car.setVisible(false);
        car.setFill(CAR_COLOR);
        
        this.getChildren().addAll(dirInd, car);
        
        switch(direction){
            case NORTH:
                this.setAlignment(Pos.TOP_CENTER);
                car.setRotate(90.0);
                car.setTranslateY(CELL_SIZE/2);
                break;
                
            case SOUTH:
                this.setAlignment(Pos.BOTTOM_CENTER);
                car.setRotate(90.0);
                car.setTranslateY(-CELL_SIZE/2);
                break;   
            
            case EAST:
                this.setAlignment(Pos.CENTER_RIGHT);
                car.setTranslateX(-CELL_SIZE/2 + car.getHeight());
                break;
                
            case WEST:
                this.setAlignment(Pos.CENTER_LEFT);
                car.setTranslateX(CELL_SIZE/2 - car.getHeight());
                break;
        }
    }
    
    /**
     * Shows the car in the road cell
     */
    public void showCar(){
        car.setVisible(true);
    }
    
    /**
     * Hides the car from the road cell
     */
    public void hideCar(){
        car.setVisible(false);
    }
    
    /**
     * Checks if the road cell has a car in it (if it is visible in it)
     * @return true if the car is visible, false otherwise
     */
    public boolean hasCar(){
        return car.isVisible();
    }
    
    /**
     * Gets the direction in which the road cell is oriented
     * @return The direction in which the road cell is oriented
     */
    public Dir getDirection(){
        return direction;
    }
}
