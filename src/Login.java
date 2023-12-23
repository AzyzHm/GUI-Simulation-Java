import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    public Login() {
        setTitle("Admin Login");
        ImageIcon Login = new ImageIcon("Login.png");
        setIconImage(Login.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(400, 250);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameLabel.setBounds(50, 50, 100, 30);
        JTextField usernameField = new JTextField();
        usernameField.setBounds(160, 50, 200, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setBounds(50, 100, 100, 30);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(160, 100, 200, 30);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBounds(75, 150, 100, 40);

        JButton Cancel = new JButton("Cancel");
        Cancel.setFont(new Font("Arial", Font.ITALIC, 16));
        Cancel.setBounds(200,150,100,40);

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(Cancel);

        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("Admin") && password.equals("Admin")) {
                    dispose();
                    new AdminSpace();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        Cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                dispose();
                new DashBoard();
            }
        });
    }}

