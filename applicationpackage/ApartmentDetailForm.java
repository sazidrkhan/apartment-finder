package applicationpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ApartmentDetailForm extends JFrame implements ActionListener {
    private ApartmentManager apartmentManager;
    private JLabel operationText, aidLabel, addressLabel, sizeLabel, priceLabel, statusLabel, imagePathLabel, descriptionLabel;
    private JTextField aidField, addressField, sizeField, priceField, imagePathField, descriptionField;
    private JComboBox<String> statusComboBox;
    private JButton saveButton, cancelButton;
    private String[] originalData;

    public ApartmentDetailForm(ApartmentManager apartmentManager, String[] data) {
        this.apartmentManager = apartmentManager;
        this.originalData = data;

        operationText = new JLabel(data == null ? "Adding new apartment..." : "Updating apartment...");
        aidLabel = new JLabel("ID:");
        addressLabel = new JLabel("Address:");
        sizeLabel = new JLabel("Size (sq ft):");
        priceLabel = new JLabel("Price:");
        statusLabel = new JLabel("Status:");
        imagePathLabel = new JLabel("Image Path:");
        descriptionLabel = new JLabel("Description:");
        String[] statuses = {"Available", "Rented", "Under Maintenance"};
        saveButton = new JButton(data == null ? "Add" : "Update");
        cancelButton = new JButton("Cancel");

        aidField = new JTextField();
        addressField = new JTextField();
        sizeField = new JTextField();
        priceField = new JTextField();
        imagePathField = new JTextField();
        descriptionField = new JTextField();
        statusComboBox = new JComboBox<>(statuses);

        aidField.setEditable(false);
        
        Font message = new Font("Times New Roman", Font.BOLD, 26);
        operationText.setFont(message);
    
        Font labelFont = new Font("Georgia", Font.BOLD, 14);
        aidLabel.setFont(labelFont);
        addressLabel.setFont(labelFont);
        sizeLabel.setFont(labelFont);
        priceLabel.setFont(labelFont);
        statusLabel.setFont(labelFont);
        imagePathLabel.setFont(labelFont);
        descriptionLabel.setFont(labelFont);

        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        aidField.setFont(fieldFont);
        addressField.setFont(fieldFont);
        sizeField.setFont(fieldFont);
        priceField.setFont(fieldFont);
        imagePathField.setFont(fieldFont);
        descriptionField.setFont(fieldFont);

        operationText.setBounds(100, 20, 300, 30);
        aidLabel.setBounds(50, 70, 100, 30);
        aidField.setBounds(150, 70, 300, 30);
        imagePathLabel.setBounds(50, 120, 100, 30);
        imagePathField.setBounds(150, 120, 300, 30);
        sizeLabel.setBounds(50, 170, 100, 30);
        sizeField.setBounds(150, 170, 300, 30);
        priceLabel.setBounds(50, 220, 100, 30);
        priceField.setBounds(150, 220, 300, 30);
        statusLabel.setBounds(50, 270, 100, 30);
        statusComboBox.setBounds(150, 270, 300, 30);
        addressLabel.setBounds(50, 320, 100, 30);
        addressField.setBounds(150, 320, 300, 30);
        descriptionLabel.setBounds(50, 370, 100, 30);
        descriptionField.setBounds(150, 370, 300, 30);
        cancelButton.setBounds(150, 420, 100, 30);
        saveButton.setBounds(270, 420, 100, 30);

        add(operationText);
        add(aidLabel);
        add(aidField);
        add(imagePathLabel);
        add(imagePathField);
        add(sizeLabel);
        add(sizeField);
        add(priceLabel);
        add(priceField);
        add(statusLabel);
        add(statusComboBox);
        add(addressLabel);
        add(addressField);
        add(descriptionLabel);
        add(descriptionField);
        add(cancelButton);
        add(saveButton);

        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);

        if (data != null) {
            aidField.setText(data[0]);
            imagePathField.setText(data[1]);
            sizeField.setText(data[2]);
            priceField.setText(data[3]);
            statusComboBox.setSelectedItem(data[4]);
            addressField.setText(data[5]);
            descriptionField.setText(data[6]);
        } else {
            aidField.setText(Integer.toString(apartmentManager.getNextApartmentID()));
        }

        setTitle(data == null ? "Add New Apartment" : "Update Apartment");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
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
        if (e.getSource() == saveButton) {
            String[] newData = {
                aidField.getText(),
                imagePathField.getText(),
                sizeField.getText(),
                priceField.getText(),
                statusComboBox.getSelectedItem().toString(),
                addressField.getText(),
                descriptionField.getText()
            };

            if (originalData == null) {
                apartmentManager.addNewApartment(newData);
            } else {
                System.arraycopy(newData, 0, originalData, 0, newData.length); // Copy new data to original data
                apartmentManager.updateApartment(originalData);
            }
            dispose();
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }
}
