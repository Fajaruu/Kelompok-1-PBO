public class Diskon extends Promotion {
    public Diskon(String promoCode, boolean tanggalBerlaku) {
        super(promoCode, tanggalBerlaku);
    }

    @Override
    public int getDiskon() {
        return 0;
    }

    @Override
    public boolean isCustomerEligible(Customer x) {
        return false;
    }

    @Override
    public boolean isMinimumPriceEligible(Order x) {
        return false;
    }

    @Override
    public boolean isShippingFeeEligible(Order x) {
        return false;
    }
}
