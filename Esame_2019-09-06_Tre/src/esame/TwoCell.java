package esame;

import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * A cell with two buttons
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class TwoCell extends Cell{
    /**
     * The button that creates a random shape
     */
    private Button randomBtn;

    /**
     * Default constructor
     */
    public TwoCell() {
        super();
        randomBtn = new Button("R");
        buttons.getChildren().add(randomBtn);
        
        randomBtn.setOnAction(e -> {
            //remove old shape from cell layout
            shapePane.getChildren().remove(shape);
            
            //50% probability
            if(new Random().nextDouble() < 0.5){
                shape = new Circle(CELL_SIZE / 2 * 0.75);
                shape.setStroke(Color.RED);
                shape.setFill(Color.YELLOW);
            }else{
                shape = new Rectangle(CELL_SIZE * 0.75, CELL_SIZE * 0.75);
                shape.setStroke(Color.LIGHTBLUE);
                shape.setFill(Color.LIGHTGRAY);
            }
            shape.setStrokeWidth(BORDER_SIZE);
            shape.setVisible(true);
            
            //add new shape to cell layout
            shapePane.getChildren().add(shape);
        });
    }
    
    
}
