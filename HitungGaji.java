import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Scanner;

public class HitungGaji {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Array Gaji pokok
    int[] gajiGolongan = { 5000000, 6500000, 9500000 };

    // Array persentase gaji lembur
    double[] persentaseLembur = { 0.0, 0.30, 0.32, 0.34, 0.36, 0.38 };

    // Input
    System.out.println("Input golongan (A/B/C): ");
    String golongan = scanner.nextLine().toUpperCase();

    if (!golongan.equals("A") && !golongan.equals("B") && !golongan.equals("C")) {
      System.out.println("Golongan tidak valid! Mohon masukkan A, B, atau C.");
      scanner.close();
      return;
    }

    System.out.println("Input jam lembur: ");
    int jamLembur = 0;

    try {
      jamLembur = scanner.nextInt();
      if (jamLembur < 0) {
        System.out.println("Jam lembur tidak valid! Tidak menerima bilangan negatif.");
        scanner.close();
        return;
      }
    } catch (Exception e) {
      System.out.println("Jam lembur tidak valid! Mohon masukkan angka yang benar.");
      scanner.close();
      return;
    }

    // Pembulatan jam lembur
    int jamLemburBulat = (int) jamLembur;

    // Tentukan gaji pokok tiap golongan
    int gajiPokok = 0;
    if (golongan.equals("A")) {
      gajiPokok = gajiGolongan[0];
    } else if (golongan.equals("B")) {
      gajiPokok = gajiGolongan[1];
    } else if (golongan.equals("C")) {
      gajiPokok = gajiGolongan[2];
    } else {
      System.out.println("Golongan tidak valid!");
      scanner.close();
      return;
    }

    // Hitung gaji lembur berdasarkan jamLemburBulat
    double gajiLembur = 0;
    if (jamLemburBulat <= 0) {
      gajiLembur = persentaseLembur[0] * gajiPokok;
    } else if (jamLemburBulat >= 5) {
      gajiLembur = persentaseLembur[5] * gajiPokok;
    } else {
      gajiLembur = persentaseLembur[jamLemburBulat] * gajiPokok;
    }

    // hitung total gaji
    double totalGaji = gajiPokok + gajiLembur;

    // Output
    System.out.println(
        "Jumlah penghasilan: " +
            formatRupiah(gajiPokok) + " + " +
            formatRupiah(gajiLembur) + " = " +
            formatRupiah(totalGaji));

    scanner.close();
  }

  public static String formatRupiah(Object amount) {
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    symbols.setGroupingSeparator('.');
    symbols.setDecimalSeparator(',');

    DecimalFormat formatter = new DecimalFormat("Rp#,##0.00", symbols);
    return formatter.format(amount);
  }
}
