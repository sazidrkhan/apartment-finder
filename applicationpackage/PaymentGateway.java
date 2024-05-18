package applicationpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaymentGateway extends JDialog implements ActionListener {
    private UserDashboard userDashboard;
    private ApartmentDetailDialog apartmentDetailDialog;
    private String price, apartmentID;
    private JLabel priceLabel, paymentLabel;
    private JButton nagadButton, bkashButton;

    public PaymentGateway(JDialog parentDialog, String price, String apartmentID, UserDashboard userDashboard, ApartmentDetailDialog apartmentDetailDialog) {
        super(parentDialog, "Payment Gateway", true);
        this.price = price;
        this.apartmentID = apartmentID;
        this.userDashboard = userDashboard;
        this.apartmentDetailDialog = apartmentDetailDialog;

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
        setResizable(false);
        setLocationRelativeTo(parentDialog);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                SwingUtilities.invokeLater(() -> {
                    getContentPane().requestFocusInWindow();
                });
            }
        });
    
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button actions here
        if (e.getSource() == nagadButton || e.getSource() == bkashButton) {
            String method = e.getSource() == nagadButton ? "Nagad" : "bKash";
            setVisible(false);
            new PaymentWindow(this, price, method, apartmentID, userDashboard, apartmentDetailDialog);
        }
    }

    public static void main(String[] args) {
        new PaymentGateway(null, "5000", null, null, null);
    }
}
