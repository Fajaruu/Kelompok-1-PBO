
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
        return x.getDiskon() - this.getDiskon();
    }
    public abstract int getDiskon();

}