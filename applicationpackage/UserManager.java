package applicationpackage; // Package containing the classes of the application

// Importing necessary packages
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

// UserManager class to provide the user management functionality
public class UserManager extends JFrame implements ActionListener {
    // Declaring the necessary components
    private JTable userTable;
    private JButton addButton, updateButton, deleteButton, refreshButton, backButton;
    private DefaultTableModel tableModel;
    private AdminDashboard adminDashboard;
    private final String dataFilePath = "database/UserData.txt";

    // Constructor to initialize the components
    public UserManager(AdminDashboard adminDashboard) {
        // Initializing the components
        this.adminDashboard = adminDashboard;
    
        // Setting window properties
        setTitle("User Manager");
        setSize(800, 500);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setBackground(Color.LIGHT_GRAY);
    
        // Adding window listener to the frame to request focus when opened and dispose when closed
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
    
        // Setting the table model for the users
        String[] columnNames = {"UID", "Name", "Username", "Age", "Gender", "NID/BID No.", "Password"};
        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        userTable = new JTable(tableModel);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userTable.setFont(new Font("Arial", Font.PLAIN, 14));
        adjustColumnWidth(userTable);
        setTableAlignment(userTable);
        add(userTable, BorderLayout.CENTER);
        loadUsers();
    
        // Adding scroll pane to the table
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);
        add(scrollPane, BorderLayout.CENTER);
    
        // Adding buttons panel to the window
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        addButton = new JButton("Add User");
        updateButton = new JButton("Update User");
        deleteButton = new JButton("Delete User");
        refreshButton = new JButton("Refresh List");
        backButton = new JButton("Go Back");
    
        // Adding action listeners to the buttons
        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        refreshButton.addActionListener(this);
        backButton.addActionListener(this);
    
        // Adding buttons to the panel
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);
    
        add(buttonPanel, BorderLayout.SOUTH);   // Adding the button panel to the window
    
        setVisible(true);   // Making the window visible
    }

    // Adjusting the column width of the table
    private void adjustColumnWidth(JTable table){
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(130);
        table.getColumnModel().getColumn(3).setPreferredWidth(40);
        table.getColumnModel().getColumn(4).setPreferredWidth(60);
        table.getColumnModel().getColumn(5).setPreferredWidth(160);
        table.getColumnModel().getColumn(6).setPreferredWidth(200);
    }

    // Setting the alignment of the table
    private void setTableAlignment(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    // Loading the users from the file
    private void loadUsers() {
        tableModel.setRowCount(0);
        try (Scanner scanner = new Scanner(new File(dataFilePath))) {
            while (scanner.hasNextLine()) { // Looping through the data to add it to the table
                String line = scanner.nextLine();
                String[] data = line.split(" \\$ ");
                tableModel.addRow(data);
            }
        } catch (FileNotFoundException e) { // Catching the file not found exception
            JOptionPane.showMessageDialog(this, "User data file not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Adding a new user to the table
    public void addNewUser(String[] data) {
        tableModel.addRow(data);
        saveUsers();
    }

    // Updating an existing user in the table
    public void updateUser(String[] data) {
        int row = userTable.getSelectedRow();
        if (row != -1) {
            for (int i = 0; i < data.length; i++) {   // Looping through the data 
                tableModel.setValueAt(data[i], row, i);   // Setting the data in the table
            }
            saveUsers();
        }
    }

    // Saving the users to the file
    private void saveUsers() {
        try (PrintWriter out = new PrintWriter(new FileWriter(dataFilePath))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {    // Looping through the rows of the table
                for (int j = 0; j < tableModel.getColumnCount(); j++) {  // Looping through the columns of the table
                    out.print(tableModel.getValueAt(i, j) + (j < tableModel.getColumnCount() - 1 ? " $ " : "\n"));  // Writing the data to the file
                }
            }
        } catch (IOException ex) {  // Catching the IO exception
            JOptionPane.showMessageDialog(this, "Failed to save user data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Getting the next user ID
    public int getNextUserID() {
        int maxID = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {    // Looping through the rows of the table
            int currentId = Integer.parseInt(tableModel.getValueAt(i, 0).toString());
            if (currentId > maxID) {
                maxID = currentId;  // Getting the maximum ID
            }
        }
        return maxID + 1;
    }

    // Overriding the actionPerformed method of the ActionListener interface to handle the action events
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {   // Opening the user detail form to when the add button is clicked
            new UserDetailForm(this, null);
        } else if (e.getSource() == updateButton) { // Opening the user detail form when the update button is clicked
            int row = userTable.getSelectedRow();
            if (row != -1) {
                if (JOptionPane.showConfirmDialog(this, "Are you sure you want to update this user?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    String[] data = new String[tableModel.getColumnCount()];
                    for (int i = 0; i < data.length; i++) {  // Looping through the data to collect the user data
                        data[i] = tableModel.getValueAt(row, i).toString();
                    }
                    new UserDetailForm(this, data);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No user selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == deleteButton) { // Deleting the user when the delete button is clicked
            int row = userTable.getSelectedRow();
            if (row != -1) {
                if (JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    tableModel.removeRow(row);
                    saveUsers();
                }
            } else {
                JOptionPane.showMessageDialog(this, "No user selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == refreshButton) {    // Refreshing the user list when the refresh button is clicked
            loadUsers();
        } else if (e.getSource() == backButton) {   // Going back to the admin dashboard when the back button is clicked
            this.dispose();
            adminDashboard.setVisible(true);
        }
    }

    // Main method to test the User Manager class
    public static void main(String[] args) {
        new UserManager(null);
    }
}
