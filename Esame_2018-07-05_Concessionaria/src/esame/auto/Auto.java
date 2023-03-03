/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame.auto;

import esame.auto.motori.*;

/**
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public abstract class Auto{
    protected static int numberOfCars = 0;
    protected final int ID; //could be long
    protected final int ANNO;
    /**
     * Mese di esposizione
     */
    protected final int MESE_ESP; //could find better object than int    
    protected final double PREZZO_BASE;  
    protected final String MODELLO;
    protected final Motore MOTORE;

    /**
     * 
     * @param modello Modello
     * @param motore Motore
     * @param anno Anno
     * @param prezzoBase Prezzo base
     * @param meseEsp Mese di esposizione
     */
    public Auto(String modello, Motore motore, int anno, double prezzoBase, int meseEsp) {
        ID = ++numberOfCars;
        this.MODELLO = modello;
        this.MOTORE = motore;
        this.ANNO = anno;
        this.PREZZO_BASE = prezzoBase;
        this.MESE_ESP = meseEsp;
    }
    
    public abstract double getPrezzo();
    
    @Override
    public String toString(){
        String txt = "";
        txt += "id=" + ID + "\n";
        txt += "tipologia=" + this.getClass().getSimpleName() + "\n";
        txt += "modello=" + MODELLO + "\n";
        txt += "cilindrata=" + MOTORE.getCilindrata() + "\n";
        txt += "motore=" + MOTORE.getClass().getSimpleName().toLowerCase() + "\n";
        txt += "anno=" + ANNO + "\n";
        txt += "prezzo base=" + PREZZO_BASE + "\n";
        txt += "prezzo vendita=" + getPrezzo() + "\n";
        txt += "esposto=" + (MESE_ESP + 1) + "\n";
        return txt;
    }
    
    public int getID(){
        return ID;
    }
    
    public int getMeseEsp(){
        return MESE_ESP;
    }
    
    public int getAnno(){
        return ANNO;
    }
    
    
}
