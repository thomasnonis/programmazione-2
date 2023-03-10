public class Persona {
    private final double SCONTO_SENIOR = 0.35;
    protected final double PREZZO_STANDARD = 1000.0;
    private String cognome;
    private String nome;
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
}
