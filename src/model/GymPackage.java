
package model;

public class GymPackage {

    private int idPaket;
    private String namaPaket;
    private int durasi;
    private double harga;
    private String deskripsi;

    // Constructor
    public GymPackage(int idPaket, String namaPaket, int durasi,
            double harga, String deskripsi) {
        this.idPaket = idPaket;
        this.namaPaket = namaPaket;
        this.durasi = durasi;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }

    // Getter
    public int getIdPaket() {
        return idPaket;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public int getDurasi() {
        return durasi;
    }

    public double getHarga() {
        return harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    // Setter
    public void setIdPaket(int idPaket) {
        this.idPaket = idPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
