import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddClient extends JFrame {
    private boolean isValidName(String text) {
        return text.matches("[a-zA-Z ]+") && text.trim().length() >= 4;
    }

    public AddClient() {
        setTitle("Add Client");
        ImageIcon client = new ImageIcon("AddClient.png");
        setIconImage(client.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(600,500);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLayout(null); // Using no layout manager
        setLocationRelativeTo(null);

        Font labelFont = new Font("Arial", Font.BOLD, 24);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        nameLabel.setBounds(50, 50, 120, 30);
        JTextField nameField = new JTextField();
        nameField.setFont(labelFont);
        nameField.setBounds(200, 50, 300, 30);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(labelFont);
        lastNameLabel.setBounds(50, 100, 140, 30);
        JTextField lastNameField = new JTextField();
        lastNameField.setFont(labelFont);
        lastNameField.setBounds(200, 100, 300, 30);

        JLabel specialtyLabel = new JLabel("Speciality:");
        specialtyLabel.setFont(labelFont);
        specialtyLabel.setBounds(50, 150, 150, 30);
        String[] specialties = {"Select a Speciality", "IM-Fondamentale", "IM-Gaming", "Big-Data"};
        JComboBox<String> specialtyComboBox = new JComboBox<>(specialties);
        specialtyComboBox.setBounds(200, 150, 300, 30);

        JLabel clubsLabel = new JLabel("Club:");
        clubsLabel.setFont(labelFont);
        clubsLabel.setBounds(50, 200, 120, 30);
        String[] clubs = {"Select a Club", "CyberSecurity", "Developpement Web", "IT"};
        JComboBox<String> clubsComboBox = new JComboBox<>(clubs);
        clubsComboBox.setBounds(200, 200, 300, 30);

        JButton submitButton = new JButton("Add");
        submitButton.setFont(labelFont);
        submitButton.setBounds(50, 300, 150, 50);
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(labelFont);
        resetButton.setBounds(350, 300, 150, 50);
        JButton backButton = new JButton("Back to Dashboard");
        backButton.setFont( new Font("Arial", Font.ITALIC, 20));
        backButton.setBounds(125,375,300,50);

        add(nameLabel);
        add(nameField);
        add(lastNameLabel);
        add(lastNameField);
        add(specialtyLabel);
        add(specialtyComboBox);
        add(clubsLabel);
        add(clubsComboBox);
        add(submitButton);
        add(resetButton);
        add(backButton);

        setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve input values from form fields
                String name = nameField.getText();
                String lastName = lastNameField.getText();
                String selectedSpecialty = (String) specialtyComboBox.getSelectedItem();
                String selectedClub = (String) clubsComboBox.getSelectedItem();

                // Check if any field is empty
                if (name.isEmpty() || lastName.isEmpty() || selectedSpecialty.equals("Select a Speciality") || selectedClub.equals("Select a Club")) {
                    // Display error if any field is empty
                    JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Stop further execution
                }

                // Validate name and last name for alphabetic characters and minimum length
                if (!isValidName(name) || !isValidName(lastName)) {
                // Display error for invalid name or last name format
                    JOptionPane.showMessageDialog(null, "Name and Last Name should contain only alphabetic characters and have a length of at least 4 characters!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                    }

                // Database insertion after successful validation
                try {
                    // Establish a database connection
                    Connection connection = DatabaseConnection.getConnection(); 

                    // Create and execute the SQL INSERT statement
                    String insertQuery = "INSERT INTO client (nom, prénom, spec, club) VALUES (?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, lastName);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, selectedSpecialty);
                    preparedStatement.setString(4, selectedClub);

                    int rowsInserted = preparedStatement.executeUpdate();

                    // Check if data insertion was successful
                    if (rowsInserted > 0) {
                        // Display success message and reset form fields
                        JOptionPane.showMessageDialog(null, "Data inserted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        nameField.setText("");
                        lastNameField.setText("");
                        specialtyComboBox.setSelectedIndex(0);
                        clubsComboBox.setSelectedIndex(0);
                        }

                    // Close the connection
                    connection.close();
}               catch (SQLIntegrityConstraintViolationException ex) {
                    // Handle duplicate entry error
                    JOptionPane.showMessageDialog(null, "Duplicate entry detected! This record already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                    // Additional actions can be performed based on the error
                    ex.printStackTrace();
}               catch (SQLException ex) {
                    ex.printStackTrace(); // Handle or log the exception
}
}
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                lastNameField.setText("");
                specialtyComboBox.setSelectedIndex(0);
                clubsComboBox.setSelectedIndex(0);
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DashBoard();
            }
        });
    }
}
