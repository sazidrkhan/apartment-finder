package applicationpackage;   // Package containing the main classes of the application

// Importing necessary libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Class for the registration window
public class Register extends JFrame implements ActionListener {
    // Declaring the necessary components
    private JLabel registerText, nameLabel, usernameLabel, ageLabel, nidOrBidLabel, genderLabel, passwordLabel, confirmPasswordLabel;
    private JTextField nameField, usernameField, ageField, nidOrBidField;
    private JPasswordField passwordField, confirmPasswordField;
    private JCheckBox showPassword;
    private JButton registerButton, backButton;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private ButtonGroup genderButtonGroup;

    // Constructor to initialize the components
    public Register(UserLogin login) {
        // Initializing the labels
        registerText = new JLabel("Please Register...");
        nameLabel = new JLabel("Name:");
        usernameLabel = new JLabel("Username:");
        ageLabel = new JLabel("Age:");
        nidOrBidLabel = new JLabel("NID/BID No.");
        genderLabel = new JLabel("Gender:");
        passwordLabel = new JLabel("Password:");
        confirmPasswordLabel = new JLabel("Confirm Password:");
    
        // Initializing the fields
        nameField = new JTextField();
        usernameField = new JTextField();
        ageField = new JTextField();
        nidOrBidField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();
    
        // Initializing the buttons
        registerButton = new JButton("Register");
        backButton = new JButton("Go Back");
    
        // Initializing the radio buttons and button group
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        genderButtonGroup = new ButtonGroup();
    
        // Adding the radio buttons to the button group
        genderButtonGroup.add(maleRadioButton);
        genderButtonGroup.add(femaleRadioButton);
    
        // Setting the font for the message
        Font message = new Font("Times New Roman", Font.BOLD, 26);
        registerText.setFont(message);
    
        // Setting the font for the labels
        Font labelFont = new Font("Georgia", Font.BOLD, 14);
        nameLabel.setFont(labelFont);
        usernameLabel.setFont(labelFont);
        ageLabel.setFont(labelFont);
        nidOrBidLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        confirmPasswordLabel.setFont(labelFont);
        genderLabel.setFont(labelFont);
    
        // Setting the font for the fields
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        nameField.setFont(fieldFont);
        usernameField.setFont(fieldFont);
        ageField.setFont(fieldFont);
        nidOrBidField.setFont(fieldFont);
        passwordField.setFont(fieldFont);
        confirmPasswordField.setFont(fieldFont);
    
        // Setting the font for the buttons
        Font buttonFont = new Font("Georgia", Font.BOLD, 14);
        maleRadioButton.setFont(buttonFont);
        femaleRadioButton.setFont(buttonFont);
        registerButton.setFont(buttonFont);
        backButton.setFont(buttonFont);
    
        // Setting the background color for the radio buttons
        maleRadioButton.setBackground(Color.lightGray);
        femaleRadioButton.setBackground(Color.lightGray);
    
        // Setting the background color for the show password checkbox
        showPassword = new JCheckBox("Show Password");
        showPassword.setBackground(Color.lightGray);
    
        // Setting the bounds for the components
        registerText.setBounds(150, 20, 250, 30);
        nameLabel.setBounds(50, 80, 100, 30);
        nameField.setBounds(200, 80, 200, 30);
        usernameLabel.setBounds(50, 120, 100, 30);
        usernameField.setBounds(200, 120, 200, 30);
        ageLabel.setBounds(50, 160, 100, 30);
        ageField.setBounds(200, 160, 200, 30);
        genderLabel.setBounds(50, 200, 100, 30);
        maleRadioButton.setBounds(200, 200, 100, 30);
        femaleRadioButton.setBounds(300, 200, 100, 30);
        nidOrBidLabel.setBounds(50, 240, 100, 30);
        nidOrBidField.setBounds(200, 240, 200, 30);
        passwordLabel.setBounds(50, 280, 150, 30);
        passwordField.setBounds(200, 280, 200, 30);
        confirmPasswordLabel.setBounds(50, 320, 150, 30);
        confirmPasswordField.setBounds(200, 320, 200, 30);
        showPassword.setBounds(200, 350, 150, 30);
        backButton.setBounds(145, 400, 100, 30);
        registerButton.setBounds(255, 400, 100, 30);
    
        // Adding the components to the frame
        add(registerText);
        add(nameLabel);
        add(nameField);
        add(usernameLabel);
        add(usernameField);
        add(ageLabel);
        add(ageField);
        add(nidOrBidLabel);
        add(nidOrBidField);
        add(genderLabel);
        add(maleRadioButton);
        add(femaleRadioButton);
        add(passwordLabel);
        add(passwordField);
        add(confirmPasswordLabel);
        add(confirmPasswordField);
        add(showPassword);
        add(registerButton);
        add(backButton);
    
        // Adding action listeners to the buttons
        registerButton.addActionListener(this);
        backButton.addActionListener(this);
        showPassword.addActionListener(this);
    
        // Adding an action listener to the show password checkbox
        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                    confirmPasswordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('$');
                    confirmPasswordField.setEchoChar('$');
                }
            }
        });
    
        // Setting the frame properties
        setTitle("Registration");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        getContentPane().setBackground(Color.LIGHT_GRAY);
    }

    // Overriding the actionPerformed method to handle the button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {  // If the register button is clicked
            String name = nameField.getText().trim();
            String username = usernameField.getText().trim();
            String age = ageField.getText().trim();
            String nidOrBid = nidOrBidField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String gender = maleRadioButton.isSelected() ? "Male" : "Female";
        
            if (name.isEmpty() || username.isEmpty() || age.isEmpty() || nidOrBid.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected())){
                JOptionPane.showMessageDialog(this, "Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            if (password.length() < 6) {
                JOptionPane.showMessageDialog(this, "Password must be at least 6 characters long", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            int ageInt;
            long nidOrBidLong;
            try {   // Validating if the age and NID/BID No. are valid integers
                ageInt = Integer.parseInt(age);
                if (ageInt < 18 || ageInt >= 100) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid age between 18 to 100", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            
                if (nidOrBid.startsWith("0")) {
                    JOptionPane.showMessageDialog(this, "NID/BID No. cannot start with 0", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            
                nidOrBidLong = Long.parseLong(nidOrBid);
                if (nidOrBidLong <= 0) {
                    JOptionPane.showMessageDialog(this, "NID/BID No. must be a positive number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            
                String nidOrBidString = Long.toString(nidOrBidLong);
                int length = nidOrBidString.length();
                if (length != 17) {
                    JOptionPane.showMessageDialog(this, "NID/BID No. must be a 17-digit number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            
            } catch (NumberFormatException ex) {    // Catching the NumberFormatException if the age and NID/BID No. are not valid integers
                JOptionPane.showMessageDialog(this, "Age and NID/BID No. must be valid integers", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            User user = new User(name, username, ageInt, gender, nidOrBidLong, password);
            boolean isRegistered = user.registerUser();
        
            if (!isRegistered) {    // If the registration fails
                JOptionPane.showMessageDialog(this, "Registration failed", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {    // If the registration is successful
                int response = JOptionPane.showOptionDialog(this, "Registration successful", "Success", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK"}, "OK");
            
                if (response == JOptionPane.OK_OPTION) {
                    new UserLogin(null);    // Opening the login window after successful registration
                    setVisible(false);
                }
            
                // Clearing the fields after successful registration
                nameField.setText("");
                usernameField.setText("");
                ageField.setText("");
                nidOrBidField.setText("");
                passwordField.setText("");
                confirmPasswordField.setText("");
                maleRadioButton.setSelected(false);
                femaleRadioButton.setSelected(false);
            }
        } else if (e.getSource() == backButton) {   // If the back button is clicked
            setVisible(false);
            new UserLogin(null);    // Opening the login window after clicking the back button
        }
    }

    // Main method to test the Register class
    public static void main(String[] args) {
        new Register(null);
    }
}
