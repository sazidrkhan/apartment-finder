package applicationpackage; // Package name for the class 

import javax.swing.*;   // Importing the javax.swing package for the GUI components
import java.awt.*;  // Importing the java.awt package for the Abstract Window Toolkit components
import java.awt.event.*;    // Importing the java.awt.event package for the event listeners
import java.io.*;    // Importing the java.io package for file input and output operations
import java.util.Scanner;   // Importing the java.util.Scanner package for reading the file data

// Login class to authenticate admins and allow them to login to the application
public class AdminLogin extends JFrame implements ActionListener {
    private UserLogin userLogin; // UserLogin object to access the main window components and methods
    private JLabel title, usernameLabel, passwordLabel; // Labels for the login window
    private JTextField usernameField; // Text fields for the login window
    private JPasswordField passwordField; // Password field for the login window
    private JCheckBox showPassword; // Checkbox to show password in the login window
    private JButton loginButton, exitButton, cancelButton; // Buttons for the login window
    private String username, password; // Username and password strings for the login window
    private boolean isCorrect = false; // Boolean variable to check if the login credentials are correct

    // Constructor for this class with UserLogin object as a parameter to access the main window components and methods 
    public AdminLogin(UserLogin userLogin) {
        this.userLogin = userLogin;

        title = new JLabel("Please login to your account..."); // Title label for the login window
        usernameLabel = new JLabel("Username: "); // Username label
        passwordLabel = new JLabel("Password: "); // Password label

        usernameField = new JTextField(); // Username text field
        passwordField = new JPasswordField(); // Password text field

        exitButton = new JButton("Exit"); // Exit button
        cancelButton = new JButton("Cancel"); // Cancel button
        loginButton = new JButton("Login"); // Login button

        Font labelFont = new Font("Georgia", Font.BOLD, 14); // Font for labels
        title.setFont(labelFont); // Setting font for the title label
        usernameLabel.setFont(labelFont); // Setting font for the username label
        passwordLabel.setFont(labelFont); // Setting font for the password label

        Font fieldFont = new Font("Georgia", Font.PLAIN, 14); // Font for text fields
        usernameField.setFont(fieldFont); // Setting font for the username text field
        passwordField.setFont(fieldFont); // Setting font for the password text field

        Font buttonFont = new Font("Georgia", Font.BOLD, 14); // Font for buttons
        loginButton.setFont(buttonFont); // Setting font for the login button
        exitButton.setFont(buttonFont); // Setting font for the exit button
        cancelButton.setFont(buttonFont); // Setting font for the admin button

        showPassword = new JCheckBox("Show Password"); // Show password checkbox
        showPassword.setBackground(Color.lightGray); // Setting background color for the checkbox

        title.setBounds(90, 50, 310, 30); // Setting bounds for the title label

        usernameLabel.setBounds(90, 100, 100, 30); // Setting bounds for the username label
        usernameField.setBounds(190, 100, 200, 30); // Setting bounds for the username text field
        
        passwordLabel.setBounds(90, 150, 100, 30); // Setting bounds for the password label
        passwordField.setBounds(190, 150, 200, 30); // Setting bounds for the password text field

        showPassword.setBounds(190, 200, 150, 30); // Setting bounds for the show password checkbox

        loginButton.setBounds(190, 280, 100, 30); // Setting bounds for the login button

        exitButton.setBounds(130, 350, 100, 30); // Setting bounds for the exit button
        cancelButton.setBounds(250, 350, 100, 30); // Setting bounds for the admin button

        add(title); // Adding the title label to the frame
        add(usernameLabel); // Adding the username label to the frame
        add(passwordLabel); // Adding the password label to the frame
        add(usernameField); // Adding the username text field to the frame
        add(passwordField); // Adding the password text field to the frame
        add(showPassword); // Adding the show password checkbox to the frame
        add(loginButton); // Adding the login button to the frame
        add(exitButton); // Adding the exit button to the frame
        add(cancelButton); // Adding the admin button to the frame

        loginButton.addActionListener(this); // Adding action listener to the login button
        exitButton.addActionListener(this); // Adding action listener to the exit button
        cancelButton.addActionListener(this); // Adding action listener to the admin button

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
                    passwordField.setEchoChar('$'); // Showing the password as masked text
                }
            }
        });

        setTitle("Admin Login"); // Setting title for the login window
        setSize(500, 500); // Setting size for the login window
        setLocationRelativeTo(null); // Setting the window to the center of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closing the window when the close button is clicked
        setLayout(new BorderLayout()); // Setting layout to null for absolute positioning of components
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
            String name = null; // Variable to store the admin name after successful login
            
            try {   // Trying to read user data from the file and validate the login credentials
                File file = new File("database/AdminData.txt"); // Creating a new file object with the file path
                Scanner scan = new Scanner(file); // Creating a new scanner object to read the file
                // Looping through each line in the file to check for the username and password match
                while (scan.hasNextLine()) {
                    String data = scan.nextLine(); // Reading the next line from the file
                    String[] admin = data.split(" \\$ ");    // Splitting the line data based on the delimiter "$" into an array of strings 
                    if (username.equals(admin[2]) && password.equals(admin[6])) {
                        isCorrect = true; // Setting isCorrect to true if the credentials match the admin data in the file
                        name = admin[1]; // Storing the admin name from the file data after successful login
                        break; // Breaking the loop if the credentials match
                    }
                }
                scan.close(); // Closing the scanner object after reading the file data Catching file not found exception if the file is not found in the specified path
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Admin data file not found.", "Error", JOptionPane.ERROR_MESSAGE); // Displaying an error message dialog to the user if the file is not found in the specified path
                ex.printStackTrace(); // Printing the stack trace of the exception to the console
            }
            // If the login credentials are correct, hiding the login window and opening the Dashboard window
            if (username.isEmpty() && password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter username and password!", "Error", JOptionPane.ERROR_MESSAGE); // Displaying an error message dialog to the user
            } else if (username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter username!", "Error", JOptionPane.ERROR_MESSAGE); // Displaying an error message dialog to the user
            } else if (password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter password!", "Error", JOptionPane.ERROR_MESSAGE); // Displaying an error message dialog to the user
            } else if (isCorrect && name != null) {
                setVisible(false); // Hiding the login window after successful login
                new AdminDashboard(name);; // Opening the AdminDashboard window with the admin name as the parameter after successful login
                dispose();
                // If the login credentials are incorrect, displaying an error message to the user
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE); // Displaying an error message dialog to the user
                passwordField.setText(""); // Clearing the password field after login attempt
            }
        // If the exit button is clicked, exiting the application with status code 0
        } else if (e.getSource() == exitButton) {
            System.exit(0); // Exiting the application with status code 0 If the admin button is clicked, hiding the login window and opening the AdminLogin window
            userLogin.dispose(); // Closing the main window after exiting the application
        } else if (e.getSource() == cancelButton) {
            setVisible(false); // Hiding the login window after clicking the admin button
            usernameField.setText(""); // Clearing the username field after clicking the cancel button
            passwordField.setText(""); // Clearing the password field after clicking the cancel button
            userLogin.setVisible(true); // Opening the AdminLogin window with the current login window as the parameter
            // If the action event is invalid, displaying a message to the console
        } else {
            System.out.println("Invalid action event!"); // Printing message to the console
        }
    }

    public static void main(String[] args) {    // Main method to run the AdminLogin class
        new AdminLogin(null); // Creating an object of the AdminLogin class to display the login window
    }
}