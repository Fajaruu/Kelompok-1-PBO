import java.util.*;

public class Receipt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PesananCafe pes = new PesananCafe();
        Menu m = new Menu();
        Pembayaran pb = new Pembayaran();
        m.displayMenu();
        //member
        System.out.println("Apakah pelanggan member (Y/N)?");
        String input = sc.nextLine();
        if (input.equals("Y")) {
            System.out.print("\nNama depan : ");
            String namaDepan = sc.nextLine();
            System.out.print("Nama belakang : ");
            String namaBelakang = sc.nextLine();
            System.out.print("Lama sejak menjadi member ( /hari): ");
            int lamaMember = sc.nextInt();
            sc.nextLine();

            PelangganMember pme = new PelangganMember(namaDepan, namaBelakang, lamaMember);
            pme.makeOrder();
            System.out.print("\nNomor Pesanan : ");
            int nomorPesanan = sc.nextInt();
            System.out.println("\n=========================");
            System.out.print("Jumlah pesanan : ");
            int jumlahPesan = sc.nextInt();
            pme.setJumlahPesanan(jumlahPesan);

            System.out.println("=========================");
            PesananCafe[] pc = new PesananCafe[jumlahPesan];
            String[] jenisPesan = new String[jumlahPesan];
            String[] namaPesanan = new String[jumlahPesan];
            int[] jumlahPesanan = new int[jumlahPesan];
            int[] hargaPesanan = new int[jumlahPesan];
            int[] totalPesanan = new int[jumlahPesan];
            int total = 0;
            int totalSemua = 0;
            for (int i = 0; i < jumlahPesan; i++) {
                sc.nextLine();
                System.out.println("(1 = Makanan || 2 = Minuman)");
                System.out.print("Jenis pesanan : ");
                jenisPesan[i] = sc.nextLine();
                //exception code try catch=========================================================== Exception
                try {
                    System.out.print("Kode nama pesanan : ");
                    input = sc.nextLine();
                    if (input.compareTo("1") < 0 || input.compareTo("6") > 0) {
                        throw new Exception("Mohon Maaf Kode pesanan tidak tidak tertera dalam menu");
                    }
                    namaPesanan[i] = input;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.exit(0);
                }

                System.out.print("Jumlah Beli : ");
                jumlahPesanan[i] = sc.nextInt();
                System.out.print("Harga : ");
                hargaPesanan[i] = sc.nextInt();
                System.out.println("----------------------------------");
                pc[i] = new PesananCafe(jenisPesan[i], namaPesanan[i], hargaPesanan[i], jumlahPesanan[i]);
                totalPesanan[i] = pc[i].getTotal();
            }
            System.out.println("\nCheck out? (Y/N)");
            sc.nextLine();
            String CO = sc.nextLine();
            if (CO.equals("Y")) {
                pes.checkOut();
                System.out.print("Harga ongkir : ");
                int Ong = sc.nextInt();
                sc.nextLine();
                System.out.println("=============================================================================");
                System.out.println("No. Pesanan : " + nomorPesanan);
                System.out.println("Nama pelanggan : " + pme.getFullName());
                pes.printDetails();
                pes.setOngkir(Ong);
                for (int i = 0; i < jumlahPesan; i++) {
                    pc[i].display();
                }
                for (int i = 0; i < jumlahPesan; i++) {
                    total += totalPesanan[i];
                }
                for (int i = 0; i < jumlahPesan; i++) {
                    totalSemua = total + pes.getOngkir();
                }
                System.out.println("\n=============================================================================");
                System.out.println("Total Belanja : " + total);
                System.out.println("Ongkos kirim : " + pes.getOngkir());
                System.out.println("Total : " + totalSemua);
                System.out.println("=============================================================================\n");

                Date da = new Date();
                Date tanggalBerlaku = new Date(da.getTime() + (1000 * 120 * 120 * 60 * 1000L));
                boolean berlakuSampai = da.before(tanggalBerlaku);


                Applicable.PercentOffPromo diskonPersenOff = new Applicable.PercentOffPromo("CPKTW110", berlakuSampai, 50);
                Applicable.CashBack diskonCB = new Applicable.CashBack("MSBRO999", berlakuSampai, 10000);
                Applicable.ShippingFee diskonOng = new Applicable.ShippingFee("HDEHH55", berlakuSampai, 500000);


                CustomerEligible c = new CustomerEligible(pme.getFullName(), lamaMember);
                OrderEligible o = new OrderEligible(totalSemua, 40000);
                OrderEligible or = new OrderEligible(Integer.toString(Ong), Integer.toString(5000));

                System.out.println("List promo : ");

                if (diskonPersenOff.isCustomerEligible(c) && diskonPersenOff.isMinimumPriceEligible(o) && diskonCB.isCustomerEligible(c) && diskonCB.isMinimumPriceEligible(o)) {
                    //comparable
                    if (diskonPersenOff.compareTo(diskonCB) > diskonCB.compareTo(diskonPersenOff) && diskonPersenOff.compareTo(diskonOng) > diskonOng.compareTo(diskonPersenOff)) {
                        System.out.println("Promo 'CPKTW110', untuk potongan diskon 50%, tersedia");
                        System.out.println("Promo 'MSBRO999', untuk potongan Cash Back, tersedia");
                    } else {
                        System.out.println("Promo 'MSBRO999', untuk potongan Cash Back, tersedia");
                        System.out.println("Promo 'CPKTW110', untuk potongan diskon 50%, tersedia");
                    }
                }

                if (diskonOng.isCustomerEligible(c) && diskonOng.isMinimumPriceEligible(o) && diskonOng.isShippingFeeEligible(or)) {
                    System.out.println("Promo 'HDEHHH555', untuk potongan ongkir, tersedia");
                }

                System.out.println("\nGunakan Promo? ");
                System.out.print("Promo code : ");
                String code = sc.nextLine();
                Diskon d = new Diskon(code, berlakuSampai);

                if (d.getPromoCode().equals("CPKTW110")) {
                        if (berlakuSampai == true) {
                            System.out.println("Promo diskon 50% berhasil diaplikasikan!");
                            System.out.println("\n=============================================================================");
                            System.out.println("No. Pesanan : " + nomorPesanan);
                            System.out.println("Nama pelanggan : " + pme.getFullName());
                            pes.printDetails();
                            Applicable.PercentOffPromo diskonPersen = new Applicable.PercentOffPromo(code, berlakuSampai, 50);
                            for (int i = 0; i < jumlahPesan; i++) {
                                pc[i].display();
                            }
                            System.out.println("\n=============================================================================");
                            System.out.println("Total Belanja : " + total);
                            System.out.println("Ongkos kirim : " + pes.getOngkir());
                            System.out.println("Total : " + totalSemua);
                            System.out.print("----------------------------------\n");
                            double hargaDiskon = diskonPersen.applyPromo(totalSemua);
                            System.out.print("Total : " + hargaDiskon + "\n");
                            System.out.println("=============================================================================\n");
                        } else {
                            System.out.println("Promo expired!");
                        }

                    //diskon CashBack
                } else if (d.getPromoCode().equals("MSBRO999")) {
                        if (berlakuSampai == true) {
                            System.out.println("Promo cash back berhasil diaplikasikan!");
                            System.out.println("\n=============================================================================");
                            System.out.println("No. Pesanan : " + nomorPesanan);
                            System.out.println("Nama pelanggan : " + pme.getFullName());
                            pes.printDetails();
                            Applicable.CashBack diskonCashB = new Applicable.CashBack(code, berlakuSampai, 10000);
                            for (int i = 0; i < jumlahPesan; i++) {
                                pc[i].display();
                            }
                            System.out.println("\n=============================================================================");
                            System.out.println("Total Belanja : " + total);
                            System.out.println("Ongkos kirim : " + pes.getOngkir());
                            System.out.println("Total : " + totalSemua);
                            System.out.print("----------------------------------\n");
                            double hargaDiskon = diskonCashB.applyPromo(totalSemua);
                            System.out.print("Total : " + hargaDiskon + "\n");
                            System.out.println("=============================================================================\n");
                        } else {
                            System.out.println("Promo expired!");
                        }

                    //promo freeong
                } else if (d.getPromoCode().equals("HDEHHH555")) {
                        if (berlakuSampai == true) {
                            System.out.println("Promo ongkir berhasil diaplikasikan!");
                            System.out.println("\n=============================================================================");
                            System.out.println("No. Pesanan : " + nomorPesanan);
                            System.out.println("Nama pelanggan : " + pme.getFullName());
                            pes.printDetails();
                            Applicable.ShippingFee diskonSF = new Applicable.ShippingFee(code, berlakuSampai, 5000);
                            for (int i = 0; i < jumlahPesan; i++) {
                                pc[i].display();
                            }
                            System.out.println("\n=============================================================================");
                            System.out.println("Total Belanja : " + total);
                            System.out.println("Ongkos kirim : " + pes.getOngkir());
                            System.out.println("Total : " + totalSemua);
                            System.out.print("----------------------------------\n");
                            double hargaDiskon = diskonSF.applyPromo(totalSemua);
                            System.out.print("Total : " + hargaDiskon + "\n");
                            System.out.println("=============================================================================\n");
                        } else {
                            System.out.println("Promo expired!");
                        }
                } else {
                    System.out.println("Promo tidak ditemukan!");
                }

                System.out.println("\nPelanggan membayar? (Y/N)");
                String byr = sc.nextLine();
                if (byr.equals("Y")) {
                    System.out.print("Masukkan nomor pesanan: ");
                    int nomPesanan = sc.nextInt();
                    pb.setNomorPesanan(nomorPesanan);
                    pb.confirmPay(nomPesanan);
                    if (nomPesanan == nomorPesanan)
                        pb.pay();
                } else {
                    pb.setStatus(Pembayaran.StatusPesanan.UNPAID);
                    if (pb.getStatus() == Pembayaran.StatusPesanan.UNPAID)
                        System.out.println("Status pembayaran : belum dibayar/UNPAID");
                }

            } else if (CO.equals("N")) {
                pb.setStatus(Pembayaran.StatusPesanan.CANCELED);
                if (pb.getStatus() == Pembayaran.StatusPesanan.CANCELED)
                    System.out.println("Status pembayaran : dibatalkan/CANCELED");
            }


            //BUKAN MEMBER
        } else if (input.equals("N")){
            System.out.print("\nNama depan : ");
            String namaDepan = sc.nextLine();
            System.out.print("Nama belakang : ");
            String namaBelakang = sc.nextLine();


            Guest g = new Guest(namaDepan, namaBelakang);
            g.makeOrder();
            System.out.print("\nNomor Pesanan : ");
            int nomorPesanan = sc.nextInt();
            System.out.println("\n=========================");
            System.out.print("Jumlah pesanan : ");
            int jumlahPesan = sc.nextInt();
            g.setJumlahPesanan(jumlahPesan);

            System.out.println("=========================");
            PesananCafe[] pc = new PesananCafe[jumlahPesan];
            String[] jenisPesan = new String[jumlahPesan];
            String[] namaPesanan = new String[jumlahPesan];
            int[] jumlahPesanan = new int[jumlahPesan];
            int[] hargaPesanan = new int[jumlahPesan];
            int[] totalPesanan = new int[jumlahPesan];
            int total = 0;
            int totalSemua = 0;
            for (int i = 0; i < jumlahPesan; i++) {
                sc.nextLine();
                System.out.println("(1 = Makanan || 2 = Minuman)");
                System.out.print("Jenis pesanan : ");
                jenisPesan[i] = sc.nextLine();
                //exception code try catch=========================================================== Exception
                try {
                    System.out.print("Kode nama pesanan : ");
                    input = sc.nextLine();
                    if (input.compareTo("1") < 0 || input.compareTo("6") > 0) {
                        throw new Exception("Mohon Maaf Kode pesanan tidak tertera dalam menu");
                    }
                    namaPesanan[i] = input;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.exit(0);
                }

                //exception code try catch=========================================================== Exception
                System.out.print("Jumlah Beli : ");
                jumlahPesanan[i] = sc.nextInt();
                System.out.print("Harga : ");
                hargaPesanan[i] = sc.nextInt();
                System.out.println("----------------------------------");
                pc[i] = new PesananCafe(jenisPesan[i], namaPesanan[i], hargaPesanan[i], jumlahPesanan[i]);
                totalPesanan[i] = pc[i].getTotal();
            }
            System.out.println("\nCheck out? (Y/N)");
            sc.nextLine();
            String CO = sc.nextLine();
            if (CO.equals("Y")) {
                pes.checkOut();
                System.out.println();
                System.out.print("Harga ongkir : ");
                int Ong = sc.nextInt();
                System.out.println();
                sc.nextLine();
                System.out.println("=============================================================================");
                System.out.println("No. Pesanan : " + nomorPesanan);
                System.out.println("Nama pelanggan : " + g.getFullName());
                pes.printDetails();
                pes.setOngkir(Ong);
                for (int i = 0; i < jumlahPesan; i++) {
                    pc[i].display();
                }
                for (int i = 0; i < jumlahPesan; i++) {
                    total += totalPesanan[i];
                }
                for (int i = 0; i < jumlahPesan; i++) {
                    totalSemua = total + pes.getOngkir();
                }
                System.out.println("\n=============================================================================");
                System.out.println("Total Belanja : " + total);
                System.out.println("Ongkos kirim : " + pes.getOngkir());
                System.out.println("Total : " + totalSemua);
                System.out.println("=============================================================================\n");

                System.out.println("\nPelanggan membayar? (Y/N)");
                String byr = sc.nextLine();
                if (byr.equals("Y")) {
                    System.out.print("Masukkan nomor pesanan: ");
                    int nomPesanan = sc.nextInt();
                    pb.setNomorPesanan(nomorPesanan);
                    pb.confirmPay(nomPesanan);
                    if (nomPesanan == nomorPesanan)
                        pb.pay();
                } else {
                    pb.setStatus(Pembayaran.StatusPesanan.UNPAID);
                    if (pb.getStatus() == Pembayaran.StatusPesanan.UNPAID)
                        System.out.println("Status pembayaran : belum dibayar/UNPAID");
                }

            } else if (CO.equals("N")) {
                pb.setStatus(Pembayaran.StatusPesanan.CANCELED);
                if (pb.getStatus() == Pembayaran.StatusPesanan.CANCELED)
                    System.out.println("Status pembayaran : dibatalkan/CANCELED");
            }
        }
    }
}