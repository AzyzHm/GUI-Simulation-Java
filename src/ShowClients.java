import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ShowClients extends JFrame {
    public ShowClients() {
        setTitle("Users");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        ImageIcon Show_User = new ImageIcon("ShowUser.png");
        setIconImage(Show_User.getImage());
        setSize(800, 500);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel specialtyLabel = new JLabel("Select by Speciality:");
        Font labelFont = specialtyLabel.getFont();
        specialtyLabel.setFont(new Font(labelFont.getName(), Font.ITALIC, 18));
        specialtyLabel.setBounds(20, 20, 180, 30);

        String[] specialties = {"", "IM-Fondamentale", "IM-Gaming", "Big-Data"};
        JComboBox<String> specialtyComboBox = new JComboBox<>(specialties);
        specialtyComboBox.setFont(new Font(labelFont.getName(), Font.PLAIN, 16));
        specialtyComboBox.setBounds(190, 20, 180, 30);

        JLabel clubLabel = new JLabel("Select by Club:");
        clubLabel.setFont(new Font(labelFont.getName(), Font.ITALIC, 18));
        clubLabel.setBounds(420, 20, 150, 30);

        String[] clubs = {"", "CyberSecurity", "Developpement Web", "IT"};
        JComboBox<String> clubComboBox = new JComboBox<>(clubs);
        clubComboBox.setFont(new Font(labelFont.getName(), Font.PLAIN, 16));
        clubComboBox.setBounds(550, 20, 180, 30);

        JButton Back = new JButton("Back to AdminSpace");
        Back.setBounds(300, 385, 200, 40);
        Back.setFont(new Font("Arial", Font.ITALIC, 16));

        JButton showClientsButton = new JButton("Show Clients");
        showClientsButton.setBounds(300, 70, 200, 40);
        showClientsButton.setFont(new Font("Arial", Font.BOLD, 18));

        add(specialtyLabel);
        add(specialtyComboBox);
        add(clubLabel);
        add(clubComboBox);
        add(showClientsButton);
        add(Back);

        JTable clientTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(clientTable);
        scrollPane.setBounds(10, 125, 760, 250);
        add(scrollPane);

        showClientsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedSpecialty = (String) specialtyComboBox.getSelectedItem();
                String selectedClub = (String) clubComboBox.getSelectedItem();
                try {
                    Connection connection = DatabaseConnection.getConnection();
                    String selectQuery;
                    if (selectedSpecialty.isEmpty() && selectedClub.isEmpty()) {
                        selectQuery = "SELECT * FROM client";
                    } else if (!selectedSpecialty.isEmpty() && selectedClub.isEmpty()) {
                        selectQuery = "SELECT * FROM client WHERE spec = ?";
                    } else if (selectedSpecialty.isEmpty() && !selectedClub.isEmpty()) {
                        selectQuery = "SELECT * FROM client WHERE club = ?";
                    } else {
                        selectQuery = "SELECT * FROM client WHERE spec = ? AND club = ?";
                    }

                    PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                    if (!selectedSpecialty.isEmpty() && selectedClub.isEmpty()) {
                        preparedStatement.setString(1, selectedSpecialty);
                    } else if (selectedSpecialty.isEmpty() && !selectedClub.isEmpty()) {
                        preparedStatement.setString(1, selectedClub);
                    } else if (!selectedSpecialty.isEmpty() && !selectedClub.isEmpty()) {
                        preparedStatement.setString(1, selectedSpecialty);
                        preparedStatement.setString(2, selectedClub);
                    }

                    ResultSet resultSet = preparedStatement.executeQuery();

                    DefaultTableModel tableModel = new DefaultTableModel();
                    tableModel.addColumn("Last Name");
                    tableModel.addColumn("Name");
                    tableModel.addColumn("Speciality");
                    tableModel.addColumn("Club");

                    while (resultSet.next()) {
                        String nom = resultSet.getString("nom");
                        String prénom = resultSet.getString("prénom");
                        String spec = resultSet.getString("spec");
                        String club = resultSet.getString("club");
                        tableModel.addRow(new Object[]{nom, prénom, spec, club});
                    }

                    clientTable.setModel(tableModel);
                    revalidate();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                dispose();
                new AdminSpace();
            }
        });

        setVisible(true);
    }}