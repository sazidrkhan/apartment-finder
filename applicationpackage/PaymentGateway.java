package applicationpackage; // Package containing the main classes of the application

// Importing the necessary classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Payment Gateway class to display the payment options
public class PaymentGateway extends JDialog implements ActionListener {
    // Declaring the necessary variables
    private UserDashboard userDashboard;
    private ApartmentDetailDialog apartmentDetailDialog;
    private String price, apartmentID;
    private JLabel priceLabel, paymentLabel;
    private JButton nagadButton, bkashButton;

    // Constructor to initialize the components
    public PaymentGateway(JDialog parentDialog, String price, String apartmentID, UserDashboard userDashboard, ApartmentDetailDialog apartmentDetailDialog) {
        // Initializing the components
        super(parentDialog, "Payment Gateway", true);
        this.price = price;
        this.apartmentID = apartmentID;
        this.userDashboard = userDashboard;
        this.apartmentDetailDialog = apartmentDetailDialog;
    
        // Labeling the components
        priceLabel = new JLabel("Amount Due: " + price);
        paymentLabel = new JLabel("Please select a payment method...");
        nagadButton = new JButton("Nagad");
        bkashButton = new JButton("bKash");
    
        // Setting the font for the labels
        Font labelFont = new Font("Georgia", Font.BOLD, 14);
        priceLabel.setFont(labelFont);
        paymentLabel.setFont(labelFont);
    
        // Setting the font for the buttons
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        nagadButton.setFont(buttonFont);
        bkashButton.setFont(buttonFont);
    
        // Setting the bounds for the components
        priceLabel.setBounds(80, 50, 200, 30);
        paymentLabel.setBounds(80, 100, 300, 30);
        nagadButton.setBounds(80, 150, 200, 30);
        bkashButton.setBounds(80, 200, 200, 30);
    
        // Adding action listeners to the buttons
        nagadButton.addActionListener(this);
        bkashButton.addActionListener(this);
    
        // Adding the components to the dialog
        add(priceLabel);
        add(paymentLabel);
        add(nagadButton);
        add(bkashButton);
    
        // Setting the properties of the dialog
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(400, 400);
        setResizable(false);
        setLocationRelativeTo(parentDialog);
        getContentPane().setBackground(Color.LIGHT_GRAY);
    
        // Adding a window listener to the dialog to request focus
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                SwingUtilities.invokeLater(() -> {
                    getContentPane().requestFocusInWindow();
                });
            }
        });
    
        // Making the dialog visible
        setVisible(true);
    }

    // Overriding the actionPerformed method to handle button actions
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button actions here
        if (e.getSource() == nagadButton || e.getSource() == bkashButton) {
            String method = e.getSource() == nagadButton ? "Nagad" : "bKash";
            setVisible(false);
            new PaymentWindow(this, price, method, apartmentID, userDashboard, apartmentDetailDialog);
        }
    }

    // Main method to test the PaymentGateway class
    public static void main(String[] args) {
        new PaymentGateway(null, "5000", null, null, null);
    }
}
