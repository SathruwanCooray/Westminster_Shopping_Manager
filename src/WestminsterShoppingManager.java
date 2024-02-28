import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class WestminsterShoppingManager implements ShoppingManager{
    //Variables
    private static List<Product> productList = new ArrayList<>();
    private List<String> productIDList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private boolean retrievedSavedData = false;

    /**
     *   The Main menu that control the console inputs.
     *   Calls other methods to perform operations.
     *   Making product items and adding it to the arraylist.
     */
    public boolean runMenu(){

        if (!retrievedSavedData){ //If statement make the block run only once
            readFile(); //Execute the read file method
            retrievedSavedData = true;
        }


        boolean exit = false;
        while (true){
            System.out.println("""
                    *** Welcome to Online Westminster Shopping Manager ***
                    1. Add a new product
                    2. Delete a product
                    3. Print the list of products
                    4. Save in a file
                    5. Open GUI
                    6. Stop the program""");

            System.out.println("Enter a Command: ");
            String commandInput = scanner.nextLine();

            if (Objects.equals(commandInput, "1")) { //First Option in the menu to add a product to the system
                String productID = productIDGenerator(); //Generate a automated product ID
                System.out.println("Enter Product Category [Electronic/Clothing]: ");
                String categoryInput = scanner.nextLine(); //Take product category from user
                if (!categoryInput.equalsIgnoreCase("electronic") && !categoryInput.equalsIgnoreCase("clothing")){ //Error Handling
                    System.out.println("Invalid input, Please try again.");
                    continue;
                }
                System.out.println("Enter product name: ");
                String productName = scanner.nextLine(); //Take product Name from user
                System.out.println("Enter number of available products: ");
                int numOfProductAvailable = scanner.nextInt(); //Take number of products available from user
                scanner.nextLine();//Newline Character
                System.out.println("Enter product price: ");
                double productPrice = scanner.nextDouble(); //Take product price from user
                scanner.nextLine();//Newline Character

                if (categoryInput.equalsIgnoreCase("electronic")) {
                    System.out.println("Enter product brand: ");
                    String productBrand = scanner.nextLine(); //Take product brand from user
                    System.out.println("Enter product warranty: ");
                    int productWarranty = scanner.nextInt(); //Take product warranty from user
                    scanner.nextLine();//Newline Character
                    System.out.println("Product ID item: " + productID + "\n");

                    Product electronicProduct = new Electronics(productID,productName,numOfProductAvailable,productPrice,"Electronic",productBrand,productWarranty);
                    addProduct(electronicProduct); //Create product object and add to an arraylist
                }else{
                    System.out.println("Enter product size: ");
                    String productSize = scanner.nextLine(); //Take product size from user
                    System.out.println("Enter product Color: ");
                    String productColour = scanner.nextLine(); //Take product color from user
                    System.out.println("Product ID item: " + productID);

                    Product clothingProduct = new Clothing(productID,productName,numOfProductAvailable,productPrice,"Clothing",productSize,productColour);
                    addProduct(clothingProduct); //Create product object and add to an arraylist
                }
                //Second Option in the menu to remove a product in the system
            }else if(Objects.equals(commandInput, "2")){
                System.out.println("Enter Product ID: ");
                String productIDRemoval = scanner.nextLine();
                deleteProduct(productIDRemoval);
                System.out.println("Product with the ID [" + productIDRemoval + "] has been successfully removed from the system\n");
            }else if (Objects.equals(commandInput, "3")){ //Second Option in the menu to remove a product in the system
                printProducts(); //Runs the print product method
            }else if (Objects.equals(commandInput, "4")){ //fourth Option in the menu to save the product data into a text file
                saveFile(); //Runs the save file method
            }else if (Objects.equals(commandInput, "5")){ //fifth Option in the menu to open the user-interface
                openUI();  //Runs the open UI method
            }else if (Objects.equals(commandInput, "6")){ //sixth Option in the menu to stop the program
                exit = true;
                break;
            }
        }
        return exit;
    }

    /**
     *   Unique Product ID generator.
     *   Retrieve a random index from the iDCharacters variable and format the ID using StringBuilder.
     *   Check with ProductID list. if the ID already exist, a new unique ID will be made.
     */
    public String productIDGenerator(){
        String iDCharacters = "ABCDEFGHIJKLMNOFQRSTUVWXYZ01234567890123456789"; //Provide Characters to make the product ID
        StringBuilder stringBuilder = new StringBuilder(); //Initialing string builder
        Random random = new Random(); //Initialing Random

        while (true){
            for(int i = 0;i < 6;i++){
                int index = random.nextInt(iDCharacters.length()); //Retrieve a random index from iDCharacters
                stringBuilder.append(iDCharacters.charAt(index));  //Format the String using the char of retrieve index
            }
            if (!productIDList.contains(stringBuilder)) {   //Checks with productIDList
                productIDList.add(stringBuilder.toString());    //Add to productIDList
                return stringBuilder.toString();
            }
        }
    }

    /**
     *   Adding product to the System
     *   Add the Product to the ProductList using the input retrieving from the parameter.
     *   Manipulated by the runMenu method.
     */
    @Override
    public void addProduct(Product addingProduct) {
        productList.add(addingProduct); //Adding to the productList
    }

    /**
     *   Removing a product from the System
     *   Removing the Product from the ProductList using the input retrieving from the parameter.
     *   Manipulated by the runMenu method.
     *   Removing both Product ID list and Product list.
     */
    @Override
    public void deleteProduct(String productID) {
        for (int i = 0;i < productList.size();i++){ //Loop through the product list
            if (productList.get(i).getProductID().equals(productID)){ //Checks for the specific product ID
                productList.remove(i); //Remove from the list
                productIDList.remove(i); //Remove from the list
            }
        }

    }
    /**
     *   Printing the products in the system.
     *   Making a copy of Product and Sorting Alphabetically using the product id.
     *   Checking if the item is an instance of Electronic, if it is casting it to electronic and printing its attributes.
     *   Removing both Product ID list and Product list.
     */
    @Override
    public String printProducts() {
        List<Product> sortedProductList = new ArrayList<>(productList); //Initialize sorted arraylist
        sortedProductList.sort(Comparator.comparing(Product::getProductID)); //Sort the arraylist
        for (int i = 0;i < sortedProductList.size();i++){ //Prints out the sorted
            int index = i;
            System.out.println("\n" + (index+1) + ". Product ID: " + sortedProductList.get(i).getProductID() + //prints out productID
                    "\nProduct Name: " + sortedProductList.get(i).getProductName() + //prints out product name
                    "\nNumber of products available: " +  sortedProductList.get(i).getNumOfItemsAvailable() + //prints out number of products available
                    "\nProduct Price: RS." + sortedProductList.get(i).getPrice()+ //prints out product price
                    "\nProduct Category: " + sortedProductList.get(i).getCategory()); //prints out product category
            if (sortedProductList.get(i) instanceof  Electronics){
                Electronics electronics = (Electronics) sortedProductList.get(i);
                System.out.println("Product Brand: " + electronics.getBrand() + //prints out product brand
                        "\nProduct warranty: " + electronics.getWarranty() + //prints out product warranty
                        "\n");
            }else {
                Clothing clothing = (Clothing) sortedProductList.get(i);
                System.out.println("Product size: " + clothing.getSize() + //prints out product size
                        "\nProduct color: " + clothing.getColor() + //prints out product color
                        "\n");
            }
        }

        return "Successfully printed the products"; //For JUnit Testing
    }
    /**
     *   Save the product data into a text file.
     *   FileWriter appropriately printout each attribute in each line to maintain clarity.
     */
    @Override
    public String saveFile() {
        try {
            FileWriter fileWriter = new FileWriter("ProductList.txt");
            for (int i = 0;i < productList.size();i++){
                int index = i;
                fileWriter.write("\n" + (index+1) + ". Product ID: " + productList.get(i).getProductID() + "\n" + //Write product ID to the file
                        "Product category: " + productList.get(i).getCategory() + "\n" + //Write product category to the file
                        "Product Name: " + productList.get(i).getProductName() + "\n" + //Write product name to the file
                        "Number of products available: " + productList.get(i).getNumOfItemsAvailable() + "\n" + //Write number of product available to the file
                        "Product price: " + productList.get(i).getPrice() + "\n"); //Write product price to the file
                if (productList.get(i) instanceof Electronics){
                    Electronics electronics = (Electronics) productList.get(i);
                    fileWriter.write("Product Brand: " + electronics.getBrand() + //Write product brand to the file
                            "\nProduct warranty: " + electronics.getWarranty() + //Write product warranty to the file
                            "\n");

                }else {
                    Clothing clothing = (Clothing) productList.get(i);
                    fileWriter.write("Product size: " + clothing.getSize() + //Write product size to the file
                            "\nProduct color: " + clothing.getColor() + //Write product color to the file
                            "\n");
                }
            }
            fileWriter.close(); //Closes file writer
            System.out.println("Products successfully saved into a text file."); //Output a message
        }catch (IOException e){
            System.out.println(e);
        }

        return "Successfully saved the file"; //For JUnit Testing
    }
    /**
     *   Read data saved in a file and input into the program.
     *   Separate each attribute and turn them into product objects .
     */
    @Override
    public void readFile() {
        productList.clear(); //Clears data (Just to make sure)
        ArrayList<String> textFileProductData = new ArrayList<>(); //Initializing of ArrayList
        int textFileArrayListDataIndex;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ProductList.txt")); //File directory initializing
            String line;
            while ((line = bufferedReader.readLine()) != null){
                if (line.trim().isEmpty()){
                    continue;
                }
                String[] parts = line.split(": "); //Separate the sentence to two parts using ":" and save in an array
                textFileProductData.add(parts[1]); //Adding the second element of the array to an arraylist for easy accesses
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        textFileArrayListDataIndex = textFileProductData.size();

        int numberOfProduct = textFileArrayListDataIndex/7;

        for (int i = 0;i < numberOfProduct;i++){
            int numberOfItemAvailable = Integer.parseInt(textFileProductData.get(3));
            double price = Double.parseDouble(textFileProductData.get(4));
            productIDList.add(textFileProductData.get(0));

            if (textFileProductData.get(1).equals("Electronic")){
                int readFileWarranty = Integer.parseInt(textFileProductData.get(6));

                Product electronicProduct = new Electronics(
                        textFileProductData.get(0), // Product ID
                        textFileProductData.get(1), // Product Name
                        numberOfItemAvailable, // Product Count
                        price,  //Product Price
                        "Electronic",  //Product Category
                        textFileProductData.get(5), //Product Brand
                        readFileWarranty    //Product warranty
                );
                addProduct(electronicProduct);
            }else if (textFileProductData.get(1).equals("Clothing")){

                Product clothingProduct = new Clothing(
                        textFileProductData.get(0), // Product ID
                        textFileProductData.get(1), // Product Name
                        numberOfItemAvailable, // Product Count
                        price,  //Product Price
                        "Clothing",  //Product Category
                        textFileProductData.get(5), //Product Size
                        textFileProductData.get(6)    //Product Color
                );
                addProduct(clothingProduct);
            }
            for (int x = 6; x >= 0; x--) {
                textFileProductData.remove(x);
            }
        }
    }

    /**
     *   Opening User-Interface
     *   Takes username and the password as inputs
     *   Use user class.
     */
    @Override
    public String openUI() {
        String username;
        String password = "";
        User user = new User();

        do {
            System.out.print("Enter Username: ");
            username = scanner.nextLine(); //Take username from the user

            if (!username.equals(user.getUserName())) {
                System.out.println("\nInvalid Username"); //Error Handling
                continue;
            }

            System.out.print("Enter Password: ");
            password = scanner.nextLine(); //Take password from the user
            if (!password.equals(user.getPassWord())) {
                System.out.println("\nInvalid Password"); //Error Handling
            }
        } while (!(username.equals(user.getUserName()) && password.equals(user.getPassWord())));


        MainFrame mainFrame = new MainFrame(); //Initialize mainframe object

        return "Successfully Opened the User-Interface"; //For JUnit Testing
    }
    /**
     *   Getter for productList.
     */
    public static List<Product> getProductList() {
        return productList;
    }

}
