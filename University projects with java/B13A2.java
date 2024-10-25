import java.util.Scanner;

public class B13A2 {

    static void floyd_warshall(Graph graph)
    {

        int n = graph.getN();
        int[][] dist = new int[n][n];

        // Initialisieren der Distanzmatrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = graph.getWeight(i, j);
            }
        }

        // Floyd-Warshall Algorithmus
        for (int k = 0; k < n; k++) { // in-between Matrix
            for (int i = 0; i < n; i++) { // die zu behandelnde Reihe
                for (int j = 0; j < n; j++) { // die zu behandelnde Spalte
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&
                        dist[i][k] + dist[k][j] < dist[i][j]) { // wenn wir uns zwei Pfade anschauen und diese addieren wollen, darf keines von beiden unendlich sein, ansonsten Abbruch
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Prüfen auf negative Zyklen
        boolean hasNegativeCycle = false;
        for (int i = 0; i < n; i++) {
            if (dist[i][i] < 0) {
                hasNegativeCycle = true;
                break;
            }
        }

        // Ausgabe der Ergebnisse
        if (hasNegativeCycle) {
            System.out.println("Negativen Zyklus entdeckt!");
        } else {
            for (int i = 0; i < n; i++) {
                System.out.print(i + ":");
                boolean hasConnections = false;
                for (int j = 0; j < n; j++) {
                    if (i != j && dist[i][j] != Integer.MAX_VALUE) {
                        System.out.print(" (" + j + ", " + dist[i][j] + ")");
                        hasConnections = true;
                    }
                }
                if (!hasConnections) {
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
    }



    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        // Initialize the scanner and read the amount of expected integers
        int n = input.nextInt();
        int m = input.nextInt();

        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            int s, t, d;
            s = input.nextInt();
            t = input.nextInt();
            d = input.nextInt();

            graph.addDirEdge(s, t, d);
        }

        floyd_warshall(graph);
        input.close();
    }
}