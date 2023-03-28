public class Diskon extends Promotion {
    public Diskon(String promoCode, boolean tanggalBerlaku) {
        super(promoCode, tanggalBerlaku);
    }

    @Override
    public int getDiskon() {
        return 0;
    }

    @Override
    public boolean isCustomerEligible(CustomerEligible x) {
        return false;
    }

    @Override
    public boolean isMinimumPriceEligible(OrderEligible x) {
        return false;
    }

    @Override
    public boolean isShippingFeeEligible(OrderEligible x) {
        return false;
    }

}
