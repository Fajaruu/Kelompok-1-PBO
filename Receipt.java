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
                System.out.print("Kode nama pesanan : ");
                namaPesanan[i] = sc.nextLine();
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
                double hargaPersenOff = diskonPersenOff.applyPromo(totalSemua);
                Applicable.CashBack diskonCB = new Applicable.CashBack("MSBRO999", berlakuSampai, 10000);
                double hargaCB = diskonCB.applyPromo(totalSemua);
                Applicable.ShippingFee diskonOng = new Applicable.ShippingFee("HDEHH55", berlakuSampai, 3000);
                double hargaFreeOng = diskonOng.applyPromo(totalSemua);

                //Pake ArrayList
                List<Promotion> promo = new ArrayList<>();
                promo.add(new Applicable.PercentOffPromo("CPKTW110", berlakuSampai, 50)); //3
                promo.add(new Applicable.CashBack("MSBRO999", berlakuSampai, 10000)); //2
                promo.add(new Applicable.ShippingFee("HDEHHH555", berlakuSampai, 500000)); //2


                Collections.sort(promo);

                for (Promotion p : promo) {
                    if (p.getPromoCode() == "CPKTW110") {
                        System.out.println("Kode diskon : " + p.getPromoCode() + ", Potongan sebesar " + p.getDiskon() + "%");
                    } else if (p.getPromoCode() == "MSBRO999") {
                        System.out.println("Kode diskon : " + p.getPromoCode() + ", Potongan cash back sebesar " + p.getDiskon() + " ribu");
                    } else if (p.getPromoCode() == "HDEHHH555") {
                        System.out.println("Kode diskon : " + p.getPromoCode() + ", Potongan bebas ongkir sebesar " + p.getDiskon()/100 + " ribu");
                    }
                }

                //NGGAK PAKE ArrayList
//                //dari yang paling besar ke paling kecil
//                System.out.println("Diskon yang tersedia : ");
//                if (Double.compare(hargaPersenOff, hargaCB) >= 0 && Double.compare(hargaPersenOff, hargaFreeOng) >= 0) {
//                    System.out.println("Promo 'CPKTW110' diskon 50% : " + hargaPersenOff);
//                    System.out.println("Promo 'MSBRO999' Cash Back : " + hargaCB);
//                    System.out.println("Promo 'HDEHHH555' bebas ongkir : " + hargaFreeOng);
//                } else if (Double.compare(hargaCB, hargaFreeOng) >= 0) {
//                    System.out.println("Promo 'MSBRO999' Cash Back : " + hargaCB);
//                    System.out.println("Promo 'CPKTW110' diskon 50% : " + hargaPersenOff);
//                    System.out.println("Promo 'HDEHHH555' bebas ongkir : " + hargaFreeOng);
//                } else {
//                    System.out.println("Promo 'HDEHHH555' bebas ongkir : " + hargaFreeOng);
//                    System.out.println("Promo 'MSBRO999' Cash Back : " + hargaCB);
//                    System.out.println("Promo 'CPKTW110' diskon 50% : " + hargaPersenOff);
//                }

                System.out.println("\nGunakan Promo? ");
                System.out.print("Promo code : ");
                String code = sc.nextLine();

                Customer c = new Customer(pme.getFullName(), lamaMember);
                Order o = new Order(totalSemua, 40000);
                Order or = new Order(Integer.toString(Ong), Integer.toString(5000));
                Diskon d = new Diskon(code, berlakuSampai);

                if (d.getPromoCode().equals("CPKTW110")) {
                    if (diskonPersenOff.isCustomerEligible(c) && diskonPersenOff.isMinimumPriceEligible(o)) {
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
                    } else {
                        System.out.println("Promo tidak memenuhi syarat ketentuan!");
                    }

                    //diskon CashBack
                } else if (d.getPromoCode().equals("MSBRO999")) {
                    if (diskonCB.isCustomerEligible(c) && diskonCB.isMinimumPriceEligible(o)) {
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
                    } else {
                        System.out.println("Promo tidak memenuhi syarat ketentuan!");
                    }

                    //promo freeong
                } else if (d.getPromoCode().equals("HDEHHH555")) {
                    if (diskonOng.isCustomerEligible(c) && diskonOng.isMinimumPriceEligible(o) && diskonOng.isShippingFeeEligible(or)) {
                        if (berlakuSampai == true) {
                            System.out.println("Promo cash back berhasil diaplikasikan!");
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
                        System.out.println("Promo tidak memenuhi syarat ketentuan!");
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
            sc.nextLine();

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
                System.out.print("Kode nama pesanan : ");
                namaPesanan[i] = sc.nextLine();
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