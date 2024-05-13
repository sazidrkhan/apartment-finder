package applicationpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaymentGateway extends JDialog implements ActionListener {
    private String price;
    private JLabel priceLabel, paymentLabel;
    private JButton nagadButton, bkashButton;

    public PaymentGateway(JDialog parentDialog, String price) {
        super(parentDialog, "Payment Gateway", true);
        this.price = price;

        priceLabel = new JLabel("Amount Due: " + price);
        paymentLabel = new JLabel("Please select a payment method...");
        nagadButton = new JButton("Nagad");
        bkashButton = new JButton("bKash");
        
        Font labelFont = new Font("Georgia", Font.BOLD, 14);
        priceLabel.setFont(labelFont);
        paymentLabel.setFont(labelFont);

        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        nagadButton.setFont(buttonFont);
        bkashButton.setFont(buttonFont);

        priceLabel.setBounds(80, 50, 200, 30);
        paymentLabel.setBounds(80, 100, 300, 30);
        nagadButton.setBounds(80, 150, 200, 30);
        bkashButton.setBounds(80, 200, 200, 30);

        nagadButton.addActionListener(this);
        bkashButton.addActionListener(this);

        add(priceLabel);
        add(paymentLabel);
        add(nagadButton);
        add(bkashButton);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(400, 400);
        setLocationRelativeTo(parentDialog);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button actions here
        if (e.getSource() == nagadButton || e.getSource() == bkashButton) {
            String method = e.getSource() == nagadButton ? "Nagad" : "bKash";
            dispose();
            new PaymentWindow(this, price, method).setVisible(true);
        }
    }

    public static void main(String[] args) {
        // For testing purposes, ensure there is a dummy parent
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);
        new PaymentGateway(null, "5000");
    }
}
