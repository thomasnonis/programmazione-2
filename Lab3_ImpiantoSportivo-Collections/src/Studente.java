public class Studente extends Persona {
    final double SCONTO_TRIENNALE = 0.4d;
    final double SCONTO_MAGISTRALE = 0.2d;
    String uni;
    String tipoCorso;

    public Studente(String cognome, String nome, String cf, int annoDiNascita, String uni, String tipoCorso) {
        super(cognome, nome, cf, annoDiNascita);
        this.uni = uni.toUpperCase().trim();
        this.tipoCorso = tipoCorso.toUpperCase().trim();
    }

    protected double getSconto(){
        if(tipoCorso.equals("LT")){
            return super.getSconto() > SCONTO_TRIENNALE ? super.getSconto() : SCONTO_TRIENNALE;
        }
        return super.getSconto() > SCONTO_MAGISTRALE ? super.getSconto() : SCONTO_MAGISTRALE;
    }

    protected String datiUniversità(){
        return uni + " " + tipoCorso;
    }

    public String toString(){
        return datiPersonali() + " " + datiUniversità() + " | Tariffa: " + getTariffa();
    }


}

