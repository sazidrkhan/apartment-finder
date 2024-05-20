package applicationpackage; // Package containing the main classes of the application

// Importing the necessary classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Apartment Detail Dialog class to display the details of the apartments
public class ApartmentDetailDialog extends JDialog implements ActionListener {
    // Declaring the necessary components
    private UserDashboard userDashboard;
    private ApartmentItem apartment;
    private JButton rentButton, cancelButton;
    private JPanel detailsPanel, buttonPanel;
    private boolean reserve;

    // Constructor to initialize the components
    public ApartmentDetailDialog(UserDashboard userDashboard, ApartmentItem apartment) {
        // Initializing the components
        super(userDashboard, true);
        this.userDashboard = userDashboard;
        this.apartment = apartment;
    
        // Setting the layout for the dialog
        setTitle("Apartment Details");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    
        // Adding a window listener to the dialog to request focus when opened and clear selection when closed
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent event) {
                SwingUtilities.invokeLater(() -> {
                    getContentPane().requestFocusInWindow();
                });
            }
        
            @Override
            public void windowClosing(WindowEvent event) {
                userDashboard.clearSelection();
                dispose();
            }
        });
    
        // Setting the image label for the apartments
        JLabel imageLabel = new JLabel(new ImageIcon(apartment.image.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH)));
        imageLabel.setBackground(Color.LIGHT_GRAY);
    
        // Setting the details panel for the apartments
        detailsPanel = new JPanel();
        detailsPanel.setLayout(null);
        detailsPanel.add(new JLabel("<html><b>Status: " + apartment.status + "</b></html>")).setBounds(10, 10, 500, 30);
        detailsPanel.add(new JLabel("<html><b>Price (TK/month): " + apartment.price + "</b></html>")).setBounds(10, 40, 500, 30);
        detailsPanel.add(new JLabel("<html><b>Size (sq ft): " + apartment.size + "</b></html>")).setBounds(10, 70, 500, 30);
        detailsPanel.add(new JLabel("<html><b>Address: " + apartment.address + "</b></html>")).setBounds(10, 100, 500, 30);
        detailsPanel.add(new JLabel("<html><b>Details:</b> " + apartment.details + "</html>")).setBounds(10, 130, 490, 100);
        detailsPanel.setPreferredSize(new Dimension(500, 227));
        detailsPanel.setBackground(Color.LIGHT_GRAY);
    
        // Setting the font for the details panel
        Font detailsFont = new Font("Georgia", Font.PLAIN, 16);
        for (Component component : detailsPanel.getComponents()) {  // Loop through the components of the details panel to set the font for the labels
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setFont(detailsFont);
            }
        }
    
        // Checking if the apartment is under maintenance to enable the reserve button
        if (apartment.status.equals("Under Maintenance")) {
            reserve = true;
        }
    
        // Setting the button panel for the dialog
        buttonPanel = new JPanel();
        cancelButton = new JButton("Cancel");
        rentButton = new JButton(reserve ? "Reserve" : "Rent");
        buttonPanel.add(cancelButton);
        buttonPanel.add(rentButton);
        buttonPanel.setSize(500, 30);
        buttonPanel.setBackground(Color.LIGHT_GRAY);
    
        // Adding action listeners to the buttons
        cancelButton.addActionListener(this);
        rentButton.addActionListener(this);
    
        // Disabling the rent button if the apartment is already rented
        if (apartment.status.equals("Rented")) {
            rentButton.setEnabled(false);
        }
    
        // Setting the layout for the dialog
        add(imageLabel, BorderLayout.NORTH);
        add(detailsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    
        // Setting the properties for the dialog
        pack();
        setLocationRelativeTo(userDashboard);
        setResizable(false);
        setVisible(true);
    }

    // Updating the details of the apartment
    public void updateDetails() {
        detailsPanel.removeAll();
        detailsPanel.add(new JLabel("<html><b>Status: Rented</b></html>")).setBounds(10, 10, 500, 30);
        detailsPanel.add(new JLabel("<html><b>Price (TK/month): " + apartment.price + "</b></html>")).setBounds(10, 40, 500, 30);
        detailsPanel.add(new JLabel("<html><b>Size (sq ft): " + apartment.size + "</b></html>")).setBounds(10, 70, 500, 30);
        detailsPanel.add(new JLabel("<html><b>Address: " + apartment.address + "</b></html>")).setBounds(10, 100, 500, 30);
        detailsPanel.add(new JLabel("<html><b>Details:</b> " + apartment.details + "</html>")).setBounds(10, 130, 490, 100);
        detailsPanel.revalidate();  // Refresh the panel layout after adding new labels 
        detailsPanel.repaint(); // Reloading the panel to display the updated details
    
        // Setting the font for the details panel after updating the details
        Font detailsFont = new Font("Georgia", Font.PLAIN, 16);
        for (Component component : detailsPanel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setFont(detailsFont);
            }
        }
    
        // Disabling the rent button by removing and recreating it after the apartment is rented 
        buttonPanel.remove(rentButton);
        buttonPanel.add(new JButton("Rent")).setEnabled(false);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    // Overriding the actionPerformed method to handle the button actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rentButton) {  // Opening the payment gateway dialog when the rent button is clicked
            new PaymentGateway(this, apartment.price, apartment.apartmentId, userDashboard, this);
            userDashboard.clearSelection();
        } 
    
        if (e.getSource() == cancelButton) {    // Clearing the selection and disposing the dialog when the cancel button is clicked
            userDashboard.clearSelection();
            dispose();
        } 
    }
}