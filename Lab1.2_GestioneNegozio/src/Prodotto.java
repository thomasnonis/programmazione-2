public abstract class Prodotto {
    String barcode;
    String description;

    double basePrice;
    static double discount = 0.05; //5%

    public Prodotto(String barcode, String description, double basePrice) {
        this.barcode = barcode;
        this.description = description;
        this.basePrice = basePrice;
    }

    public double price(){
        return basePrice * (1-discount());
    }

    public abstract double discount();

    public String label(){
        return "barcode=" + barcode +
                ", description=" + description +
                ", basePrice=" + basePrice + ", price=" + price();
    }

    @Override
    public String toString() {
        return "Prodotto{" + label() + '}';
    }


}
