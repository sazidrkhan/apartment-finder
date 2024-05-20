package applicationpackage; // Package containing the main classes of the application

// Importing the necessary classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Payment Window class to display the payment details
public class PaymentWindow extends JDialog implements ActionListener {
    // Declaring the necessary variables
    private UserDashboard userDashboard;
    private ApartmentDetailDialog apartmentDetailDialog;
    private JLabel message, logoLabel, paymentMethodLabel, paymentLabel, accountNumberLabel, pinLabel;
    private JTextField accountNumberField;
    private JPasswordField pinField;
    private JButton payButton, cancelButton;
    private JCheckBox showPin;
    private String amountDue, paymentMethod, apartmentID;
    private boolean whichLogo;

    // Constructor to initialize the components
    public PaymentWindow(JDialog parent, String amountDue, String paymentMethod, String apartmentID, UserDashboard userDashboard, ApartmentDetailDialog apartmentDetailDialog) {
        // Initializing the components
        super(parent, true);
        this.amountDue = amountDue;
        this.paymentMethod = paymentMethod;
        this.apartmentID = apartmentID;
        this.userDashboard = userDashboard;
        this.apartmentDetailDialog = apartmentDetailDialog;
    
        // Setting the properties of the dialog
        setTitle("Payment Window");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    
        // Adding a window listener to the dialog to confirm the cancellation of the payment
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(PaymentWindow.this, "Are you sure you want to cancel the payment?", "Cancel Payment", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    dispose();
                    new PaymentGateway(null, amountDue, apartmentID, userDashboard, apartmentDetailDialog);
                }
            }
        });
    
        // Setting the layout of the dialog
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
    
        // Checking the payment method to display the respective logo
        if (paymentMethod.equals("bKash")) {
            whichLogo = true;
        } else {
            whichLogo = false;
        }
    
        // Displaying the respective logo
        ImageIcon icon = new ImageIcon(new ImageIcon(new File(whichLogo ? "images/bkash.png" : "images/nagad.png").getAbsolutePath()).getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH));
    
        // Initializing the components
        logoLabel = new JLabel(icon);
        message = new JLabel("Please enter your payment details below...");
        paymentMethodLabel = new JLabel("Payment Method: " + paymentMethod);
        paymentLabel = new JLabel("Amount Due: " + amountDue);
        accountNumberLabel = new JLabel("Account Number:");
        pinLabel = new JLabel("PIN:");
        accountNumberField = new JTextField(16);
        pinField = new JPasswordField(4);
        showPin = new JCheckBox("Show PIN");
        showPin.setSelected(false);
        payButton = new JButton("Pay");
        cancelButton = new JButton("Cancel");
    
        // Setting the font for the labels
        Font labelFont = new Font("Georgia", Font.BOLD, 14);
        accountNumberLabel.setFont(labelFont);
        pinLabel.setFont(labelFont);
        paymentMethodLabel.setFont(labelFont);
        paymentLabel.setFont(labelFont);
        message.setFont(labelFont);
    
        // Setting the font for the fields
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        accountNumberField.setFont(fieldFont);
        pinField.setFont(fieldFont);
    
        // Handling the visibility of the PIN
        showPin.addActionListener(new ActionListener() {  
            @Override
            public void actionPerformed(ActionEvent e) {    
                if (showPin.isSelected()) {    
                    pinField.setEchoChar((char) 0);    
                } else {    
                    pinField.setEchoChar('$'); 
                }
            }
        });
    
        // Adding action listeners to the buttons
        payButton.addActionListener(this);
        cancelButton.addActionListener(this);
    
        // Adding the components to the dialog
        add(new JLabel(""));    // Empty label for spacing purposes
        add(message, gbc);
        add(logoLabel, gbc);
        add(paymentMethodLabel, gbc);
        add(paymentLabel, gbc);
        add(accountNumberLabel, gbc);
        add(accountNumberField, gbc);
        add(pinLabel, gbc);
        add(pinField, gbc);
        add(showPin, gbc);
        add(payButton, gbc);
        add(cancelButton, gbc);
    
        // Setting the properties of the dialog
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Marking the apartment as rented in the database
    public void markApartmentRented(String apartmentId) {
        File file = new File("database/ApartmentData.txt");
        List<String> lines = new ArrayList<>();
        boolean updated = false;
    
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) { // Reading the data line by line
                String line = scanner.nextLine();
                String[] details = line.split(" \\$ ");
                if (details[0].equals(apartmentId) && !details[4].equals("Rented")) {   // Checking if the apartment ID matches and the status is not Rented
                    details[4] = "Rented";  // Changing the status to Rented
                    line = String.join(" $ ", details);
                    updated = true;
                }
                lines.add(line);
            }
        } catch (FileNotFoundException e) { // Catching the exception if the file is not found
            JOptionPane.showMessageDialog(null, "Apartment data file not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    
        // Writing the updated data back to the file
        if (updated) {
            try (PrintWriter writer = new PrintWriter(file)) {
                for (String line : lines) {
                    writer.println(line);   // Writing the data line by line
                }
            } catch (IOException e) {   // Catching the exception if an I/O error occurs
                JOptionPane.showMessageDialog(null, "Failed to save apartment data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Overriding the actionPerformed method to handle button actions
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == payButton) {   // Handling the Pay button action
            String accountNumber = accountNumberField.getText().trim();
            String pin = new String(pinField.getPassword()).trim();
        
            if (accountNumber.isEmpty() || pin.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your account number and PIN to proceed.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } 
        
            if (!accountNumber.startsWith("01")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid " + paymentMethod + " account number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            if (accountNumber.length() != 11) {
                JOptionPane.showMessageDialog(this, "Please enter a valid 11-digit " + paymentMethod + " account number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            try {   // Checking if the account number is a number
                Integer.parseInt(accountNumber);
            } catch (NumberFormatException e) { // Catching the exception if the account number is not a number
                JOptionPane.showMessageDialog(this, "Account number must be a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            
            try {   // Checking if the PIN is a number
                Integer.parseInt(pin);
                if (paymentMethod.equals("bKash") && (pin.length() != 4 && pin.length() != 5)) {
                    JOptionPane.showMessageDialog(this, "Please enter a 4 or 5-digit PIN.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (paymentMethod.equals("Nagad") && pin.length() != 4) {
                    JOptionPane.showMessageDialog(this, "PIN must be 4 digits long.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) { // Catching the exception if the PIN is not a number
                JOptionPane.showMessageDialog(this, "PIN must be a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
        
            // Processing the payment
            JOptionPane.showMessageDialog(this, "Payment successfully processed!", "Success", JOptionPane.INFORMATION_MESSAGE);
            markApartmentRented(apartmentID);
            userDashboard.refreshApartmentList();
            apartmentDetailDialog.updateDetails();
            this.dispose();
        } else if (event.getSource() == cancelButton) {  // Handling the Cancel button action
            if (JOptionPane.showConfirmDialog(PaymentWindow.this, "Are you sure you want to cancel the payment?", "Cancel Payment", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                dispose();
                new PaymentGateway(null, amountDue, apartmentID, userDashboard, apartmentDetailDialog);
            }
        }
    }

    // Main method to test the PaymentWindow class
    public static void main(String[] args) {
        new PaymentWindow(null, "1000", "bKash", null, null, null);
    }
}

