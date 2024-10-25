import java.util.Arrays;
import java.util.Scanner;

class B6A1 {
    public static int getMin(int[] data) {
        int min = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] < min) {
                min = data[i];
            }
        }
        return min;

    }

    public static int getMax(int[] data) {
        int max = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        return max;

    }

    public static int[] count(int[] data, int min, int max) {

        int C[] = new int[max - min + 1];

        for (int i = 0; i < data.length; i++) {
            C[data[i] - min]++;
        }
        return C;
    }

    public static int[] countingSort(int[] data) {
        if (data == null || data.length == 0) {
            return new int[0];
        }

        // Schritt 1: Bestimme min und max Werte
        int min = getMin(data);
        int max = getMax(data);

        // Schritt 2: Zählen der Häufigkeiten der Werte
        int[] count = count(data, min, max);

        // Schritt 3: Erstelle das Ausgabearray in absteigender Reihenfolge
        int B[] = new int[data.length];
        int index = 0;
        for (int i = count.length - 1; i >= 0; i--) {
            while (count[i] > 0) {
                B[index++] = i + min;
                count[i]--;
            }
        }
        return B;
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
        int min = getMin(arr);
        System.out.printf("The minimum value: %d\n", min);
        int max = getMax(arr);
        System.out.printf("The minimum value: %d\n", max);
        int[] count = count(arr, min, max);
        System.out.print("Frequencies: ");
        System.out.println(Arrays.toString(count));
        int[] output = countingSort(arr);
        System.out.print("After sorting: ");
        System.out.println(Arrays.toString(output));
    }
}
