
package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.PaketDAO;
import model.GymPackage;
import view.PaketPanel;

public class PaketController {

    private PaketDAO paketDAO;
    private PaketPanel paketPanel;

    public PaketController(PaketPanel paketPanel) {
        this.paketDAO = new PaketDAO();
        this.paketPanel = paketPanel;
    }

    public void loadPaket() {

        try {
            List<GymPackage> listPaket = paketDAO.getAllPaket();

            paketPanel.displayPaket(listPaket);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    paketPanel,
                    "Gagal memuat data paket: " + e.getMessage());
        }
    }

    public void addPaket(GymPackage paket) {

        try {
            boolean berhasil = paketDAO.insertPaket(paket);

            if (berhasil) {
                JOptionPane.showMessageDialog(
                        paketPanel,
                        "Paket berhasil ditambahkan!");

                loadPaket();
                paketPanel.clearForm();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    paketPanel,
                    "Gagal menambahkan paket: " + e.getMessage());
        }
    }

    public void editPaket(GymPackage paket) {

        try {
            boolean berhasil = paketDAO.updatePaket(paket);

            if (berhasil) {
                JOptionPane.showMessageDialog(
                        paketPanel,
                        "Paket berhasil diperbarui!");

                loadPaket();
                paketPanel.clearForm();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    paketPanel,
                    "Gagal mengedit paket: " + e.getMessage());
        }
    }

    public void removePaket(int idPaket) {

        try {
            boolean berhasil = paketDAO.deletePaket(idPaket);

            if (berhasil) {
                JOptionPane.showMessageDialog(
                        paketPanel,
                        "Paket berhasil dihapus!");

                loadPaket();
                paketPanel.clearForm();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    paketPanel,
                    "Gagal menghapus paket: " + e.getMessage());
        }
    }

}
