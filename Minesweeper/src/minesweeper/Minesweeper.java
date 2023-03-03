package minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Main application of the minesweeper game
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Minesweeper extends Application {
    /**
     * The size of the square grid
     */
    private static final int GRID_SIZE = 9;
    
    /**
     * The number of bombs in the grid
     */
    private static final int N_BOMBS = 10;
    
    /**
     * The number of non-bomb cells the user has yet to click
     */
    int cellsLeft;
    
    /**
     * Keeps track of the mode
     */
    boolean peekMode;
    
    //GUI elements
    Button testBtn, randomBtn, quitBtn, peekBtn;
    HBox controls;
    GridPane grid;
    VBox root;
    Scene scene;
    
    /**
     * Matrix used to keep track of the coordinates of the cells
     */
    Cell[][] cells = new Cell[GRID_SIZE][GRID_SIZE];
    
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Initializes the application
     */
    @Override
    public void init(){
        testBtn = new Button("Test");
        randomBtn = new Button("Random");
        quitBtn = new Button("Abbandona");
        peekBtn = new Button("Sbircia");
        
        controls = new HBox(testBtn, randomBtn, quitBtn, peekBtn);
        grid = new GridPane();
        root = new VBox(grid, controls);
        
        scene = new Scene(root);
        
        grid.setVgap(1.0);
        grid.setHgap(1.0);
        grid.setMouseTransparent(true);
        root.setAlignment(Pos.CENTER);
        controls.setAlignment(Pos.CENTER);
        controls.setPadding( new Insets(10.0, 0, 10.0, 0) );
        controls.setSpacing(25.0);
        
        reset();
    }

    /**
     * Starts the application
     * @param window The primary stage
     */
    @Override
    public void start(Stage window) {
        testBtn.setOnAction(e -> {
            fillTest();
            grid.setMouseTransparent(false);
            testBtn.setDisable(true);
            randomBtn.setDisable(true);
            quitBtn.setDisable(false);
            peekBtn.setDisable(false);
        });
        
        randomBtn.setOnAction(e -> {
            fillRandom();
            grid.setMouseTransparent(false);
            testBtn.setDisable(true);
            randomBtn.setDisable(true);
            quitBtn.setDisable(false);
            peekBtn.setDisable(false);
        });
        
        
        quitBtn.setOnAction(e ->  {
            Platform.exit();
        });
        
        peekBtn.setOnAction(e -> {
            peekMode = true;
            peekBtn.setDisable(true);
        });
        
        window.setScene(scene);
        window.sizeToScene();
        window.setResizable(false);
        window.setTitle("Minesweeper");
        window.show();
    }
    
    /**
     * Sets the game to its initial status
     */
    public void reset(){
        peekMode = false;
        testBtn.setDisable(false);
        randomBtn.setDisable(false);
        quitBtn.setDisable(true);
        peekBtn.setDisable(true);
        grid.setMouseTransparent(true);
        cellsLeft = GRID_SIZE * GRID_SIZE;
        fill();
    }
    
    /**
     * Fills the grid with normal cells
     */
    private void fill(){
        for(int y = 0; y < GRID_SIZE; y++){
            for(int x = 0; x < GRID_SIZE; x++){
                setCell(new Normal(), x, y);
            }
        }
        //computeCells() should not be needed at this point, as no bombs are present
    }
    
    /**
     * Replaces the diagonal and the top right cells with bomb cells
     */
    private void fillTest(){
        for(int i = 0; i < GRID_SIZE; i++){
            setCell(new Bomb(), i, i);
        }
        setCell(new Bomb(), GRID_SIZE - 1, 0);
        computeCells();
    }
    
    /**
     * Randomly fills the grid with bomb and normal cells
     */
    private void fillRandom(){
        ArrayList<Cell> pool = new ArrayList<>();
        for(int i = 0; i < N_BOMBS; i++){
            pool.add(new Bomb());
        }
        for(int i = 0; i < GRID_SIZE * GRID_SIZE - N_BOMBS; i++){
            pool.add(new Normal());
        }
        
        Collections.shuffle(pool);
        
        for(int y = 0; y < GRID_SIZE; y++){
            for(int x = 0; x < GRID_SIZE; x++){
                setCell(pool.remove(0), x, y);
            }
        }
        computeCells();
    }
    
    /**
     * Sets the cell at the desired coordinates in the grid. If a cell is already present, it is replaced.
     * @param cell The new cell to add
     * @param x The x coordinate
     * @param y The y coordinate
     */
    private void setCell(Cell cell, int x, int y){
        grid.getChildren().remove( cells[x][y] ); //if no element was present it does nothing
        grid.add(cell, x, y);
        cells[x][y] = cell; //purposely inverted
        cell.setOnMouseClicked(cellHandler);
    }
    
    /**
     * Computes normal cell values
     */
    private void computeCells(){
        for(int y = 0; y < GRID_SIZE; y++){
            for(int x = 0; x < GRID_SIZE; x++){
                if(cells[x][y] instanceof Normal){
                    ((Normal) cells[x][y]).setCloseBombs(cells, x, y);
                }
            }
        } 
    }
    
    /**
     * Main event handler for the game
     */
    EventHandler<Event> cellHandler = e -> {
        ((Cell) e.getSource()).trigger(this);
        if(cellsLeft <= N_BOMBS){
            new Prompt(Prompt.Status.VICTORY);
            reset();
        }
        if(peekMode){
            peekMode = false;
        }
    };
    
}
