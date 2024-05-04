package applicationpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This class extends JFrame and implements ActionListener to handle GUI and events
public class ApartmentFinder extends JFrame implements ActionListener {
    // Declare components
    private JLabel welcomeText/*, imageLabel*/;
    private JButton startButton;    // Start button
    // private ImageIcon background;

    // Constructor
    public ApartmentFinder() {
        // Initialize components
        welcomeText = new JLabel("Welcome to Apartment Finder!");  // Welcome text label
        startButton = new JButton("Let's Get Started...");  // Text for start button

        // background = new ImageIcon("image\\Background 1.jpg");
        // imageLabel = new JLabel(background);

        // Welcome text
        Font message = new Font("Times New Roman", Font.BOLD, 30);
        welcomeText.setFont(message);   // Set font for the welcome text
        
        // Start button
        Font buttonFont = new Font("Georgia", Font.BOLD, 14);
        startButton.setFont(buttonFont);    // Set font for the start button
        startButton.setBackground(Color.WHITE); // Set background color for the start button

        // Set bounds for the components
        welcomeText.setBounds(200, 100, 700, 50);   // Set bounds for the welcome text
        startButton.setBounds(300, 300, 180, 30);   // Set bounds for the start button
        
        // imageLabel.setBounds(100, 120, background.getIconWidth(), background.getIconHeight());

        // Add components to the frame
        add(welcomeText);   // Add the welcome text to the frame
        add(startButton);   // Add the start button to the frame 
        
        // add(imageLabel);

        // Set properties for the frame window
        setTitle("Apartment Finder");   // Window title
        setSize(800, 600);  // Window size 
        setLocationRelativeTo(null);    // Set the window to the center of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Close the window when the close button is clicked
        setLayout(null);    // Set layout to null for absolute positioning
        setVisible(true);   // Set the frame to be visible to the user
        getContentPane().setBackground(Color.LIGHT_GRAY);   // Set background color for the frame
    }

    @Override   // Override the actionPerformed method
    public void actionPerformed(ActionEvent e) {
        // If the start button is clicked
        if (e.getSource() == startButton) {
            setVisible(false);  // Hide the current window
            new Login();    // Open the next window
            dispose();  // Close the current window
        }
    }

    public static void main(String[] args) {
        new ApartmentFinder();  // Create an object of the ApartmentFinder class to display the frame
    }
}