
public abstract class Promotion implements Applicable, Comparable<Promotion> {
        private String promoCode;
    private boolean tanggalBerlaku;

    public Promotion(String promoCode, boolean tanggalBerlaku) {
        this.promoCode = promoCode;
        this.tanggalBerlaku = tanggalBerlaku;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public int compareTo(Promotion x) {
        if (this.getDiskon() > x.getDiskon()) {
            return -1;
        } else if (this.getDiskon() == x.getDiskon()) {
            return 0;
        } else {
            return 1;
        }
    }
    public abstract int getDiskon();

}