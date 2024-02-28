public abstract class Product {
    //Variables
    private  String productID;
    private  String productName;
    private  int numOfItemsAvailable;
    private double price;
    private String category;

    //Constructors
    public Product(String productID, String productName, int numOfItemsAvailable, double price,String category) {
        this.productID = productID;
        this.productName = productName;
        this.numOfItemsAvailable = numOfItemsAvailable;
        this.price = price;
        this.category = category;
    }
    public Product(){

    }

    //Getters
    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getNumOfItemsAvailable() {
        return numOfItemsAvailable;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    //Setters
    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setNumOfItemsAvailable(int numOfItemsAvailable) {
        this.numOfItemsAvailable = numOfItemsAvailable;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
