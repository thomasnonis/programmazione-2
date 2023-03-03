package esame;

import java.util.Random;

/**
 * Standard Card.
 * It has a value, which it displays when it is in its uncovered status.
 * When the card gets uncovered, its value is summed to the current score, when it gets covered it is subtracted.
 * @author Thomas
 * @author thomas.nonis@studenti.unitn.it
 */
public class TesseraS extends Tessera{
    /**
     * The minimum value a card can have
     */
    private final int MIN_VALUE = 1;
    
    /**
     * The maximum value a card can have
     */
    private final int MAX_VALUE = 9;
    
    /**
     * The value of the card
     */
    private int value;
    
    /**
     * Generates and sets random value for the card
     */
    public TesseraS() {
        super();
        value = new Random().nextInt(MAX_VALUE - 1) + MIN_VALUE;
    } 
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void cover() {
        covered = true;
        setText( "" );
        Game.setPts( Game.getPts() - value );
        System.out.println("Value of card: " + value + " - Current points: " + Game.getPts());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uncover() {
        covered = false;
        setText( Integer.toString(value) );
        Game.setPts( Game.getPts() + value );
        System.out.println("Value of card: " + value + " - Current points: " + Game.getPts());
    }
    
    public String toString(){
        return Integer.toString(value);
    }
    
}
