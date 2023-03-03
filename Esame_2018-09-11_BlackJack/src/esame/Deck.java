
package esame;

import esame.Card.Seed;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The deck of cards of the game
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Deck{
    /**
     * The deck
     */
    private ArrayList<Card> deck = new ArrayList<>();
    
    /**
     * Default constructor.
     * <br>
     * It fills the deck with one card for each type.
     */
    Deck(){
        for(int v = Card.MIN_VALUE; v <= Card.MAX_VALUE; v++){
            for(Seed s : Card.Seed.values()){
                if(s == Seed.J){
                    continue;
                }
                deck.add( new Card(v, s) );
            }
        }
        deck.add( Card.getBlackJack() );
        
        shuffle();
        
        //System.out.println("Deck: " + toString());
    }
    
    /**
     * Test environment for the deck
     * @param args the argument list
     */
    public static void main(String[] args) {
        Deck testDeck = new Deck();
        System.out.println(testDeck);
        testDeck.sort();
        System.out.println(testDeck);
    }
    
    /**
     * Shuffles the deck
     */
    public void shuffle(){
        Collections.shuffle(deck);
    }
    
    /**
     * Sorts the deck
     */
    public void sort(){
        Collections.sort(deck);
    }
    
    /**
     * Checks if the deck has any cards left in it
     * @return true if there are cards, false if there are none left.
     */
    public boolean hasCards(){
        return deck.size() != 0;
    }
    
    /**
     * Draws a the card on top of the deck
     * @return The card on top of the deck
     */
    public Card draw(){
        Card c = deck.get(0);
        deck.remove(c);
        return c;
    }
    
    /**
     * Gets the deck
     * @return the deck
     */
    public ArrayList<Card> getDeck(){
        return deck;
    }
    
    public String toString(){
        String txt = "";
        
        for(Card c : getDeck()){
            txt += c.toString() + " ";
        }

        return txt.trim();
    }
    
}
