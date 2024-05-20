package applicationpackage; // Package containing the classes of the application

// Importing necessary classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// UserDetailForm class to display the user detail form
public class UserDetailForm extends JFrame implements ActionListener {
    // Declaring the instance variables
    private UserManager userManager;
    private JLabel operationText, uidLabel, nameLabel, usernameLabel, ageLabel, nidOrBidLabel, genderLabel, passwordLabel;
    private JTextField uidField, nameField, usernameField, ageField, nidOrBidField, passwordField;
    private JButton saveButton, cancelButton;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private ButtonGroup genderButtonGroup;
    private String[] originalData;

    // Constructor to initialize the instance variables
    public UserDetailForm(UserManager userManager, String[] data) {
        // Initializing the instance variables
        this.userManager = userManager;
        this.originalData = data;
    
        // Setting the labels for the user detail form
        operationText = new JLabel(data == null ? "Adding new user..." : "Updating user...");
        uidLabel = new JLabel("UID:");
        nameLabel = new JLabel("Name:");
        usernameLabel = new JLabel("Username:");
        ageLabel = new JLabel("Age:");
        nidOrBidLabel = new JLabel("NID/BID No.");
        passwordLabel = new JLabel("Password:");
        genderLabel = new JLabel("Gender:");
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        saveButton = new JButton(data == null ? "Add" : "Update");
        cancelButton = new JButton("Cancel");
    
        // Setting the text fields for the user detail form
        uidField = new JTextField();
        nameField = new JTextField();
        usernameField = new JTextField();
        ageField = new JTextField();
        nidOrBidField = new JTextField();
        passwordField = new JTextField();
    
        uidField.setEditable(false);    // Making the UID field non-editable
    
        // Setting the button group and adding the radio buttons to it
        genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(maleRadioButton);
        genderButtonGroup.add(femaleRadioButton);
    
        // Setting the fonts for the operation text
        Font message = new Font("Times New Roman", Font.BOLD, 26);
        operationText.setFont(message);
    
        // Setting the fonts for the labels
        Font labelFont = new Font("Georgia", Font.BOLD, 14);
        uidLabel.setFont(labelFont);
        nameLabel.setFont(labelFont);
        usernameLabel.setFont(labelFont);
        ageLabel.setFont(labelFont);
        nidOrBidLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        genderLabel.setFont(labelFont);
    
        // Setting the fonts for the fields
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        uidField.setFont(fieldFont);
        nameField.setFont(fieldFont);
        usernameField.setFont(fieldFont);
        ageField.setFont(fieldFont);
        nidOrBidField.setFont(fieldFont);
        passwordField.setFont(fieldFont);
    
        // Setting the fonts for the buttons
        Font buttonFont = new Font("Georgia", Font.BOLD, 14);
        maleRadioButton.setFont(buttonFont);
        femaleRadioButton.setFont(buttonFont);
        saveButton.setFont(buttonFont);
        cancelButton.setFont(buttonFont);
    
        // Setting background color for the radio buttons
        maleRadioButton.setBackground(Color.lightGray);
        femaleRadioButton.setBackground(Color.lightGray);
    
        // Setting the bounds for the components
        operationText.setBounds(150, 20, 250, 30);
        uidLabel.setBounds(50, 80, 100, 30);
        uidField.setBounds(200, 80, 200, 30);
        nameLabel.setBounds(50, 120, 100, 30);
        nameField.setBounds(200, 120, 200, 30);
        usernameLabel.setBounds(50, 160, 100, 30);
        usernameField.setBounds(200, 160, 200, 30);
        ageLabel.setBounds(50, 200, 100, 30);
        ageField.setBounds(200, 200, 200, 30);
        genderLabel.setBounds(50, 240, 100, 30);
        maleRadioButton.setBounds(200, 240, 100, 30);
        femaleRadioButton.setBounds(300, 240, 100, 30);
        nidOrBidLabel.setBounds(50, 280, 100, 30);
        nidOrBidField.setBounds(200, 280, 200, 30);
        passwordLabel.setBounds(50, 320, 150, 30);
        passwordField.setBounds(200, 320, 200, 30);
        cancelButton.setBounds(150, 400, 100, 30);
        saveButton.setBounds(270, 400, 100, 30);
    
        // Adding the components to the frame
        add(operationText);
        add(uidLabel);
        add(uidField);
        add(nameLabel);
        add(nameField);
        add(usernameLabel);
        add(usernameField);
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
        add(saveButton);
        add(cancelButton);
    
        // Adding the action listeners to the buttons
        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);
    
        // Setting the data for the user detail form if the data is not null
        if (data != null) {
            uidField.setText(data[0]);
            nameField.setText(data[1]);
            usernameField.setText(data[2]);
            ageField.setText(data[3]);
            if ("Male".equals(data[4])) {
                maleRadioButton.setSelected(true);
            } else if ("Female".equals(data[4])) {
                femaleRadioButton.setSelected(true);
            }
            nidOrBidField.setText(data[5]);
            passwordField.setText(data[6]);
        } else {    // Setting the next UID if the data is null
            uidField.setText(Integer.toString(userManager.getNextUserID()));
        }
    
        // Setting the properties for the frame
        setTitle(data == null ? "Add New User" : "Update User");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);
    
        // Adding the window listener to the frame to request focus in window
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                SwingUtilities.invokeLater(() -> {
                    getContentPane().requestFocusInWindow();
                });
            }
        });        
    
        // Making the frame visible
        setVisible(true);
    }

    // Overriding the actionPerformed method to handle the button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {  // Checking if the save button is clicked
            String name = nameField.getText().trim();
            String username = usernameField.getText().trim();
            String age = ageField.getText().trim();
            String nidOrBid = nidOrBidField.getText().trim();
            String password = new String(passwordField.getText());
            String gender = maleRadioButton.isSelected() ? "Male" : femaleRadioButton.isSelected() ? "Female" : "";
        
            if (name.isEmpty() || username.isEmpty() || age.isEmpty() || nidOrBid.isEmpty() || password.isEmpty() || (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected())){
                JOptionPane.showMessageDialog(this, "Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            if (password.length() < 6) {
                JOptionPane.showMessageDialog(this, "Password must be at least 6 characters long", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            int ageInt;
            long nidOrBidLong;
            try {   // Checking if the age and NID/BID No. are valid integers
                ageInt = Integer.parseInt(age);
                if (ageInt < 18 || ageInt > 100) {  
                    JOptionPane.showMessageDialog(this, "Please enter a valid age between 18 to 100", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            
                if (nidOrBid.startsWith("0")) {
                    JOptionPane.showMessageDialog(this, "NID/BID No. cannot start with 0", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            
                nidOrBidLong = Long.parseLong(nidOrBid);
                if (nidOrBidLong < 0) {
                    JOptionPane.showMessageDialog(this, "NID/BID No. must be a positive number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            
                String nidOrBidString = Long.toString(nidOrBidLong);
                int length = nidOrBidString.length();
                if (length != 17) {
                    JOptionPane.showMessageDialog(this, "NID/BID No. must be a 17-digit number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            
            } catch (NumberFormatException ex) {    // Catching the NumberFormatException if the age or NID/BID No. is not a valid integer
                JOptionPane.showMessageDialog(this, "Age and NID/BID No. must be valid integers", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // Adding the new user or updating the existing user
            String[] newData = {
                uidField.getText(),
                nameField.getText(), 
                usernameField.getText(), 
                ageField.getText(), 
                gender, 
                nidOrBidField.getText(), 
                passwordField.getText()
            };

            if (originalData == null) {
                userManager.addNewUser(newData);    // Adding the new user if the original data is null
            } else {
                System.arraycopy(newData, 0, originalData, 0, newData.length); // Copy new data to original data
                userManager.updateUser(newData);    // Updating the user if the original data is not null
            }
            dispose();
        } else if (e.getSource() == cancelButton) {   // Checking if the cancel button is clicked
            dispose();
        }
    }
}
