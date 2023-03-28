import java.util.Calendar;

public class PesananCafe {
    private String namaPesanan;
    private String jenisPesanan;
    private int harga;
    private int jumlahBeli;
    private int ongkir;

    public PesananCafe() {
        namaPesanan = "";
        jenisPesanan = "";
    }
    public PesananCafe(String jenisPesan, String namaMakanan, int harga, int jumlahBeli) {
        this.jenisPesanan = jenisPesan;
        this.namaPesanan = namaMakanan;
        this.jumlahBeli = jumlahBeli;
        this.harga = harga;
    }


    public void setNamaMakanan(String namaMakanan) {
        this.namaPesanan = namaMakanan;
    }

    public void setJenisPesanan(String jenisPesanan) {
        this.jenisPesanan = jenisPesanan;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
       this.harga = harga;
    }

    public String getNamaPesanan() {
        return namaPesanan;
    }

    public String getJenisPesanan() {
        return jenisPesanan;
    }

    public int getJumlahBeli() {
        return jumlahBeli;
    }

    public int getOngkir() {
        return ongkir;
    }

    public void setOngkir(int ongkir) {
        this.ongkir = ongkir;
    }

    public void display() {
        if (getJenisPesanan().equals("1")) {
            setJenisPesanan("Makanan");

            switch (getNamaPesanan()) {
                case "1":
                    setNamaMakanan("Sate");
                    break;
                case "2":
                    setNamaMakanan("Rendang");
                    break;
                case "3":
                    setNamaMakanan("Gulai");
                    break;
                case "4":
                    setNamaMakanan("Ayam Bakar");
                    break;
                case "5" :
                    setNamaMakanan("Soto Ayam");
                    break;
                case "6" :
                    setNamaMakanan("Nasi Uduk");
                    break;
                default:
                    System.out.println("Tidak ada di menu!");
                    break;
            }
        }
        else if (getJenisPesanan().equals("2")) {
            setJenisPesanan("Minuman");

            switch (getNamaPesanan()) {
                case "1":
                    setNamaMakanan("Es Teh");
                    break;
                case "2":
                    setNamaMakanan("Lemon Tea");
                    break;
                case "3":
                    setNamaMakanan("Milo");
                    break;
                default:
                    System.out.println("Tidak ada di menu!");
                    break;
            }
        }
        System.out.format("%-9s    %12s   %17d   %11d\n",getJenisPesanan(),getNamaPesanan(),getHarga(),getJumlahBeli());
    }
    public int getTotal() {
        return getHarga()*jumlahBeli;
    }

    public void checkOut() {
        System.out.println("Check out berhasil dilakukan!\n");
    }

    public static void getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        System.out.printf("Tanggal : %d-%d-%d", day, month, year);
    }
    public void printDetails() {
        getCurrentDate();
        System.out.printf("\n%50s", "------------DETAIL PESANAN------------\n");
        System.out.println("=============================================================================");
        System.out.print("Jenis\t\t\tNama Pesanan\t\t\tHarga\t\t\tJumlah\n");
        System.out.println("-----------------------------------------------------------------------------");
    }
}