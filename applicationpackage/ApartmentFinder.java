package applicationpackage; // Package containing the main classes of the application

// Importing the necessary classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Apartment Finder class to display the welcome message
public class ApartmentFinder extends JFrame implements ActionListener {
    // Declaring the necessary components
    private JLabel welcomeText;
    private JButton startButton;

    // Constructor to initialize the components
    public ApartmentFinder() {
        // Initializing the components
        welcomeText = new JLabel("Welcome to Apartment Finder!");
        startButton = new JButton("Let's Get Started...");
    
        // Setting the font for the components
        Font message = new Font("Times New Roman", Font.BOLD, 30);
        welcomeText.setFont(message);
    
        // Setting the font for the button
        Font buttonFont = new Font("Georgia", Font.BOLD, 14);
        startButton.setFont(buttonFont);
    
        // Setting the bounds for the components
        welcomeText.setBounds(40, 100, 700, 50);
        startButton.setBounds(150, 250, 200, 30);
    
        // Adding an action listener to the button
        startButton.addActionListener(this);
    
        // Adding the components to the frame
        add(welcomeText);
        add(startButton);
    
        // Setting the properties of the frame
        setTitle("Apartment Finder");
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        getContentPane().setBackground(Color.LIGHT_GRAY);
    
        // Setting the focus on the window
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                SwingUtilities.invokeLater(() -> {
                    getContentPane().requestFocusInWindow();
                });
            }
        });
    }

    // Overriding the actionPerformed method to handle the button click
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            new UserLogin(null);    // Creating a new UserLogin object to display the login screen
            dispose();
        }
    }

    // Main method to test the Apartment Finder class
    public static void main(String[] args) {
        new ApartmentFinder();
    }
}
