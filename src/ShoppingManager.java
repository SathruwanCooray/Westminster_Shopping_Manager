public interface ShoppingManager { //Interface with all the abstraction methods

    public void addProduct(Product addingProduct);

    public void deleteProduct(String productID);

    public String printProducts();

    public String saveFile();

    public void readFile();

    public boolean runMenu();

    public String openUI();


}
