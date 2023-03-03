public class ImpiantoSportivo {
    final int MAX_ABBONATI = 100;
    Persona abbonati[] = new Persona[MAX_ABBONATI];
    int abbonatiAttuali;

    ImpiantoSportivo(){
        abbonatiAttuali = 0;
    }

    void add(Persona p){
        if(abbonatiAttuali < MAX_ABBONATI) {
            abbonati[abbonatiAttuali] = p;
            abbonatiAttuali++;
            return;
        }
    }

    void stampaAbbonati(String tipo){
        int i;
        if(tipo.equals("tutti")){
            System.out.println("=== ABBONATI - TUTTI ===");
            i = 0;
            while(abbonati[i] != null || i < abbonatiAttuali){
                System.out.println(abbonati[i]);
                i++;
            }
            return;
        }
        if(tipo.equals("studenti")){
            System.out.println("=== ABBONATI - SOLI STUDENTI ===");
            i = 0;
            while(abbonati[i] != null || i < abbonatiAttuali){
                if(abbonati[i] instanceof Studente) {
                    System.out.println(abbonati[i]);
                }
                i++;
            }
            return;
        }
        if(tipo.equals("atleti")){
            System.out.println("=== ABBONATI - SOLI ATLETI ===");
            i = 0;
            while(abbonati[i] != null || i < abbonatiAttuali){
                if(abbonati[i] instanceof InterfacciaAtleta) {
                    System.out.println(abbonati[i]);
                }
                i++;
            }
            return;
        }
    }




    public static void main(String args[]){

        ImpiantoSportivo palestra = new ImpiantoSportivo();

        palestra.add(new Studente("Bianchi", "Anna", "4", 1990, "UniPD", "LM"));
        palestra.add(new Studente("Bianchi", "Giovanni", "3", 1995, "UniTN", "LT"));
        palestra.add(new StudenteAtleta("Ferrari", "Alberto", "7", 1993, "UniTN", "LM", "Atletica", "Internazionale"));
        palestra.add(new StudenteAtleta("Ferrari", "Vincenzo", "8", 1997, "UniVR", "LT", "Atletica", "Nazionale"));
        palestra.add(new Persona("Rossi", "Carla", "2", 1990));
        palestra.add(new Persona("Rossi", "Mario", "1", 1950));
        palestra.add(new Atleta("Verdi", "Alice", "6", 1967, "Curling", "Internazionale"));
        palestra.add(new Atleta("Verdi", "Giacomo", "5", 1991, "Nuoto", "Nazionale"));

        palestra.stampaAbbonati("tutti");
        palestra.stampaAbbonati("studenti");
        palestra.stampaAbbonati("atleti");


    }
}
