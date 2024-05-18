package applicationpackage; // Package name for the class 

// Importing necessary libraries for the class
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

// Login class to authenticate users and allow them to login to the application
public class UserLogin extends JFrame implements ActionListener {
    private JLabel title, usernameLabel, passwordLabel; // Labels for the login window
    private JTextField usernameField; // Text fields for the login window
    private JPasswordField passwordField; // Password field for the login window
    private JCheckBox showPassword; // Checkbox to show password in the login window
    private JButton loginButton, registerButton, exitButton, adminButton; // Buttons for the login window
    private String username, password; // Username and password strings for the login window
    private boolean isCorrect = false; // Boolean variable to check if the login credentials are correct

    // Constructor for this class with ApartmentFinder object as a parameter to access the main window components and methods 
    public UserLogin(ApartmentFinder apartmentFinder) {
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

        title.setBounds(90, 50, 310, 30); // Setting bounds for the title label

        usernameLabel.setBounds(90, 100, 100, 30); // Setting bounds for the username label
        usernameField.setBounds(190, 100, 200, 30); // Setting bounds for the username text field
        
        passwordLabel.setBounds(90, 150, 100, 30); // Setting bounds for the password label
        passwordField.setBounds(190, 150, 200, 30); // Setting bounds for the password text field

        showPassword.setBounds(190, 200, 150, 30); // Setting bounds for the show password checkbox

        loginButton.setBounds(190, 280, 100, 30); // Setting bounds for the login button

        registerButton.setBounds(90, 350, 100, 30); // Setting bounds for the register button
        exitButton.setBounds(195, 350, 90, 30); // Setting bounds for the exit button
        adminButton.setBounds(290, 350, 100, 30); // Setting bounds for the admin button

        add(title); // Adding the title label to the frame
        add(usernameLabel); // Adding the username label to the frame
        add(passwordLabel); // Adding the password label to the frame
        add(usernameField); // Adding the username text field to the frame
        add(passwordField); // Adding the password text field to the frame
        add(showPassword); // Adding the show password checkbox to the frame
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
                    passwordField.setEchoChar('$'); // Showing the password as masked text
                }
            }
        });

        setTitle("User Login"); // Setting title for the login window
        setSize(500, 500); // Setting size for the login window
        setResizable(false);    // Disabling window resizing
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
            String name = null; // Variable to store the name of the user from the file
            // Trying to read user data from the file and validate the login credentials
            try {
                File file = new File("database/UserData.txt"); // Creating a new file object with the file path
                Scanner scan = new Scanner(file); // Creating a new scanner object to read the file
                // Looping through each line in the file to check for the username and password match
                while (scan.hasNextLine()) {
                    String data = scan.nextLine(); // Reading the next line from the file
                    String[] user = data.split(" \\$ "); // Splitting the line into username and password using delimiter isCorrect to true and breaking the loop
                    if (username.equals(user[2]) && password.equals(user[6])) {
                        isCorrect = true; // Setting isCorrect to true if the credentials match the user data in the file
                        name = user[1]; // Storing the name of the user from the file data to display in the dashboard
                        break; // Breaking the loop if the credentials match
                    }
                }
                scan.close(); // Closing the scanner object after reading the file data Catching file not found exception if the file is not found in the specified path
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "User data file not found.", "Error", JOptionPane.ERROR_MESSAGE); // Displaying an error message dialog to the user if the file is not found in the 
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
                new UserDashboard(name, this); // Opening the Dashboard window with the user's name as the parameter and making it visible to the user
                usernameField.setText(""); // Clearing the username field after successful login
                passwordField.setText(""); // Clearing the password field after successful login
                dispose(); // Disposing the login window after successful login
                // If the login credentials are incorrect, displaying an error message to the user
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE); // Displaying an error message dialog to the user
                passwordField.setText(""); // Clearing the password field after login attempt
            }
        // If the register button is clicked, hiding the login window and opening the Register window
        } else if (e.getSource() == registerButton) {
            usernameField.setText(""); // Clearing the username field after clicking the register button
            passwordField.setText(""); // Clearing the password field after clicking the register button
            new Register(null); // Opening the Register window with the current login window as the parameter
            setVisible(false); // Hiding the login window after clicking the register button
        // If the exit button is clicked, exiting the application
        } else if (e.getSource() == exitButton) {
            System.exit(0); // Exiting the application with status code 0
        } else if (e.getSource() == adminButton) {
            new AdminLogin(null); // Opening the AdminLogin window with the current login window as the parameter
            setVisible(false); // Hiding the login window after clicking the admin button
            usernameField.setText(""); // Clearing the username field after clicking the register button
            passwordField.setText(""); // Clearing the password field after clicking the register button
            dispose(); // Disposing the login window after clicking the admin button
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