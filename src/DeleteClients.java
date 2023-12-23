import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteClients extends JFrame {
    public DeleteClients() {
        setTitle("Delete Clients");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        ImageIcon deleteIcon = new ImageIcon("C:/Users/medth/Desktop/Notebook/Java/Interface_Simulation/icons/DeleteUser.png");
        setIconImage(deleteIcon.getImage());
        setSize(400, 300);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel nameLabel = new JLabel("Client's Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        nameLabel.setBounds(50, 50, 200, 30);

        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        nameField.setBounds(180, 50, 150, 30);

        JLabel lastNameLabel = new JLabel("Client's Last Name:");
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        lastNameLabel.setBounds(25, 100, 200, 30);

        JTextField lastNameField = new JTextField();
        lastNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        lastNameField.setBounds(180, 100, 150, 30);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 20));
        deleteButton.setBounds(220, 160, 100, 40);

        JButton Back = new JButton("Back to AdminSpace");
        Back.setBounds(25,160, 180, 40);
        Back.setFont(new Font("Arial", Font.ITALIC, 14));

        
        add(nameLabel);
        add(nameField);
        add(lastNameLabel);
        add(lastNameField);
        add(deleteButton);
        add(Back);


        Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                dispose();
                new AdminSpace();
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String lastName = lastNameField.getText();
                if (name.isEmpty() || lastName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the client's name and last name!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this client?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        Connection connection = DatabaseConnection.getConnection();
                        String deleteQuery = "DELETE FROM client WHERE nom = ? AND prénom = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                        preparedStatement.setString(1, lastName);
                        preparedStatement.setString(2, name);
                        int rowsDeleted = preparedStatement.executeUpdate();

                        if (rowsDeleted > 0) {
                            JOptionPane.showMessageDialog(null, "Client deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            nameField.setText("");
                            lastNameField.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "No client found with that name and last name!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        setVisible(true);
    }}
