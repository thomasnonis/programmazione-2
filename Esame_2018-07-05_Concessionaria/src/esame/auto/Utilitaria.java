/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esame.auto;

import esame.auto.motori.*;
import java.util.Calendar;

/**
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Utilitaria extends Auto{
    protected static final double SCONTO = 0.05; //%

    public Utilitaria(String modello, Motore motore, int anno, double prezzoBase, int meseEsp) {
        super(modello, motore, anno, prezzoBase, meseEsp);
    }

    
    
    @Override
    public double getPrezzo() {
        if(MOTORE instanceof Ibrido){
            double s = SCONTO * (Calendar.getInstance().get(Calendar.MONTH) - MESE_ESP);
            if(s > Utilitaria.SCONTO){
                s = Utilitaria.SCONTO;
            }
            return PREZZO_BASE * (1 - s) - Ibrido.INCENTIVI;
        }
        return PREZZO_BASE * (1 - SCONTO);
    }
    
}
