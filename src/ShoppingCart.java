public class ShoppingCart {
    //Variables
    private Product product;
    private Integer quantity = 0;

    //Constructor
    public ShoppingCart(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    //Getters
    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    //Setters
    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity += quantity;
    }
}

