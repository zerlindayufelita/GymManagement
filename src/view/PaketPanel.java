
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import model.GymPackage;
import controller.PaketController;

public class PaketPanel extends JPanel {

    private JTextField txtNamaPaket;
    private JTextField txtDurasi;
    private JTextField txtHarga;
    private JTextArea txtDeskripsi;

    private JTable tablePaket;
    private DefaultTableModel tableModel;

    private JButton btnTambah;
    private JButton btnEdit;
    private JButton btnHapus;
    private JButton btnClear;

    private PaketController paketController;

    public PaketPanel() {

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Membuat form input
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));

        txtNamaPaket = new JTextField();
        txtDurasi = new JTextField();
        txtHarga = new JTextField();
        txtDeskripsi = new JTextArea(3, 20);
        txtDeskripsi.setLineWrap(true);
        txtDeskripsi.setWrapStyleWord(true);

        formPanel.add(new JLabel("Nama Paket"));
        formPanel.add(txtNamaPaket);

        formPanel.add(new JLabel("Durasi (Bulan)"));
        formPanel.add(txtDurasi);

        formPanel.add(new JLabel("Harga"));
        formPanel.add(txtHarga);

        formPanel.add(new JLabel("Deskripsi"));
        formPanel.add(new JScrollPane(txtDeskripsi));

        // Membuat tombol
        btnTambah = new JButton("Tambah");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        buttonPanel.add(btnTambah);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnHapus);
        buttonPanel.add(btnClear);

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // Membuat tabel
        String[] columns = {
                "ID Paket", "Nama Paket", "Durasi (Bulan)", "Harga", "Deskripsi"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablePaket = new JTable(tableModel);
        tablePaket.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(tablePaket), BorderLayout.CENTER);

        // Menghubungkan View dengan Controller
        paketController = new PaketController(this);

        // Memasang event tombol
        btnTambah.addActionListener(e -> tambahPaket());
        btnEdit.addActionListener(e -> editPaket());
        btnHapus.addActionListener(e -> hapusPaket());
        btnClear.addActionListener(e -> clearForm());

        // Event ketika baris tabel dipilih
        tablePaket.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                pilihPaket();
            }
        });

        // Memuat data dari database
        paketController.loadPaket();
    }

    private void tambahPaket() {

        try {
            String nama = txtNamaPaket.getText().trim();
            String durasiText = txtDurasi.getText().trim();
            String hargaText = txtHarga.getText().trim();
            String deskripsi = txtDeskripsi.getText().trim();

            if (nama.isEmpty() || durasiText.isEmpty()
                    || hargaText.isEmpty()) {

                showMessage("Nama, durasi, dan harga wajib diisi!");
                return;
            }

            int durasi = Integer.parseInt(durasiText);
            double harga = parseHarga(hargaText);

            if (durasi <= 0 || harga <= 0) {
                showMessage("Durasi dan harga harus lebih dari 0!");
                return;
            }

            GymPackage paket = new GymPackage(
                    0, nama, durasi, harga, deskripsi);

            paketController.addPaket(paket);

        } catch (NumberFormatException e) {
            showMessage("Durasi harus berupa angka bulat dan harga harus berupa angka!");
        }

    }

    private void editPaket() {

        int selectedRow = tablePaket.getSelectedRow();

        if (selectedRow == -1) {
            showMessage("Pilih paket yang ingin diedit!");
            return;
        }

        try {
            int idPaket = (int) tableModel.getValueAt(selectedRow, 0);

            String nama = txtNamaPaket.getText().trim();
            String durasiText = txtDurasi.getText().trim();
            String hargaText = txtHarga.getText().trim();
            String deskripsi = txtDeskripsi.getText().trim();

            if (nama.isEmpty() || durasiText.isEmpty()
                    || hargaText.isEmpty()) {
                showMessage("Nama, durasi, dan harga wajib diisi!");
                return;
            }

            int durasi = Integer.parseInt(durasiText);
            double harga = parseHarga(hargaText);

            if (durasi <= 0 || harga <= 0) {
                showMessage("Durasi dan harga harus lebih dari 0!");
                return;
            }

            GymPackage paket = new GymPackage(
                    idPaket, nama, durasi, harga, deskripsi);

            paketController.editPaket(paket);

        } catch (NumberFormatException e) {
            showMessage("Format durasi atau harga tidak valid!");
        }
    }

    private void hapusPaket() {

        int selectedRow = tablePaket.getSelectedRow();

        if (selectedRow == -1) {
            showMessage("Pilih paket yang ingin dihapus!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(
                this,
                "Apakah kamu yakin ingin menghapus paket ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION);

        if (konfirmasi == JOptionPane.YES_OPTION) {

            int idPaket = (int) tableModel.getValueAt(selectedRow, 0);

            paketController.removePaket(idPaket);
        }
    }

    private void pilihPaket() {

        int selectedRow = tablePaket.getSelectedRow();

        if (selectedRow != -1) {

            txtNamaPaket.setText(
                    tableModel.getValueAt(selectedRow, 1).toString());

            txtDurasi.setText(
                    tableModel.getValueAt(selectedRow, 2).toString());

            txtHarga.setText(
                    tableModel.getValueAt(selectedRow, 3).toString());

            Object deskripsi = tableModel.getValueAt(selectedRow, 4);

            txtDeskripsi.setText(
                    deskripsi == null ? "" : deskripsi.toString());
        }
    }

    public void displayPaket(List<GymPackage> listPaket) {

        tableModel.setRowCount(0);

        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(
                new Locale("id", "ID"));

        formatRupiah.setMaximumFractionDigits(0);

        for (GymPackage paket : listPaket) {

            Object[] row = {
                    paket.getIdPaket(),
                    paket.getNamaPaket(),
                    paket.getDurasi(),
                    formatRupiah.format(paket.getHarga()),
                    paket.getDeskripsi()
            };

            tableModel.addRow(row);
        }
    }

    public void clearForm() {

        txtNamaPaket.setText("");
        txtDurasi.setText("");
        txtHarga.setText("");
        txtDeskripsi.setText("");

        tablePaket.clearSelection();
    }

    private double parseHarga(String hargaText) {

        String hargaNormal = hargaText.trim()
                .replace(".", "")
                .replace(",", ".");

        if (!hargaNormal.matches("\\d+(\\.\\d{1,2})?")) {
            throw new NumberFormatException("Format harga tidak valid");
        }

        return Double.parseDouble(hargaNormal);
    }

    private void showMessage(String message) {

        JOptionPane.showMessageDialog(this, message);
    }

}