package applicationpackage; // Package containing the main classes of the application

// Importing the necessary classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Admin Dashboard class to display the dashboard for the admin user after successful login 
public class AdminDashboard extends JFrame implements ActionListener {
    // Declaring the necessary components
    private JButton manageUsersButton, manageApartmentsButton, logoutButton, exitButton;
    private JLabel welcomeLabel;

    // Constructor to initialize the dashboard
    public AdminDashboard(String name) {
        setTitle("Admin Dashboard");
        setSize(500, 500);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.LIGHT_GRAY);
    
        // Setting focus on the window
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                SwingUtilities.invokeLater(() -> {
                    getContentPane().requestFocusInWindow();
                });
            }
        });
    
        // Displaying the welcome message
        welcomeLabel = new JLabel("Welcome, " + name + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
    
        // Adding panel components to the window
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(welcomeLabel);
        panel.setBackground(Color.LIGHT_GRAY);
    
        // Adding buttons to the window
        manageUsersButton = new JButton("Manage Users");
        manageApartmentsButton = new JButton("Manage Apartments");
        logoutButton = new JButton("Logout");
        exitButton = new JButton("Exit");
    
        // Setting font for the buttons
        Font buttonFont = new Font("Georgia", Font.PLAIN, 18);
        manageUsersButton.setFont(buttonFont);
        manageApartmentsButton.setFont(buttonFont);
        logoutButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);
    
        // Setting bounds for the components
        panel.setBounds(0, 0, 500, 100);
        manageApartmentsButton.setBounds(150, 150, 200, 50);
        manageUsersButton.setBounds(175, 220, 150, 50);
        logoutButton.setBounds(200, 290, 100, 50);
        exitButton.setBounds(215, 360, 70, 50);
    
        // Adding components to the window
        add(panel);
        add(manageApartmentsButton);
        add(manageUsersButton);
        add(logoutButton);
        add(exitButton);
    
        // Adding action listeners to the buttons
        manageUsersButton.addActionListener(this);
        manageApartmentsButton.addActionListener(this);
        logoutButton.addActionListener(this);
        exitButton.addActionListener(this);
    
        // Setting the window to be visible
        setVisible(true);
    }

    // Overriding the actinPerformed method to handle the button clicks on the window
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

    // Main method to test the AdminDashboard class
    public static void main(String[] args) {
        new AdminDashboard("Sazid R Khan");
    }
}
