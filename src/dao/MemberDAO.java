
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import model.Member;

public class MemberDAO {

    // Menampilkan semua data member
    public List<Member> getAllMember() throws SQLException {

        List<Member> listMember = new ArrayList<>();

        String sql = "SELECT * FROM member";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Member member = new Member(
                        rs.getInt("id_member"),
                        rs.getString("nama"),
                        rs.getString("jenis_kelamin"),
                        rs.getString("no_telepon"),
                        rs.getString("alamat"),
                        rs.getString("tanggal_daftar"));

                listMember.add(member);
            }
        }

        return listMember;
    }

    // Menambahkan member baru
    public boolean insertMember(Member member) throws SQLException {

        String sql = "INSERT INTO member "
                + "(nama, jenis_kelamin, no_telepon, alamat, tanggal_daftar) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, member.getNama());
            stmt.setString(2, member.getJenisKelamin());
            stmt.setString(3, member.getNoTelepon());
            stmt.setString(4, member.getAlamat());
            stmt.setString(5, member.getTanggalDaftar());

            return stmt.executeUpdate() > 0;
        }
    }

    // Mengedit data member
    public boolean updateMember(Member member) throws SQLException {

        String sql = "UPDATE member SET "
                + "nama = ?, "
                + "jenis_kelamin = ?, "
                + "no_telepon = ?, "
                + "alamat = ?, "
                + "tanggal_daftar = ? "
                + "WHERE id_member = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, member.getNama());
            stmt.setString(2, member.getJenisKelamin());
            stmt.setString(3, member.getNoTelepon());
            stmt.setString(4, member.getAlamat());
            stmt.setString(5, member.getTanggalDaftar());
            stmt.setInt(6, member.getIdMember());

            return stmt.executeUpdate() > 0;
        }
    }

    // Menghapus data member
    public boolean deleteMember(int idMember) throws SQLException {

        String sql = "DELETE FROM member WHERE id_member = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMember);

            return stmt.executeUpdate() > 0;
        }
    }
}
