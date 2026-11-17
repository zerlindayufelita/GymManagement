
package model;

public class Member {
    private int idMember;
    private String nama;
    private String jenisKelamin;
    private String noTelepon;
    private String alamat;
    private String tanggalDaftar;

    // Constructor kosong
    public Member() {
    }

    // Constructor dengan parameter
    public Member(int idMember, String nama, String jenisKelamin,
                  String noTelepon, String alamat, String tanggalDaftar) {
        this.idMember = idMember;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.noTelepon = noTelepon;
        this.alamat = alamat;
        this.tanggalDaftar = tanggalDaftar;
    }

    // Getter dan Setter
    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTanggalDaftar() {
        return tanggalDaftar;
    }

    public void setTanggalDaftar(String tanggalDaftar) {
        this.tanggalDaftar = tanggalDaftar;
    }
}
