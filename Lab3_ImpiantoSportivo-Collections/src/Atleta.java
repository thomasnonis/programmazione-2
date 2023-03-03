public class Atleta extends Persona implements InterfacciaAtleta{
    String disciplina;
    String rilevanza;

    public Atleta(String cognome, String nome, String cf, int annoDiNascita, String disciplina, String rilevanza) {
        super(cognome, nome, cf, annoDiNascita);
        this.disciplina = disciplina.toLowerCase().trim();
        this.rilevanza = rilevanza.toLowerCase().trim();
    }

    public double getSconto(){
        if(rilevanza.equals("internazionale")){
            return super.getSconto() > SCONTO_INTERNAZIONALE ? super.getSconto() : SCONTO_INTERNAZIONALE;
        }
        return super.getSconto() > SCONTO_NAZIONALE ? super.getSconto() : SCONTO_NAZIONALE;
    }

    public String datiSport(){
        return disciplina + " " + rilevanza;
    }

    public String toString(){
        return datiPersonali() + " " + datiSport() + " | Tariffa: " + getTariffa();
    }

}
