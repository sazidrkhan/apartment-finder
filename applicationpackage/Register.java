package applicationpackage; // Package declaration

import javax.swing.*;   // Importing the javax.swing package
import java.awt.*;   // Importing the java.awt package
import java.awt.event.*;   // Importing the java.awt.event package

public class Register extends JFrame implements ActionListener {    // This class extends JFrame and implements ActionListener to handle GUI and events
    private UserLogin login;    // UserLogin object to access the login window components and methods 
    private JLabel registerText, nameLabel, usernameLabel, ageLabel, nidOrBidLabel, genderLabel, passwordLabel, confirmPasswordLabel;   // Labels for the components in the frame window 
    private JTextField nameField, usernameField, ageField, nidOrBidField;  // Text fields for the user to enter data 
    private JPasswordField passwordField, confirmPasswordField;   // Password fields for the user to enter data 
    private JCheckBox showPassword;   // Checkbox to show the password 
    private JButton registerButton, cancelButton;   // Buttons for the user to perform actions 
    private JRadioButton maleRadioButton;       // Radio button
    private JRadioButton femaleRadioButton;    // Radio button 
    private ButtonGroup genderButtonGroup;   // Button group for radio buttons

    public Register(UserLogin login) {  // Constructor to initialize the components and set the layout of the frame window 
        this.login = login; // Assigning the login object to the class variable

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

        Font message = new Font("Times New Roman", Font.BOLD, 26);  // Creating a font object for the message
        registerText.setFont(message);  // Setting the font for the registerText label 

        Font labelFont = new Font("Georgia", Font.BOLD, 14);    // Creating a font object for the labels
        nameLabel.setFont(labelFont);   // Setting the font for the nameLabel 
        usernameLabel.setFont(labelFont);   // Setting the font for the usernameLabel
        ageLabel.setFont(labelFont);    // Setting the font for the ageLabel
        nidOrBidLabel.setFont(labelFont);   // Setting the font for the nidOrBidLabel
        passwordLabel.setFont(labelFont);   // Setting the font for the passwordLabel
        confirmPasswordLabel.setFont(labelFont);    // Setting the font for the confirmPasswordLabel
        genderLabel.setFont(labelFont);   // Setting the font for the genderLabel

        Font fieldFont = new Font("Georgia", Font.PLAIN, 14);   // Creating a font object for the text fields
        nameField.setFont(fieldFont);   // Setting the font for the nameField
        usernameField.setFont(fieldFont);   // Setting the font for the usernameField
        ageField.setFont(fieldFont);    // Setting the font for the ageField
        nidOrBidField.setFont(fieldFont);   // Setting the font for the nidOrBidField
        passwordField.setFont(fieldFont);   // Setting the font for the passwordField
        confirmPasswordField.setFont(fieldFont);    // Setting the font for the confirmPasswordField

        Font buttonFont = new Font("Georgia", Font.BOLD, 14);   // Creating a font object for the buttons
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

        showPassword.addActionListener(new ActionListener() {  // Adding an action listener to the showPassword checkbox
            @Override
            public void actionPerformed(ActionEvent e) {    // Overriding the actionPerformed method to handle checkbox click events
                if (showPassword.isSelected()) {    // If the checkbox is selected (checked), showing the password as plain text
                    passwordField.setEchoChar((char) 0);    // Setting the echo character to 0 for the password field
                    confirmPasswordField.setEchoChar((char) 0); // Setting the echo character to 0 for the confirm password field
                } else {    // If the checkbox is not selected (unchecked), showing the password as masked text
                    passwordField.setEchoChar('#'); // Set the echo character to '#' for the password field
                    confirmPasswordField.setEchoChar('#');  // Set the echo character to '#' for the confirm password field
                }
            }
        });

        setTitle("Registration");   // Setting the title for the frame window
        setSize(500, 500);  // Setting the size for the frame window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Closing the window when the close button is clicked
        setLocationRelativeTo(null);    // Setting the window to the center of the screen
        setLayout(null);    // Setting the layout to GridLayout with 9 rows and 2 columns 
        setVisible(true);   // Setting the frame to be visible to the user
        getContentPane().setBackground(Color.LIGHT_GRAY);   // Setting the background color for the frame
    }

    @Override
    public void actionPerformed(ActionEvent e) {    // Overriding the actionPerformed method to handle button click events 
        if (e.getSource() == registerButton) {  // If the register button is clicked
            String name = nameField.getText().trim();   // Getting the name from the nameField and removing leading and trailing whitespaces 
            String username = usernameField.getText().trim();   // Getting the username from the usernameField and removing leading and trailing whitespaces 
            String age = ageField.getText().trim();  // Getting the age from the ageField and removing leading and trailing whitespaces 
            String nidOrBid = nidOrBidField.getText().trim();   // Getting the NID/BID number from the nidOrBidField and removing leading and trailing whitespaces 
            String password = new String(passwordField.getPassword());  // Getting the password from the passwordField and converting it to a string 
            String confirmPassword = new String(confirmPasswordField.getPassword());    // Getting the confirm password from the confirmPasswordField and converting it to a string 
            String gender = maleRadioButton.isSelected() ? "Male" : "Female";   // Assigning "Male" if maleRadioButton is selected, otherwise assigning "Female"

            if (name.isEmpty() || username.isEmpty() || age.isEmpty() || nidOrBid.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected())){  // If any of the fields are empty
                JOptionPane.showMessageDialog(this, "Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE);   // Showing an error message if any of the fields are empty
            } else if (password.length() < 6) {  // If the password length is less than 6 characters
                JOptionPane.showMessageDialog(this, "Password must be at least 6 characters long", "Error", JOptionPane.ERROR_MESSAGE);   // Showing an error message if the password length is less than 6 characters
            } else if (!password.equals(confirmPassword)) {   // If the password and confirm password do not match
                JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);  // Showing an error message if the password and confirm password do not match
            } else {    // If the age or NID/BID number is not a valid integer
                int ageInt = 0; // Initializing the age as an integer
                int nidOrBidInt = 0;    // Initializing the NID/BID number as an integer
                try {   // Parsing the age and NID/BID number to integers and handling the NumberFormatException if the input is not a valid integer 
                    ageInt = Integer.parseInt(age); // Parsing the age to an integer
                    nidOrBidInt = Integer.parseInt(nidOrBid);   // Parsing the NID/BID number to an integer
                } catch (NumberFormatException ex) {    // Catching the NumberFormatException and showing an error message
                    JOptionPane.showMessageDialog(this, "Age and NID/BID No. must be valid integers", "Error", JOptionPane.ERROR_MESSAGE);  // Showing an error message if the age or NID/BID number is not a valid integer 
                    return; // Returning from the method
                }
                User user = new User(name, username, ageInt, nidOrBidInt, password, gender);    // Creating a new User object with the input values 
                boolean isRegistered = user.registerUser(); // Registering the user and getting the status of registration
                if (!isRegistered) {    // If the registration fails 
                    JOptionPane.showMessageDialog(this, "Registration failed", "Error", JOptionPane.ERROR_MESSAGE);  // Showing an error message if the registration fails 
                    return; // Returning from the method
                } else {    // If the registration is successful
                    JOptionPane.showMessageDialog(this, "Registration successful", "Success", JOptionPane.INFORMATION_MESSAGE);   // Showing a success message if the registration is successful
                    nameField.setText("");  // Clearing the nameField 
                    usernameField.setText("");  // Clearing the usernameField
                    ageField.setText("");   // Clearing the ageField
                    nidOrBidField.setText("");  // Clearing the nidOrBidField
                    passwordField.setText("");  // Clearing the passwordField
                    confirmPasswordField.setText("");   // Clearing the confirmPasswordField
                    maleRadioButton.setSelected(true);  // Selecting the maleRadioButton
                }
            }
        } else if (e.getSource() == cancelButton) {   // If the cancel button is clicked
            this.setVisible(false); // Hiding the current window
            login.setVisible(true); // Showing the login window
        }
    }

    public static void main(String[] args) {    // Main method to run the program
        new Register(null); // Creating an object of the Register class to display the login window
    }
}
