package esame;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Bicolor extends Tile{
    Shape innerShape;
    ColorWheel innerColors;
    
    public enum ShapeType{
        CIRCLE(DISPLAY_SIZE / 4),
        SQUARE(DISPLAY_SIZE / 2);
        
        private double size;
        
        ShapeType(double size){
            this.size = size;
        }
        
        public double getSize(){
            return size;
        }
    }
    
    public Bicolor(String code, String material, double price, double size, boolean colorChangeable, ShapeType shape) {
        super(code, material, price, size, colorChangeable);
        
        innerColors = new ColorWheel();
        
        setShape(shape);
        
        this.getChildren().add(innerShape);
        
        if(colorChangeable){
            innerShape.setOnMouseClicked(e -> {
                innerShape.setFill( innerColors.getNextColor() );
            });
        }
    }
    
    private void setShape(ShapeType shape){
        switch(shape){
            case CIRCLE:
                innerShape = new Circle(shape.getSize(), innerColors.getColor(1));
                break;
                
            case SQUARE:
                innerShape = new Rectangle(shape.getSize(), shape.getSize(), innerColors.getColor(1));
                break;
        }
    }
    
}
