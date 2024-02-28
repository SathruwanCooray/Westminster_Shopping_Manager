public class User {
    //Variables
    private String userName;
    private String passWord;

    //Constructor
    public User() {
        this.userName = "Sathruwan";
        this.passWord = "Sathruwan123";
    }

    //Getters
    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    //Setters
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
