public class StudenteAtleta extends Studente implements InterfacciaAtleta{
    String uni;
    String tipoCorso;
    String disciplina;
    String rilevanza;

    public StudenteAtleta(String cognome, String nome, String cf, int annoDiNascita, String uni, String tipoCorso, String disciplina, String rilevanza) {
        super(cognome, nome, cf, annoDiNascita, uni, tipoCorso);
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
        return datiPersonali() + " " + datiUniversità() + " " + datiSport() + " " + " | Tariffa: " + getTariffa();
    }
}
