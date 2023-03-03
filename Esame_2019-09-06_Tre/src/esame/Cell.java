package esame;

import java.util.Objects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Abstract class for all cells
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public abstract class Cell extends VBox{
    /**
     * The size of the cell in px
     */
    protected static final double CELL_SIZE = 50.0;
    
    /**
     * The size of the shape border
     */
    protected static final double BORDER_SIZE = 1; //px
    
    /**
     * The pane that holds the shape
     */
    protected StackPane shapePane;
    
    /**
     * The shape displayed in the cell
     */
    protected Shape shape;
    
    /**
     * The layout that holds the buttons of the cell
     */
    protected HBox buttons;
    
    /**
     * The button that clears the cell. It is present in every cell as the first button
     */
    private Button clearBtn;
    
    /**
     * Default constructor
     */
    Cell(){
        clearBtn = new Button("C");
        
        //placeholder shape to mantain the shape of the cells
        shape = new Rectangle(CELL_SIZE * 0.75, CELL_SIZE * 0.75);
        //set the shape to be invisibile at first
        shape.setVisible(false);
       
        buttons = new HBox(clearBtn);
        buttons.setAlignment(Pos.CENTER);
        shapePane = new StackPane();
        shapePane.getChildren().add(shape);
        shapePane.setPadding(new Insets(CELL_SIZE * 0.15));
        
        clearBtn.setOnAction(e -> clear());
        this.setOnMouseClicked(e -> clear());
        
        this.getChildren().addAll(shapePane, buttons);
        this.setAlignment(Pos.CENTER);
        this.setMinWidth(CELL_SIZE);
        this.setMinHeight(CELL_SIZE);
        this.setPadding(new Insets(CELL_SIZE * 0.1));
    }
    
    /**
     * Clears the cell, hiding the shape
     */
    public void clear(){
        System.out.println("Clearing cell");
        shape.setVisible(false);
    }

    /**
     * Returns true if two cells are equal. Two cells are equal if they display the same shape
     * @param obj The cell to compare to
     * @return true if the cells display the same shape. Otherwise it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if(!shape.isVisible()){
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        
        Cell c = (Cell) obj;
       return this.shape.getClass().equals( c.shape.getClass() );
        //return( this.shape.getClass() == ((Cell) obj).shape.getClass() );
    }

}
