import java.util.Comparator;
import java.util.Objects;

public class Persona {
    private final double SCONTO_SENIOR = 0.35;
    protected final double PREZZO_STANDARD = 1000.0;
    public String cognome;
    public String nome;
    private String cf;
    int annoDiNascita;

    public Persona(String cognome, String nome, String cf, int annoDiNascita) {
        this.cognome = cognome;
        this.nome = nome;
        this.cf = cf;
        this.annoDiNascita = annoDiNascita;
    }

    private boolean isSenior(){
        return annoDiNascita < 1968 ? true : false;
    }

    protected double getSconto(){
        if(isSenior()){
            return SCONTO_SENIOR;
        }
        return 0d;
    }

    protected double getTariffa(){
        return PREZZO_STANDARD * (1 - getSconto());
    }

    protected String datiPersonali(){
        return cognome + " " + nome + " " + cf + " " + annoDiNascita;
    }

    public String toString(){
        return datiPersonali() + " | tariffa: " + getTariffa();
    }

    @Override
    public boolean equals(Object o) {
        //se gli oggetti sono IDENTICI
        if (this == o) return true;
        //se o è null o se le classi sono diverse
        if (o == null || getClass() != o.getClass()) return false;

        return nome == ((Persona)o).nome &&
                cognome == ((Persona)o).cognome &&
                annoDiNascita == ((Persona)o).annoDiNascita &&
                cf == ((Persona)o).cf;
    }

    @Override
    public int hashCode(){
        return Objects.hash(nome, cognome, annoDiNascita, cf);
    }

}
