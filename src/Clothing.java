public class Clothing extends Product {
    //Variables
    private String size;
    private String color;

    //Constructors
    public Clothing(String productID, String productName, int numOfItemsAvailable, double price,String category, String size, String color) {
        super(productID, productName, numOfItemsAvailable, price,category);
        this.size = size;
        this.color = color;
    }

    //Getters
    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    //Setters
    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
