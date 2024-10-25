import java.util.Scanner;

class B11A2 {

    public static void SquareCounting(Integer[][] M, int n, int m) {
        int[][] dp = new int[n][m];
        int[] counts = new int[Math.min(n, m) + 1];
        int totalSquares = 0;

        // Initialisieren und dynamisches Programm ausführen
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (M[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    counts[dp[i][j]]++;
                }
            }
        }

        // Anzahl der quadratischen Untermatrizen für jede Größe berechnen und summieren
        for (int size = Math.min(n, m); size > 0; size--) {
            totalSquares += counts[size];
            counts[size - 1] += counts[size];
        }

        // Ausgabe der Gesamtanzahl der Untermatrizen
        System.out.println("Anzahl Untermatrizen: " + totalSquares);

        // Ausgabe der Anzahl der Untermatrizen für jede Größe
        for (int size = 1; size <= Math.min(n, m); size++) {
            System.out.println("Anzahl " + size + "-Untermatrizen: " + counts[size]);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Initialize the scanner and read the amount of expected integers
        int n = input.nextInt();
        int m = input.nextInt();
        Integer[][] M = new Integer[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                M[i][j] = input.nextInt();
            }
        }
        input.close();

        SquareCounting(M, n, m);
    }
}