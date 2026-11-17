
package controller;

import java.sql.SQLException;
import java.util.List;

import dao.MemberDAO;
import model.Member;
import view.MemberPanel;

public class MemberController {

    private MemberDAO memberDAO;
    private MemberPanel memberPanel;

    public MemberController(MemberPanel memberPanel) {
        this.memberDAO = new MemberDAO();
        this.memberPanel = memberPanel;
    }

    // Menampilkan data member
    public void loadMember() {
        try {
            List<Member> listMember = memberDAO.getAllMember();
            memberPanel.displayMember(listMember);

        } catch (SQLException e) {
            memberPanel.showMessage(
                    "Gagal menampilkan data member: " + e.getMessage());
        }
    }

    // Menambahkan member
    public void addMember(Member member) {
        try {
            boolean success = memberDAO.insertMember(member);

            if (success) {
                memberPanel.showMessage("Member berhasil ditambahkan!");
                loadMember();
                memberPanel.clearForm();
            } else {
                memberPanel.showMessage("Member gagal ditambahkan!");
            }

        } catch (SQLException e) {
            memberPanel.showMessage(
                    "Error menambahkan member: " + e.getMessage());
        }
    }

    // Mengedit member
    public void editMember(Member member) {
        try {
            boolean success = memberDAO.updateMember(member);

            if (success) {
                memberPanel.showMessage("Data member berhasil diperbarui!");
                loadMember();
                memberPanel.clearForm();
            } else {
                memberPanel.showMessage("Data member gagal diperbarui!");
            }

        } catch (SQLException e) {
            memberPanel.showMessage(
                    "Error mengedit member: " + e.getMessage());
        }
    }

    // Menghapus member
    public void removeMember(int idMember) {
        try {
            boolean success = memberDAO.deleteMember(idMember);

            if (success) {
                memberPanel.showMessage("Member berhasil dihapus!");
                loadMember();
                memberPanel.clearForm();
            } else {
                memberPanel.showMessage("Member gagal dihapus!");
            }

        } catch (SQLException e) {
            memberPanel.showMessage(
                    "Error menghapus member: " + e.getMessage());
        }
    }
}
