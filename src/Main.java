public class Main {
    public static void main(String[] args) {
        //Create an object of westminster shopping manager
        WestminsterShoppingManager system = new WestminsterShoppingManager();
        //set boolean to not true
        boolean exit = false;
        //runs until the loop becomes false
        while (!exit){
            exit = system.runMenu();
        }

    }
}