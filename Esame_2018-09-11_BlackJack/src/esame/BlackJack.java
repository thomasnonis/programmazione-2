package esame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class of the BlackJack game
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class BlackJack extends Application{
    /**
     * The deck of the game
     */
    public static Deck deck = new Deck();
    
    /**
     * The number of cards each user gets when the game begins
     */
    private static final int INIT_CARDS_PER_USER = 4;
    
    /**
     * The title of the windoe
    */
    private static final String TITLE = "BlackJack";

    
    
    //GUI elements
    Scene scene;
    BorderPane root;
    User user1;
    User user2;
    
    /**
     * The main method. It launches the application with the command line arguments list
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Initialization of the application
     */
    public void init(){
        user1 = new User("Pippo");
        user2 = new User("Pluto");
        
        VBox bottomUser = user1.getLayout(Pos.BOTTOM_CENTER);
        VBox topUser = user2.getLayout(Pos.TOP_CENTER);
        
        user1.setEnemy(user2);
        user2.setEnemy(user1);
        
        user1.setFirst();
        
        root = new BorderPane();
        
        root.setTop(topUser);
        root.setBottom(bottomUser);
        
        //distribute cards
        for(int i = 0; i < INIT_CARDS_PER_USER; i++){
            user1.addCard(deck.draw());
            user2.addCard(deck.draw());
        }
        
        user1.printHand();
        user2.printHand();
        
    }
    
    /**
     * Starts the application
     * @param window The primary stage
     */
    @Override
    public void start(Stage window){       
        scene = new Scene(root, 600, 300);
        window.setScene(scene);
        window.setTitle(TITLE);
        window.setResizable(false);
        window.show();
    }
    
}
