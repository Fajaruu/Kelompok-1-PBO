public class Pembayaran {
    private static int count = 1;
    private int nomorPesanan;
    private boolean statusPembayaran;
    private StatusPesanan status;

    public Pembayaran() {
    }

    public int getNomorPesanan() {
        return nomorPesanan;
    }

    public void setNomorPesanan(int nomorPesanan) {
        this.nomorPesanan = nomorPesanan;
    }

    public enum StatusPesanan {
        UNPAID,
        SUCCESSFUL,
        CANCELED
    }

    public StatusPesanan getStatus() {
        return status;
    }

    public void setStatus(StatusPesanan status) {
        this.status = status;
    }

    public void confirmPay(int nomPesan) {
        if (getNomorPesanan() == nomPesan) {
            System.out.println("terkonfirmasi!");
        } else if (getNomorPesanan() != nomPesan) {
            System.out.println("Pesanan tidak ditemukan");
        }
    }

    public void pay() {
        setStatus(StatusPesanan.SUCCESSFUL);
        if (getStatus() == Pembayaran.StatusPesanan.SUCCESSFUL) {
            System.out.println("Status pembayaran: sukses/SUCCESSFUL");
        }
    }
}