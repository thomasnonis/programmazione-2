/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import java.util.Random;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author Thomas
 */
public class Cell extends StackPane{
    private static final int MAX_VALUE = 3;
    private static final int MIN_VALUE = 0;
    private static final double SIZE = 50.0;
    private static final Color TXT_COLOR = Color.YELLOW;
    private Rectangle background;
    private Text txt;
    protected IntegerProperty value = new SimpleIntegerProperty();

   
    
    
    
    public Cell(Color color, int value){
        super();
        background = new Rectangle(SIZE, SIZE);
        background.setFill(color);
        
        setValue(value);
        
        txt = new Text();
        txt.setFill(TXT_COLOR);
        txt.textProperty().bind(valueProperty().asString());
        
        this.getChildren().addAll(background, txt);
    }
    
    public Cell(Color color) {
        this(color, new Random().nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE); 
    }
    
    public Color getColor(){
        return (Color) background.getFill();
    }
    
    public int getValue() {
        return value.get();
    }

    public void setValue(int value) {
        this.value.set(value);
    }

    public IntegerProperty valueProperty() {
        return value;
    }
    
    public void increment(){
        setValue((getValue() + 1) % MAX_VALUE);
    }
    
    public void decrement(){
        setValue((getValue() - 1) % MAX_VALUE);
    }
    
}
