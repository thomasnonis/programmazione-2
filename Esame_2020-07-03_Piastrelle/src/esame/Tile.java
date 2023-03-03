package esame;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Tile extends StackPane{
    protected static final double DISPLAY_SIZE = 130.0;
    private String code;
    private String material;
    private double price;
    private double size;
    private boolean colorChangeable;
    private ColorWheel colors;
    
    private Rectangle tile;

    public Tile(String code, String material, double price, double size, boolean colorChangeable) {
        this.code = code;
        this.material = material;
        this.price = price;
        this.size = size;
        this.colorChangeable = colorChangeable;
        
        colors = new ColorWheel();
        
        tile = new Rectangle(DISPLAY_SIZE, DISPLAY_SIZE, colors.getColor(0));
        
        if(colorChangeable){
            tile.setOnMouseClicked(e -> {
                tile.setFill( colors.getNextColor() );
            });
        }
        
        this.getChildren().add(tile);
    }
    
    @Override
    public String toString(){
        String txt = "";
        
        txt += "Codice: " + code + "\n";
        txt += "materiale: " + material + "\n";
        txt += "prezzo: " + price + " EUR\n";
        txt += "dimensioni: " + size + " cm\n";
        txt += "scelta colore: " + (colorChangeable ? "SI" : "NO");
        
        return txt;
    }
    
    
    
    
}
