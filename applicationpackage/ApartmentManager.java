package applicationpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ApartmentManager extends JFrame implements ActionListener {
    private JTextArea apartmentDisplayArea;
    private JButton addButton, editButton, removeButton, refreshButton;

    public ApartmentManager() {
        setTitle("Apartment Manager");
        setSize(350, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        apartmentDisplayArea = new JTextArea();
        apartmentDisplayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(apartmentDisplayArea);

        addButton = new JButton("Add Apartment");
        editButton = new JButton("Edit Apartment");
        removeButton = new JButton("Remove Apartment");
        refreshButton = new JButton("Refresh");

        addButton.addActionListener(this);
        editButton.addActionListener(this);
        removeButton.addActionListener(this);
        refreshButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(refreshButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loadApartments();
    }

    private void loadApartments() {
        apartmentDisplayArea.setText("");  // Clear the area to avoid duplication
        try (Scanner scanner = new Scanner(new File("database/ApartmentsData.txt"))) {
            while (scanner.hasNextLine()) {
                apartmentDisplayArea.append(scanner.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Apartment data file not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // Open a dialog or another frame to add a new apartment
            addApartment();
        } else if (e.getSource() == editButton) {
            // Open a dialog or another frame to edit an existing apartment
            editApartment();
        } else if (e.getSource() == removeButton) {
            // Confirm before removing an apartment
            removeApartment();
        } else if (e.getSource() == refreshButton) {
            loadApartments();  // Reload apartment listings from file
        }
    }

    private void addApartment() {
        // Implementation for adding a new apartment
        // This could involve opening a new dialog window where you input the details
        // Then write those details into `ApartmentsData.txt`
    }

    private void editApartment() {
        // Implementation for editing an existing apartment
        // This might involve selecting an apartment from the list and then opening a dialog with the details pre-filled
        // After editing, update the details in the file
    }

    private void removeApartment() {
        // Implementation for removing an existing apartment
        // Typically involves selecting the apartment from the list, confirming the deletion, and then updating the file
    }

    public static void main(String[] args) {
        new ApartmentManager();
    }
}
