import java.util.Scanner;
import java.util.Arrays;
import java.time.Instant;
import java.time.Duration;

class B5A1 {
    // test if the data[l,r] is partitioned with pivotelement p at position m.
    // data[i] >=p for l<=i < m
    // data[i] <= p for m < i <=r
    public static boolean isPartitioned(int[] data, int l, int r, int m, int p) {
        // Überprüfen, ob die Partitionierung gültig ist
        for (int i = l; i < m; i++) {
            if (data[i] > p) {
                return false; // Die linke Partition enthält ein Element größer als das Pivot-Element
            }
        }
        for (int i = m; i <= r; i++) {
            if (data[i] <= p) {
                return false; // Die rechte Partition enthält ein Element kleiner oder gleich dem Pivot-Element
            }
        }
        return true; // Die Partitionierung ist gültig
    }


    // l,r inclusive -> partition data[l,r]
    public static int partition(int[] data, int l, int r) {

        // dijkstra partition
        int pivot = data[ l ];

        int position = r;
        int i = 0;
        int j = 0;

        while(j <= position) {

            if ( data[ j ] > pivot ) { // pushing elements to the left of Pivot if they are bigger

                // swapping data[i] and data[position]
                int temp = data[ i ];
                data[ i ] = data[ j ];
                data[ j ] = temp;

                i++;
                j++;

            } else if ( data[ j ] < pivot ) { // pushing elements to the right of pivot if they are smaller
                // swapping data[j] and data[position]
                int temp = data[ j ];
                data[ j ] = data[ position ];
                data[ position ] = temp;
                position -= 1;

            } else {
                j++;
            }


        }

        // Überprüfen, ob die Partitionierung korrekt durchgeführt wurde
        assert isPartitioned(data, l, r, i, pivot);

        return position;
    }

    // l,r inclusive -> sort data[l,r]
    public static void qsort(int[] data, int l, int r) {

        if ( l < r ) { // only sorts if there is more than one element in partition.
            int pivot = partition( data , l , r );
            qsort( data , l , pivot - 1 ); // sorting lower half
            qsort( data , pivot + 1 , r ); // sorting lower half
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

        // Starte die Zeitmessung
        Instant start = Instant.now();

    // Sortiere das Array
    qsort(arr, 0, arr.length - 1);

    // Überprüfe, ob das Array absteigend sortiert ist
    assert isSorted(arr) : "Array is not sorted in descending order";

    // Beende die Zeitmessung
    Instant finish = Instant.now();

    // Berechne die Dauer des Sortierens
    long time = Duration.between(start, finish).toMillis();

    // Ausgabe des sortierten Arrays
    System.out.println("After sorting:");
    System.out.println(Arrays.toString(arr));

    // Ausgabe der Dauer des Sortierens
    System.out.println("Time: " + time);
    }
}