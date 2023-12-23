import javax.swing.*;
import java.awt.*;

public class DashBoard extends JFrame {
    public DashBoard() {
        setTitle("DashBoard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(600, 450);

        // Setting icon for the frame
        ImageIcon icon = new ImageIcon("DashBord.png");
        setIconImage(icon.getImage());

        getContentPane().setBackground(Color.LIGHT_GRAY); // BackGround Color
        setLocationRelativeTo(null); // Center the window
        setLayout(null); // Set layout to null

        ImageIcon admin = new ImageIcon("AdminSpace.png");
        ImageIcon client = new ImageIcon("AddClient.png");

        // Resize the images
        Image adminImage = admin.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        Image clientImage = client.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);

        // Create new ImageIcons with the resized images
        ImageIcon resizedAdminIcon = new ImageIcon(adminImage);
        ImageIcon resizedClientIcon = new ImageIcon(clientImage);

        JButton clientButton = new JButton("Add a Client");
        clientButton.setIcon(resizedClientIcon);
        JButton adminButton = new JButton("Admin Space");
        adminButton.setIcon(resizedAdminIcon);

        clientButton.setBounds(150, 100, 300, 75); // Set bounds for client button
        adminButton.setBounds(150, 200, 300, 75); // Set bounds for admin button

        clientButton.setFont(new Font("Arial", Font.BOLD, 20));
        adminButton.setFont(new Font("Arial", Font.BOLD, 20));

        clientButton.addActionListener(e -> {
            dispose();
            new AddClient();
        });

        adminButton.addActionListener(e -> {
            dispose();
            new Login();
        });

        add(clientButton); // Add client button
        add(adminButton); // Add admin button

        setVisible(true);
    }}

