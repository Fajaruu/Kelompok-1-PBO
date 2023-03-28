class CustomerEligible {
    private String name;
    private int lamaJoin;

    public CustomerEligible(String name, int lamaJoin) {
        this.name = name;
        this.lamaJoin = lamaJoin; //hari
    }

    public int getLamaJoin() {
        return lamaJoin;
    }
}

class OrderEligible {
    private int totHarga;
    private int minimumPrice;
    private int ongkir;
    private int minimumOngkir;

    public OrderEligible(int totHarga, int minimumPrice) {
        this.totHarga = totHarga;
        this.minimumPrice = minimumPrice;
    }

    public OrderEligible(String ongkir, String minimumOngkir) {
        this.ongkir = Integer.parseInt(ongkir);
        this.minimumOngkir = Integer.parseInt(minimumOngkir);
    }

    public int getMinimumPrice() {
        return minimumPrice;
    }

    public int getOngkir() {
        return ongkir;
    }
}

public interface Applicable {

    boolean isCustomerEligible(CustomerEligible x);
    boolean isMinimumPriceEligible(OrderEligible x);
    boolean isShippingFeeEligible(OrderEligible x);

    class PercentOffPromo extends Promotion  {
        private double percentOff;

        public PercentOffPromo(String promoCode, boolean tanggalBerlaku, double percentOff) {
            super(promoCode, tanggalBerlaku);
            this.percentOff = percentOff;
        }

        public double applyPromo(double totalHarga) {
            return totalHarga * (percentOff / 100);
        }

        @Override
        public boolean isCustomerEligible(CustomerEligible x) {
            int lamaJoin = x.getLamaJoin();
            if (lamaJoin >= 30) { //hari
                return true;
            } else
                return false;
        }

        @Override
        public boolean isMinimumPriceEligible(OrderEligible x) {
            int hargaMinim = x.getMinimumPrice();
            if (hargaMinim >= 40000)
                return true;
            else
                return false;
        }

        @Override
        public boolean isShippingFeeEligible(OrderEligible x) {
            int hargaOngkir = x.getOngkir();
            if (hargaOngkir >= 5000)
                return true;
            else
                return false;
        }

        @Override
        public int getDiskon() {
            return (int) percentOff;
        }

    }

     class CashBack extends Promotion implements Applicable {
        private double kembalianCashBack;

        public CashBack(String promoCode, boolean tanggalBerlaku, double kembalianCashBack) {
            super(promoCode, tanggalBerlaku);
            this.kembalianCashBack = kembalianCashBack;
        }

        public double applyPromo(double totalHarga) {
            return totalHarga - kembalianCashBack;
        }

        @Override
        public boolean isCustomerEligible(CustomerEligible x) {
            int lamaJoin = x.getLamaJoin();
            if (lamaJoin >= 30) { //hari
                return true;
            } else
                return false;
        }

        @Override
        public boolean isMinimumPriceEligible(OrderEligible x) {
            int hargaMinim = x.getMinimumPrice();
            if (hargaMinim >= 40000)
                return true;
            else
                return false;
        }

        @Override
        public boolean isShippingFeeEligible(OrderEligible x) {
            int hargaOngkir = x.getOngkir();
            if (hargaOngkir >= 10000)
                return true;
            else
                return false;
        }

        @Override
        public int getDiskon() {
            return (int) kembalianCashBack;
        }


     }

     class ShippingFee extends Promotion implements Applicable {
        private double ongkir;

        public ShippingFee(String promoCode, boolean tanggalBerlaku, double ongkir) {
            super(promoCode, tanggalBerlaku);
            this.ongkir = ongkir;
        }


        public int applyPromo(int totalHarga) {
            if (totalHarga >= ongkir/100) {
                return totalHarga - (int) ongkir;
            } else
                return totalHarga;
        }

        @Override
        public boolean isCustomerEligible(CustomerEligible x) {
            int lamaJoin = x.getLamaJoin();
            if (lamaJoin >= 30) { //hari
                return true;
            } else
                return false;
        }

        @Override
        public boolean isMinimumPriceEligible(OrderEligible x) {
            int hargaMinim = x.getMinimumPrice();
            if (hargaMinim >= 40000)
                return true;
            else
                return false;
        }

        @Override
        public boolean isShippingFeeEligible(OrderEligible x) {
            int hargaOngkir = x.getOngkir();
            if (hargaOngkir >= 5000)
                return true;
            else
                return false;
        }

        @Override
        public int getDiskon() {
            return (int) ongkir;
        }

    }
}