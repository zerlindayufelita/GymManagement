
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import model.GymPackage;

public class PaketDAO {

    public List<GymPackage> getAllPaket() throws SQLException {

        List<GymPackage> listPaket = new ArrayList<>();

        String sql = "SELECT * FROM paket_gym";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                GymPackage paket = new GymPackage(
                        rs.getInt("id_paket"),
                        rs.getString("nama_paket"),
                        rs.getInt("durasi"),
                        rs.getDouble("harga"),
                        rs.getString("deskripsi"));

                listPaket.add(paket);
            }
        }

        return listPaket;
    }

    public boolean insertPaket(GymPackage paket) throws SQLException {

        String sql = "INSERT INTO paket_gym "
                + "(nama_paket, durasi, harga, deskripsi) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paket.getNamaPaket());
            stmt.setInt(2, paket.getDurasi());
            stmt.setDouble(3, paket.getHarga());
            stmt.setString(4, paket.getDeskripsi());

            return stmt.executeUpdate() > 0;
        }

    }

    public boolean updatePaket(GymPackage paket) throws SQLException {

        String sql = "UPDATE paket_gym SET "
                + "nama_paket = ?, "
                + "durasi = ?, "
                + "harga = ?, "
                + "deskripsi = ? "
                + "WHERE id_paket = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paket.getNamaPaket());
            stmt.setInt(2, paket.getDurasi());
            stmt.setDouble(3, paket.getHarga());
            stmt.setString(4, paket.getDeskripsi());
            stmt.setInt(5, paket.getIdPaket());

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deletePaket(int idPaket) throws SQLException {

        String sql = "DELETE FROM paket_gym WHERE id_paket = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPaket);

            return stmt.executeUpdate() > 0;
        }
    }

}
