public class NonAlimentare extends Prodotto {
    public String material;
    public double recyclableDiscount = 0.1; //10%

    public NonAlimentare(String barcode, String description, String material, double basePrice) {
        super(barcode, description, basePrice);
        this.material = material.toLowerCase().trim();
    }

    public boolean isRecyclable(){
        return (material.equals("carta") || material.equals("plastica") || material.equals("vetro"));
    }

    /*
    @Override
    public double price() {
        if(isRecyclable()){
            return super.price() * (1 - recyclableDiscount);
        }
        return super.price();
    }
    */

    public double discount(){
        return discount + recyclableDiscount;
    }

    @Override
    public String toString() {
        return "NonAlimentare{" + super.label() + ", Material=" + material +'}';
    }
}
