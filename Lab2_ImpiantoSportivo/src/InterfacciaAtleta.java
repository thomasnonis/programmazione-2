public interface InterfacciaAtleta {
    static final double SCONTO_INTERNAZIONALE = 0.5d;
    static final double SCONTO_NAZIONALE = 0.3d;

    public double getSconto();
    public String datiSport();
    public String toString();
}
