package applicationpackage; // Package containing the main classes of the application

// Importing the necessary classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

// Admin Login class to display the login window for the admin user
public class AdminLogin extends JFrame implements ActionListener {
    // Declaring the necessary components
    private JLabel title, usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPassword;
    private JButton loginButton, exitButton, backButton;
    private String username, password;
    private boolean isCorrect = false;

    // Constructor to initialize the components
    public AdminLogin(UserLogin userLogin) {
        // Setting labels for the components
        title = new JLabel("Please login to your account...");
        usernameLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password: ");
    
        // Setting buttons for the components
        exitButton = new JButton("Exit");
        backButton = new JButton("Go Back");
        loginButton = new JButton("Login");
    
        // Setting fields for the components
        usernameField = new JTextField();
        passwordField = new JPasswordField();
    
        // Setting fonts for the labels
        Font labelFont = new Font("Georgia", Font.BOLD, 14);
        title.setFont(labelFont);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
    
        // Setting fonts for the fields
        Font fieldFont = new Font("Georgia", Font.PLAIN, 14);
        usernameField.setFont(fieldFont);
        passwordField.setFont(fieldFont);
    
        // Setting fonts for the buttons
        Font buttonFont = new Font("Georgia", Font.BOLD, 14);
        loginButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);
        backButton.setFont(buttonFont);
    
        // Setting show password checkbox components
        showPassword = new JCheckBox("Show Password");
        showPassword.setBackground(Color.lightGray);
    
        // Adding item listener to the show password checkbox
        showPassword.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('$');
                }
            }
        });
    
        // Setting bounds for the components on the window
        title.setBounds(90, 50, 310, 30);
        usernameLabel.setBounds(90, 100, 100, 30);
        passwordLabel.setBounds(90, 150, 100, 30);
        usernameField.setBounds(190, 100, 200, 30);
        passwordField.setBounds(190, 150, 200, 30);
        showPassword.setBounds(190, 200, 150, 30);
        loginButton.setBounds(190, 280, 100, 30);
        exitButton.setBounds(130, 350, 100, 30);
        backButton.setBounds(250, 350, 100, 30);
    
        // Adding components to the window
        add(title);
        add(usernameLabel);
        add(passwordLabel);
        add(usernameField);
        add(passwordField);
        add(showPassword);
        add(loginButton);
        add(exitButton);
        add(backButton);
    
        // Adding action listeners to the buttons
        loginButton.addActionListener(this);
        exitButton.addActionListener(this);
        backButton.addActionListener(this);
    
        // Setting the window properties and making it visible
        setTitle("Admin Login");
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        getContentPane().setBackground(Color.LIGHT_GRAY);
    }

    // Overriding the actionPerformed method to handle the action events (clicks) for the buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        // Checking the source of the action event
        if (e.getSource() == loginButton) {
            // Getting the username and password entered by the user
            username = usernameField.getText();
            password = new String(passwordField.getPassword());
            String name = null;
        
            // Try catch block to read the data from the file and validate the user 
            try {
                // Reading the data from the file
                File file = new File("database/AdminData.txt");
                Scanner scan = new Scanner(file);
            
                // Looping through the data to validate the user
                while (scan.hasNextLine()) { 
                    String data = scan.nextLine();
                    String[] admin = data.split(" \\$ ");
                
                    // Checking if the username and password entered by the user are correct
                    if (username.equals(admin[2]) && password.equals(admin[6])) {
                        isCorrect = true;
                        name = admin[1];
                        break;
                    }
                }
                scan.close();   // Closing the scanner
            } catch (FileNotFoundException ex) {    // Catching the file not found exception
                JOptionPane.showMessageDialog(this, "Admin data file not found.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();   // Printing the stack trace of the exception
            }
        
            // Checking if the username and password are empty or incorrect
            if (username.isEmpty() && password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter username and password!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter username!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter password!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (isCorrect && name != null) { 
                new AdminDashboard(name);   // Opening the admin dashboard window if the user is valid
                dispose();  // Closing the current window
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                passwordField.setText("");  // Clearing the password field if the username or password is invalid
            }
        } else if (e.getSource() == exitButton) {
            System.exit(0); // Exiting the application if the exit button is clicked
        } else if (e.getSource() == backButton) {
            new UserLogin(null);   // Going back to the user login window if the back button is clicked
            usernameField.setText("");
            passwordField.setText("");
            dispose();  // Closing the admin login window and clearing the fields
        } else {
            System.out.println("Invalid action event!");
        }
    }

    // Main method to test the AdminLogin class
    public static void main(String[] args) {
        new AdminLogin(null);
    }
}
