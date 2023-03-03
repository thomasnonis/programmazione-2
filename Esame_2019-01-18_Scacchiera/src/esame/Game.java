package esame;

import java.util.Random;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

//NOTE: TesseraV and TesseraP could be grouped by another class like TesseraPrompt

/**
 * Main class of the game
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Game extends Application{
    /**
     * Title of the game window
     */
    private static final String WINDOW_TITLE = "Play the game!";
    
    /**
     * Number of Winning cards in the game
     */
    private static final int N_V = 1;
    
    /**
     * Number of losing cards in the game
     */
    private static final int N_P = 1;
    
    /**
     * Minimum grid size allowed
     */
    private static final int MIN_GRID_SIZE = 3;
    
    /**
     * Maximum grid size allowed
     */
    private static final int MAX_GRID_SIZE = 9;
    
    /**
     * Game score
     */
    private static int pts = 0;
    
    /**
     * Number of won games
     */
    private static int victories = 0;
    
    //Layout elements
    private static Button resetBtn;
    private static Button cheatBtn;
    private static Label ptsLabel;
    private static Label victoriesLabel;
    private static GridPane grid;
    private static HBox labels;
    private static HBox buttons;
    private static VBox root;
    private static int gridSize;
    
    /**
     * Main method. It launches the application
     * @param args The arguments
     */
    public static void main(String[] args) {
        launch(args);
    }    
    
    /**
     * Initializes the application before the start() method gets called
     */
    @Override
    public void init(){
        resetBtn = new Button("Reset");
        cheatBtn = new Button("Cheat");
        ptsLabel = new Label("Punteggio " + pts);
        victoriesLabel = new Label("Partite vinte " + victories);
        
        labels = new HBox();
        labels.getChildren().addAll(ptsLabel, victoriesLabel);
        labels.setSpacing(25.0);
        labels.setAlignment(Pos.CENTER);
        
        buttons = new HBox();
        buttons.getChildren().addAll(resetBtn, cheatBtn);
        buttons.setSpacing(10.0);
        buttons.setAlignment(Pos.CENTER);
        
        grid = new GridPane();
        grid.setCursor(Cursor.CROSSHAIR);
        grid.setPadding( new Insets(0, 40.0, 0, 40.0) );
        
        root = new VBox();
        root.getChildren().addAll(labels, grid, buttons);
        root.setAlignment(Pos.CENTER);
    }
    
    /**
     * Starts the application
     * @param window the primary stage 
     */
    @Override
    public void start(Stage window){
        //need to fix. Impossible to exit/cancel
        do{
            String response = InputDialog.getResponse();
            
            try{
                gridSize = Integer.parseInt(response);
            }
            catch(NumberFormatException e){
                System.err.println("NaN");
            }
            
        }while(gridSize < MIN_GRID_SIZE || gridSize > MAX_GRID_SIZE);
        
        fill();
        
        resetBtn.setOnAction(e -> {
            reset();
            setVictories(0);
        });
        
        cheatBtn.setOnAction(e -> {
            cheat();
        });
        
        
        Scene scene = new Scene(root);
        
        window.setScene(scene);
        window.sizeToScene();
        window.setTitle(WINDOW_TITLE);
        window.setResizable(false);
        window.show();
    }
    
    /*
     * Convenience methods
     */ 
        
    /**
     * Returns the element at the desired coordinated in the grid
     * @param grid The grid into which to search for
     * @param row The desired row
     * @param col The desired column
     * @return The required element. If no element is found, it returns null
     */
    private static Node getElementByCoords(GridPane grid, int row, int col){
        for(Node n : grid.getChildren()){
            if(GridPane.getRowIndex(n) == row && GridPane.getColumnIndex(n) == col){
                return n;
            }
        }
        return null;
    }
    
    /**
     * Randomly fills an <b>empty</b> grid with cards
     */
    private static void fill(){
        int col;
        int row;
        Tessera t;
        for(int i = 0; i < N_V; i++){
            do{
                row = new Random().nextInt(gridSize);
                col = new Random().nextInt(gridSize);
            }while(getElementByCoords(grid, row, col) != null);
            t = new TesseraV();
            grid.add(t, col, row);
            t.setOnMouseClicked(gridEventListener);
        }
        
        for(int i = 0; i < N_P; i++){
            do{
                row = new Random().nextInt(gridSize);
                col = new Random().nextInt(gridSize);
            }while(getElementByCoords(grid, row, col) != null);
            t = new TesseraP();
            grid.add(t, col, row);
            t.setOnMouseClicked(gridEventListener);
        }
        
        for(int i = 0; i < (gridSize * gridSize) - N_V - N_P; i++){
            do{
                row = new Random().nextInt(gridSize);
                col = new Random().nextInt(gridSize);
            }while(getElementByCoords(grid, row, col) != null);
            t = new TesseraS();
            grid.add(t, col, row);
            t.setOnMouseClicked(gridEventListener);
        }
    }
    
    /**
     * Resets the game, zeroing out all counters and creating a new grid
     */
    public static void reset(){
        setPts(0);
        grid.getChildren().remove(0, gridSize * gridSize);
        fill();
    }
    
    /**
     * Opens the cheat window
     */
    private static void cheat(){
        Text txt = new Text();
        StackPane root = new StackPane();
        
        String text = "";
        Tessera t;
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                t = (Tessera) getElementByCoords(grid, i, j);
                text += t.toString();
                if(j != gridSize - 1){
                    text += " | ";
                }
            }
            text += "\n";
        }
        
        txt.setText( text );
        
        root.getChildren().add(txt);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20.0));
        
        Scene scene = new Scene(root);
        Stage cheatWindow = new Stage();
                
        cheatWindow.setScene(scene);
        cheatWindow.setTitle("You cheater!");
        cheatWindow.initModality(Modality.APPLICATION_MODAL);
        cheatWindow.initStyle(StageStyle.UTILITY);
        cheatWindow.showAndWait();
    }
    
    /**
     * Increments the victories counter by 1
     */
    public static void incrementVictories(){
        setVictories( getVictories() + 1);
    }
    
    /**
     * Sets the new score
     * @param newPts the new score
     */
    public static void setPts(int newPts){
        pts = newPts;
        ptsLabel.setText( "Punteggio " + pts );
        
        if(pts == 0){
            ptsLabel.setTextFill(Color.BLACK);
            return;
        }
        if(pts < 10){
            ptsLabel.setTextFill(Color.BLUE);
            return;
        }
        if(pts > 10){
            ptsLabel.setTextFill(Color.RED);
            return;
        }
        ptsLabel.setTextFill(Color.GREEN);
        
        String message = "Complimenti, hai vinto!";
        Label msg = new Label(message);
        Button okBtn = new Button("OK");
        VBox layout = new VBox();
        
        layout.getChildren().addAll(msg, okBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(5.0);
        
        Scene scene = new Scene(layout);
        Stage prompt = new Stage();
        
        
        okBtn.setOnAction(e -> {
            prompt.close();
            incrementVictories();
            reset();
        });
                
        prompt.setScene(scene);
        prompt.initModality(Modality.APPLICATION_MODAL);
        prompt.setTitle(message);
        prompt.setResizable(false);
        prompt.showAndWait();
    }
        
    /**
     * Gets the current score
     * @return The current score
     */
    public static int getPts(){
        return pts;
    }
    
    /**
     * Sets the number of victories
     * @param newVictories the new number of victories
     */
    public static void setVictories(int newVictories){
        victories = newVictories;
        victoriesLabel.setText("Partite vinte " + victories);
    }
    
    /**
     * Gets the current victories
     * @return The current victories
     */
    public static int getVictories(){
        return victories;
    } 
    
    
    /*
     * Event handlers
     */
    
    /**
     * Event handler that calls the trigger method of a cell when it is clicked on
     */
    static EventHandler gridEventListener = new EventHandler(){
        @Override
        public void handle(Event event) {
            if(event.getSource() instanceof Tessera){
                Tessera t = (Tessera) event.getSource();
                t.trigger();
            }
        }
        
    };
}
