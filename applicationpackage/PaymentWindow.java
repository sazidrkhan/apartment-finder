package applicationpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PaymentWindow extends JDialog implements ActionListener {
    private UserDashboard userDashboard;
    private ApartmentDetailDialog apartmentDetailDialog;
    private JLabel message, logoLabel, paymentMethodLabel, paymentLabel, accountNumberLabel, pinLabel;
    private JTextField accountNumberField;
    private JPasswordField pinField;
    private JButton payButton, cancelButton;
    private JCheckBox showPin;
    private String amountDue, paymentMethod, apartmentID;
    private boolean whichLogo;

    public PaymentWindow(JDialog parent, String amountDue, String paymentMethod, String apartmentID, UserDashboard userDashboard, ApartmentDetailDialog apartmentDetailDialog) {
        super(parent, true);
        this.amountDue = amountDue;
        this.paymentMethod = paymentMethod;
        this.apartmentID = apartmentID;
        this.userDashboard = userDashboard;
        this.apartmentDetailDialog = apartmentDetailDialog;
    
        setTitle("Payment Window");
        setSize(500, 500);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(PaymentWindow.this, "Are you sure you want to cancel the payment?", "Cancel Payment", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    dispose();
                    new PaymentGateway(null, amountDue, apartmentID, userDashboard, apartmentDetailDialog);
                }
            }
        });
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
    
        if (paymentMethod.equals("bKash")) {
            whichLogo = true;
        } else {
            whichLogo = false;
        }
    
        ImageIcon icon = new ImageIcon(new ImageIcon(new File(whichLogo ? "images/bkash.png" : "images/nagad.png").getAbsolutePath()).getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH));
    
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
    
        Font labelFont = new Font("Georgia", Font.BOLD, 14);
        accountNumberLabel.setFont(labelFont);
        pinLabel.setFont(labelFont);
        paymentMethodLabel.setFont(labelFont);
        paymentLabel.setFont(labelFont);
        message.setFont(labelFont);
    
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        accountNumberField.setFont(fieldFont);
        pinField.setFont(fieldFont);
        
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
    
        payButton.addActionListener(this);
        cancelButton.addActionListener(this);
        
        add(new JLabel(""));
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
    
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void markApartmentRented(String apartmentId) {
        File file = new File("database/ApartmentData.txt");
        List<String> lines = new ArrayList<>();
        boolean updated = false;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split(" \\$ ");
                if (details[0].equals(apartmentId) && !details[4].equals("Rented")) {
                    details[4] = "Rented";  // Change the status to Rented
                    line = String.join(" $ ", details);
                    updated = true;
                }
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Apartment data file not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (updated) {
            try (PrintWriter writer = new PrintWriter(file)) {
                for (String line : lines) {
                    writer.println(line);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Failed to save apartment data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == payButton) {
            String accountNumber = accountNumberField.getText().trim();
            String pin = new String(pinField.getPassword()).trim();

            if (accountNumber.isEmpty() || pin.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your account number and PIN to proceed with the payment.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } 

            try {
                Integer.parseInt(pin);
                if (paymentMethod.equals("bKash") && (pin.length() != 4 && pin.length() != 5)) {
                    JOptionPane.showMessageDialog(this, "Please enter a 4 or 5-digit PIN.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (paymentMethod.equals("Nagad") && pin.length() != 4) {
                    JOptionPane.showMessageDialog(this, "PIN must be 4 digits long.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "PIN must be a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                Integer.parseInt(accountNumber);
                if (accountNumber.length() != 11) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid 11-digit " + paymentMethod + " account number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            
                if (!accountNumber.startsWith("01")) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid " + paymentMethod + " account number starting with '01'.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } 
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Account number must be a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            JOptionPane.showMessageDialog(this, "Payment successfully processed!", "Success", JOptionPane.INFORMATION_MESSAGE);
            markApartmentRented(apartmentID);
            userDashboard.refreshApartmentList();
            apartmentDetailDialog.updateDetails();
            this.dispose();
        } else if (event.getSource() == cancelButton) {
            if (JOptionPane.showConfirmDialog(PaymentWindow.this, "Are you sure you want to cancel the payment?", "Cancel Payment", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                dispose();
                new PaymentGateway(null, amountDue, apartmentID, userDashboard, apartmentDetailDialog);
            }
        }
    }

    public static void main(String[] args) {
        new PaymentWindow(null, "1000", "bKash", null, null, null);
    }
}
