public class Alimentare extends Prodotto {
    int expiry;
    final int expiryThreshold = 10;
    static double expiredDiscount = 0.05; //5%

    public Alimentare(String barcode, String description, int expiry, double basePrice) {
        super(barcode, description, basePrice);
        this.expiry = expiry;
    }

    public boolean isNearExpiry(){
        return (expiry < expiryThreshold);
    }

    /*
    @Override
    public double price() {
        if(isNearExpiry()){
            return super.price() * (1 - expiredDiscount);
        }
        return super.price();
    }
    */


    public double discount(){
        return discount + expiredDiscount;
    }

    @Override
    public String toString() {
        return "Alimentare{" + super.label() + ", Expiry=" + expiry +'}';
    }
}
