package controller;

import javax.swing.JOptionPane;

import view.LoginFrame;
import view.DashboardFrame;

public class LoginController {
    private LoginFrame loginView;

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

        if (username.equals("admin") &&
                password.equals("admin123")) {

            JOptionPane.showMessageDialog(loginView, "Login berhasil");

            loginView.dispose();

            DashboardFrame dashboard = new DashboardFrame(username);
            dashboard.setVisible(true);

        } else {

            JOptionPane.showMessageDialog(loginView, "Username atau password salah!");

        }

    }
}
