
package view;

import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import model.Member;
import controller.MemberController;

public class MemberPanel extends JPanel {

    private JTextField txtNama;
    private JComboBox<String> cmbJenisKelamin;
    private JTextField txtNoTelepon;
    private JTextField txtAlamat;

    private JTable tableMember;
    private DefaultTableModel tableModel;

    private JButton btnTambah;
    private JButton btnEdit;
    private JButton btnHapus;
    private JButton btnClear;

    private MemberController memberController;

    public MemberPanel() {

        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // Judul panel
        JLabel lblJudul = new JLabel("DATA MEMBER GYM");
        lblJudul.setFont(new Font("Arial", Font.BOLD, 24));
        lblJudul.setHorizontalAlignment(SwingConstants.CENTER);

        add(lblJudul, BorderLayout.NORTH);

        // Panel utama
        JPanel panelUtama = new JPanel();
        panelUtama.setLayout(new BoxLayout(panelUtama, BoxLayout.Y_AXIS));
        panelUtama.setBackground(Color.WHITE);

        // Form input
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Form Member"));

        txtNama = new JTextField();

        cmbJenisKelamin = new JComboBox<>(
                new String[] { "Laki-laki", "Perempuan" });

        txtNoTelepon = new JTextField();
        txtAlamat = new JTextField();

        panelForm.add(new JLabel("Nama Member"));
        panelForm.add(txtNama);

        panelForm.add(new JLabel("Jenis Kelamin"));
        panelForm.add(cmbJenisKelamin);

        panelForm.add(new JLabel("No Telepon"));
        panelForm.add(txtNoTelepon);

        panelForm.add(new JLabel("Alamat"));
        panelForm.add(txtAlamat);

        panelUtama.add(panelForm);

        // Panel tombol
        JPanel panelTombol = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnTambah = new JButton("Tambah");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");

        panelTombol.add(btnTambah);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);

        panelUtama.add(panelTombol);

        // Tabel member
        String[] kolom = {
                "ID Member",
                "Nama",
                "Jenis Kelamin",
                "No Telepon",
                "Alamat",
                "Tanggal Daftar"
        };

        tableModel = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableMember = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableMember);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Data Member"));

        panelUtama.add(scrollPane);

        add(panelUtama, BorderLayout.CENTER);

        // Controller
        memberController = new MemberController(this);

        // Event tombol
        btnTambah.addActionListener(e -> tambahMember());

        btnEdit.addActionListener(e -> editMember());

        btnHapus.addActionListener(e -> hapusMember());

        btnClear.addActionListener(e -> clearForm());

        // Event pilih tabel
        tableMember.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                pilihMember();
            }
        });

        // Load data awal
        memberController.loadMember();
    }

    // Tambah member
    private void tambahMember() {

        if (txtNama.getText().trim().isEmpty()
                || txtNoTelepon.getText().trim().isEmpty()
                || txtAlamat.getText().trim().isEmpty()) {

            showMessage("Semua data harus diisi!");
            return;
        }

        Member member = new Member(
                0,
                txtNama.getText(),
                cmbJenisKelamin.getSelectedItem().toString(),
                txtNoTelepon.getText(),
                txtAlamat.getText(),
                LocalDate.now().toString());

        memberController.addMember(member);
    }

    // Edit member
    private void editMember() {

        int selectedRow = tableMember.getSelectedRow();

        if (selectedRow == -1) {
            showMessage("Pilih data member yang ingin diedit!");
            return;
        }

        int idMember = Integer.parseInt(
                tableModel.getValueAt(selectedRow, 0).toString());

        Member member = new Member(
                idMember,
                txtNama.getText(),
                cmbJenisKelamin.getSelectedItem().toString(),
                txtNoTelepon.getText(),
                txtAlamat.getText(),
                LocalDate.now().toString());

        memberController.editMember(member);
    }

    // Hapus member
    private void hapusMember() {

        int selectedRow = tableMember.getSelectedRow();

        if (selectedRow == -1) {
            showMessage("Pilih data member yang ingin dihapus!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus member ini?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {

            int idMember = Integer.parseInt(
                    tableModel.getValueAt(selectedRow, 0).toString());

            memberController.removeMember(idMember);
        }
    }

    // Memilih data dari tabel
    private void pilihMember() {

        int selectedRow = tableMember.getSelectedRow();

        if (selectedRow != -1) {

            txtNama.setText(
                    tableModel.getValueAt(selectedRow, 1).toString());

            cmbJenisKelamin.setSelectedItem(
                    tableModel.getValueAt(selectedRow, 2).toString());

            txtNoTelepon.setText(
                    tableModel.getValueAt(selectedRow, 3).toString());

            txtAlamat.setText(
                    tableModel.getValueAt(selectedRow, 4).toString());
        }
    }

    // Menampilkan data ke tabel
    public void displayMember(List<Member> listMember) {

        tableModel.setRowCount(0);

        for (Member member : listMember) {

            Object[] row = {
                    member.getIdMember(),
                    member.getNama(),
                    member.getJenisKelamin(),
                    member.getNoTelepon(),
                    member.getAlamat(),
                    member.getTanggalDaftar()
            };

            tableModel.addRow(row);
        }
    }

    // Membersihkan form
    public void clearForm() {

        txtNama.setText("");
        cmbJenisKelamin.setSelectedIndex(0);
        txtNoTelepon.setText("");
        txtAlamat.setText("");

        tableMember.clearSelection();
    }

    // Menampilkan pesan
    public void showMessage(String message) {

        JOptionPane.showMessageDialog(this, message);
    }
}
