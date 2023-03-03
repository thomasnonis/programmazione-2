package esame.auto;

import esame.auto.motori.*;
import java.util.Calendar;

/**
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public class Berlina extends Auto{
    private static final double SCONTO = 0.01;

    public Berlina(String modello, Motore motore, int anno, double prezzoBase, int meseEsp) {
        super(modello, motore, anno, prezzoBase, meseEsp);
    }

    
    
    @Override
    public double getPrezzo() {
        double s = SCONTO * (Calendar.getInstance().get(Calendar.MONTH) - MESE_ESP);
        if(s > Utilitaria.SCONTO){
            s = Utilitaria.SCONTO;
        }
        if(MOTORE instanceof Ibrido){
            return PREZZO_BASE * (1 - s) - Ibrido.INCENTIVI;
        }
        return PREZZO_BASE * (1 - s);
    }
    
    
}
