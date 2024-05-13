package applicationpackage;

import javax.swing.*;
import java.awt.*;

public class ApartmentDetailDialog extends JDialog {
    private UserDashboard parentFrame;

    public ApartmentDetailDialog(UserDashboard parent, ApartmentItem apartment) {
        super(parent, "Apartment Details", true);
        this.parentFrame = parent;

        setTitle("Apartment Details");
        setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel(new ImageIcon(apartment.image.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH)));
        add(imageLabel, BorderLayout.NORTH);

        JTextArea detailText = new JTextArea(
            "Status: " + apartment.status + "\n" + 
            "Price (Tk/month): " + apartment.price + "\n" + 
            "Size (sq ft): " + apartment.size + "\n" +
            "Address: " + apartment.address + "\n" + 
            "Details: " + apartment.details
        );
        detailText.setEditable(false);
        detailText.setWrapStyleWord(true);
        detailText.setLineWrap(true);
        detailText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(detailText, BorderLayout.CENTER);

        Font textFont = new Font("Georgia", Font.PLAIN, 16);
        detailText.setFont(textFont);

        JPanel buttonPanel = new JPanel();
        JButton rentButton = new JButton("Rent");
        rentButton.addActionListener(e -> confirmPayment(apartment.price));
        buttonPanel.add(rentButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            parentFrame.clearSelection();
            dispose();
        });
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void confirmPayment(String price) {
        PaymentGateway paymentGateway = new PaymentGateway(this, price);
        paymentGateway.setVisible(true);
    }
}
