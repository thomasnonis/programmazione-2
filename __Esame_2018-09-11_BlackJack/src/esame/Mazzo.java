package esame;

import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class Mazzo {
    private static final int N_NORMAL_CARDS = 16;
    private static final int N_BLACKJACKS = 1;
    
    private ArrayList<Carta> mazzo;

    public Mazzo() {
        mazzo = new ArrayList<>();
        init();
    }
    
    private void init(){
        //TODO
        shuffle();
        System.err.println("Method init not implemented");
    }
    
    private void shuffle(){
        //TODO
        System.err.println("Method shuffle not implemented");
    }
}
