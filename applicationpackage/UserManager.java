package applicationpackage;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class UserManager extends JFrame implements ActionListener {
    private JTable userTable;
    private JButton addButton, updateButton, deleteButton, refreshButton, backButton;
    private DefaultTableModel tableModel;
    private AdminDashboard adminDashboard;

    public UserManager(AdminDashboard adminDashboard) {
        this.adminDashboard = adminDashboard;

        setTitle("User Manager");
        setSize(800, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        String[] columnNames = {"Name", "Username", "Age", "Gender", "NID/BID No.", "Password"};
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
        loadUsers();  // Load user data from file

        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        addButton = new JButton("Add User");
        updateButton = new JButton("Update User");
        deleteButton = new JButton("Delete User");
        refreshButton = new JButton("Refresh List");
        backButton = new JButton("Go Back");

        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        refreshButton.addActionListener(this);
        backButton.addActionListener(this);

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

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
