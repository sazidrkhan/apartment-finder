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

        setVisible(true);   // Setting the frame visible to the user
    }

    private void adjustColumnWidth(JTable table){   // Method to adjust the column width of the table
        table.getColumnModel().getColumn(0).setPreferredWidth(200);  // Setting the preferred width of the first column
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Setting the preferred width of the second column
        table.getColumnModel().getColumn(2).setPreferredWidth(40);  // Setting the preferred width of the third column
        table.getColumnModel().getColumn(3).setPreferredWidth(60);  // Setting the preferred width of the fourth column
        table.getColumnModel().getColumn(4).setPreferredWidth(150); // Setting the preferred width of the fifth column
        table.getColumnModel().getColumn(5).setPreferredWidth(200); // Setting the preferred width of the sixth column
    }

    private void setTableAlignment(JTable table) {  // Method to set the alignment of the table cells 
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();   // Creating a DefaultTableCellRenderer object 
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);   // Setting the horizontal alignment of the renderer to center 
        for (int i = 0; i < table.getColumnCount(); i++) {  // Looping through the columns of the table
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);    // Setting the cell renderer for each column
        }
    }

    private void loadUsers() {  // Method to load user data from file to the table 
        tableModel.setRowCount(0);  // Setting the row count of the table model to 0
        try (Scanner scanner = new Scanner(new File("database/UserData.txt"))) {    // Creating a Scanner object to read user data from the file 
            while (scanner.hasNextLine()) { // Looping through the lines of the file
                String line = scanner.nextLine();   // Reading a line from the file
                String[] data = line.split(" \\$ ");    // Splitting the line based on delimiter
                tableModel.addRow(data);    // Adding the data to the table model
            }
        } catch (FileNotFoundException e) {  // Catching file not found exception
            JOptionPane.showMessageDialog(this, "User data file not found.", "Error", JOptionPane.ERROR_MESSAGE);   // Displaying an error message if the file is not found
        }
    }

    public void addNewUser(String[] data) {  // Method to add a new user to the table
        tableModel.addRow(data);    // Adding the data to the table model
        saveUsers();    // Saving the user data to the file
    }

    public void updateUser(String[] data) { // Method to update a user in the table
        int row = userTable.getSelectedRow();   // Getting the selected row
        if (row != -1) {    // Checking if a row is selected
            for (int i = 0; i < data.length; i++) {   // Looping through the data
                tableModel.setValueAt(data[i], row, i);   // Setting the value of the cell in the table model
            }
            saveUsers();   // Saving the user data to the file
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {    // Overriding actionPerformed method
        if (e.getSource() == addButton) {   // Checking if the add button is clicked
            new UserDetailForm(this, null);    // Creating a new UserDataManager object
        } else if (e.getSource() == updateButton) {   // Checking if the update button is clicked
            int row = userTable.getSelectedRow();   // Getting the selected row
            if (row != -1) {    // Checking if a row is selected
                String[] data = new String[tableModel.getColumnCount()];    // Creating an array to store the data
                for (int i = 0; i < data.length; i++) {  // Looping through the data
                    data[i] = tableModel.getValueAt(row, i).toString();   // Getting the value of the cell
                }
                new UserDetailForm(this, data); // Creating a new UserDataManager object with data
            }
        } else if (e.getSource() == deleteButton) {  // Checking if the delete button is clicked
            int row = userTable.getSelectedRow();   // Getting the selected row
            if (row != -1) {    // Checking if a row is selected
                if (JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {    // Displaying a confirmation dialog for deletion 
                    tableModel.removeRow(row);  // Removing the row from the table model
                    saveUsers();    // Saving the user data to the file
                } 
            } else {    // If no row is selected
                JOptionPane.showMessageDialog(this, "No user selected.", "Error", JOptionPane.ERROR_MESSAGE);   // Displaying an error message
            }
        } else if (e.getSource() == refreshButton) {    // Checking if the refresh button is clicked
            loadUsers();    // Loading user data from file to the table
        } else if (e.getSource() == backButton) {   // Checking if the back button is clicked
            this.dispose(); // Disposing the current frame
            adminDashboard.setVisible(true);    // Setting the admin dashboard frame visible
        }
    }

    private void saveUsers() {  // Method to save user data to the file
        try (PrintWriter out = new PrintWriter(new FileWriter("database/UserData.txt"))) {  // Creating a PrintWriter object to write user data to the file
            for (int i = 0; i < tableModel.getRowCount(); i++) {    // Looping through the rows of the table model
                for (int j = 0; j < tableModel.getColumnCount(); j++) { // Looping through the columns of the table model
                    out.print(tableModel.getValueAt(i, j) + (j < tableModel.getColumnCount() - 1 ? " $ " : "\n"));    // Writing the data to the file with delimiter 
                }
            }
        } catch (IOException ex) {  // Catching IO exception 
            JOptionPane.showMessageDialog(this, "Failed to save user data.", "Error", JOptionPane.ERROR_MESSAGE);   // Displaying an error message
        }
    }

    public static void main(String[] args) {    // Main method
        new UserManager(null);  // Creating a new UserDataManager object with null parameter to test the functionality of the class
    }
}
