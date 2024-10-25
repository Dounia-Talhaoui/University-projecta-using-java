import java.util.PriorityQueue;
import java.util.Scanner;

class Graph {
    private int n, m;
    private int[][] weights;

    Graph(int n) {
        this.n = n;
        this.m = 0;

        weights = new int[n][n];

        for (int i = 0; i < n; i++) {
            weights[i] = new int[n];
            for (int j = 0; j < n; j++) {
                weights[i][j] = Integer.MAX_VALUE;
            }

            weights[i][i] = 0;
        }
    }

    int getN() {
        return n;
    }

    int getM() {
        return m;
    }

    int getWeight(int src, int dst) {
        return weights[src][dst];
    }

    void addEdge(int src, int dst, int weight) {
        m++;
        weights[src][dst] = weight;
        weights[dst][src] = weight;
    }

    void addDirEdge(int src, int dst, int weight) {
        m++;
        weights[src][dst] = weight;
    }

    void print() {
        for (int i = 0; i < n; i++) {
            System.out.print(i + ": ");

            for (int j = 0; j < n; j++) {
                int weight = getWeight(i, j);
                if (weight > 0) {
                    System.out.print(j + " (" + weight + ")");
                }
            }

            System.out.println();
        }
    }
}

class Pair implements Comparable<Pair> {

    public int a;
    public int b;

    public Pair() {
    }

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Pair other) {
        return Integer.compare(this.a, other.a);
    }
}

class B13A1 {
    static void dijkstra(Graph graph, int s) {
        int n = graph.getN();
        int[] distance = new int[n];
        boolean[] visited = new boolean[n];

        // Initialisiere alle Entfernungen mit unendlich und setze Startknoten auf 0
        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[s] = 0;

        // Erzeuge eine Prioritätswarteschlange und füge den Startknoten hinzu
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, s));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int u = current.b;

            // Überspringe bereits besuchte Knoten
            if (visited[u]) continue;
            visited[u] = true;

            // Gehe alle Nachbarn von u durch
            for (int v = 0; v < n; v++) {
                if (graph.getWeight(u, v) < Integer.MAX_VALUE) {
                    int weight = graph.getWeight(u, v);

                    // Aktualisiere die Distanz, falls ein kürzerer Weg gefunden wird
                    if (distance[u] + weight < distance[v]) {
                        distance[v] = distance[u] + weight;
                        pq.add(new Pair(distance[v], v));
                    }
                }
            }
        }

        // Ausgabe der kürzesten Entfernungen
        for (int i = 0; i < n; i++) {
            if (distance[i] != Integer.MAX_VALUE) {
                System.out.println(i + ": " + distance[i]);
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

            graph.addEdge(s, t, d);
        }

        String command;
        while (true) {
            System.out.print("Enter command (s to search, q to quit): ");
            command = input.next();
            if (command.equals("s")) {
                int s = input.nextInt();
                dijkstra(graph, s);
            } else if (command.equals("q")) {
                break;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }
        input.close();
    }
}
