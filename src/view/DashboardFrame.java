package view;

import javax.swing.*;

import controller.LoginController;
import java.awt.*;

public class DashboardFrame extends JFrame {

        private CardLayout cardLayout;
        private JPanel contentPanel;

        public DashboardFrame(String username) {
                setTitle("Gym Management - Dashboard");
                setSize(1000, 600);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);

                // Sidebar
                JPanel sidebar = new JPanel();
                sidebar.setLayout(new GridLayout(6, 1, 5, 5));
                sidebar.setPreferredSize(new Dimension(180, 0));

                JButton dashboardButton = new JButton("Dashboard");
                JButton memberButton = new JButton("Member");
                JButton paketButton = new JButton("Paket");
                JButton pembayaranButton = new JButton("Pembayaran");
                JButton absensiButton = new JButton("Absensi");
                JButton logoutButton = new JButton("Logout");

                sidebar.add(dashboardButton);
                sidebar.add(memberButton);
                sidebar.add(paketButton);
                sidebar.add(pembayaranButton);
                sidebar.add(absensiButton);
                sidebar.add(logoutButton);

                // Content
                cardLayout = new CardLayout();
                contentPanel = new JPanel(cardLayout);

                contentPanel.add(
                                createPage("Selamat Datang, " + username + "!"),
                                "Dashboard");

                contentPanel.add(
                                createPage("Halaman Data Member"),
                                "Member");

                contentPanel.add(
                                createPage("Halaman Paket Gym"),
                                "Paket");

                contentPanel.add(
                                createPage("Halaman Pembayaran"),
                                "Pembayaran");

                contentPanel.add(
                                createPage("Halaman Absensi"),
                                "Absensi");

                // Menu actions
                dashboardButton.addActionListener(
                                e -> showPage("Dashboard"));

                memberButton.addActionListener(
                                e -> showPage("Member"));

                paketButton.addActionListener(
                                e -> showPage("Paket"));

                pembayaranButton.addActionListener(
                                e -> showPage("Pembayaran"));

                absensiButton.addActionListener(
                                e -> showPage("Absensi"));

                logoutButton.addActionListener(e -> {
                        int confirm = JOptionPane.showConfirmDialog(
                                        this,
                                        "Apakah kamu yakin ingin logout?",
                                        "Konfirmasi Logout",
                                        JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                                dispose();

                                LoginFrame login = new LoginFrame();
                                new LoginController(login);
                                login.setVisible(true);
                        }

                });

                add(sidebar, BorderLayout.WEST);
                add(contentPanel, BorderLayout.CENTER);

        }

        private JPanel createPage(String title) {
                JPanel panel = new JPanel(new BorderLayout());

                JLabel label = new JLabel(
                                title,
                                SwingConstants.CENTER);

                label.setFont(new Font("Arial", Font.BOLD, 24));

                panel.add(label, BorderLayout.CENTER);

                return panel;
        }

        private void showPage(String page) {
                cardLayout.show(contentPanel, page);
        }
}