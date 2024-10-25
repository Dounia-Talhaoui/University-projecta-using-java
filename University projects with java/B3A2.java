import java.util.Arrays;
import java.util.Scanner;

class B3A2 {

    // Methode, um die k-kleinste Permutation zu finden
    public static int[] find(int[] arr, int k) {
        int n = arr.length;
        int[] result = new int[n];
        // Basisfall: Wenn das Array nur ein Element hat, ist das die Permutation
        if (n == 1) {
            return arr;
        }

// Finde die kleinste Permutation für das restliche Array
        int index = k / factorial(n-1);
        int[] next = find(arr, k - index * factorial(n - 1));

        // Setze die restlichen Elemente des Ergebnisarrays
        result[0] = arr[index];
        for (int i = 0; i < n - 1; i++) {
            result[i + 1] = next[i];
        }

        return result;
    }

 // Hilfsmethode zur Berechnung der Fakultät
    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        input.close();

        Arrays.sort(arr);
        System.err.println("Sorted input:");
        System.out.println(Arrays.toString(arr));
        System.err.println("The " + k + "-smallest permutation is:");

         int[] result = find(arr, k);
        System.out.println(Arrays.toString(result));
    }
}