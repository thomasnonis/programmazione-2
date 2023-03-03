package esame;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * A cell with three buttons
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class ThreeCell extends Cell{
    /**
     * The color of the shape's border
     */
    private static final Color BORDER_COLOR = Color.RED;
    
    /**
     * The fill color of the shape
     */
    private static final Color COLOR = Color.YELLOW;
    
    /**
     * The button that creates a circle
     */
    private Button circleBtn;
    
    /**
     * The button that creates a triangle
     */
    private Button triangleBtn;

    /**
     * Default constructor
     */
    public ThreeCell() {
        //call to superclass constructor
        super();
        
        //instantiate buttons
        circleBtn = new Button("O");
        triangleBtn = new Button("T");
        //add buttons to superclass buttons layout
        buttons.getChildren().addAll(circleBtn, triangleBtn);
        
        //set handler for creating circle
        circleBtn.setOnAction(e -> {
            System.out.println("Circle set");
            
            //remove old shape from cell layout
            shapePane.getChildren().remove(shape);
            
            //create new shape
            shape = new Circle(CELL_SIZE / 2 * 0.75);
            shape.setFill(COLOR);
            shape.setStroke(BORDER_COLOR);
            shape.setStrokeWidth(BORDER_SIZE);
            shape.setVisible(true);
            
            //add new shape to cell layout
            shapePane.getChildren().add(shape);
            
        });
        
        //set handler for creating triangle
        triangleBtn.setOnAction(e -> {
            System.out.println("Triangle set");
            shapePane.getChildren().remove(shape);
            
            shape = new Triangle(CELL_SIZE * 0.75, CELL_SIZE * 0.75);
            shape.setFill(COLOR);
            shape.setStroke(BORDER_COLOR);
            shape.setStrokeWidth(BORDER_SIZE);
            shape.setVisible(true);
            
            shapePane.getChildren().add(shape);
        });
        
    }
}
