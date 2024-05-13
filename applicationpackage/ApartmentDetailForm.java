package applicationpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ApartmentDetailForm extends JFrame implements ActionListener {
    private ApartmentManager apartmentManager;
    private JLabel operationText, idLabel, addressLabel, sizeLabel, priceLabel, statusLabel, imagePathLabel, descriptionLabel;
    private JTextField idField, addressField, sizeField, priceField, imagePathField, descriptionField;
    private JComboBox<String> statusComboBox;
    private JButton saveButton, cancelButton;
    private String[] originalData;

    public ApartmentDetailForm(ApartmentManager apartmentManager, String[] data) {
        this.apartmentManager = apartmentManager;
        this.originalData = data;

        operationText = new JLabel(data == null ? "Adding new apartment..." : "Updating apartment...");
        idLabel = new JLabel("ID:");
        addressLabel = new JLabel("Address:");
        sizeLabel = new JLabel("Size (sq ft):");
        priceLabel = new JLabel("Price:");
        statusLabel = new JLabel("Status:");
        imagePathLabel = new JLabel("Image Path:");
        descriptionLabel = new JLabel("Description:");

        idField = new JTextField();
        addressField = new JTextField();
        sizeField = new JTextField();
        priceField = new JTextField();
        imagePathField = new JTextField();
        descriptionField = new JTextField();

        idField.setEditable(false);

        String[] statuses = {"Available", "Rented", "Under Maintenance"};
        statusComboBox = new JComboBox<>(statuses);

        saveButton = new JButton(data == null ? "Add" : "Update");
        cancelButton = new JButton("Cancel");

        Font labelFont = new Font("Georgia", Font.BOLD, 14);
        idLabel.setFont(labelFont);
        addressLabel.setFont(labelFont);
        sizeLabel.setFont(labelFont);
        priceLabel.setFont(labelFont);
        statusLabel.setFont(labelFont);
        imagePathLabel.setFont(labelFont);
        descriptionLabel.setFont(labelFont);

        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        idField.setFont(fieldFont);
        addressField.setFont(fieldFont);
        sizeField.setFont(fieldFont);
        priceField.setFont(fieldFont);
        imagePathField.setFont(fieldFont);
        descriptionField.setFont(fieldFont);

        setLayout(new GridLayout(9, 2)); // GridLayout for simplicity
        add(operationText);
        add(new JLabel(""));
        add(idLabel);
        add(idField);
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
            idField.setText(data[0]);
            imagePathField.setText(data[1]);
            sizeField.setText(data[2]);
            priceField.setText(data[3]);
            statusComboBox.setSelectedItem(data[4]);
            addressField.setText(data[5]);
            descriptionField.setText(data[6]);
        } else {
            idField.setText(Integer.toString(apartmentManager.getNextApartmentID()));
        }

        setTitle(data == null ? "Add New Apartment" : "Update Apartment");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        getContentPane().setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            String[] newData = {
                idField.getText(),
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
