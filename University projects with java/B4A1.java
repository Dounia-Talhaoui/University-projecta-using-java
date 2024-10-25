import java.util.Arrays;
import java.util.Scanner;

class B4A1 {
    // Hilfsfunktion zum Vertauschen zweier Werte
    public static void swap(int[] data, int i, int j) {
        int h = data[i];
        data[i] = data[j];
        data[j] = h;
    }

    public static void maxHeapifyUp(int[] data, int i) {
        int parent = (i - 1) / 2;
        while (i > 0 && data[i] > data[parent]) {
            swap(data, i, parent);
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    public static void maxHeapifyDown(int[] data, int n) {
        int i = 0;
        while (2 * i + 1 < n) {
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;
            int maxChild = leftChild;
            if (rightChild < n && data[rightChild] > data[leftChild]) {
                maxChild = rightChild;
            }
            if (data[i] < data[maxChild]) {
                swap(data, i, maxChild);
                i = maxChild;
            } else {
                break;
            }
        }
    }

    public static void buildMaxHeap(int[] data) {
        for (int i = data.length / 2 - 1; i >= 0; i--) {
            maxHeapifyDown(data, data.length);
        }
    }

    public static int extractMax(int[] data, int n) {
        int max = data[0];
        data[0] = data[n - 1];
        maxHeapifyDown(data, n - 1);
        return max;
    }

    public static int heapSelect(int[] data, int k) {
        buildMaxHeap(data);
        int n = data.length;
        for (int i = 0; i < k - 1; i++) {
            extractMax(data, n - i);
        }
        return data[0];
    }

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        Scanner input = new Scanner(System.in);
        // Initialize the scanner and read the amount of expected integers
        int n = input.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        input.close();
        System.out.print("Input Array: ");
        System.out.println(Arrays.toString(arr));

        int v = heapSelect(arr, k);
        System.out.println("The " + k + "-smallest element is " + v);

    }
}