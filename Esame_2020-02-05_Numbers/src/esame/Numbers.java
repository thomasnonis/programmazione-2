package esame;

import java.util.Random;
import javafx.application.Application;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Numbers extends Application{
    private static final int COLS = 5;
    private static final int ROWS = COLS;
    private static final double WIDTH = 250.0;
    private static final double HEIGHT = 300.0;
    private static final String TITLE = "Numbers!";
    //private int currentValue = 1;
    private final IntegerProperty currentValue = new SimpleIntegerProperty(1);

    
    
    
    
    
    Scene scene;
    VBox root;
    GridPane grid;
    HBox buttons;
    Button changeValueBtn, changeRowBtn, changeColBtn;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void init(){
        grid = new GridPane();
        changeValueBtn = new Button();
        changeRowBtn = new Button("Cambia Riga");
        changeColBtn = new Button("Cambia Colonna");
        buttons = new HBox(changeValueBtn, changeRowBtn, changeColBtn);
        root = new VBox(grid, buttons);
        scene = new Scene(root, WIDTH, HEIGHT);
        
        buttons.setAlignment(Pos.CENTER);
        //buttons.setSpacing(8.0);
        root.setSpacing(10.0);
        
        grid.setHgap(1.0);
        grid.setVgap(1.0);
        
        fill();
        
        changeValueBtn.textProperty().bind( currentValueProperty().asString() );
        changeValueBtn.setOnAction(e -> {
            incrementCurrentValue();
        });
        
        scene.setOnKeyPressed(e -> {
            switch(e.getCode()){
                case DIGIT1:;
                    setCurrentValue(1);
                    break;
                    
                case DIGIT2:
                    setCurrentValue(2);
                    break;
                    
                case DIGIT3:
                    setCurrentValue(3);
                    break;

                case DIGIT4:
                    setCurrentValue(4);
                    break;
            }
        });
        
        changeColBtn.setOnAction(e -> {
            Cell c;
            for(int x = 0; x < COLS - 1; x++){
                if((c = getCellByCoord(getCurrentValue() - 1, x)).getColor() == Color.BLUE){
                    c.decrement();
                }else{
                    c.increment();
                }
            }
            for(int y = 0; y < ROWS - 1; y++){
                //check for color (should be different classes)
                getCellByCoord(y, COLS).increment();
            }
            
            computeBottomMarginal();
            computeRightMarginal();
        });
        
        changeRowBtn.setOnAction(e -> {
            Cell c;
            for(int y = 0; y < ROWS - 1; y++){
                if((c = getCellByCoord(y, getCurrentValue() - 1)).getColor() == Color.BLUE){
                    c.decrement();
                }else{
                    c.increment();
                }
            }
            
            computeBottomMarginal();
            computeRightMarginal();
        });
    }
    
    public void start(Stage window){
        window.setScene(scene);
        window.setTitle(TITLE);
        window.sizeToScene();
        window.setResizable(false);
        window.show();
    }
    
    public int getCurrentValue() {
        return currentValue.get();
    }

    public void setCurrentValue(int value) {
        currentValue.set(value);
    }

    public IntegerProperty currentValueProperty() {
        return currentValue;
    }
    
    private void incrementCurrentValue(){
        int val = getCurrentValue();
        val++;
        //TODO: find a way to use modulus
        if(val > 4){
            val = 1;
        }
        setCurrentValue(val);
    }
    
    /**
     * Fills the grid with cells
     */
    private void fill(){
        Cell cell;
        
        //generate center cells
        for(int x = 0; x < ROWS - 1; x++){
            for(int y = 0; y < COLS - 1; y++){
                grid.add(getRandomCell(), y, x);
            }
        }
        
        computeRightMarginal();
        computeBottomMarginal();
        
    }
    
    private Cell getRandomCell(){
        if(new Random().nextDouble() < 0.5){
            return new Cell(Color.BLUE);
        }
        return new Cell(Color.GRAY);
    }
    
    private void computeRightMarginal(){
        Cell selectedCell;
        SumCell selectedMarginal;
        int sum = 0;
        for(int x = 0; x < ROWS - 1; x++){
            
            //if marginal does not exist, create it
            if(getCellByCoord(COLS, x) == null){
                selectedMarginal = new SumCell(Color.GREEN);
                grid.add(selectedMarginal, COLS, x);
            }else{
                selectedMarginal = (SumCell) getCellByCoord(COLS, x);
            }

            //assign cells to marginals
            
            for(int y = 0; y < COLS - 1; y++){
                selectedCell = getCellByCoord(y, x);
                sum += selectedCell.getValue();
            }
            selectedMarginal.setSum(sum);
            sum = 0;
        }
    }
    
    private void computeBottomMarginal(){
        Cell selectedCell;
        SumCell selectedMarginal;
        int sum = 0;
        
        for(int y = 0; y < COLS - 1; y++){
            
            //if marginal does not exist, create it
            if(getCellByCoord(y, ROWS) == null){
                selectedMarginal = new SumCell(Color.RED);
                grid.add(selectedMarginal, y, ROWS);
            }else{
                selectedMarginal = (SumCell) getCellByCoord(y, ROWS);
            }

            //assign cells to marginals
            
            for(int x = 0; x < ROWS - 1; x++){
                selectedCell = getCellByCoord(y, x);
                sum += selectedCell.getValue();
            }
            selectedMarginal.setSum(sum);
            sum = 0;
        }
    }
    
    /**
     * Gets the cell at the required coordinates in the grid
     * @param col The desired column
     * @param row The desired row
     * @return The desired cell
     */
    private Cell getCellByCoord(int col, int row){
        for(Node n : grid.getChildren()){
            if(GridPane.getColumnIndex(n) == col && GridPane.getRowIndex(n) == row){
                return (Cell) n;
            }
        }
        return null;
    }
}
