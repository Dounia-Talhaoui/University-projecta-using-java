import java.util.Scanner;
import java.util.Arrays;

class B6A2 {

    public static void sortByByte(int[] input, int l, int r, int b) {
        // Frequenzarray für Bytewerte (0-255)
        int[] count = new int[256];
        int[] output = new int[r - l + 1];

        // Frequenz jedes Bytes im Bereich [l, r] berechnen
        for (int i = l; i <= r; i++) {
            int byteValue = (input[i] >> (8 * b)) & 0xFF;
            count[byteValue]++;
        }

        // Kumulative Frequenz berechnen
        for (int i = 254; i >= 0; i--) {
            count[i] += count[i + 1];
        }

        // Das Array stabil sortieren (absteigend)
        for (int i = r; i >= l; i--) {
            int byteValue = (input[i] >> (8 * b)) & 0xFF;
            output[--count[byteValue]] = input[i];
        }

        // Die sortierten Werte zurück ins Originalarray kopieren
        for (int i = l; i <= r; i++) {
            input[i] = output[i - l];
        }
    }

    public void radix(int[] data) {
        int l = 0;
        int r = data.length - 1;

        // Sortiere jedes Byte von LSD zu MSD
        for (int b = 0; b < 4; b++) {
            sortByByte(data, l, r, b);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Initialize the scanner and read the amount of expected integers
        int n = input.nextInt();
        assert n > 0 : "Error: length of the Input Array < 1";
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        input.close();
        System.out.print("Input Array: ");
        System.out.println(Arrays.toString(arr));
        B6A2 Sorter = new B6A2();
        Sorter.radix(arr);
        System.out.println("After Sorting: " + Arrays.toString(arr));
    }

}
