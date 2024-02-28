import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class MainFrame extends JFrame implements ActionListener {

    ArrayList<Product> productList;
    ArrayList<ShoppingCart> cartInformation;
    String cartProductID;
    Object[] columnNames = {"Product ID", "Name", "Category", "Price", "Info"};
    JButton shoppingCartButton;
    JButton addCartButton;
    JPanel topPanel;
    JPanel bottomPanel;
    JComboBox dropDownMenu;
    DefaultTableModel tableModel;
    DefaultTableModel electronicTableModel;
    DefaultTableModel clothingTableModel;
    JTable table;
    JLabel headingLabel;
    JLabel Details;
    JScrollPane scrollPane;
    ListSelectionModel selectionModel;

    /**
     *   Main user-interface with all the UI components
     *   Consist of multiple table-model that switch with the use of combobox
     *   Import product list from WestminsterShoppingManager.
     */
    MainFrame() {
        //Initializing westminsterShoppingManager to retrieve the product information
        productList = (ArrayList<Product>) WestminsterShoppingManager.getProductList();

        JFrame mainFrame = new JFrame("Westminster shopping"); //Initializing frame
        mainFrame.setSize(1000, 1000); //set size
        mainFrame.setResizable(false);  //avoiding the frame from resizing
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Initializing colors for the panels
        Color topPanelColour = new Color(220, 220, 220);
        Color bottomPanelColour = new Color(189, 189, 189);
        //Initializing top panel
        topPanel = new JPanel();
        topPanel.setBackground(topPanelColour);
        topPanel.setBounds(0, 0, 1000, 600);
        topPanel.setLayout(null); //set layout to null
        mainFrame.add(topPanel); //add the panel to the mainframe
        //Initializing of shopping cart button
        shoppingCartButton = new JButton("Shopping Cart");
        shoppingCartButton.setBounds(820, 10, 140, 40);
        shoppingCartButton.setFont(new Font("Arial",Font.BOLD,14));
        shoppingCartButton.addActionListener(this); //add an action listener
        shoppingCartButton.setFocusable(false);
        topPanel.add(shoppingCartButton); //add to the panel
        //Initializing table model columns
        String[] category = {"All", "Electronic", "Clothing"};
        dropDownMenu = new JComboBox<>(category); //Initializing combo box
        dropDownMenu.setBounds(400, 20, 200, 30); //set size for combo box
        dropDownMenu.setSelectedIndex(0);
        dropDownMenu.addActionListener(e ->{ //Add action listener for combo box
            String selectedCategory = (String) dropDownMenu.getSelectedItem();
            headingLabel.setVisible(false);
            Details.setVisible(false);
            //Change the table model of the table with the output of the combo box
            if ("Electronic".equals(selectedCategory)){
                electronicTableModel = productFilteringElectronic(); //method for filtering the products
                table.setModel(electronicTableModel);
                table.repaint();
                table.setDefaultRenderer(Object.class,new RedRowRenderer()); //method for making red for products less than 3
            }else if ("Clothing".equals(selectedCategory)){
                clothingTableModel = productFilteringClothing(); //method for filtering the products
                table.setModel(clothingTableModel);
                table.repaint();
                table.setDefaultRenderer(Object.class,new RedRowRenderer()); //method for making red for products less than 3
            } else if ("All".equals(selectedCategory)) {
                table.setModel(tableModel); //set to main table model
                table.repaint();
                table.setDefaultRenderer(Object.class,new RedRowRenderer()); //method for making red for products less than 3
            }
            TableColumn infoColumn = table.getColumnModel().getColumn(4);
            infoColumn.setPreferredWidth(200); //set column width
        });
        topPanel.add(dropDownMenu);
        //Initialize bottom panels
        bottomPanel = new JPanel();
        bottomPanel.setBackground(bottomPanelColour);
        bottomPanel.setLayout(null); //set layout null
        bottomPanel.setBounds(0, 800, 1000, 50); //set size
        mainFrame.add(bottomPanel); //add to panel
        //Heading label for product detail displaying
        headingLabel = new JLabel("Selected Product - Details");
        headingLabel.setBounds(50,620,400,30);
        headingLabel.setFont(new Font("Arial",Font.BOLD,25));
        headingLabel.setVisible(false);
        bottomPanel.add(headingLabel);
        //Default text for product displaying will be changed later with the help of the program
        Details = new JLabel("Default-Text");
        Details.setBounds(50,650,500,300);
        Details.setFont(new Font("Arial",Font.PLAIN,20));
        Details.setVisible(false); //sets visibility to false
        bottomPanel.add(Details);
        //Initializing of add to cart button
        addCartButton = new JButton("Add to Shopping Cart");
        addCartButton.setBounds(400, 900, 200, 50);
        addCartButton.setFont(new Font("Arial",Font.BOLD,16));
        addCartButton.addActionListener(this); //set action listener
        addCartButton.setFocusable(false);
        bottomPanel.add(addCartButton);
        //set mainframe visibility to true
        mainFrame.setVisible(true);

        Object[][] data = new Object[productList.size()][columnNames.length]; //retrieve table data
        for (int i = 0; i < productList.size();i++){ //retrieve data of the product and add row by row to the array (Electronic)
            if (productList.get(i) instanceof Electronics){
                Object[] newRow = {productList.get(i).getProductID(),
                        productList.get(i).getProductName(),
                        productList.get(i).getCategory(),
                        productList.get(i).getPrice(),
                        ((Electronics) productList.get(i)).getBrand() + ", " + ((Electronics) productList.get(i)).getWarranty() + " Weeks Warranty"};
                data[i] = newRow; //adding to the array
            }else { //retrieve data of the product and add row by row to the array (Clothing)
                Object[] newRow = {productList.get(i).getProductID(),
                        productList.get(i).getProductName(),
                        productList.get(i).getCategory(),
                        productList.get(i).getPrice(),
                        ((Clothing) productList.get(i)).getSize() + ", " + ((Clothing) productList.get(i)).getColor()};
                data[i] = newRow; //adding to the array
            }
        }
        System.out.println(Arrays.deepToString(data));
        //Initializing table model
        tableModel = new DefaultTableModel(data,columnNames);
        table = new JTable(tableModel); //set table model
        table.setDefaultRenderer(Object.class,new RedRowRenderer()); //method for making red for products less than 3
        table.setRowHeight(40);//set row height
        TableColumn infoColumn = table.getColumnModel().getColumn(4);
        infoColumn.setPreferredWidth(200); // set column width
        scrollPane = new JScrollPane(table); //add to scroll pane
        scrollPane.setBounds(100,125,775,400); //set size
        topPanel.add(scrollPane);

        selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){ //Runs the code after the value is done changing
                    int selectedRow = table.getSelectedRow(); //gets the selected row
                    if (selectedRow != -1){
                        //retrieve data into each variable
                        Object tableProductID = table.getValueAt(selectedRow,0);
                        Object tableProductName = table.getValueAt(selectedRow,1);
                        Object tableProductCategory = table.getValueAt(selectedRow,2);
                        Object tableProductInfo = table.getValueAt(selectedRow,4);
                        Object tableProductCount = null;
                        Object brand = null;
                        Object warranty = null;
                        Object size = null;
                        Object color = null;
                        //retrieve data into each variable for instance variable that differs
                        for (Product product: productList){
                            if (product.getProductID().equals(tableProductID)) {
                                tableProductCount = product.getNumOfItemsAvailable();
                                if (product instanceof Electronics){
                                    brand = ((Electronics) product).getBrand();
                                    warranty = ((Electronics) product).getWarranty();
                                }else if (product instanceof Clothing){
                                    size  = ((Clothing) product).getSize();
                                    color = ((Clothing) product).getColor();

                                }
                            }
                        }
                        //sets the heading to visible
                        headingLabel.setVisible(true);
                        String formattedString;
                        if (tableProductCategory.equals("Electronic")){
                            formattedString= String.format("<html>Product ID: %1s <br><br> " +
                                    "Category: %2s <br><br>" +
                                    "Name: %3s <br><br> " +
                                    "Warranty: %4s <br><br>" +
                                    "Brand: %5s <br><br>" +
                                    "Item Available: %6s </html>", tableProductID,tableProductCategory,tableProductName,warranty,brand,tableProductCount); //uses formatted strings with html

                            cartProductID = (String) tableProductID;

                        }else {
                            formattedString= String.format("<html>Product ID: %1s <br><br> " +
                                    "Category: %2s <br><br>" +
                                    "Name: %3s <br><br> " +
                                    "Size: %4s <br><br>" +
                                    "Colour: %5s <br><br>" +
                                    "Item Available: %6s </html>", tableProductID,tableProductCategory,tableProductName,size,color,tableProductCount); //uses formatted strings with html

                            cartProductID = (String) tableProductID;

                        }
                        Details.setText(formattedString);
                        Details.setVisible(true);
                    }
                }

            }
        });
    }
    /**
     *   Use to interact with buttons and events
     *   Method for performing actions according to changes happening to Ui
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == shoppingCartButton) {
            ShoppingCartFrame shoppingCartFrame = new ShoppingCartFrame(cartInformation); //Invoke shopping cart frame
        }

        if (e.getSource() == addCartButton) {
            if (cartInformation == null) {
                cartInformation = new ArrayList<>(); // Initialize cartInformation if it's null
            }

            boolean found = false;
            for (ShoppingCart item : cartInformation) {
                if (item.getProduct().getProductID().equals(cartProductID)) {
                    item.setQuantity(1);
                    found = true;
                    break;
                }
            }

            if (!found) {
                for (Product product : productList) {
                    if (product.getProductID().equals(cartProductID)) {
                        ShoppingCart shoppingCart = new ShoppingCart(product, 1);
                        cartInformation.add(shoppingCart);
                        break;
                    }
                }
            }
        }
    }

    /**
     *   Method to filter electronic products and create a table model
     *   returns the table model to the mainframe method
     */
    public DefaultTableModel productFilteringElectronic(){ //filter electronic products
        String brand = null;
        int warranty = 0;
        DefaultTableModel electronicModel;
        ArrayList<Product> electronicProducts = new ArrayList<>();
        for (Product product: productList){
            if (product.getCategory().equals("Electronic")){
                electronicProducts.add(product); //filter the electronic products
            }
        }
        Object[][] electronicData = new Object[electronicProducts.size()][columnNames.length]; //get the model column size and row size
        for (int i = 0;i < electronicProducts.size();i++){
            if (electronicProducts.get(i) instanceof Electronics){
                brand = ((Electronics) electronicProducts.get(i)).getBrand();
                warranty = ((Electronics) electronicProducts.get(i)).getWarranty();

            }
            //add the products into the array
            Object[] newRow = {electronicProducts.get(i).getProductID(),
                        electronicProducts.get(i).getProductName(),
                        electronicProducts.get(i).getCategory(),
                        electronicProducts.get(i).getPrice(),
                        brand + "," + warranty };


            electronicData[i] = newRow; //add the row into the array
        }

        electronicModel = new DefaultTableModel(electronicData,columnNames); //initialize the model
        return electronicModel; //return the model

    }

    /**
     *   Method to filter clothing products and create a table model
     *   returns the table model to the mainframe method
     */
    public DefaultTableModel productFilteringClothing(){ //filter clothing products
        String clothSize = null;
        String clothColour = null;
        DefaultTableModel clothingModel;
        ArrayList<Product> clothingProducts = new ArrayList<>();
        for (Product product: productList){
            if (product.getCategory().equals("Clothing")){
                clothingProducts.add(product); //filter the clothing products
            }
        }
        Object[][] clothingData = new Object[clothingProducts.size()][columnNames.length]; //get the model column size and row size
        for (int i = 0;i < clothingProducts.size();i++){
            if (clothingProducts.get(i) instanceof Clothing){
                clothSize = ((Clothing) clothingProducts.get(i)).getSize();
                clothColour = ((Clothing) clothingProducts.get(i)).getColor();

            }
            //add the products into the array
            Object[] newRow = {clothingProducts.get(i).getProductID(),
                    clothingProducts.get(i).getProductName(),
                    clothingProducts.get(i).getCategory(),
                    clothingProducts.get(i).getPrice(),
                    clothSize + "," + clothColour};

            clothingData[i] = newRow; //add the row into the array
        }

        clothingModel = new DefaultTableModel(clothingData,columnNames); //initialize the model
        return clothingModel; //return the model

    }


    private class RedRowRenderer extends DefaultTableCellRenderer {
        ArrayList<Product> filteredProducts;

        /**
         *   Used for making product in the table red with less than 3 products
         */
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            filteredProducts = LessThanThreeProductFiltering();

            String productId = table.getValueAt(row, 0).toString();
            if (isProductInFilteredList(productId)) { //check if the returned value is true if yes turn the background of the product row to red
                setBackground(Color.RED);
            } else {
                setBackground(null);
            }

            return rendererComponent;
        }
        /**
         *   Checks for similarity of the product in the filtered product list
         */
        private boolean isProductInFilteredList(String productId) { //check if the product id is in the filtered list
            for (Product product : filteredProducts) {
                if (product.getProductID().equals(productId)) {
                    return true; //if it is return true
                }
            }
            return false; //if it is not return false
        }
        /**
         *   Filter products with less than 3 available
         */
        private ArrayList<Product> LessThanThreeProductFiltering() {
            ArrayList<Product> filteredProducts = new ArrayList<>();

            for (Product product : productList) { //filters the products with less than 3 for the quantity
                if (product.getNumOfItemsAvailable() < 3) {
                    filteredProducts.add(product);
                }
            }

            return filteredProducts;
        }
    }

}