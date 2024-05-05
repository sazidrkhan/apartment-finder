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

    public Register(UserLogin login) {
        this.login = login;

        registerText = new JLabel("Please Register...");
        nameLabel = new JLabel("Name:");
        usernameLabel = new JLabel("Username:");
        ageLabel = new JLabel("Age:");
        nidOrBidLabel = new JLabel("NID/BID No.");
        genderLabel = new JLabel("Gender:");
        passwordLabel = new JLabel("Password:");
        confirmPasswordLabel = new JLabel("Confirm Password:");

        nameField = new JTextField();
        usernameField = new JTextField();
        ageField = new JTextField();
        nidOrBidField = new JTextField();

        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();

        registerButton = new JButton("Register");
        cancelButton = new JButton("Cancel");

        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");

        genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(maleRadioButton);
        genderButtonGroup.add(femaleRadioButton);

        // Set font for the components
        Font message = new Font("Times New Roman", Font.BOLD, 26);
        registerText.setFont(message);

        // Set labelFont for the components
        Font labelFont = new Font("Georgia", Font.BOLD, 14);
        nameLabel.setFont(labelFont);
        usernameLabel.setFont(labelFont);
        ageLabel.setFont(labelFont);
        nidOrBidLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        confirmPasswordLabel.setFont(labelFont);
        genderLabel.setFont(labelFont);

        // Set fieldFont for the text fields
        Font fieldFont = new Font("Georgia", Font.PLAIN, 14);
        nameField.setFont(fieldFont);
        usernameField.setFont(fieldFont);
        ageField.setFont(fieldFont);
        nidOrBidField.setFont(fieldFont);
        passwordField.setFont(fieldFont);
        confirmPasswordField.setFont(fieldFont);

        // Set buttonFont for the buttons
        Font buttonFont = new Font("Georgia", Font.BOLD, 14);
        maleRadioButton.setFont(buttonFont);
        femaleRadioButton.setFont(buttonFont);
        registerButton.setFont(buttonFont);
        cancelButton.setFont(buttonFont);

        // Set the background color for the radio buttons
        maleRadioButton.setBackground(Color.lightGray);
        femaleRadioButton.setBackground(Color.lightGray);


        showPassword = new JCheckBox("Show Password");
        showPassword.setBackground(Color.lightGray);

        // Set bounds for the components
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

        registerButton.setBounds(250, 400, 100, 30);
        cancelButton.setBounds(150, 400, 100, 30);

        // Add components to the frame
        add(registerText);
        add(new JLabel(""));
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
        add(new JLabel(""));
        add(femaleRadioButton);
        add(passwordLabel);
        add(passwordField);
        add(confirmPasswordLabel);
        add(confirmPasswordField);
        add(showPassword);
        add(new JLabel(""));
        add(registerButton);
        add(cancelButton);

        // Add action listeners
        registerButton.addActionListener(this);
        cancelButton.addActionListener(this);
        showPassword.addActionListener(this);

        // Showing or hiding the password based on the checkbox state
        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                    confirmPasswordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('#');
                    confirmPasswordField.setEchoChar('#');
                }
            }
        });

        // Set layout
        setLayout(null);    // Set layout to GridLayout with 9 rows and 2 columns 

        // Set frame properties
        setTitle("Registration");   // Set title for the frame window
        setSize(500, 500);  // Set size for the frame window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Close the window when the close button is clicked
        setLocationRelativeTo(null);    // Set the window to the center of the screen
        // setResizable(false);    // Disable resizing of the window 
        setVisible(true);   // Set the frame to be visible to the user
        getContentPane().setBackground(Color.LIGHT_GRAY);   // Set background color for the frame
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
