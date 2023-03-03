package esame.auto.motori;

/**
 * @author Thomas Nonis
 * @author thomas.nonis@studenti.unitn.it
 */
public abstract class Motore {
    private int cilindrata;

    public Motore(int cilindrata) {
        this.cilindrata = cilindrata;
    }

    public int getCilindrata() {
        return cilindrata;
    }
    
    
    
    
}
