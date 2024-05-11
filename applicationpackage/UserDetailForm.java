package applicationpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserDetailForm extends JFrame implements ActionListener {
    private UserManager userManager;
    private JLabel operationText, nameLabel, usernameLabel, ageLabel, nidOrBidLabel, genderLabel, passwordLabel;
    private JTextField nameField, usernameField, ageField, nidOrBidField, passwordField;
    private JButton saveButton, cancelButton;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private ButtonGroup genderButtonGroup;
    private String[] originalData;

    public UserDetailForm(UserManager userManager, String[] data) {
        this.userManager = userManager;
        this.originalData = data;

        operationText = new JLabel(data == null ? "Adding new user..." : "Updating user...");
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

        nameField = new JTextField();
        usernameField = new JTextField();
        ageField = new JTextField();
        nidOrBidField = new JTextField();
        passwordField = new JTextField();

        genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(maleRadioButton);
        genderButtonGroup.add(femaleRadioButton);

        Font message = new Font("Times New Roman", Font.BOLD, 26);
        operationText.setFont(message);

        Font labelFont = new Font("Georgia", Font.BOLD, 14);
        nameLabel.setFont(labelFont);
        usernameLabel.setFont(labelFont);
        ageLabel.setFont(labelFont);
        nidOrBidLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        genderLabel.setFont(labelFont);

        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        nameField.setFont(fieldFont);
        usernameField.setFont(fieldFont);
        ageField.setFont(fieldFont);
        nidOrBidField.setFont(fieldFont);
        passwordField.setFont(fieldFont);

        Font buttonFont = new Font("Georgia", Font.BOLD, 14);
        maleRadioButton.setFont(buttonFont);
        femaleRadioButton.setFont(buttonFont);
        saveButton.setFont(buttonFont);
        cancelButton.setFont(buttonFont);

        maleRadioButton.setBackground(Color.lightGray);
        femaleRadioButton.setBackground(Color.lightGray);

        operationText.setBounds(150, 20, 250, 30);

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

        cancelButton.setBounds(150, 400, 100, 30);
        saveButton.setBounds(270, 400, 100, 30);

        add(operationText);
        add(new JLabel(""));
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
        add(new JLabel(""));
        add(femaleRadioButton);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel(""));
        add(saveButton);
        add(cancelButton);

        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);

        if (data != null) {
            nameField.setText(data[0]);
            usernameField.setText(data[1]);
            ageField.setText(data[2]);
            nidOrBidField.setText(data[4]);
            passwordField.setText(data[5]);
            if ("Male".equals(data[3])) {
                maleRadioButton.setSelected(true);
            } else if ("Female".equals(data[3])) {
                femaleRadioButton.setSelected(true);
            }
        }

        setTitle(data == null ? "Add New User" : "Update User");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        getContentPane().setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
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
            try {
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

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age and NID/BID No. must be valid integers", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String[] newData = {
                nameField.getText(), 
                usernameField.getText(), 
                ageField.getText(), 
                gender, 
                nidOrBidField.getText(), 
                passwordField.getText()
            };

            if (originalData == null) {
                userManager.addNewUser(newData);
            } else {
                System.arraycopy(newData, 0, originalData, 0, newData.length); // Copy new data to original data
                userManager.updateUser(newData);
            }
            dispose();
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new UserDetailForm(null, null);
    }
}
