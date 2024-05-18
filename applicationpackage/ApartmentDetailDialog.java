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
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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

        JLabel imageLabel = new JLabel(new ImageIcon(apartment.image.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH)));
        imageLabel.setBackground(Color.LIGHT_GRAY);
    
        detailsPanel = new JPanel();
        detailsPanel.setLayout(null);
        detailsPanel.add(new JLabel("<html><b>Status: " + apartment.status + "</b></html>")).setBounds(10, 10, 500, 30);
        detailsPanel.add(new JLabel("<html><b>Price (TK/month): " + apartment.price + "</b></html>")).setBounds(10, 40, 500, 30);
        detailsPanel.add(new JLabel("<html><b>Size (sq ft): " + apartment.size + "</b></html>")).setBounds(10, 70, 500, 30);
        detailsPanel.add(new JLabel("<html><b>Address: " + apartment.address + "</b></html>")).setBounds(10, 100, 500, 30);
        detailsPanel.add(new JLabel("<html><b>Details:</b> " + apartment.details + "</html>")).setBounds(10, 130, 500, 100);
        detailsPanel.setPreferredSize(new Dimension(500, 227));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
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
        buttonPanel.setSize(500, 30);
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
        setResizable(false);
        setVisible(true);
    }

    public void updateDetails() {
        detailsPanel.removeAll();
        // Add updated labels
        detailsPanel.add(new JLabel("<html><b>Status: Rented</b></html>")).setBounds(10, 10, 500, 30);
        detailsPanel.add(new JLabel("<html><b>Price (TK/month): " + apartment.price + "</b></html>")).setBounds(10, 40, 500, 30);
        detailsPanel.add(new JLabel("<html><b>Size (sq ft): " + apartment.size + "</b></html>")).setBounds(10, 70, 500, 30);
        detailsPanel.add(new JLabel("<html><b>Address: " + apartment.address + "</b></html>")).setBounds(10, 100, 500, 30);
        detailsPanel.add(new JLabel("<html><b>Details:</b> " + apartment.details + "</html>")).setBounds(10, 130, 500, 100);
        detailsPanel.revalidate();  // Refresh the panel layout after adding new labels 
        detailsPanel.repaint();

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