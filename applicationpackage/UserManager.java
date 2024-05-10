package applicationpackage; // This is the package name of the class

import javax.swing.*;   // Importing javax.swing package for GUI components
import javax.swing.table.*; // Importing javax.swing.table package for JTable
import java.awt.*;  // Importing java.awt package for BorderLayout
import java.awt.event.*;    // Importing java.awt.event package for ActionListener
import java.io.*;   // Importing java.io package for File, FileWriter, PrintWriter
import java.util.Scanner;   // Importing java.util package for Scanner

public class UserManager extends JFrame implements ActionListener { // Class declaration extending JFrame and implementing ActionListener
    private JTable userTable;   // JTable to display user data in tabular form 
    private JButton addButton, updateButton, deleteButton, refreshButton, backButton; // JButton for adding, updating, deleting, refreshing and going back to the previous frame respectively 
    private DefaultTableModel tableModel;   // DefaultTableModel to store user data in tabular form 
    private AdminDashboard adminDashboard;  // AdminDashboard object to go back to the previous frame

    public UserManager(AdminDashboard adminDashboard) { // Constructor with AdminDashboard object as parameter 
        this.adminDashboard = adminDashboard;   // Assigning the parameter to the instance variable

        setTitle("User Manager");   // Setting the title of the frame
        setSize(800, 500);  // Setting the size of the frame
        setLayout(new BorderLayout());  // Setting the layout of the frame
        setLocationRelativeTo(null);    // Setting the location of the frame to the center of the screen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Setting the default close operation of the frame
        getContentPane().setBackground(Color.LIGHT_GRAY);   // Setting the background color of the frame

        String[] columnNames = {"Name", "Username", "Age", "Gender", "NID/BID No.", "Password"};    // Array of column names for the table
        tableModel = new DefaultTableModel(columnNames, 0){ // Creating a DefaultTableModel with column names and 0 rows to store user data in tabular form 
            @Override
            public boolean isCellEditable(int row, int column) {    // Overriding isCellEditable method to make cells non-editable 
                return false;   // Returning false to make cells non-editable 
            }
        };
        userTable = new JTable(tableModel); // Creating a JTable with the DefaultTableModel 
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);    // Setting the selection mode of the table to single selection 
        userTable.setFont(new Font("Arial", Font.PLAIN, 14));   // Setting the font of the table 
        adjustColumnWidth(userTable);   // Adjusting the column width of the table
        setTableAlignment(userTable);   // Setting the alignment of the table cells
        add(userTable, BorderLayout.CENTER); // Adding the table to the center of the frame
        loadUsers();    // Loading user data from file to the table

        JScrollPane scrollPane = new JScrollPane(userTable);    // Creating a JScrollPane with the JTable 
        scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);   // Setting the background color of the scroll pane 
        add(scrollPane, BorderLayout.CENTER);   // Adding the scroll pane to the center of the frame 

        JPanel buttonPanel = new JPanel();  // Creating a JPanel for buttons
        buttonPanel.setBackground(Color.LIGHT_GRAY);    // Setting the background color of the panel
        addButton = new JButton("Add User");    // Creating a JButton for adding user 
        updateButton = new JButton("Update User");  // Creating a JButton for updating user
        deleteButton = new JButton("Delete User");  // Creating a JButton for deleting user
        refreshButton = new JButton("Refresh List");    // Creating a JButton for refreshing the list
        backButton = new JButton("Go Back");    // Creating a JButton for going back to the previous frame

        addButton.addActionListener(this);  // Adding ActionListener to the add button
        updateButton.addActionListener(this);   // Adding ActionListener to the update button
        deleteButton.addActionListener(this);   // Adding ActionListener to the delete button
        refreshButton.addActionListener(this);  // Adding ActionListener to the refresh button
        backButton.addActionListener(this); // Adding ActionListener to the back button

        buttonPanel.add(addButton); // Adding add button to the panel
        buttonPanel.add(updateButton);  // Adding update button to the panel
        buttonPanel.add(deleteButton);  // Adding delete button to the panel
        buttonPanel.add(refreshButton); // Adding refresh button to the panel
        buttonPanel.add(backButton);    // Adding back button to the panel

        add(buttonPanel, BorderLayout.SOUTH);   // Adding the button panel to the south of the frame

        setVisible(true);
    }

    private void adjustColumnWidth(JTable table){
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(40);
        table.getColumnModel().getColumn(3).setPreferredWidth(60);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
    }

    private void setTableAlignment(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void loadUsers() {
        tableModel.setRowCount(0);
        try (Scanner scanner = new Scanner(new File("database/UserData.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" \\$ ");
                tableModel.addRow(data);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "User data file not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addNewUser(String[] data) {
        tableModel.addRow(data);
        saveUsers(); // Save to file after adding
    }

    public void updateUser(String[] data) {
        int row = userTable.getSelectedRow();
        if (row != -1) {
            for (int i = 0; i < data.length; i++) {
                tableModel.setValueAt(data[i], row, i);
            }
            saveUsers(); // Save to file after updating
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            new UserDataManager(this, null); // Null indicates a new user
        } else if (e.getSource() == updateButton) {
            int row = userTable.getSelectedRow();
            if (row != -1) {
                String[] data = new String[tableModel.getColumnCount()];
                for (int i = 0; i < data.length; i++) {
                    data[i] = tableModel.getValueAt(row, i).toString();
                }
                new UserDataManager(this, data);
            }
        } else if (e.getSource() == deleteButton) {
            int row = userTable.getSelectedRow();
            if (row != -1) {
                if (JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    tableModel.removeRow(row);
                    saveUsers();
                } 
                
            } else {
                JOptionPane.showMessageDialog(this, "No user selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == refreshButton) {
            loadUsers();
        } else if (e.getSource() == backButton) {
            this.dispose();
            adminDashboard.setVisible(true);
        }
    }

    private void saveUsers() {
        try (PrintWriter out = new PrintWriter(new FileWriter("database/UserData.txt"))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    out.print(tableModel.getValueAt(i, j) + (j < tableModel.getColumnCount() - 1 ? " $ " : ""));
                }
                out.println();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to save user data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new UserManager(null);
    }
}
