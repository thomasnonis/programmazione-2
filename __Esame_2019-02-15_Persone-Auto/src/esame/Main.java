package esame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Thomas
 */
public class Main extends Application{
    private static final double WIDTH = 500.0;
    private static final double HEIGHT = 300.0;
    
    @Override
    public void start(Stage window){
        HBox root = new HBox();
        VBox leftPanel = Persona.getInterface();
        VBox rightPanel = Automobile.getInterface();
        
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(leftPanel, rightPanel);
        
        leftPanel.setAlignment(Pos.CENTER_RIGHT);
        rightPanel.setAlignment(Pos.CENTER_LEFT);
        
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        window.setScene(scene);
        window.setTitle("Programma strano");
        window.show();
    }
    
}
