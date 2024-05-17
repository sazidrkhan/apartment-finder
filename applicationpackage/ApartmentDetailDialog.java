package applicationpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ApartmentDetailDialog extends JDialog implements ActionListener {
    private UserDashboard userDashboard;
    private ApartmentItem apartment;
    private JButton rentButton, cancelButton;
    private JPanel detailsPanel, buttonPanel;

    public ApartmentDetailDialog(UserDashboard userDashboard, ApartmentItem apartment) {
        super(userDashboard, true);
        this.userDashboard = userDashboard;
        this.apartment = apartment;

        setTitle("Apartment Details");
        setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel(new ImageIcon(apartment.image.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH)));
        imageLabel.setBackground(Color.LIGHT_GRAY);
    
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(6, 2));
        detailsPanel.add(new JLabel("Status: " + apartment.status));
        detailsPanel.add(new JLabel("Price (TK/month): " + apartment.price));
        detailsPanel.add(new JLabel("Size (sq ft): " + apartment.size));
        detailsPanel.add(new JLabel("Address: " + apartment.address));
        detailsPanel.add(new JLabel("Details: " + apartment.details));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        detailsPanel.setBackground(Color.LIGHT_GRAY);
    
        Font detailsFont = new Font("Georgia", Font.PLAIN, 16);
        for (Component component : detailsPanel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setFont(detailsFont);
            }
        }

        buttonPanel = new JPanel();
        cancelButton = new JButton("Cancel");
        rentButton = new JButton("Rent");
        buttonPanel.add(cancelButton);
        buttonPanel.add(rentButton);
        buttonPanel.setBackground(Color.LIGHT_GRAY);
    
        cancelButton.addActionListener(this);
        rentButton.addActionListener(this);

        if (apartment.status.equals("Rented")) {
            rentButton.setEnabled(false);
        }

        add(imageLabel, BorderLayout.NORTH);
        add(detailsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    
        pack();
        setLocationRelativeTo(userDashboard);
        setVisible(true);
    }

    public void updateDetails() {
        detailsPanel.removeAll();
        // Add updated labels
        detailsPanel.add(new JLabel("Status: Rented"));
        detailsPanel.add(new JLabel("Price (TK/month): " + apartment.price));
        detailsPanel.add(new JLabel("Size (sq ft): " + apartment.size));
        detailsPanel.add(new JLabel("Address: " + apartment.address));
        detailsPanel.add(new JLabel("Details: " + apartment.details));
        detailsPanel.revalidate();  // Refresh the panel layout after adding new labels 
        detailsPanel.repaint();
        if ("Rented".equals(apartment.status)) {
            rentButton.setEnabled(false);
        }

        Font detailsFont = new Font("Georgia", Font.PLAIN, 16);
        for (Component component : detailsPanel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setFont(detailsFont);
            }
        }
        
        buttonPanel.remove(rentButton);
        buttonPanel.add(new JButton("Rent")).setEnabled(false);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Rent")) {
            new PaymentGateway(this, apartment.price, apartment.apartmentId, userDashboard, this);
            userDashboard.clearSelection();
        } 
    
        if (e.getSource() == cancelButton) {
            userDashboard.clearSelection();
            dispose();
        } 
    }
}