public class Menu extends PesananCafe {
    private String menuMakanan[][] = new String[5][5];
    private String menuMinuman[][] = new String[5][5];

    public Menu() {
    }

    public void menuMakanan() {
        System.out.printf("\n%38s", "------------FILKOM CAFE------------");
        System.out.println("\n================MENU MAKANAN====================");
        String menuMakanan[][] = {{"1. Sate", "1000"}, {"2. Rendang", "13000"}, {"3. Gulai", "25000"}, {"4. Ayam Bakar", "18000"},
                {"5. Soto Ayam", "15000"}, {"6. Nasi Uduk", "5000"}};
        this.menuMakanan = menuMakanan;
        for(int i = 0; i < menuMakanan.length; i++) {
            for (int j = 0; j < menuMakanan[i].length; j++) {
                System.out.printf("%-30s \t", menuMakanan[i][j]);
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    public void menuMinuman() {
        System.out.println("\n================MENU MINUMAN====================");
        String menuMinuman[][] = {{"1. Es Teh", "2000"}, {"2. Lemon Tea", "4000"}, {"3. Milo", "5000"}, {"4. Boba", "8000"}, {"5. Red Velvet", "12000"}, {"6. Teh Tarik", "7000"}};
        this.menuMinuman = menuMinuman;
        for (int i = 0; i < menuMinuman.length; i++) {
            for (int j = 0; j < menuMinuman[i].length; j++) {
                System.out.printf("%-30s \t", menuMinuman[i][j]);
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    public void displayMenu() {
        menuMakanan();
        menuMinuman();
    }

    @Override
    public void display () {
        super.display();
        System.out.println();
    }
}
