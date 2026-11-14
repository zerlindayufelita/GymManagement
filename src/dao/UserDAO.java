package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DBConnection;
import model.User;
import security.PasswordUtil;

public class UserDAO {

    public User authenticate(String username, String password)
            throws SQLException {

        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    String storedHash = rs.getString("password_hash");

                    if (PasswordUtil.verifyPassword(password, storedHash)) {

                        return new User(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("full_name"),
                                rs.getString("role"));
                    }
                }
            }
        }

        return null;
    }
}