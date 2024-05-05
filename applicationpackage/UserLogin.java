package applicationpackage; // Package name for the class 

// Importing necessary libraries for the class
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

// Login class to authenticate users and allow them to login to the application
public class UserLogin extends JFrame implements ActionListener {
    private ApartmentFinder apartmentFinder; // ApartmentFinder object to access the main window components and methods
    private JLabel title, errorMessage, usernameLabel, passwordLabel; // Labels for the login window
    private JTextField usernameField; // Text fields for the login window
    private JPasswordField passwordField; // Password field for the login window
    private JCheckBox showPassword; // Checkbox to show password in the login window
    private JButton loginButton, registerButton, exitButton, adminButton; // Buttons for the login window
    private String username, password; // Username and password strings for the login window
    private boolean isCorrect = false; // Boolean variable to check if the login credentials are correct

    private static final Color VERY_DARK_RED = new Color(180, 0, 0); // Custom color for error message text in the login window 

    // Constructor for the Login class with ApartmentFinder object as a parameter to access the main window components and methods 
    public UserLogin(ApartmentFinder apartmentFinder) {
        this.apartmentFinder = apartmentFinder;

        title = new JLabel("Please register or login to your account..."); // Title label for the login window
        usernameLabel = new JLabel("Username: "); // Username label
        passwordLabel = new JLabel("Password: "); // Password label

        usernameField = new JTextField(); // Username text field
        passwordField = new JPasswordField(); // Password text field

        loginButton = new JButton("Login"); // Login button
        registerButton = new JButton("Register"); // Register button
        exitButton = new JButton("Exit"); // Exit button
        adminButton = new JButton("Admin"); // Admin button

        Font labelFont = new Font("Georgia", Font.BOLD, 14); // Font for labels
        title.setFont(labelFont); // Setting font for the title label
        usernameLabel.setFont(labelFont); // Setting font for the username label
        passwordLabel.setFont(labelFont); // Setting font for the password label

        Font fieldFont = new Font("Georgia", Font.PLAIN, 14); // Font for text fields
        usernameField.setFont(fieldFont); // Setting font for the username text field
        passwordField.setFont(fieldFont); // Setting font for the password text field

        Font buttonFont = new Font("Georgia", Font.BOLD, 14); // Font for buttons
        loginButton.setFont(buttonFont); // Setting font for the login button
        registerButton.setFont(buttonFont); // Setting font for the register button
        exitButton.setFont(buttonFont); // Setting font for the exit button
        adminButton.setFont(buttonFont); // Setting font for the admin button

        showPassword = new JCheckBox("Show Password"); // Show password checkbox
        showPassword.setBackground(Color.lightGray); // Setting background color for the checkbox
        errorMessage = new JLabel(); // Error message label for invalid login credentials
        errorMessage.setForeground(VERY_DARK_RED); // Setting text color for the error message

        title.setBounds(100, 50, 300, 30); // Setting bounds for the title label

        usernameLabel.setBounds(100, 100, 100, 30); // Setting bounds for the username label
        usernameField.setBounds(200, 100, 200, 30); // Setting bounds for the username text field
        
        passwordLabel.setBounds(100, 150, 100, 30); // Setting bounds for the password label
        passwordField.setBounds(200, 150, 200, 30); // Setting bounds for the password text field

        showPassword.setBounds(200, 200, 150, 30); // Setting bounds for the show password checkbox
        errorMessage.setBounds(160, 230, 200, 30); // Setting bounds for the errorMessage label

        loginButton.setBounds(200, 280, 100, 30); // Setting bounds for the login button

        registerButton.setBounds(100, 350, 100, 30); // Setting bounds for the register button
        exitButton.setBounds(200, 350, 100, 30); // Setting bounds for the exit button
        adminButton.setBounds(300, 350, 100, 30); // Setting bounds for the admin button

        add(title); // Adding the title label to the frame
        add(usernameLabel); // Adding the username label to the frame
        add(passwordLabel); // Adding the password label to the frame
        add(usernameField); // Adding the username text field to the frame
        add(passwordField); // Adding the password text field to the frame
        add(showPassword); // Adding the show password checkbox to the frame
        add(errorMessage); // Adding the errorMessage label to the frame
        add(loginButton); // Adding the login button to the frame
        add(registerButton); // Adding the register button to the frame
        add(exitButton); // Adding the exit button to the frame
        add(adminButton); // Adding the admin button to the frame

        loginButton.addActionListener(this); // Adding action listener to the login button
        registerButton.addActionListener(this); // Adding action listener to the register button
        exitButton.addActionListener(this); // Adding action listener to the exit button
        adminButton.addActionListener(this); // Adding action listener to the admin button

        // Showing or hiding the password based on the checkbox state
        showPassword.addItemListener(new ItemListener() {
            // Handling checkbox state change event in the ItemStateChanged method
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                // If the checkbox is selected (checked), showing the password as plain text
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    passwordField.setEchoChar((char) 0); // Showing the password as plain text
                // If the checkbox is deselected (unchecked), showing the password as masked text
                } else {
                    passwordField.setEchoChar('#'); // Showing the password as masked text
                }
            }
        });

        setTitle("Login"); // Setting title for the login window
        setSize(500, 500); // Setting size for the login window
        setLocationRelativeTo(null); // Setting the window to the center of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closing the window when the close button is clicked
        setLayout(null); // Setting layout to null for absolute positioning of components
        setVisible(true); // Setting the login window to be visible to the user
        getContentPane().setBackground(Color.LIGHT_GRAY); // Setting background color for the login window
    }

    // Handling button click events in the login window in the actionPerformed method
    @Override
    public void actionPerformed(ActionEvent e) {
        // If the login button is clicked
        if (e.getSource() == loginButton) {
            username = usernameField.getText(); // Getting the username entered by the user
            password = new String(passwordField.getPassword()); // Getting the password entered by the user
            // Trying to read user data from the file and validate the login credentials
            try {
                File file = new File("database/UserData.txt"); // Creating a new file object with the file path
                Scanner scan = new Scanner(file); // Creating a new scanner object to read the file
                // Looping through each line in the file to check for the username and password match
                while (scan.hasNextLine()) {
                    String data = scan.nextLine(); // Reading the next line from the file
                    String[] user = data.split("||"); // Splitting the line into username and password using delimiter isCorrect to true and breaking the loop
                    if (username.equals(user[0]) && password.equals(user[1])) {
                        isCorrect = true; // Setting isCorrect to true if the credentials match the user data in the file
                        break; // Breaking the loop if the credentials match
                    }
                }
                scan.close(); // Closing the scanner object after reading the file data Catching file not found exception if the file is not found in the specified path
            } catch (FileNotFoundException ex) {
                ex.printStackTrace(); // Printing the stack trace of the exception to the console
            }
            // If the login credentials are correct, hiding the login window and opening the Dashboard window
            if (isCorrect) {
                setVisible(false); // Hiding the login window after successful login
                new Dashboard(this); // Opening the Dashboard window with the current login window as the parameter
                // If the login credentials are incorrect, displaying an error message to the user
            } else {
                errorMessage.setText("Invalid username or password!"); // Setting the error message text for invalid credentials
                passwordField.setText(""); // Clearing the password field after login attempt
            }
        // If the register button is clicked, hiding the login window and opening the Register window
        } else if (e.getSource() == registerButton) {
            setVisible(false); // Hiding the login window after clicking the register button
            new Register(this); // Opening the Register window with the current login window as the parameter
        // If the exit button is clicked, exiting the application
        } else if (e.getSource() == exitButton) {
            System.exit(0); // Exiting the application with status code 0 If the admin button is clicked, hiding the login window and opening the AdminLogin window
            apartmentFinder.dispose(); // Closing the main window after exiting the application
        } else if (e.getSource() == adminButton) {
            setVisible(false); // Hiding the login window after clicking the admin button
            new AdminLogin(this); // Opening the AdminLogin window with the current login window as the parameter
            // If the action event is invalid, displaying a message to the console
        } else {
            System.out.println("Invalid action event!"); // Printing message to the console
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        new UserLogin(null); // Creating an object of the UserLogin class to display the login window
    }
}