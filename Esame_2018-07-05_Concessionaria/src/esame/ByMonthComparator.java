/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame;

import esame.auto.Auto;
import java.util.Comparator;

/**
 * It compares two {@code Auto}s by their month.
 * If the months are equal, they are compared by year.
 * Otherwise they're considered equal
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class ByMonthComparator implements Comparator<Auto>{

    @Override
    public int compare(Auto a1, Auto a2) {
        if(a1.getMeseEsp() > a2.getMeseEsp()){
            return 1;
        }
        if(a1.getMeseEsp() < a2.getMeseEsp()){
            return -1;
        }
        if(a1.getAnno() > a2.getAnno()){
            return 1;
        }
        if(a1.getAnno() < a2.getAnno()){
            return -1;
        }
        return 0;
    }
    
}
