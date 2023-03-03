import java.util.Comparator;

public class PersonaComparatorByEta implements Comparator<Persona> {
    @Override
    public int compare(Persona p1, Persona p2) {
        return Integer.compare(p1.annoDiNascita, p2.annoDiNascita);
    }
}
