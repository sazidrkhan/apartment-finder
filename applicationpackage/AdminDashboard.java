package applicationpackage;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame implements ActionListener {
    private JButton manageUsersButton, manageApartmentsButton, logoutButton, refreshButton;
    private JLabel welcomeLabel;

    public AdminDashboard(String adminName) {
        setTitle("Admin Dashboard");
        setSize(500, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Welcome label displays the admin's name
        welcomeLabel = new JLabel("Welcome, " + adminName + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("New ", Font.BOLD, 16));

        // Buttons
        manageUsersButton = new JButton("Manage Users");
        manageApartmentsButton = new JButton("Manage Apartments");
        logoutButton = new JButton("Logout");
        refreshButton = new JButton("Refresh");

        manageUsersButton.addActionListener(this);
        manageApartmentsButton.addActionListener(this);
        logoutButton.addActionListener(this);
        refreshButton.addActionListener(this);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10)); // Grid layout for spacing
        buttonPanel.add(manageUsersButton);
        buttonPanel.add(manageApartmentsButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(refreshButton);

        add(welcomeLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manageUsersButton) {
            new UserManager(this).setVisible(true);
        } else if (e.getSource() == manageApartmentsButton) {
            new ApartmentManager(this).setVisible(true);
        } else if (e.getSource() == logoutButton) {
            dispose(); // Or navigate back to the login screen
            new UserLogin(null).setVisible(true); // Assuming you want to show the login screen again
        } else if (e.getSource() == refreshButton) {
            // Refresh data or UI components
            JOptionPane.showMessageDialog(this, "Data refreshed!", "Refresh", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // For testing, a direct name string is provided
        new AdminDashboard("Admin Name");
    }
}
