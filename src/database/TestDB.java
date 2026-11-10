package database;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDB {

    public static void main(String[] args) {

        try (Connection conn = DBConnection.getConnection()) {

            System.out.println("Koneksi MySQL Berhasil!");

        } catch (SQLException e) {

            System.out.println("Koneksi MySQL Gagal!");
            e.printStackTrace();

        }

    }
}