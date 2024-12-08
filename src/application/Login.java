package application;

// This class represents a simple Login model with a username and password.
public class Login {

    // Instance variables to store the username and password of the user.
    private String username;
    private String password;

    // Constructor to initialize the Login object with a username and password.
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter method for retrieving the username.
    public String getUsername() {
        return username;
    }

    // Setter method for updating the username.
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter method for retrieving the password.
    public String getPassword() {
        return password;
    }

    // Setter method for updating the password.
    public void setPassword(String password) {
        this.password = password;
    }

}
