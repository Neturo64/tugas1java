// mengimport scanner atau fungsi input
import java.util.Scanner;


// membuat class menu untuk menyimpan data menu seperti nama, harga, dan kategori
class Menu {
    String name;
    double price;
    String category;

    Menu(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}

// membuat class main untuk menjalankan program
public class Main {
    // membuat method main untuk menjalankan program
    public static void main(String[] args) {
        // membuat scanner untuk input dengan try agar scanner otomatis ditutup dan tidak memory leak
        try (Scanner scanner = new Scanner(System.in)) {
            // Inisialisasi data menu
            Menu[] menus = {
                    new Menu("Nasi Padang", 25000, "makanan"),
                    new Menu("Sate Ayam", 20000, "makanan"),
                    new Menu("Es Teh Manis", 5000, "minuman"),
                    new Menu("Nasi Goreng", 22000, "makanan"),
                    new Menu("Es Jeruk", 5000, "minuman"),
                    new Menu("Mie Goreng", 23000, "makanan"),
            };

            // input maksimal 4 pesanan
            String[] pesanan = new String[4];
            double totalBiaya = 0;
            int minumanCount = 0;

            // Looping input pesanan jika pesanan kosong
            boolean pesananKosong;
            do {
                pesananKosong = false;

                // Tampilkan menu
                System.out.println("Daftar Menu Restoran:");
                for (Menu menu : menus) {
                    System.out.println(menu.name + " (" + menu.category + ") - Rp. " + menu.price);
                }

                System.out.println("Silakan masukkan pesanan Anda (maksimal 4 menu). Format: NamaMenu = Jumlah");

                // Looping input pesanan sampai maksimal 4 pesanan
                for (int i = 0; i < 4; i++) {
                    System.out.print("Pesanan ke-" + (i + 1) + ": ");
                    String pesananInput = scanner.nextLine();

                    // Jika pesanan tidak kosong, kita lakukan perhitungan
                    if (!pesananInput.isEmpty()) {
                        pesanan[i] = pesananInput;
                        String[] pesananSplit = pesananInput.split(" = ");
                        String namaMenu = pesananSplit[0];
                        int jumlah = Integer.parseInt(pesananSplit[1]);

                        // mencari harga menu dan harga berdasarkan nama menu
                        for (Menu menu : menus) {
                            if (menu.name.equals(namaMenu)) {
                                double subtotal = menu.price * jumlah;
                                totalBiaya += subtotal;
                                pesanan[i] = namaMenu + " = " + jumlah + " x Rp." + menu.price + " = Rp. " + subtotal;
                                if (menu.category.equals("minuman")) {
                                    minumanCount += jumlah;
                                }
                                break;
                            }
                        }
                        // jika pesanan ke-1 kosong, kita lanjutkan looping ke awal
                    } else if (i == 0) {
                        pesananKosong = true;
                        System.out.println("Pesanan ke-1 tidak boleh kosong. Silakan pesan kembali.");
                        break;
                    } else {
                        // Pesanan ke-2, ke-3, atau ke-4 kosong, kita keluar dari loop
                        break;
                    }
                }
            } while (pesananKosong);

            // Lanjutkan dengan perhitungan dan mencetak struk seperti sebelumnya
            double diskon = 0;

            if (totalBiaya > 100000) {
                diskon = 0.1 * totalBiaya;
            }

            double totalBiayaSetelahDiskon = totalBiaya - diskon;
            double pajak = 0.1 * totalBiayaSetelahDiskon;

            System.out.println("\nStruk Pesanan:");
            for (String pesananItem : pesanan) {
                if (pesananItem != null) {
                    System.out.println(pesananItem);
                }
            }

            System.out.println("Total Biaya Pesanan: Rp. " + totalBiaya);

            if (totalBiaya > 50000 && minumanCount > 0) {
                System.out.println("Anda dapat penawaran beli 1 gratis 1 minuman untuk pembelian diatas Rp. 50000.0.");
            }

            if (diskon > 0) {
                System.out.println("Diskon 10% telah diberikan karena total pesanan di atas Rp. 100000.0.");
                System.out.println("Potongan Diskon: - Rp. " + diskon);
                System.out.println("Total Biaya Pesanan Setelah Diskon: Rp. " + totalBiayaSetelahDiskon);
            }

            System.out.println("Pajak (10%): Rp. " + pajak);
            System.out.println("Biaya Pelayanan: Rp. 20000.0");
            double totalHarga = totalBiayaSetelahDiskon + pajak + 20000;
            System.out.println("Total: Rp. " + totalHarga);
        }
    }
}
