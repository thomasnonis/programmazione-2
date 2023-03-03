/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame.auto;

import esame.auto.motori.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Sportiva extends Auto{
    private List<String> accessori;
    
    public Sportiva(String modello, Motore motore, int anno, double prezzoBase, int meseEsp) {
        super(modello, motore, anno, prezzoBase, meseEsp);
        accessori = new ArrayList<>();
    }

    public Sportiva(String modello, Motore motore, int anno, double prezzoBase, int meseEsp, String ...accessori) {
        super(modello, motore, anno, prezzoBase, meseEsp);
        this.accessori = new ArrayList<>();
        for(String a : accessori){
            this.accessori.add(a);
        }
    }

    
    /**
     * Se l'auto è ibrida toglie gli incentivi dal prezzo, altrimenti ritorna il prezzo base
     * @return Il prezzo dell'auto
     */
    @Override
    public double getPrezzo() {
        return (MOTORE instanceof Ibrido) ? (PREZZO_BASE - Ibrido.INCENTIVI) : PREZZO_BASE;
    }
    
    // dovrebbe funzionare facendo getAccessori().add();
    public List getAccessori(){
        return accessori;
    }
    
    @Override
    public String toString(){
        String txt = super.toString();
        txt += "accessori in offerta=[";
        for(String s : accessori){
            txt += s + ", ";
        }
        //removes last ", "
        txt = txt.substring(0, txt.length()-2);
        txt += "]";
        return txt;
    }
    
}