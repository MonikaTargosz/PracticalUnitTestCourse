public class Product {

    String id;
    double netPrice;

    public Product(String id, double netPrice){
        this.id=id;
        this.netPrice=netPrice;
    }

    public double getNetPrice() {
        return netPrice;
    }
}
