package applicationpackage; // Package declaration for the class

import java.io.*;   // Importing necessary libraries for the class

public class User  {    // This class is used to store user information and register users
    private String name, username, password, gender;    // 
    private int age;    // Declaring variables to store user information
    private long nidOrBid;  // Declaring variables to store user information

    public User(String name, String username, int ageInt, String gender, long nidOrBidLong, String password) {  // Constructor to initialize the user object
        this.name = name;   // Initializing the name variable
        this.username = username;   // Initializing the username variable
        this.age = ageInt;  // Initializing the age variable
        this.gender = gender;   // Initializing gender variable
        this.nidOrBid = nidOrBidLong;   // Initializing nidOrBid variable
        this.password = password;   // Initializing password variable
    }

    private static int setNewUserID() {
        int lines = 1;  // Initializing lines variable to 0
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("database/UserData.txt"))) {  // Try block to read the file UserData.txt in the database folder
            while (bufferedReader.readLine() != null) {    // Loop to read the file line by line
                lines++;    // Incrementing the lines variable
            }
            // return lines;   // Returning the lines variable
        } catch (IOException e) {   // Catch block to catch any exceptions that occur while reading the file
            e.printStackTrace();    // Printing the stack trace of the exception
            // return -1;  // Returning -1 if the lines variable is not successfully initialized
        }
        return lines++; // Returning the lines variable
    }

    public boolean registerUser() { // Method to register the user
        int uid = setNewUserID();    // Initializing the uid variable with the value returned by the setNewUserID method
        try (FileWriter fileWriter = new FileWriter("database/UserData.txt", true)) {  // Try block to write user information to the file UserData.txt in the database folder 
            fileWriter.write(uid + " $ " + name + " $ " + username + " $ " + age + " $ " + gender + " $ " + nidOrBid + " $ " + password + System.lineSeparator());    // Writing user information to the file in a specific format to read later on easily 
            return true;    // Returning true if the user is successfully registered 
        } catch (IOException e) {   // Catch block to catch any exceptions that occur while writing to the file 
            e.printStackTrace();    // Printing the stack trace of the exception 
            return false;   // Returning false if the user is not successfully registered 
        }
    }
}
