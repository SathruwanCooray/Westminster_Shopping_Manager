import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ShoppingCartFrame extends JFrame {

    double total;
    boolean firstPurchase;
    double firstPurchaseDiscount;
    double sameCategoryDiscount;
    double finalTotal;
    ArrayList<ShoppingCart> cartData;
    Object[] columnNames = {"Product","Quality","Price"};
    DefaultTableModel cartDataModel;
    JPanel topPanel;
    JPanel bottomPanel;
    static JTable cartTable;
    JLabel cartDetails;

    /**
     *   Main user-interface for shopping-cart
     *   consist of a table model and table
     *   Imports cart data mainframe
     */
    public ShoppingCartFrame(ArrayList<ShoppingCart> carData) {
        //Initialize the data retriving to a arraylist
        this.cartData = carData;
        JFrame cartFrame = new JFrame("Shopping Cart");
        cartFrame.setSize(1000, 800); //set size
        cartFrame.setResizable(false); //avoid frame from resizing
        cartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cartFrame.setVisible(true); //set visibility to true
        //Initializing the code
        Color topPanelColour = new Color(220, 220, 220);
        Color bottomPanelColour = new Color(189, 189, 189);
        //initializing the top panel
        topPanel = new JPanel();
        topPanel.setBackground(topPanelColour);
        topPanel.setBounds(0, 0, 1000, 500);
        topPanel.setLayout(null);
        cartFrame.add(topPanel);
        //initializing the bottom panel
        bottomPanel = new JPanel();
        bottomPanel.setBackground(bottomPanelColour);
        bottomPanel.setBounds(0, 500, 1000, 300);
        bottomPanel.setLayout(null);
        cartFrame.add(bottomPanel);

        if (cartData != null){

        }

        Object[][] data = new Object[cartData.size()][columnNames.length];
        for (int i = 0;i < cartData.size();i++){ //Store product details to a string
            if (cartData.get(i).getProduct() instanceof Electronics){
                String productInfo = String.format("<html> %1s <br>" +
                                "%2s <br> " +
                                "%3s, %4s Weeks Warranty </html>",
                        cartData.get(i).getProduct().getProductID(),
                        cartData.get(i).getProduct().getProductName(),
                        ((Electronics) cartData.get(i).getProduct()).getBrand(),
                        ((Electronics) cartData.get(i).getProduct()).getWarranty());

                //add the product details to a new row in the array
               Object[] newRow = {productInfo,
                        cartData.get(i).getQuantity(),
                        cartData.get(i).getProduct().getPrice()};
                data[i] = newRow;
            }else {
                String productInfo = String.format("<html> %1s <br>" + //Store product details to a string
                                "%2s <br> " +
                                "%3s, %4s </html>",
                        cartData.get(i).getProduct().getProductID(),
                        cartData.get(i).getProduct().getProductName(),
                        ((Clothing) cartData.get(i).getProduct()).getSize(),
                        ((Clothing) cartData.get(i).getProduct()).getColor());
                //add the product details to a new row in the array
                Object[] newRow = {productInfo,
                        cartData.get(i).getQuantity(),
                        cartData.get(i).getProduct().getPrice()};
                data[i] = newRow;
            }
        }
        //make a cart table model and add the column and data
        cartDataModel = new DefaultTableModel(data,columnNames);
        cartTable = new JTable(cartDataModel); //set model
        cartTable.setRowHeight(60);
        JScrollPane scrollPane = new JScrollPane(cartTable); //set scrollpane
        scrollPane.setBounds(100,50,775,400);
        topPanel.add(scrollPane);// add scroll pane

        firstPurchase = false; //discount varible for 10%
        int electronicCount = 0; //discount varible for 20%
        int clothingCount = 0; //discount varible for 20%
        for (ShoppingCart items: cartData) { //Take the price without discounts
            double productPrice = items.getProduct().getPrice();
            total = total + productPrice;
        }
        //Calculate first purchase discount
        if (!firstPurchase){
            firstPurchase = true;
            firstPurchaseDiscount = total* 0.1;
        }
        //Calculate more than 3 items of the same category discount
        for (ShoppingCart item : cartData) {
            if (item.getQuantity() >=3){
                sameCategoryDiscount = total * 0.2;
                break;
            }
            if (item.getProduct() instanceof Electronics) {
                if (item.getQuantity() >= 3){}
                electronicCount++;
            } else {
                clothingCount++;
            }
            if (electronicCount >= 3 || clothingCount >= 3) {
                sameCategoryDiscount = total * 0.2;
            }
        }
        //Calculate the discount
        finalTotal = total-(firstPurchaseDiscount + sameCategoryDiscount);

        //Display the final price on the bottom panel
        cartDetails = new JLabel(String.format("<html><div style='text-align: right;'> Total:     %.2f £ <br><br>" +
                " First Purchase Discount (10%%):    - %.2f £ <br><br> " +
                "Three Items in Same Category Discount (20%%):    - %.2f £ <br><br> " +
                "Final Total:    %.2f £ </div></html>", total, firstPurchaseDiscount, sameCategoryDiscount, finalTotal));

        bottomPanel.add(cartDetails);
        cartDetails.setFont(new Font("Arial",Font.BOLD,20));
        cartDetails.setBounds(200,475,700,300);
    }
}
