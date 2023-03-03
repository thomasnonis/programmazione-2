package esame;

import java.util.Random;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Esame 2018/06/15 - Lucky Click!
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class LuckyClick extends Application{
    /**
     * The title of the game. It is displayed on the top of the window.
     */
    public static final String TITLE = "Lucky Click!";
    /**
     * The number of rows in the game's grid
     */
    private static final int ROWS = 10;
    /**
     * The number of columns in the game's grid
     */
    private static final int COLS = 10;
    /**
     * The number of "moltiplicatori" generated in the game
     */
    private static final int N_MOLTIPLICATORI = 10;
    /**
     * The number of "divisori" generated in the game
     */
    private static final int N_DIVISORI = 10;
    /**
     * The number of "bombe" generated in the game
     */
    private static final int N_BOMBE = 5;
    /**
     * The game points.
     */
    public static int pts = 0;
    /**
     * Its value is false until the game is over.
     */
    private static boolean gameover = false;
    /**
     * The number of possible attempts that the user has left
     */
    private static int attempts = 10;
    /**
     * This variable is used when a bomb is triggered.
     * When it is true the attempts are not decremented,
     * as the cells are triggered by the bomb's side effects and not by the user
     */
    private boolean bombEnvironment = false;
    
    /**
     * Root layout of the game
     */
    private VBox root;
    /**
     * The label that shows the user the current game points
     */
    private Label ptsLabel;
    /**
     * The label that shows the user how many attempts he/she has left
     */
    private Label attemptsLabel;
    /**
     * The grid that holds all the game cells
     */
    private GridPane grid;
    
    /**
     * The <i>main</i> method, that launches the application
     * @param args The arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    @Override
    public void start(Stage window) {
        /**
         * Layout setup
         */
        root = new VBox();
        ptsLabel = new Label("Punteggio = " + pts);
        attemptsLabel = new Label("Tentativi = " + attempts);
        grid = new GridPane();
        
        root.getChildren().addAll(ptsLabel, attemptsLabel, grid);
        root.setAlignment(Pos.CENTER);
        
        grid.setCursor(Cursor.CROSSHAIR);
        
        /**
         * Grid initialization
         */
        fill(grid);
        
        /**
         * Scene and window setup
         */
        Scene scene = new Scene(root);
        
        window.setScene(scene);
        window.sizeToScene();
        window.setTitle(TITLE);
        window.setResizable(false);
        window.show();
        
        
        
    }// end of start()  
    
    /**
     * The event handler that handles the user interaction and the game's logic
     */
    EventHandler cellHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                if(event.getSource() instanceof Cella && !gameover){
                    Cella source = (Cella) event.getSource();
                    int col = GridPane.getColumnIndex(source);
                    int row = GridPane.getRowIndex(source);
                    System.out.println("Clicked cell " + row + " - " + col);
                    
                    //ripetizione di check se è attiva qua e nella classe
                    if(source.isActive()){
                        pts = source.trigger();
                        ptsLabel.setText("Punteggio = " + pts);
                        if(!bombEnvironment){
                            decrementAttempts();
                        }
                        
                        if(source instanceof Bomba){
                            bombEnvironment = true;
                            Cella c;
                            for(int i = 0; i < ROWS; i++){
                                //simulate click on (i, col)
                                Event.fireEvent(getElementByCoords(grid, i, col),
                                        new MouseEvent(MouseEvent.MOUSE_CLICKED,
                                                0, //The x with respect to the scene
                                                0, //The y with respect to the scene
                                                0, //The x coordinate relative to the screen
                                                0, //The y coordinate relative to the screen
                                                MouseButton.PRIMARY, //The mouse button used
                                                1, //The number of clicks
                                                true, //true if shift modifier was pressed
                                                true, //true if control modifier was pressed
                                                true, //true if alt modifier was pressed
                                                true, //true if meta modifier was pressed
                                                true, //true if primary button was pressed
                                                true, //true if middle button was pressed
                                                true, //true if secondary button was pressed
                                                true, //if this event was synthesized
                                                true, //whether this event denotes a popup trigger for current platform
                                                true, //still since press
                                                null //pick result
                                        ));
                            }
                            
                            //reset variable to true, because it can be set false by the previous cycle
                            bombEnvironment = true;
                            
                            for(int i = 0; i < COLS; i++){
                                //simulate click on (row, i)
                                Event.fireEvent(getElementByCoords(grid, row, i),
                                        new MouseEvent(MouseEvent.MOUSE_CLICKED,
                                                0, //The x with respect to the scene
                                                0, //The y with respect to the scene
                                                0, //The x coordinate relative to the screen
                                                0, //The y coordinate relative to the screen
                                                MouseButton.PRIMARY, //The mouse button used
                                                1, //The number of clicks
                                                true, //true if shift modifier was pressed
                                                true, //true if control modifier was pressed
                                                true, //true if alt modifier was pressed
                                                true, //true if meta modifier was pressed
                                                true, //true if primary button was pressed
                                                true, //true if middle button was pressed
                                                true, //true if secondary button was pressed
                                                true, //if this event was synthesized
                                                true, //whether this event denotes a popup trigger for current platform
                                                true, //still since press
                                                null //pick result
                                        ));
                            }
                            bombEnvironment = false;
                        }// end of bomba
                    }
                }
            }
        };
    
    
    /**
     * It returns the current game points
     * @return The current game points
     */
    public static int getPts(){
        return pts;
    }
    
    /**
     * This method decrements the attempts variable by one
     * and sets the application in gameover if the user doesn't have any attempts left
     */
    public void decrementAttempts(){
        attempts--;
        
        if(attempts == 0){
            gameover = true;
            attemptsLabel.setText("GAME OVER!");
        }else{
            attemptsLabel.setText("Tentativi = " + attempts);
        }
    }

    /**
     * This method fills the GridPane with the required cells.
     * @param grid The GridPane to fill with cells
     */
    private void fill(GridPane grid){
        Cella c;
        int col, row;
        
        for(int i = 0; i < N_BOMBE; i++){
            do{
                col = new Random().nextInt(COLS);
                row = new Random().nextInt(ROWS);
            }while(getElementByCoords(grid, row, col) != null);
            
            
            c = new Bomba();
            grid.add(c, col, row);
            c.setOnMouseClicked(cellHandler);
        }

        for(int i = 0; i < N_MOLTIPLICATORI; i++){
            do{
                col = new Random().nextInt(COLS);
                row = new Random().nextInt(ROWS);
            }while(getElementByCoords(grid, row, col) != null);
                
            
            c = new Moltiplicatore();
            grid.add(c, col, row);
            c.setOnMouseClicked(cellHandler);
        }
        
        for(int i = 0; i < N_DIVISORI; i++){
            do{
                col = new Random().nextInt(COLS);
                row = new Random().nextInt(ROWS);
            }while(getElementByCoords(grid, row, col) != null);
                
            c = new Divisore();
            grid.add(c, col, row);
            c.setOnMouseClicked(cellHandler);
        }
        
        for(int i = 0; i < ((ROWS * COLS) - N_BOMBE - N_DIVISORI - N_MOLTIPLICATORI); i++){
             do{
                col = new Random().nextInt(COLS);
                row = new Random().nextInt(ROWS);
            }while(getElementByCoords(grid, row, col) != null);
            
            c = new Base();
            grid.add(c, col, row);
            c.setOnMouseClicked(cellHandler);
        }
    }
    
    /**
     * This method returns the element at the passed coordinates.
     * If no element is found it returns null.
     * @param grid The grid into which the element is searched.
     * @param row The row of the desired element.
     * @param col The column of the desired element.
     * @return The element at the desired coordinates. If no element is found, it returns null
     */
    private Node getElementByCoords(GridPane grid, int row, int col){
        for(Node n : grid.getChildren()){
            if(GridPane.getRowIndex(n) == row && GridPane.getColumnIndex(n) == col){
                return n;
            }
        }
        return null;
    }
    
    
    
}// end of class
