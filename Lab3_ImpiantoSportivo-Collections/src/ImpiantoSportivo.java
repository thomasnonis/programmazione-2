import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ImpiantoSportivo {
    List<Persona> abbonati;

    ImpiantoSportivo(){
        abbonati = new ArrayList<>();
    }

    void add(Persona p){
        abbonati.add(p);
    }

    void sort(Comparator<Persona> comparator){
        Collections.sort(abbonati, comparator);
    }

    void stampaAbbonati(String tipo){
        if(tipo.equals("tutti")){
            System.out.println("=== ABBONATI - TUTTI ===");
            for (Persona p: abbonati) {
                System.out.println(p);
            }
            return;
        }

        if(tipo.equals("studenti")){
            System.out.println("=== ABBONATI - SOLI STUDENTI ===");
            for (Persona p: abbonati) {
                if(p instanceof Studente) {
                    System.out.println(p);
                }
            }
            return;
        }

        if(tipo.equals("atleti")){
            System.out.println("=== ABBONATI - SOLI ATLETI ===");
            for (Persona p: abbonati) {
                if (p instanceof InterfacciaAtleta) {
                    System.out.println(p);
                }
            }
            return;
        }
    }

    public String getAbbonati(String tipo){
        String txt;

        if(tipo.equals("tutti")){
            txt = "=== ABBONATI - TUTTI ===\n";
            for (Persona p: abbonati) {
                txt += p.toString() + "\n";
            }
            return txt;
        }

        if(tipo.equals("studenti")){
            txt = "=== ABBONATI - SOLI STUDENTI ===\n";
            for (Persona p: abbonati) {
                if(p instanceof Studente) {
                    txt += p.toString() + "\n";
                }
            }
            return txt;
        }

        if(tipo.equals("atleti")){
            txt = "=== ABBONATI - SOLI ATLETI ===" + "\n";
            for (Persona p: abbonati) {
                if (p instanceof InterfacciaAtleta) {
                    txt += p.toString() + "\n";
                }
            }
            return txt;
        }
        return "";
    }



/*
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


        palestra.sort();
        palestra.stampaAbbonati("tutti");
        palestra.stampaAbbonati("studenti");
        palestra.stampaAbbonati("atleti");


    }*/
}
