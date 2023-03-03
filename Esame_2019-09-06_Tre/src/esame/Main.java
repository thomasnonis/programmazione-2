package esame;

import java.util.Random;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Main class of the application
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Main extends Application{
    /**
     * Number of cell columns
     */
    static final int COLS = 3;
    
    /**
     * Number of cell rows
     */
    static final int ROWS = COLS;
    
    Scene scene;
    VBox root;
    GridPane grid;
    HBox buttons;
    Button clearBtn;
    Button checkBtn;
    
    /**
     * Launches the application
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Initialization before the application starts
     */
    @Override
    public void init(){
        clearBtn = new Button("clear All");
        checkBtn = new Button("Check");
        buttons = new HBox(clearBtn, checkBtn);
        grid = new GridPane();
        root = new VBox(grid, buttons);
        scene = new Scene(root);
        
        buttons.setSpacing(30.0);
        buttons.setAlignment(Pos.CENTER);
        
        fill();
        
        clearBtn.setOnAction(clearHandler);
        checkBtn.setOnAction(checkHandler);
        scene.setOnKeyPressed(e -> {
            switch(e.getCode()){
                case A:
                    clearHandler.handle(e);
                    break;
                    
                case C:
                    checkHandler.handle(e);
                    break;
            }
        });
    }
    
    /**
     * Application start
     * @param window the primary stage
     */
    @Override
    public void start(Stage window) {
        window.setScene(scene);
        window.setTitle("TRE!");
        window.setResizable(false);
        window.show();
    }
    
    /**
     * Fills the grid with cells, with a 50% probability of inserting a two- or three-type cell
     */
    private void fill(){
        for(int y = 0; y < ROWS; y++){
            for(int x = 0; x < COLS; x++){
                if(new Random().nextDouble() < 0.5){
                    grid.add(new TwoCell(), x, y);
                }else{
                    grid.add(new ThreeCell(), x, y);
                }
            }
        }
    }
    //TODO
    private String checkLines(){
        boolean flag = false;
        Cell compare;
        Cell current;
        for(int y = 0; y < ROWS; y++){
            compare = getCellByCoords(0, y); //first cell of the row
            for(int x = 1; x < COLS; x++){
                current = getCellByCoords(x, y);
                System.out.println( "x:" +  x + " y:" + y + " " + current.shape.toString());
                if( !compare.equals( current ) ){
                    break;
                }
                flag = true;
            }
            if(flag){
                return "Riga" + (GridPane.getRowIndex(compare) + 1);
            }
        }
        return null;
    }
    
    /**
     * Returns the cell at the desired coordinates
     * @param col The desired column
     * @param row The desired row
     * @return The desired cell
     */
    Cell getCellByCoords(int col, int row){
        for(Node n : grid.getChildren()){
            if(GridPane.getColumnIndex(n) == col && GridPane.getRowIndex(n) == row && n instanceof Cell){
                return ((Cell) n);
            }
        }
        return null;
    }
    
    /**
     * Event handler for clearing all cells
     */
    EventHandler clearHandler = e -> {
        for(Node n : grid.getChildren()){
            if(n instanceof Cell){
                ((Cell) n).clear();
            }
        }
    };
    
    /**
     * Event handler for checking uniform rows/columns
     */
    EventHandler checkHandler = e -> {
        System.out.println(checkLines());
    };
    
}
