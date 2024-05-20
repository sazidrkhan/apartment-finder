package applicationpackage; // Package containing the classes of the application

// Importing necessary classes
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

// UserDashboard class to display the user dashboard
public class UserDashboard extends JFrame {
    // Declaring the instance variables
    private JList<ApartmentItem> apartmentList;
    private DefaultListModel<ApartmentItem> listModel;

    // Constructor to initialize the instance variables
    public UserDashboard(String name, UserLogin userLogin) {
        setTitle("User Dashboard");
        setSize(600, 800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    
        // Adding the window listener to the frame to request focus in window
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                SwingUtilities.invokeLater(() -> {
                    getContentPane().requestFocusInWindow();
                });
            }
        });
    
        // Setting the properties for the header panel
        JPanel headerPanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome, " + name + "!");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.add(welcomeLabel);
    
        // Adding the logout button to the header panel
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {   // Checking if the user wants to logout
                userLogin.setVisible(true);
                dispose();
            }
        });
        headerPanel.add(logoutButton, BorderLayout.WEST);   // Adding the logout button to the header panel
        
    
        // Initializing the list model and loading the apartment details
        listModel = new DefaultListModel<>();
        loadApartmentDetails();
        apartmentList = new JList<>(listModel);
        apartmentList.setCellRenderer(new ApartmentListCellRenderer());
        apartmentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        apartmentList.addListSelectionListener(this::apartmentSelected);
    
        // Adding the scroll pane to the frame
        JScrollPane scrollPane = new JScrollPane(apartmentList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);
    
        // Adding the components to the frame
        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    
        // Setting the frame properties
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Loading the apartment details
    private void loadApartmentDetails() {
        try (Scanner scanner = new Scanner(new File("database/ApartmentData.txt"))) {
            while (scanner.hasNextLine()) { // Reading the file line by line
                String line = scanner.nextLine();
                String[] data = line.split(" \\$ ");
                if (data.length >= 6) { // Checking if the data is valid
                    ImageIcon icon = new ImageIcon(new ImageIcon(data[1]).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                    listModel.addElement(new ApartmentItem(data[0], data[2], data[3], data[4], data[5], data[6], icon));    // Adding the apartment item to the list model and passing the data to the ApartmentItem class to store the apartment details
                }
            }
        } catch (FileNotFoundException e) {  // Catching the FileNotFoundException if any
            JOptionPane.showMessageDialog(this, "Apartment data file not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Clearing the selection of the apartment list
    public void clearSelection() {
        apartmentList.clearSelection();
    }

    // Opening the selected apartment to view the details
    private void apartmentSelected(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && !apartmentList.isSelectionEmpty()) {
            ApartmentItem selected = apartmentList.getSelectedValue();
            if (selected != null) {
                new ApartmentDetailDialog(this, selected);
            }
        }
    }

    // Refreshing the apartment list
    public void refreshApartmentList() {
        listModel.clear();
        loadApartmentDetails(); 
        repaint();  // Refreshing the frame
    }

    // Setting the apartment item to the list model
    class ApartmentListCellRenderer extends DefaultListCellRenderer {
        // Overriding the getListCellRendererComponent method to customize the list cell renderer
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {   // Customizing the list cell renderer
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            ApartmentItem item = (ApartmentItem) value;
            setText("<html>Address: " + item.address + "<br/>Price: " + item.price + "<br/>Status: " + item.status + "</html>");
            setFont(new Font("Georgia", Font.PLAIN, 24));
            setPreferredSize(new Dimension(200, 205));
        
            // Setting the icon, background, and foreground color
            setIcon(item.image);
            if (isSelected) {
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            } else {
                setBackground(Color.LIGHT_GRAY);
                setForeground(Color.BLACK);
            }
            setOpaque(true);
            return this;
        }
    }

    // Main method to test the UserDashboard class
    public static void main(String[] args) {
        new UserDashboard("John Doe", null);
    }
}
