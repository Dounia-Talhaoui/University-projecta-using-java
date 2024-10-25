import java.util.Arrays;
import java.util.Scanner;

class B3A1 {
    public static int removeDuplicates(int[] data) {

        Arrays.sort(data); 
        int n = data.length;
        int distinct = 0; 
        for (int i = 0; i < n; i++) {
            if (i == 0 || data[i] != data[i - 1]) {
            data[distinct++] = data[i]; 
        }
    }
        return distinct; 

    }

    public static void main(String[] args) {
        if (args.length == 0) {
    System.out.println("Bitte geben Sie die Anzahl der erwarteten ganzen Zahlen als Argument ein.");
    return; // Beende das Programm
}

int k = Integer.parseInt(args[0]);

        Scanner input = new Scanner(System.in);
        // Initialize the scanner and read the amount of expected integers
        int n = input.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        input.close();
        System.out.print("Before removing duplicates: ");
        System.out.println(Arrays.toString(arr));
        int distinct = removeDuplicates(arr);

        if (distinct < k) {
        k = distinct; // Wenn es weniger verschiedene Elemente gibt als k, setzen wir k auf die Anzahl der verschiedenen Elemente
}

        int subsetsCount = 0; 

        for (int i = 0; i < (1 << distinct); i++) {
            if (Integer.bitCount(i) == k) {
        System.out.print("["); 
        int printed = 0; 
        for (int j = 0; j < distinct; j++) {
            if ((i & (1 << j)) > 0) { 
                if (printed > 0) {
                    System.out.print(", "); 
                }
                System.out.print(arr[j]); 
                printed++; 
            }
        }
        System.out.println("]"); 
        subsetsCount++; 
    }
}

System.out.println("Number of subsets: " + subsetsCount); // Wir geben die Gesamtanzahl der Teilmengen aus

    }

}