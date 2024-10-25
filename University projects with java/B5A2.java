import java.util.Scanner;
import java.util.Arrays;
import java.time.Instant;
import java.time.Duration;

class B5A2 {
    // test if the data[l,r] is partitioned with pivotelement p1 at position m1, p2
    // at m2.
    // m1 <= m2 and p1 >= p2
    // data[i] >=p1 for l<=i < m1
    // p2<=data[i] <= p1 for m1 < p < m2
    // data[i] <= p2 for m2 < p <=r

    public static boolean isPartitioned(int[] data, int l, int r, int m1, int p1, int m2, int p2) {

        for (int i = l; i < m1; i++) {
            if (data[i] > p1) {
                return false;
            }
        }
        for (int i = m1; i < m2; i++) {
            if (data[i] < p2 || data[i] > p1) {
                return false;
            }
        }
        for (int i = m2; i <= r; i++) {
            if (data[i] < p2) {
                return false;
            }
        }
        return true;

    }

    // l,r inclusive -> partition data[l,r]
    public static int[] partition(int[] data, int l, int r) {

        int p1 = data[l];
        int p2 = data[r];

        if (p1 > p2) {
            int temp = p1;
            p1 = p2;
            p2 = temp;
        }

        int i = l;
        int m1 = l;
        int j = r;
        int m2 = r;

        while (i <= j) {
            if (data[i] < p1) {
                int temp = data[i];
                data[i] = data[m1];
                data[m1] = temp;
                m1++;
                i++;
            } else if (data[i] > p2) {
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
                j--;
            } else {
                i++;
            }
        }
        // Überprüfen, ob die Partitionierung korrekt durchgeführt wurde
        assert isPartitioned(data, l, r, m1, p1, m2, p2);

        return new int[] {m1, m2};
    }

    // l,r inclusive -> sort data[l,r]
    public static void qsort(int[] data, int l, int r) {

        if (l < r) {
            int[] pivots = partition(data, l, r);
            int m1 = pivots[0];
            int m2 = pivots[1];
            qsort(data, l, m1 - 1);
            qsort(data, m1 + 1, m2 - 1);
            qsort(data, m2 + 1, r);
        }

    }

    // check if the array sorted discreading.
    public static boolean isSorted(int[] data) {
        // Überprüfen, ob das Array absteigend sortiert ist
        for (int i = 1; i < data.length; i++) {
            if (data[i - 1] < data[i]) {
                return false; // Das Array ist nicht absteigend sortiert
            }
        }
        return true; // Das Array ist absteigend sortiert
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Initialize the scanner and read the amount of expected integers
        int n = input.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        input.close();

        // Drucken, wenn die Länge for dem Sortieren weniger als 20 beträgt
        if (arr.length < 20) {
            System.out.println("Input Array:");
            System.out.println(Arrays.toString(arr));
        }

        qsort(arr, 0, arr.length - 1);

        // Überprüfe, ob das Array absteigend sortiert ist
        assert isSorted(arr) : "Array is not sorted in descending order";

        // Ausgabe des sortierten Arrays
        System.out.println("Sorted Array:");
        System.out.println(Arrays.toString(arr));
    }
}