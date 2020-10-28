public class Product {

    String id;
    double netPrice;
    String type;

    public Product(String id, double netPrice, String type) {
        this.id = id;
        this.netPrice = netPrice;
        this.type = type;
    }

    public double getNetPrice() {
        return netPrice;
    }
}
