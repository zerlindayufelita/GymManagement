import view.LoginFrame;
import javax.swing.SwingUtilities;

import controller.LoginController;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            LoginFrame login = new LoginFrame();
            LoginController controller = new LoginController(login);
            login.setVisible(true);
        });
    }
}