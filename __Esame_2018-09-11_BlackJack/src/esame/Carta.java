package esame;

import java.util.Objects;


/**
 *
 * @author Thomas
 * @authot thomas.nonis@studenti.unitn.it
 */
public class Carta implements Comparable<Carta>{
    
    private int value;
    private String seed;

    //TODO: add value and seed checking
    public Carta(int value, String seed) {
        //throw new IllegalArgumentException("Value is not");
        this.value = value;
        this.seed = seed;
    }
    
    @Override
    public String toString(){
        return value + "-" + seed;
    }

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
        final Carta other = (Carta) obj;
        if (this.value != other.value) {
            return false;
        }
        return true;
    }

    public int compareTo(Carta c){
        if(value > c.value){
            return 1;
        }else if(value < c.value){
            return -1;
        }
        return seed.compareTo(c.seed);
    }
    
}
