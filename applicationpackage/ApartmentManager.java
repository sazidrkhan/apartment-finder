package applicationpackage;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ApartmentManager extends JFrame implements ActionListener {
    private AdminDashboard adminDashboard;
    private JTable apartmentTable;
    private JButton addButton, updateButton, deleteButton, deselectButton, backButton;
    private DefaultTableModel tableModel;
    private final String dataFilePath = "database/ApartmentData.txt";

    public ApartmentManager(AdminDashboard adminDashboard) {
        this.adminDashboard = adminDashboard;

        setTitle("Apartment Manager");
        setSize(910, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Setting the default close operation of the frame
        getContentPane().setBackground(Color.LIGHT_GRAY);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose(); // Disposing the current frame
                adminDashboard.setVisible(true); // Setting the admin dashboard frame visible

            }

            @Override
            public void windowOpened(WindowEvent e) {
                SwingUtilities.invokeLater(() -> {
                    getContentPane().requestFocusInWindow();
                });
            }
        });

        String[] columnNames = { "AID", "Image Path", "Size (sq ft)", "Price (TK)", "Status", "Address",
                "Description" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        apartmentTable = new JTable(tableModel);
        apartmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        apartmentTable.setFont(new Font("Arial", Font.PLAIN, 14));
        adjustColumnWidth(apartmentTable);
        setTableAlignment(apartmentTable);
        add(apartmentTable, BorderLayout.CENTER);
        loadApartments();

        JScrollPane scrollPane = new JScrollPane(apartmentTable);
        scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        addButton = new JButton("Add Apartment");
        updateButton = new JButton("Update Apartment");
        deleteButton = new JButton("Delete Apartment");
        deselectButton = new JButton("Remove Selection");
        backButton = new JButton("Go Back");

        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        deselectButton.addActionListener(this);
        backButton.addActionListener(this);

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(deselectButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void adjustColumnWidth(JTable table) {
        table.getColumnModel().getColumn(0).setMinWidth(40);
        table.getColumnModel().getColumn(1).setMinWidth(170);
        table.getColumnModel().getColumn(2).setMinWidth(70);
        table.getColumnModel().getColumn(3).setMinWidth(70);
        table.getColumnModel().getColumn(4).setMinWidth(140);
        table.getColumnModel().getColumn(5).setMinWidth(210);
        table.getColumnModel().getColumn(6).setMinWidth(170);

        table.getColumnModel().getColumn(1).setPreferredWidth(170);
        table.getColumnModel().getColumn(5).setPreferredWidth(380);

        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(1).setMaxWidth(170);
        table.getColumnModel().getColumn(2).setMaxWidth(80);
        table.getColumnModel().getColumn(3).setMaxWidth(80);
        table.getColumnModel().getColumn(4).setMaxWidth(150);
        table.getColumnModel().getColumn(5).setMaxWidth(380);
    }

    private void setTableAlignment(JTable table) {
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }
    }

    private void loadApartments() {
        tableModel.setRowCount(0);
        try (Scanner scan = new Scanner(new File(dataFilePath))) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] data = line.split(" \\$ ");
                tableModel.addRow(data);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Apartment data file not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addNewApartment(String[] data) {
        tableModel.addRow(data);
        saveApartments();
    }

    public void updateApartment(String[] data) {
        int row = apartmentTable.getSelectedRow();
        if (row != -1) {
            for (int i = 0; i < data.length; i++) {
                tableModel.setValueAt(data[i], row, i);
            }
            saveApartments();
        }
    }

    private void saveApartments() {
        try (PrintWriter out = new PrintWriter(new FileWriter(dataFilePath))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    out.print(tableModel.getValueAt(i, j) + (j < tableModel.getColumnCount() - 1 ? " $ " : "\n"));
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to save apartment data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int getNextApartmentID() {
        int maxID = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int currentId = Integer.parseInt(tableModel.getValueAt(i, 0).toString());
            if (currentId > maxID) {
                maxID = currentId;
            }
        }
        return maxID + 1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            new ApartmentDetailForm(this, null);
        } else if (e.getSource() == updateButton) {
            int row = apartmentTable.getSelectedRow();
            if (row != -1) {
                if (JOptionPane.showConfirmDialog(this, "Are you sure you want to update this apartment?", "Confirm",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    String[] data = new String[tableModel.getColumnCount()];
                    for (int i = 0; i < data.length; i++) {
                        data[i] = tableModel.getValueAt(row, i).toString();
                    }
                    new ApartmentDetailForm(this, data);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No apartment selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == deleteButton) {
            int row = apartmentTable.getSelectedRow();
            if (row != -1) {
                if (JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this apartment?", "Confirm",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    tableModel.removeRow(row);
                    saveApartments();
                }
            } else {
                JOptionPane.showMessageDialog(this, "No apartment selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == deselectButton) {
            loadApartments();
        } else if (e.getSource() == backButton) {
            this.dispose();
            adminDashboard.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new ApartmentManager(null);
    }
}