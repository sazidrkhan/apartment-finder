package applicationpackage; // Package containing the main classes of the application

// Importing the necessary classes
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

// Apartment Manager class to manage the apartments
public class ApartmentManager extends JFrame implements ActionListener {
    // Declaring the necessary components
    private AdminDashboard adminDashboard;
    private JTable apartmentTable;
    private JButton addButton, updateButton, deleteButton, deselectButton, backButton;
    private DefaultTableModel tableModel;
    private final String dataFilePath = "database/ApartmentData.txt";

    // Constructor to initialize the components
    public ApartmentManager(AdminDashboard adminDashboard) {
        // Initializing the components
        this.adminDashboard = adminDashboard;
    
        // Setting the properties of the frame
        setTitle("Apartment Manager");
        setSize(910, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setBackground(Color.LIGHT_GRAY);
    
        // Adding a window listener to the dialog to request focus when opened and dispose the frame when closed 
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                adminDashboard.setVisible(true);
            
            }
        
            @Override
            public void windowOpened(WindowEvent e) {
                SwingUtilities.invokeLater(() -> {
                    getContentPane().requestFocusInWindow();
                });
            }
        });
        
    
        // Setting the table model for the apartments
        String[] columnNames = {"AID", "Image Path", "Size (sq ft)", "Price (TK)", "Status", "Address", "Description"};
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
    
        // Adding a scroll pane to the table
        JScrollPane scrollPane = new JScrollPane(apartmentTable);
        scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);
        add(scrollPane, BorderLayout.CENTER);
    
        // Setting the button panel for the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        addButton = new JButton("Add Apartment");
        updateButton = new JButton("Update Apartment");
        deleteButton = new JButton("Delete Apartment");
        deselectButton = new JButton("Remove Selection");
        backButton = new JButton("Go Back");
    
        // Adding action listeners to the buttons
        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        deselectButton.addActionListener(this);
        backButton.addActionListener(this);
    
        // Adding the buttons to the button panel
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(deselectButton);
        buttonPanel.add(backButton);
    
        add(buttonPanel, BorderLayout.SOUTH);   // Adding the button panel to the frame
    
        setVisible(true);   // Making the frame visible
    }

    // Adjusting the column width of the table
    private void adjustColumnWidth(JTable table){
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

    // Setting the alignment of the table
    private void setTableAlignment(JTable table) {
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }
    }

    // Loading the apartments from the data file
    private void loadApartments() {
        tableModel.setRowCount(0);
        try (Scanner scan = new Scanner(new File(dataFilePath))) {
            while (scan.hasNextLine()) {    // Looping through the lines of the file to add the apartments to the table
                String line = scan.nextLine();
                String[] data = line.split(" \\$ ");
                tableModel.addRow(data);
            }
        } catch (FileNotFoundException e) {   // Catching the exception if the file is not found
            JOptionPane.showMessageDialog(this, "Apartment data file not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Adding a new apartment to the table
    public void addNewApartment(String[] data) {
        tableModel.addRow(data);
        saveApartments();
    }

    // Updating an existing apartment in the table
    public void updateApartment(String[] data) {
        int row = apartmentTable.getSelectedRow();
        if (row != -1) {
            for (int i = 0; i < data.length; i++) {   // Looping through the data 
                tableModel.setValueAt(data[i], row, i);   // Setting the data to the table
            }
            saveApartments();
        }
    }

    // Saving the apartments to the data file
    private void saveApartments() {
        try (PrintWriter out = new PrintWriter(new FileWriter(dataFilePath))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {    // Looping through the rows of the table
                for (int j = 0; j < tableModel.getColumnCount(); j++) {   // Looping through the columns of the table 
                    out.print(tableModel.getValueAt(i, j) + (j < tableModel.getColumnCount() - 1 ? " $ " : "\n"));  // Writing the data to the file
                }
            }
        } catch (IOException ex) {  // Catching the exception if the file is not found
            JOptionPane.showMessageDialog(this, "Failed to save apartment data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Getting the next apartment ID
    public int getNextApartmentID() {
        int maxID = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {    // Looping through the rows of the table
            int currentId = Integer.parseInt(tableModel.getValueAt(i, 0).toString());
            if (currentId > maxID) {
                maxID = currentId;  // Setting the maximum ID
            }
        }
        return maxID + 1;
    }

    // Overriding the actionPerformed method of the ActionListener interface to handle the action events
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {   // Opening the apartment detail form when the add button is clicked
            new ApartmentDetailForm(this, null);
        } else if (e.getSource() == updateButton) {   // Opening the apartment detail form when the update button is clicked
            int row = apartmentTable.getSelectedRow();
            if (row != -1) {
                if (JOptionPane.showConfirmDialog(this, "Are you sure you want to update this apartment?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    String[] data = new String[tableModel.getColumnCount()];
                    for (int i = 0; i < data.length; i++) {  // Looping through the data to collect the details of the apartment
                        data[i] = tableModel.getValueAt(row, i).toString();
                    }
                    new ApartmentDetailForm(this, data);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No apartment selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == deleteButton) {  // Deleting the selected apartment from the table
            int row = apartmentTable.getSelectedRow();
            if (row != -1) {
                if (JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this apartment?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    tableModel.removeRow(row);
                    saveApartments();
                } 
            } else {
                JOptionPane.showMessageDialog(this, "No apartment selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == deselectButton) {    // Clearing the selection of the table
            loadApartments();
        } else if (e.getSource() == backButton) {   // Disposing the frame and displaying the admin dashboard
            this.dispose();
            adminDashboard.setVisible(true);
        }
    }

    // Main method to test the Apartment Manager class
    public static void main(String[] args) {
        new ApartmentManager(null);
    }
}