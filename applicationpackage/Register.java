package applicationpackage; // Package declaration

// Importing necessary libraries for the class
import javax.swing.*;   // Importing the javax.swing package
import java.awt.*;   // Importing the java.awt package
import java.awt.event.*;   // Importing the java.awt.event package

// This class extends JFrame and implements ActionListener to handle GUI and events
public class Register extends JFrame implements ActionListener {
    // Declare components
    private UserLogin login;    // UserLogin object to access the login window components and methods 
    private JLabel registerText, nameLabel, usernameLabel, ageLabel, nidOrBidLabel, genderLabel, passwordLabel, confirmPasswordLabel;   // Labels for the components in the frame window 
    private JTextField nameField, usernameField, ageField, nidOrBidField;  // Text fields for the user to enter data 
    private JPasswordField passwordField, confirmPasswordField;   // Password fields for the user to enter data 
    private JCheckBox showPassword;   // Checkbox to show the password 
    private JButton registerButton, cancelButton;   // Buttons for the user to perform actions 
    private JRadioButton maleRadioButton;       // Radio button
    private JRadioButton femaleRadioButton;    // Radio button 
    private ButtonGroup genderButtonGroup;   // Button group for radio buttons

    // Constructor to initialize the components and set the layout of the frame window 
    public Register(UserLogin login) {
        this.login = login;

        registerText = new JLabel("Please Register...");    // Create a label with the text "Please Register..." 
        nameLabel = new JLabel("Name:");    // Name label
        usernameLabel = new JLabel("Username:");    // Username label
        ageLabel = new JLabel("Age:");  // Age label
        nidOrBidLabel = new JLabel("NID/BID No.");  // NID/BID label
        genderLabel = new JLabel("Gender:");    // Gender label
        passwordLabel = new JLabel("Password:");    // Password label
        confirmPasswordLabel = new JLabel("Confirm Password:");   // Confirm password label

        nameField = new JTextField();   // Text field for the user's name
        usernameField = new JTextField();   // Text field for the user's username
        ageField = new JTextField();    // Text field for the user's age
        nidOrBidField = new JTextField();   // Text field for the user's NID/BID number

        passwordField = new JPasswordField();   // Password field for the password
        confirmPasswordField = new JPasswordField();    // Password field for the confirm password

        registerButton = new JButton("Register");   // Register button
        cancelButton = new JButton("Cancel");   // Cancel button

        maleRadioButton = new JRadioButton("Male"); // Male radio button
        femaleRadioButton = new JRadioButton("Female"); // Female radio button

        genderButtonGroup = new ButtonGroup();  // Button group for the radio buttons
        genderButtonGroup.add(maleRadioButton); // Adding male radio button to the button group
        genderButtonGroup.add(femaleRadioButton);   // Adding female radio button to the button group

        // Creating a font object for the message label 
        Font message = new Font("Times New Roman", Font.BOLD, 26);  
        registerText.setFont(message);  // Setting the font for the registerText label 

        // Creating a font object for the labels 
        Font labelFont = new Font("Georgia", Font.BOLD, 14);    
        nameLabel.setFont(labelFont);   // Setting the font for the nameLabel 
        usernameLabel.setFont(labelFont);   // Setting the font for the usernameLabel
        ageLabel.setFont(labelFont);    // Setting the font for the ageLabel
        nidOrBidLabel.setFont(labelFont);   // Setting the font for the nidOrBidLabel
        passwordLabel.setFont(labelFont);   // Setting the font for the passwordLabel
        confirmPasswordLabel.setFont(labelFont);    // Setting the font for the confirmPasswordLabel
        genderLabel.setFont(labelFont);   // Setting the font for the genderLabel

        // Creating a font object for the text fields 
        Font fieldFont = new Font("Georgia", Font.PLAIN, 14);   
        nameField.setFont(fieldFont);   // Setting the font for the nameField
        usernameField.setFont(fieldFont);   // Setting the font for the usernameField
        ageField.setFont(fieldFont);    // Setting the font for the ageField
        nidOrBidField.setFont(fieldFont);   // Setting the font for the nidOrBidField
        passwordField.setFont(fieldFont);   // Setting the font for the passwordField
        confirmPasswordField.setFont(fieldFont);    // Setting the font for the confirmPasswordField

        // Creating a font object for the buttons 
        Font buttonFont = new Font("Georgia", Font.BOLD, 14);   
        maleRadioButton.setFont(buttonFont);    // Setting the font for the maleRadioButton
        femaleRadioButton.setFont(buttonFont);  // Setting the font for the femaleRadioButton
        registerButton.setFont(buttonFont);   // Setting the font for the registerButton
        cancelButton.setFont(buttonFont);   // Setting the font for the cancelButton

        maleRadioButton.setBackground(Color.lightGray); // Setting the background color for the maleRadioButton
        femaleRadioButton.setBackground(Color.lightGray);  // Setting the background color for the femaleRadioButton


        showPassword = new JCheckBox("Show Password");  // Creating a checkbox with the text "Show Password" 
        showPassword.setBackground(Color.lightGray);    // Setting the background color for the showPassword checkbox 

        registerText.setBounds(150, 20, 250, 30);   // Setting the bounds for the registerText label 

        nameLabel.setBounds(50, 80, 100, 30);   // Setting the bounds for the nameLabel
        nameField.setBounds(200, 80, 200, 30);  // Setting the bounds for the nameField

        usernameLabel.setBounds(50, 120, 100, 30);  // Setting the bounds for the usernameLabel
        usernameField.setBounds(200, 120, 200, 30); // Setting the bounds for the usernameField

        ageLabel.setBounds(50, 160, 100, 30);   // Setting the bounds for the ageLabel
        ageField.setBounds(200, 160, 200, 30);  // Setting the bounds for the ageField

        genderLabel.setBounds(50, 200, 100, 30);    // Setting the bounds for the genderLabel
        maleRadioButton.setBounds(200, 200, 100, 30);   // Setting the bounds for the maleRadioButton
        femaleRadioButton.setBounds(300, 200, 100, 30); // Setting the bounds for the femaleRadioButton

        nidOrBidLabel.setBounds(50, 240, 100, 30);  // Setting the bounds for the nidOrBidLabel
        nidOrBidField.setBounds(200, 240, 200, 30); // Setting the bounds for the nidOrBidField

        passwordLabel.setBounds(50, 280, 150, 30);  // Setting the bounds for the passwordLabel
        passwordField.setBounds(200, 280, 200, 30); // Setting the bounds for the passwordField

        confirmPasswordLabel.setBounds(50, 320, 150, 30);   // Setting the bounds for the confirmPasswordLabel
        confirmPasswordField.setBounds(200, 320, 200, 30);  // Setting the bounds for the confirmPasswordField

        showPassword.setBounds(200, 350, 150, 30);  // Setting the bounds for the showPassword checkbox

        registerButton.setBounds(250, 400, 100, 30);    // Setting the bounds for the registerButton
        cancelButton.setBounds(150, 400, 100, 30);  // Setting the bounds for the cancelButton

        add(registerText);  // Adding the registerText label to the frame window
        add(new JLabel(""));    // Adding a blank label to the frame window
        add(nameLabel); // Adding the nameLabel to the frame window
        add(nameField); // Adding the nameField to the frame window
        add(usernameLabel); // Adding the usernameLabel to the frame window
        add(usernameField); // Adding the usernameField to the frame window
        add(ageLabel);  // Adding the ageLabel to the frame window
        add(ageField);  // Adding the ageField to the frame window
        add(nidOrBidLabel); // Adding the nidOrBidLabel to the frame window
        add(nidOrBidField); // Adding the nidOrBidField to the frame window
        add(genderLabel);   // Adding the genderLabel to the frame window
        add(maleRadioButton);   // Adding the maleRadioButton to the frame window
        add(new JLabel(""));    // Adding a blank label to the frame window
        add(femaleRadioButton); // Adding the femaleRadioButton to the frame window
        add(passwordLabel); // Adding the passwordLabel to the frame window
        add(passwordField); // Adding the passwordField to the frame window
        add(confirmPasswordLabel);  // Adding the confirmPasswordLabel to the frame window
        add(confirmPasswordField);  // Adding the confirmPasswordField to the frame window
        add(showPassword);  // Adding the showPassword checkbox to the frame window
        add(new JLabel(""));    // Adding a blank label to the frame window
        add(registerButton);    // Adding the registerButton to the frame window
        add(cancelButton);  // Adding the cancelButton to the frame window

        registerButton.addActionListener(this);  // Adding action listener to the registerButton
        cancelButton.addActionListener(this);   // Adding action listener to the cancelButton
        showPassword.addActionListener(this);   // Adding action listener to the showPassword checkbox

        // Showing or hiding the password based on the checkbox state
        showPassword.addActionListener(new ActionListener() {
            // Handling checkbox state change event in the ItemStateChanged method
            @Override
            public void actionPerformed(ActionEvent e) {
                // If the checkbox is selected (checked), show the password as plain text
                if (showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                    confirmPasswordField.setEchoChar((char) 0);
                // If the checkbox is not selected (unchecked), showing the password as masked text
                } else {
                    passwordField.setEchoChar('#'); // Set the echo character to '#' for the password field
                    confirmPasswordField.setEchoChar('#');  // Set the echo character to '#' for the confirm password field
                }
            }
        });

        
        // Setting frame properties
        setTitle("Registration");   // Setting the title for the frame window
        setSize(500, 500);  // Setting the size for the frame window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Closing the window when the close button is clicked
        setLocationRelativeTo(null);    // Setting the window to the center of the screen
        setLayout(null);    // Setting the layout to GridLayout with 9 rows and 2 columns 
        setVisible(true);   // Setting the frame to be visible to the user
        getContentPane().setBackground(Color.LIGHT_GRAY);   // Setting the background color for the frame
    }

    // Handling button click events in the register window in the actionPerformed method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String name = nameField.getText().trim();
            String username = usernameField.getText().trim();
            String age = ageField.getText().trim();
            String nidOrBid = nidOrBidField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String gender = maleRadioButton.isSelected() ? "Male" : "Female";

            // Perform validation checks on the input fields
            if (name.isEmpty() || username.isEmpty() || age.isEmpty() || nidOrBid.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected())){
                JOptionPane.showMessageDialog(this, "Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int ageInt = 0;
                int nidOrBidInt = 0;
                try {
                    ageInt = Integer.parseInt(age);
                    nidOrBidInt = Integer.parseInt(nidOrBid);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Age and NID/BID No. must be valid integers", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                User user = new User(name, username, ageInt, nidOrBidInt, password, gender);
                boolean isRegistered = user.registerUser(); // Register the user and get the status of registration
                if (!isRegistered) {
                    JOptionPane.showMessageDialog(this, "Registration failed", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    // Show success message
                    JOptionPane.showMessageDialog(this, "Registration successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Clear the input fields
                    nameField.setText("");
                    usernameField.setText("");
                    ageField.setText("");
                    nidOrBidField.setText("");
                    passwordField.setText("");
                    confirmPasswordField.setText("");
                    maleRadioButton.setSelected(true);
                }
            }
        } else if (e.getSource() == cancelButton) {
            this.setVisible(false); // Hide the current window
            login.setVisible(true); // Show the login window
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        new Register(null); // Creating an object of the Register class to display the login window
    }

}
