public class Electronics extends Product{
    //Variables
    private String brand;
    private int warranty;

    //Constructors
    public Electronics(String productID, String productName, int numOfItemsAvailable, double price,String category, String brand, int warranty) {
        super(productID, productName, numOfItemsAvailable, price,category);
        this.brand = brand;
        this.warranty = warranty;
    }

    //Getters
    public String getBrand() {
        return brand;
    }

    public int getWarranty() {
        return warranty;
    }

    //Setters
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }
}
