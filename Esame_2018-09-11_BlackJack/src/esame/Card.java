package esame;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Card class
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Card extends StackPane implements Comparable<Card>{
    /**
     * The available seeds
     */
    public enum Seed{
        C, Q, F, P, J;
    }
    
    /**
     * The minimum value a card can have
     */
    public static final int MIN_VALUE = 1;
    
    /**
     * The maximum value a card can have
     */
    public static final int MAX_VALUE = 4;
    
    /**
     * The size of the card
     */
    private static final double SIZE = 50.0;
    
    /**
     * The thickness of the border
     */
    private static final double BORDER_SIZE = 1.0;
    
    /**
     * The background color of the card
     */
    private static final Color BG = Color.TURQUOISE;
    
    /**
     * The color of the border
     */
    private static final Color BORDER_COLOR = Color.BLACK;
    
    /**
     * The value of the card
     */
    private int value;
    
    /**
     * The seed of the card
     */
    private Seed seed;
    
    /*
     * GUI elements
    */
    Rectangle rectangle;
    Label txt;
    
    /**
     * Constructor. It creates a card with the passed value and seed.
     * <br>
     * It is not possible to create BlackJacks with the constructor,
     * to do so the user should use the getBlackJack() method
     * @param value The value
     * @param seed The seed
     */
    Card(int value, Seed seed){
        //If the value is over the allowed bounds, throw an exception
        if(value < MIN_VALUE || value > MAX_VALUE){
            throw new IllegalArgumentException("Value not allowed. To create a BlackJack card use the getBlackJack() method");
        }
        this.value = value; 
        
        switch(seed){
            case C:
            case Q:
            case F:
            case P:
                this.seed = seed;
                break;
                
            default:
                throw new IllegalArgumentException("Value not allowed. To create a BlackJack card use the getBlackJack() method");
                
        }
        
        initLayout();
    }
    
    /**
     * Initializes the layout
     */
    private void initLayout(){
        rectangle = new Rectangle(SIZE, SIZE, BG);
        rectangle.setStroke(BORDER_COLOR);
        rectangle.setStrokeWidth(BORDER_SIZE);
        
        txt = new Label( toString().replace("-", " ") );
        
        this.getChildren().addAll(rectangle, txt);
    }
    

    /**
     * Gets a BlackJack card
     * @return a BlackJack card
     */
    //This is shitty AF
    public static Card getBlackJack(){
        Card blackJack = new Card(1, Seed.C);
        blackJack.seed = Seed.J;
        blackJack.value = 0;
        return blackJack;
    }
    
    @Override
    public String toString(){
        return value + "-" + seed.toString();
    }
    
    /*
    //shouldn't be needed anymore
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.value;
        return hash;
    }*/
    
    //Don't use this method, otherwise can't add multiple cards to GUI, because
    //it thinks they're duplicates (same value -> equal)
    /*
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (this.value != other.value) {
            return false;
        }
        return true;
    }*/
    
    public boolean equals(Card c) {
        return value == c.value;
    }

    @Override
    public int compareTo(Card c) {
        if(value > c.value){
            return 1;
        }
        if(value < c.value){
            return -1;
        }
        return seed.toString().compareTo( c.seed.toString() );
    }
    
    
}