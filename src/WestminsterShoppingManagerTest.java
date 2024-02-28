import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WestminsterShoppingManagerTest {
    WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();

    @Test
    void AddingProductsToTheSystem(){
        Product electronicProduct = new Electronics(westminsterShoppingManager.productIDGenerator(),"Television",10,5000.00,"Electronic","Samsung",20);
        westminsterShoppingManager.addProduct(electronicProduct);
        assertEquals(1,WestminsterShoppingManager.getProductList().size());
    }

    @Test
    void RemovingProductFromTheSystem(){
        Product electronicProduct = new Electronics(westminsterShoppingManager.productIDGenerator(),"Radio",10,5000.00,"Electronic","Panasonic",20);
        westminsterShoppingManager.addProduct(electronicProduct); //Product has been added
        westminsterShoppingManager.deleteProduct(WestminsterShoppingManager.getProductList().get(0).getProductID());
        assertEquals(0,WestminsterShoppingManager.getProductList().size());
    }

    @Test
    void PrintingTheProductList(){
        Product electronicProduct = new Electronics(westminsterShoppingManager.productIDGenerator(),"Radio",10,5000.00,"Electronic","Panasonic",20);
        Product clothingProduct = new Clothing(westminsterShoppingManager.productIDGenerator(),"Shirt",100,2500.00,"Clothing","M","Red");
        westminsterShoppingManager.addProduct(electronicProduct);//Product has been added
        westminsterShoppingManager.addProduct(clothingProduct);//Product has been added
        assertEquals("Successfully printed the products",westminsterShoppingManager.printProducts());
    }

    @Test
    void SavingProductDetails(){
        Product electronicProduct = new Electronics(westminsterShoppingManager.productIDGenerator(),"Radio",10,5000.00,"Electronic","Panasonic",20);
        Product clothingProduct = new Clothing(westminsterShoppingManager.productIDGenerator(),"Shirt",100,2500.00,"Clothing","M","Red");
        westminsterShoppingManager.addProduct(electronicProduct);//Product has been added
        westminsterShoppingManager.addProduct(clothingProduct);//Product has been added
        assertEquals("Successfully saved the file",westminsterShoppingManager.saveFile());
    }

    @Test
    void OpeningUserInterface(){
        Product electronicProduct = new Electronics(westminsterShoppingManager.productIDGenerator(),"Radio",10,5000.00,"Electronic","Panasonic",20);
        Product clothingProduct = new Clothing(westminsterShoppingManager.productIDGenerator(),"Shirt",100,2500.00,"Clothing","M","Red");
        westminsterShoppingManager.addProduct(electronicProduct);//Product has been added
        westminsterShoppingManager.addProduct(clothingProduct);//Product has been added
        assertEquals("Successfully Opened the User-Interface",westminsterShoppingManager.openUI());
    }

}