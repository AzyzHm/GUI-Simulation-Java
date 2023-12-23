import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminSpace extends JFrame {
    public AdminSpace() {
        setTitle("Admin Space");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon AdminSpace = new ImageIcon("AdminSpace.png");
        setIconImage(AdminSpace.getImage());
        setResizable(false);
        setSize(400, 300);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLayout(null);
        setLocationRelativeTo(null);

        ImageIcon ShowClt = new ImageIcon("ShowUser.png");
        ImageIcon DeleteClt = new ImageIcon("DeleteUser.png");

        // Resize the images
        Image SC = ShowClt.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        Image DC = DeleteClt.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);

        // Create new ImageIcons with the resized images
        ImageIcon resizedSC = new ImageIcon(SC);
        ImageIcon resizedDC = new ImageIcon(DC);
        
        JButton showUsers = new JButton("Show Clients");
        showUsers.setFont(new Font("Arial", Font.BOLD, 24));
        showUsers.setIcon(resizedSC);
        showUsers.setBounds(50, 30, 300, 50);

        JButton deleteUserButton = new JButton("Delete a Client");
        deleteUserButton.setFont(new Font("Arial", Font.BOLD, 24));
        deleteUserButton.setIcon(resizedDC);
        deleteUserButton.setBounds(50, 100, 300, 50);

        JButton returnButton = new JButton("Return to DashBoard");
        returnButton.setFont(new Font("Arial", Font.ITALIC, 20));
        returnButton.setBounds(50, 170, 300, 50);

        add(showUsers);
        add(deleteUserButton);
        add(returnButton);

        setVisible(true);

        showUsers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ShowClients();
            }
        });

        deleteUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DeleteClients();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DashBoard();
            }
        });
    }}

