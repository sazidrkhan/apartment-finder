package applicationpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminDashboard extends JFrame implements ActionListener {
    private JButton manageUsersButton, manageApartmentsButton, logoutButton, exitButton;
    private JLabel welcomeLabel;
    
    public AdminDashboard(String name) {
        setTitle("Admin Dashboard");
        setSize(500, 500);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                SwingUtilities.invokeLater(() -> {
                    getContentPane().requestFocusInWindow();
                });
            }
        });
        

        welcomeLabel = new JLabel("Welcome, " + name + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(welcomeLabel);
        panel.setBackground(Color.LIGHT_GRAY);
    
        manageUsersButton = new JButton("Manage Users");
        manageApartmentsButton = new JButton("Manage Apartments");
        logoutButton = new JButton("Logout");
        exitButton = new JButton("Exit");
    
        Font buttonFont = new Font("Georgia", Font.PLAIN, 18);
        manageUsersButton.setFont(buttonFont);
        manageApartmentsButton.setFont(buttonFont);
        logoutButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);
    
        panel.setBounds(0, 0, 500, 100);
        manageApartmentsButton.setBounds(150, 150, 200, 50);
        manageUsersButton.setBounds(175, 220, 150, 50);
        logoutButton.setBounds(200, 290, 100, 50);
        exitButton.setBounds(215, 360, 70, 50);
    
        add(panel);
        add(manageApartmentsButton);
        add(manageUsersButton);
        add(logoutButton);
        add(exitButton);
    
        manageUsersButton.addActionListener(this);
        manageApartmentsButton.addActionListener(this);
        logoutButton.addActionListener(this);
        exitButton.addActionListener(this);
    
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manageUsersButton) {
            new UserManager(this);
            setVisible(false);
        } else if (e.getSource() == manageApartmentsButton) {
            new ApartmentManager(this);
            setVisible(false);
        } else if (e.getSource() == logoutButton) {
            new AdminLogin(null);
            dispose();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new AdminDashboard("Sazid R Khan");
    }
}
