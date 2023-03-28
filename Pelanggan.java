public abstract class Pelanggan {
    private String firstName;
    private String lastName;
    private int jumlahPesanan;
    private Pembayaran pesanan;

    public Pelanggan(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public abstract String getFullName();

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void makeOrder() {
        if (pesanan == null) {
            pesanan = new Pembayaran();
        } else {
            System.out.println("Masih ada!");
        }
    }



    public void setJumlahPesanan(int jumlahPesanan) {
        this.jumlahPesanan = jumlahPesanan;
    }
}