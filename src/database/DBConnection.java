package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() throws SQLException {

        Properties config = new Properties();

        try (FileInputStream file = new FileInputStream("db.properties")) {
            config.load(file);
        } catch (IOException e) {
            throw new SQLException("File konfigurasi database tidak ditemukan!", e);
        }

        String url = config.getProperty("db.url");
        String user = config.getProperty("db.username");
        String password = config.getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }
}