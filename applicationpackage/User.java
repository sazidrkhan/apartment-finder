package applicationpackage; // Package containing the classes of the application

// Importing necessary classes
import java.io.*;

// User class to store the user data
public class User  {
    // Declaring the instance variables
    private String name, username, password, gender;
    private int age;
    private long nidOrBid;

    // Constructor to initialize the instance variables
    public User(String name, String username, int ageInt, String gender, long nidOrBidLong, String password) {
        // Initializing the instance variables
        this.name = name;
        this.username = username;
        this.age = ageInt;
        this.gender = gender;
        this.nidOrBid = nidOrBidLong;
        this.password = password;
    }

    // Setting the new user ID
    private static int setNewUserID() {
        int lines = 1;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("database/UserData.txt"))) {
            while (bufferedReader.readLine() != null) { // Reading the file line by line    
                lines++;    // Incrementing the lines
            }
        } catch (IOException e) {   // Catching the IOException if any
            e.printStackTrace();    // Printing the stack trace
        }
        return lines++; // Returning the incremented lines
    }

    // Registering the user
    public boolean registerUser() {
        int uid = setNewUserID();
        try (FileWriter fileWriter = new FileWriter("database/UserData.txt", true)) {
            fileWriter.write(uid + " $ " + name + " $ " + username + " $ " + age + " $ " + gender + " $ " + nidOrBid + " $ " + password + System.lineSeparator());  // Writing the user data to the file
            return true;
        } catch (IOException e) {   // Catching the IOException if any
            e.printStackTrace();    // Printing the stack trace
            return false;
        }
    }
}
