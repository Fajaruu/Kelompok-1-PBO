public class Guest extends Pelanggan {
    private Pembayaran pesanan;

    public Guest(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

}
