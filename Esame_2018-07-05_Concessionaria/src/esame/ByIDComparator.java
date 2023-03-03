/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import esame.auto.Auto;
import java.util.Comparator;

/**
 * It compares two {@code Auto}s by their IDs
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class ByIDComparator implements Comparator<Auto>{

    @Override
    public int compare(Auto a1, Auto a2) {
        if(a1.getID() > a2.getID()){
            return 1;
        }
        if(a1.getID() < a2.getID()){
            return -1;
        }
        return 0;
    }
    
}
