package esame;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Main class of the game
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class MakeARoad extends Application {
    /**
     * The number of columns in the grid
     */
    private static final int GRID_WIDTH = 10;
    
    /**
     * The number of rows in the grid
     */
    private static final int GRID_HEIGHT = GRID_WIDTH;
    
    /**
     * The title of the window
     */
    private static final String TITLE = "Make a road!";
    
    /**
     * The width of the buttons
     */
    private static final double BTN_WIDTH = 110.0; //px
    
    /**
     * The background color of the scene
     */
    private static final Color BG_COLOR = Color.DARKGRAY;
    
    /**
     * Flag for the addMode (mode in which a click corresponds in placing the car)
     */
    private static boolean addMode = false;
    
    /**
     * The selected direction in which to orient the cells
     */
    Dir selectedDirection = null;

    /*
     * GUI elements 
     */
    Scene scene;
    VBox root;
    GridPane grid;
    HBox controls;
    GridPane buildControls;
    VBox manageControls;
    Button northBtn, southBtn, eastBtn, westBtn, grassBtn, addBtn, moveBtn, resetBtn;

    /**
     * Initializes the application
     */
    @Override
    public void init() {
        northBtn = new Button("Strada NORD");
        southBtn = new Button("Strada SUD");
        eastBtn = new Button("Strada EST");
        westBtn = new Button("Strada OVEST");
        grassBtn = new Button("PRATO");
        addBtn = new Button("Aggiungi auto");
        moveBtn = new Button("Muovi auto");
        moveBtn.setDisable(true);
        resetBtn = new Button("Reset");

        buildControls = new GridPane();
        buildControls.add(westBtn, 0, 1);
        buildControls.add(northBtn, 1, 0);
        buildControls.add(grassBtn, 1, 1);
        buildControls.add(southBtn, 1, 2);
        buildControls.add(eastBtn, 2, 1);

        manageControls = new VBox();
        manageControls.getChildren().addAll(addBtn, moveBtn, resetBtn);

        controls = new HBox(buildControls, manageControls);
        controls.setSpacing(20.0);
        controls.setPadding(new Insets(10.0, 0.0, 0.0, 0.0));

        //Yes, I'm lazy
        for (Node n : buildControls.getChildren()) {
            if (n instanceof Button) {
                ((Button) n).setMinWidth(BTN_WIDTH);
            }
        }

        for (Node n : manageControls.getChildren()) {
            if (n instanceof Button) {
                ((Button) n).setMinWidth(BTN_WIDTH);
            }
        }

        grid = new GridPane();
        root = new VBox(grid, controls);
        root.setBackground(new Background(new BackgroundFill(BG_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        scene = new Scene(root);

        fill();
    }

    /**
     * Main application method
     * @param window the primary stage
     */
    @Override
    public void start(Stage window) {

        northBtn.setOnAction(northHandler);

        southBtn.setOnAction(southHandler);

        westBtn.setOnAction(westHandler);

        eastBtn.setOnAction(eastHandler);

        grassBtn.setOnAction(grassHandler);

        resetBtn.setOnAction(e -> {
            reset();
        });

        addBtn.setOnAction(e -> {
            addMode = true;
            addBtn.setDisable(true);
        });

        moveBtn.setOnAction(e -> {
            Road roadWithCar = null;
            
            //gets the cell with the car
            for (Node n : grid.getChildren()) {
                if (n instanceof Road && ((Road) n).hasCar()) {
                    roadWithCar = (Road) n;
                }
            }
            
            if(roadWithCar != null){
                int rowWithCar = GridPane.getRowIndex(roadWithCar);
                int colWithCar = GridPane.getColumnIndex(roadWithCar);

                Cell newCell;
                switch (roadWithCar.getDirection()) {
                    case NORTH:
                        newCell = getCellByCoords(rowWithCar - 1, colWithCar);
                        break;

                    case SOUTH:
                        newCell = getCellByCoords(rowWithCar + 1, colWithCar);
                        break;

                    case EAST:
                        newCell = getCellByCoords(rowWithCar, colWithCar + 1);
                        break;

                    case WEST:
                        newCell = getCellByCoords(rowWithCar, colWithCar - 1);
                        break;
                        
                    default:
                        newCell = null;
                }
                
                if(newCell instanceof Road){
                    roadWithCar.hideCar();
                    ((Road) newCell).showCar();
                }else{
                    System.err.println("Crash!");
                }
                
            }else{
                System.err.println("No car exists!");
            }

        });

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case N:
                    System.out.println("N pressed");
                    northHandler.handle(e);
                    break;

                case S:
                    System.out.println("S pressed");
                    southHandler.handle(e);
                    break;

                case E:
                    System.out.println("E pressed");
                    westHandler.handle(e);
                    break;

                case O:
                    System.out.println("O pressed");
                    eastHandler.handle(e);
                    break;

                case P:
                    System.out.println("P pressed");
                    grassHandler.handle(e);
                    break;

                /*default:
                    System.err.println("Letter not allowed");*/
            }
        });

        window.setScene(scene);
        window.sizeToScene();
        window.setResizable(false);
        window.setTitle(TITLE);
        window.show();
    }

    /**
     * Fills the grid with grass cells and assigns listeners
     */
    private void fill() {
        Cell c;
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                c = new Grass();
                grid.add(c, i, j);
                c.setOnMouseClicked(cellHandler);
            }
        }
    }

    /**
     * Sets the game to the initial status
     */
    private void reset() {
        grid.getChildren().remove(0, GRID_WIDTH * GRID_HEIGHT);
        fill();
        addBtn.setDisable(false);
        moveBtn.setDisable(true);
    }

    /**
     * Returns the cell at the desired coordinates
     * @param row The row of the desired cell
     * @param col The column of the desired cell
     * @return The desired cell
     */
    private Cell getCellByCoords(int row, int col) {
        for (Node n : grid.getChildren()) {
            if (GridPane.getRowIndex(n) == row && GridPane.getColumnIndex(n) == col) {
                return (Cell) n;
            }
        }
        return null;
    }
    
    private void deactivateOnlyBtn(Button button){
        for (Node n : buildControls.getChildren()) {
                if (n instanceof Button) {
                    ((Button) n).setDisable(false);
                }
            }
            button.setDisable(true);
    }

    /*
     * Event handlers
     */
    EventHandler cellHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            if (event.getSource() instanceof Cell) {
                Cell c = (Cell) event.getSource();
                int row = GridPane.getRowIndex(c);
                int col = GridPane.getColumnIndex(c);

                if (addMode) {
                    if (c instanceof Road) {
                        ((Road) c).showCar();
                        moveBtn.setDisable(false);
                    } else {
                        System.out.println("La macchina non può essere aggiunta su un prato!");
                    }
                    addMode = false;
                } else {
                    grid.getChildren().remove(c);

                    Cell newCell;
                    if (selectedDirection != null) {
                        newCell = new Road(selectedDirection);
                    } else {
                        newCell = new Grass();
                    }

                    grid.add(newCell, col, row);
                    newCell.setOnMouseClicked(cellHandler);
                }

            }
        }
    };
    
    EventHandler northHandler = e -> {
        selectedDirection = Dir.NORTH;
        deactivateOnlyBtn(northBtn);
    };

    EventHandler southHandler = e -> {
        selectedDirection = Dir.SOUTH;
        deactivateOnlyBtn(southBtn);
    };

    EventHandler westHandler = e -> {
        selectedDirection = Dir.WEST;
        deactivateOnlyBtn(westBtn);
    };

    EventHandler eastHandler = e -> {
        selectedDirection = Dir.EAST;
        deactivateOnlyBtn(eastBtn);
    };

    EventHandler grassHandler = e -> {
        selectedDirection = null;
        deactivateOnlyBtn(grassBtn);
    };
}
