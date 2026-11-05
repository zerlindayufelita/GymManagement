package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginView() {
        setTitle("Gym Management - Login");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // tampil di tengah layar

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        btnLogin = new JButton("Login");

        panel.add(new JLabel("Username"));
        panel.add(txtUsername);
        panel.add(new JLabel("Password"));
        panel.add(txtPassword);
        panel.add(new JLabel());
        panel.add(btnLogin);

        add(panel);
    }

    // Getter agar controller bisa membaca input & memasang aksi
    public String getUsername() {
        return txtUsername.getText();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }
}