package applicationpackage;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class UserDashboard extends JFrame {
    private JList<ApartmentItem> apartmentList;
    private DefaultListModel<ApartmentItem> listModel;

    public UserDashboard(String name, UserLogin userLogin) {
        setTitle("User Dashboard");
        setSize(600, 800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                SwingUtilities.invokeLater(() -> {
                    getContentPane().requestFocusInWindow();
                });
            }
        });
        

        JPanel headerPanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome, " + name + "!");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.add(welcomeLabel);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                userLogin.setVisible(true);
                dispose();
            }
        });
        headerPanel.add(logoutButton, BorderLayout.WEST);
        

        listModel = new DefaultListModel<>();
        loadApartmentDetails();
        apartmentList = new JList<>(listModel);
        apartmentList.setCellRenderer(new ApartmentListCellRenderer());
        apartmentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        apartmentList.addListSelectionListener(this::apartmentSelected);

        JScrollPane scrollPane = new JScrollPane(apartmentList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);

        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadApartmentDetails() {
        try (Scanner scanner = new Scanner(new File("database/ApartmentData.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" \\$ ");
                if (data.length >= 6) {
                    ImageIcon icon = new ImageIcon(new ImageIcon(data[1]).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                    listModel.addElement(new ApartmentItem(data[0], data[2], data[3], data[4], data[5], data[6], icon));
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Apartment data file not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clearSelection() {
        apartmentList.clearSelection();
    }

    private void apartmentSelected(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && !apartmentList.isSelectionEmpty()) {
            ApartmentItem selected = apartmentList.getSelectedValue();
            if (selected != null) {
                new ApartmentDetailDialog(this, selected);
            }
        }
    }

    public void refreshApartmentList() {
        listModel.clear();
        loadApartmentDetails(); 
        repaint();
    }


    class ApartmentListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            ApartmentItem item = (ApartmentItem) value;
            setText("<html>Address: " + item.address + "<br/>Price: " + item.price + "<br/>Status: " + item.status + "</html>");
            setFont(new Font("Georgia", Font.PLAIN, 24));
            setPreferredSize(new Dimension(200, 205));

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

    public static void main(String[] args) {
        new UserDashboard("John Doe", null);
    }
}
