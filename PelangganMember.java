public class PelangganMember extends Pelanggan {
    private int lamaMember;

    public PelangganMember(String firstName, String lastName, int lamaMember) {
        super(firstName, lastName);
        this.lamaMember = lamaMember;
    }

    @Override
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public int getLamaMember() {
        return lamaMember;
    }
}
