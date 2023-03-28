class Customer {
    private String name;
    private int lamaJoin;

    public Customer(String name, int lamaJoin) {
        this.name = name;
        this.lamaJoin = lamaJoin; //hari
    }

    public int getLamaJoin() {
        return lamaJoin;
    }

}

class Order {
    private int totHarga;
    private int minimumPrice;
    private int ongkir;
    private int minimumOngkir;

    public Order(int totHarga, int minimumPrice) {
        this.totHarga = totHarga;
        this.minimumPrice = minimumPrice;
    }

    public Order(String ongkir, String minimumOngkir) {
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

    boolean isCustomerEligible(Customer x);
    boolean isMinimumPriceEligible(Order x);
    boolean isShippingFeeEligible(Order x);

    public class PercentOffPromo extends Promotion  {
        private double percentOff;

        public PercentOffPromo(String promoCode, boolean tanggalBerlaku, double percentOff) {
            super(promoCode, tanggalBerlaku);
            this.percentOff = percentOff;
        }

        public double applyPromo(double totalHarga) {
            return totalHarga * (percentOff / 100);
        }

        @Override
        public boolean isCustomerEligible(Customer x) {
            int lamaJoin = x.getLamaJoin();
            if (lamaJoin >= 30) { //hari
                return true;
            } else
                return false;
        }

        @Override
        public boolean isMinimumPriceEligible(Order x) {
            int hargaMinim = x.getMinimumPrice();
            if (hargaMinim >= 40000)
                return true;
            else
                return false;
        }

        @Override
        public boolean isShippingFeeEligible(Order x) {
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

    public class CashBack extends Promotion implements Applicable {
        private double kembalianCashBack;

        public CashBack(String promoCode, boolean tanggalBerlaku, double kembalianCashBack) {
            super(promoCode, tanggalBerlaku);
            this.kembalianCashBack = kembalianCashBack;
        }

        public double applyPromo(double totalHarga) {
            return totalHarga - kembalianCashBack;
        }

        @Override
        public boolean isCustomerEligible(Customer x) {
            int lamaJoin = x.getLamaJoin();
            if (lamaJoin >= 30) { //hari
                return true;
            } else
                return false;
        }

        @Override
        public boolean isMinimumPriceEligible(Order x) {
            int hargaMinim = x.getMinimumPrice();
            if (hargaMinim >= 40000)
                return true;
            else
                return false;
        }

        @Override
        public boolean isShippingFeeEligible(Order x) {
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

    public class ShippingFee extends Promotion implements Applicable {
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
        public boolean isCustomerEligible(Customer x) {
            int lamaJoin = x.getLamaJoin();
            if (lamaJoin >= 30) { //hari
                return true;
            } else
                return false;
        }

        @Override
        public boolean isMinimumPriceEligible(Order x) {
            int hargaMinim = x.getMinimumPrice();
            if (hargaMinim >= 40000)
                return true;
            else
                return false;
        }

        @Override
        public boolean isShippingFeeEligible(Order x) {
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