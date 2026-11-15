package controller;

import javax.swing.JOptionPane;
import dao.UserDAO;
import model.User;
import java.sql.SQLException;
import view.LoginFrame;
import view.DashboardFrame;

public class LoginController {
    private LoginFrame loginView;
    private UserDAO userDAO = new UserDAO();

    public LoginController(LoginFrame loginView) {
        this.loginView = loginView;

        loginView.getBtnLogin().addActionListener(e -> {
            login();
        });
    }

    private void login() {

        String username = loginView.getUsername();
        String password = loginView.getPassword();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    loginView,
                    "Username dan password wajib diisi!");
            return;
        }

        try {

            User user = userDAO.authenticate(username, password);

            if (user != null) {

                JOptionPane.showMessageDialog(
                        loginView,
                        "Login berhasil! Selamat datang, " + user.getFullName());

                loginView.dispose();

                DashboardFrame dashboard = new DashboardFrame(user.getUsername());

                dashboard.setVisible(true);

            } else {

                JOptionPane.showMessageDialog(
                        loginView,
                        "Username atau password salah!");
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    loginView,
                    "Terjadi kesalahan koneksi database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
