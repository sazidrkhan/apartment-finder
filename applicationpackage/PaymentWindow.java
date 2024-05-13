package applicationpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PaymentWindow extends JDialog implements ActionListener {
    private JLabel message, logoLabel, paymentMethodLabel, paymentLabel, accountNumberLabel, pinLabel;
    private JTextField accountNumberField;
    private JPasswordField pinField;
    private JButton payButton, cancelButton;
    private JCheckBox showPin;
    private String paymentMethod;
    private boolean whichLogo;

    public PaymentWindow(JDialog parent, String amountDue, String paymentMethod) {
        super(parent, true);
        this.paymentMethod = paymentMethod;
        
        setTitle("Payment Window");
        setSize(500, 500);
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

        File imageLabel = new File(whichLogo ? "images/bkash.png" : "images/nagad.png");
        ImageIcon icon = new ImageIcon(new ImageIcon(imageLabel.getAbsolutePath()).getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH));

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
        
        showPin.addActionListener(new ActionListener() {  // Adding an action listener to the showPin checkbox
            @Override
            public void actionPerformed(ActionEvent e) {    // Overriding the actionPerformed method to handle checkbox click events
                if (showPin.isSelected()) {    // If the checkbox is selected (checked), showing the password as plain text
                    pinField.setEchoChar((char) 0);    // Setting the echo character to 0 for the password field
                } else {    // If the checkbox is not selected (unchecked), showing the password as masked text
                    pinField.setEchoChar('$'); // Set the echo character to '$' for the password field
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
        setLocationRelativeTo(getParent());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == payButton) {
            String accountNumber = accountNumberField.getText().trim();
            String pin = new String(pinField.getPassword());
            if (accountNumber.isEmpty() || pin.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your account number and PIN to proceed with the payment.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } 

            if (paymentMethod.equals("bKash")) {
                if (pin.length() != 4 && pin.length() != 5) {
                    JOptionPane.showMessageDialog(this, "Please enter a 4 or 5-digit PIN.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (paymentMethod.equals("Nagad")) {
                if (pin.length() != 4) {
                    JOptionPane.showMessageDialog(this, "PIN must be 4 digits long.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (accountNumber.length() != 11) {
                JOptionPane.showMessageDialog(this, "Please enter a valid 11-digit " + paymentMethod + " account number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!accountNumber.startsWith("01")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid " + paymentMethod + " account number starting with '01'.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (accountNumber.startsWith("01") && accountNumber.length() == 11) {
                if (JOptionPane.showConfirmDialog(this, "Payment successfully!", "Success", JOptionPane.OK_OPTION) == JOptionPane.OK_OPTION) {
                    ApartmentDetailDialog apartmentDetailDialog = new ApartmentDetailDialog(null, null);
                    apartmentDetailDialog.setVisible(true);
                    dispose();
                }
            }
        } else if (event.getSource() == cancelButton) {
            dispose();
        }
    }

    public static void main(String[] args) {
        PaymentWindow paymentWindow = new PaymentWindow(null, "1000", "bKash");
        paymentWindow.setVisible(true);
    }
}
