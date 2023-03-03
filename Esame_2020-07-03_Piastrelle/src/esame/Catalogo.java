package esame;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Catalogo extends Application{
    private static final double WIDTH = 300.0;
    private static final double HEIGHT = 180.0;
    ArrayList<Tile> tiles;
    int currentTile;
    
    VBox root;
    HBox layout;
    Button nextBtn;
    Button prevBtn;
    Pane tilePane;
    Text txt;
    VBox left;
    HBox controls;
    Scene scene;
    
    @Override
    public void init(){
        tiles = new ArrayList<>();
        
        tiles.add( new Tile("P1", "terracotta", 50.0, 50.0, false) );
        tiles.add( new Tile("P2", "terracotta", 50.0, 50.0, true) );
        tiles.add( new Tile("P3", "terracotta", 50.0, 50.0, true) );
        tiles.add( new Bicolor("B1", "terracotta", 100.0, 50.0, false, Bicolor.ShapeType.CIRCLE) );
        tiles.add( new Bicolor("B2", "terracotta", 120.0, 50.0, true, Bicolor.ShapeType.SQUARE) );
        tiles.add( new Bicolor("B3", "terracotta", 140.0, 50.0, true, Bicolor.ShapeType.CIRCLE) );
        
        nextBtn = new Button("next>");
        prevBtn = new Button("<prev");
        controls = new HBox(prevBtn, nextBtn);
        txt = new Text();
        
        left = new VBox(txt, controls);
        tilePane = new Pane();
        layout = new HBox(left, tilePane);
        root = new VBox(layout);
        
        scene = new Scene(root, WIDTH, HEIGHT);
        
        root.setAlignment(Pos.CENTER);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10.0);
        left.setSpacing(15.0);
        
        currentTile = 0;
        setTile(currentTile);
        prevBtn.setDisable(true);
    }

    @Override
    public void start(Stage window) {
        
        prevBtn.setOnAction(e -> {
            prevTile();
        });
        
        nextBtn.setOnAction(e -> {
            nextTile();
        });
        
        window.setScene(scene);
        window.sizeToScene();
        window.setResizable(false);
        window.setTitle("Catalogo Piastrelle");
        window.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private void setTile(int i){
        tilePane.getChildren().clear();
        tilePane.getChildren().add( tiles.get(i) );
        txt.setText( tiles.get(i).toString() );
    }
    
    private void nextTile(){
        if(currentTile < tiles.size() - 1){
            setTile(++currentTile);
        }
        if(currentTile == tiles.size() - 1){
            nextBtn.setDisable(true);
        }
        if(currentTile > 0){
            prevBtn.setDisable(false);
        }
    }
    
    private void prevTile(){
        if(currentTile > 0){
            setTile(--currentTile);
        }
        if(currentTile == 0){
            prevBtn.setDisable(true);
        }
        if(currentTile < tiles.size() - 1){
            nextBtn.setDisable(false);
        }
    }
    
}
