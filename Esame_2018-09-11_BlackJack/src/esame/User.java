package esame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 * Class that represents a user
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class User {
    /**
     * The collection that holds the cards of the user.
     * It is not a Set, because of the implementation of the equals method (TODO: check if it would work now)
     */
    private ArrayList<Card> hand = new ArrayList<>();
    
    /**
     * The name of the user
     */
    private String name;
    
    /**
     * The enemy of the user
     */
    private User enemy;
    
    /**
     * A flag used to know if at least a pair of cards has been removed from the user's hand
    */
    private boolean pairsFlag = false;
    
    /*
     * 
     * GUI elements
     * 
     */
    Button drawFromEnemyBtn;
    Button searchPairsBtn;
    Button drawFromDeckBtn;
    HBox handGrid;
    
    /**
     * Constructor
     * @param name The name of the user
     */
    User(String name){
        this.name = name;
    }
    
    /**
     * Sets the enemy of the user
     * @param enemy the enemy
     */
    public void setEnemy(User enemy){
        if(this == enemy){
            throw new IllegalArgumentException("An user can't have itself as its enemy!");
        }
        this.enemy = enemy;
    }
    
    /**
     * Sets the user to be the of the first rounder
     */
    public void setFirst(){
        drawFromEnemyBtn.setDisable(false);
        enemy.drawFromEnemyBtn.setDisable(true);
    }
    
    /**
     * Prints the hand of the user to the console
     */
    public void printHand(){
        String txt = name + " ";
        
        for(Card c : hand){
            txt += c.toString() + " ";
        }
        
        System.out.println( txt.trim() );
    }
    
    /**
     * Sets and gets the layout for the user
     * @param position The position of the layout. It can be either TOP or BOTTOM
     * otherwise it will throw an exception
     * @return The layout
     */
    public VBox getLayout(Pos position){
        Label nameLabel = new Label(name);
        drawFromEnemyBtn = new Button("Pesca dall'avversario");
        searchPairsBtn = new Button("Cerca le coppie");
        drawFromDeckBtn = new Button("Pesca dal mazzo");
        
        searchPairsBtn.setDisable(true);
        drawFromDeckBtn.setDisable(true);
        
        HBox controls = new HBox(nameLabel, drawFromEnemyBtn, searchPairsBtn, drawFromDeckBtn);
        handGrid = new HBox();
        handGrid.getChildren().addAll(hand);
        
        drawFromEnemyBtn.setOnAction(e -> {
            drawFromEnemy();
            printHand();
        });
        
        searchPairsBtn.setOnAction(e -> {
            pairsFlag = discardPairs();
            printHand();
            searchPairsBtn.setDisable(true);
            drawFromDeckBtn.setDisable(false);
        });
        
        drawFromDeckBtn.setOnAction(e -> {
            if(pairsFlag){
                System.out.println(name + ": ho scartato, non pesco");
            }else{
                drawFromDeck();
            }
            //reset flag for next
            pairsFlag = false;
            printHand();
            drawFromDeckBtn.setDisable(true);
            enemy.drawFromEnemyBtn.setDisable(false);
        });
        
        
        controls.setAlignment(Pos.CENTER);
        controls.setSpacing(10.0);
        handGrid.setAlignment(Pos.CENTER);
        
        VBox root;
        switch(position){
            case TOP_CENTER:
            case TOP_LEFT:
            case TOP_RIGHT:
                root = new VBox(controls, handGrid);
                break;
                
            case BOTTOM_CENTER:
            case BOTTOM_LEFT:
            case BOTTOM_RIGHT:
                root = new VBox(handGrid, controls);
                break;
                
            default:
                throw new IllegalArgumentException("Position not allowed");
        }
        root.setSpacing(2.0);
        return root;
    }
    
    /**
     * Adds a card to the user's hand
     * @param c The new card to add
     */
    public void addCard(Card c){
        getHand().add(c);
        handGrid.getChildren().add(c);
        Collections.sort(hand);
    }
   
    /**
     * Removes a card from the user's hand
     * @param c The card to remove
     */
    private void removeCard(Card c){
        getHand().remove(c);
        handGrid.getChildren().remove(c);
        //shoultn't need to sort
        
        if(getHand().size() == 0){            
            WinPrompt.showPrompt(name + " ha vinto");
        }
    }
    
    /**
     * Gets the hand of the user
     * @return The list containing all the cards of the user
     */
    private ArrayList<Card> getHand(){
        return hand;
    }
    
    /**
     * Gets a random card from the user's hand
     * @return A randomly picked card
     */
    private Card getRandomCard(){
        int n = new Random().nextInt(getHand().size());
        int i = 0;
        Card chosen = null;
        for(Card c : getHand()){
            if(i == n){
                chosen = c;
            }
            i++;
        }
        
        removeCard(chosen);
        return chosen;
    }
    
    /**
     * Draws a random card from the enemy, removing it from the enemy and adding it to himself
     */
    private void drawFromEnemy(){
        Card c = enemy.getRandomCard();
        //System.out.println("Picked: " + c.toString());
        addCard(c);
        drawFromEnemyBtn.setDisable(true);
        searchPairsBtn.setDisable(false);
    }
    
    /**
     * Draws a card from the deck
     */
    private void drawFromDeck(){
        if(BlackJack.deck.hasCards()){
            addCard( BlackJack.deck.draw() );
            return;
        }
        System.err.println("Deck is empty!");
    }
    
    /**
     * Removes pairs from user's hand.
     * @return true if at least one pair has been removed. It returns false otherwise
     */
    private boolean discardPairs(){
        //It deletes everything, need fix, but I don't know the original intent.
        //(doesn't always work)
        ArrayList<Card> toDelete = new ArrayList<>();
        
        //Can't edit in foreach
        for(Card compare : getHand()){
            for(Card c : getHand()){
                //They're equal, but NOT THE EXACT SAME
                if(c.equals(compare)  && c != compare && !toDelete.contains(c)){
                    toDelete.add(c);
                    toDelete.add(compare);
                }
            }
        }
        if(toDelete.isEmpty()){
            return false;
        }
        
        for(Card del : toDelete){
            removeCard(del);
        }
        return true;
    }
    
    
}
