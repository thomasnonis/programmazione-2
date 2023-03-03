import java.util.Comparator;

public class PersonaComparatorByCognome implements Comparator<Persona> {
    @Override
    public int compare(Persona p1, Persona p2) {
        if( p1.equals(p2) ) return 0;
        if(p1.cognome.compareTo(p2.cognome) > 0){
            return 1;
        }
        if(p1.cognome.compareTo(p2.cognome) < 0){
            return -1;
        }
        if(p1.nome.compareTo(p2.nome) > 0){
            return 1;
        }
        if(p1.nome.compareTo(p2.nome) < 0){
            return -1;
        }

        return 0;
    }
}
