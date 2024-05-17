package applicationpackage; // Package declaration for the class

// Importing necessary libraries for the class
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// This class extends JFrame and implements ActionListener to handle GUI and events
public class ApartmentFinder extends JFrame implements ActionListener {
    // Declaring components
    private JLabel welcomeText;
    private JButton startButton;

    // Constructor to initialize the frame and components
    public ApartmentFinder() {
        // Initializing components
        welcomeText = new JLabel("Welcome to Apartment Finder!");  // Setting the welcome text label
        startButton = new JButton("Let's Get Started...");  // Setting the text for the start button

        Font message = new Font("Times New Roman", Font.BOLD, 30);  // Setting the font for the welcome text
        welcomeText.setFont(message);   // Applying the font to the welcome text

        Font buttonFont = new Font("Georgia", Font.BOLD, 14);   // Setting the font for the start button
        startButton.setFont(buttonFont);    // Applying the font to the start button

        // Setting bounds for the components
        welcomeText.setBounds(40, 100, 700, 50);   // Setting bounds for the welcome text
        startButton.setBounds(150, 300, 180, 30);   // Setting bounds for the start button

        add(welcomeText);   // Adding the welcome text to the window
        add(startButton);   // Adding the start button to the window 

        // Setting properties for the window
        setTitle("Apartment Finder");   // Setting the window title
        setSize(500, 500);  // Setting the window size 
        setLocationRelativeTo(null);    // Setting the window to the center of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Closing the window when the close button is being clicked
        setLayout(null);    // Setting layout to null for absolute positioning
        setVisible(true);   // Making the window visible to the user
        getContentPane().setBackground(Color.LIGHT_GRAY);   // Setting background color for the window

        startButton.addActionListener(this);    // Adding action listener to the start button
    }

    // Overriding the actionPerformed method
    @Override   
    public void actionPerformed(ActionEvent e) {
        // If the start button is being clicked
        if (e.getSource() == startButton) {
            new UserLogin(this);    // Opening the next window
            dispose();  // Closing the current window
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        new ApartmentFinder(); // Creating an object of the ApartmentFinder class to display the frame
    }
}

