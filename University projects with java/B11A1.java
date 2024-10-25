import java.util.Scanner;
import java.util.LinkedList;

class B11A1 {

    public static int disjoint(Integer[] A) {
        int n = A.length;

        // Sonderfälle abfangen
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return A[0];
        }

        int[] maxDisjoint = new int[n];

        // Initialisierung
        maxDisjoint[0] = A[0];
        maxDisjoint[1] = Math.max(A[0], A[1]);

        // Arraysuche
        for (int i = 2; i < n; i++){
            maxDisjoint[i] = Math.max(maxDisjoint[i-1], A[i] + maxDisjoint[i-2]);
        }

        return maxDisjoint[n-1];
    }

    public static LinkedList<Integer> disjoint3(Integer[] A) {
        int n = A.length;

        // Sonderfälle abfangen
        if (n == 0) {
            return new LinkedList<>();
        }
        if (n == 1) {
            LinkedList<Integer> solution = new LinkedList<>();
            solution.add(A[0]);
            return solution;
        }
        if (n == 2) {
            LinkedList<Integer> solution = new LinkedList<>();
            if (A[0] > A[1]) {
                solution.add(A[0]);
            } else {
                solution.add(A[1]);
            }
            return solution;
        }

        int[] maxDisjoint = new int[n];
        int[] prevIndex = new int[n];

        // Initialisierung
        maxDisjoint[0] = A[0];
        maxDisjoint[1] = Math.max(A[0], A[1]);
        maxDisjoint[2] = Math.max(maxDisjoint[1], A[2] + maxDisjoint[0]);

        prevIndex[0] = -1;
        prevIndex[1] = (A[0] > A[1]) ? 0 : 1;
        prevIndex[2] = (A[2] + maxDisjoint[0] > maxDisjoint[1]) ? 0 : 1;

        for (int i = 3; i < n; i++) {
            int option1 = maxDisjoint[i - 1];
            int option2 = A[i] + maxDisjoint[i - 2];
            int option3 = A[i] + maxDisjoint[i - 3];

            if (option1 >= option2 && option1 >= option3) {
                maxDisjoint[i] = option1;
                prevIndex[i] = i - 1;
            } else if (option2 >= option3) {
                maxDisjoint[i] = option2;
                prevIndex[i] = i - 2;
            } else {
                maxDisjoint[i] = option3;
                prevIndex[i] = i - 3;
            }
        }

        // Rückverfolgung der Lösung
        LinkedList<Integer> solution = new LinkedList<>();
        for (int i = n - 1; i >= 0;) {
            if (prevIndex[i] == i - 1) {
                i--;
            } else {
                solution.addFirst(A[i]);
                if (prevIndex[i] == i - 3) {
                    i -= 3;
                } else {
                    i -= 2;
                }
            }
        }

        return solution;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Initialize the scanner and read the amount of expected integers
        int n = input.nextInt();
        Integer[] A = new Integer[n];

        for (int i = 0; i < n; i++) {
            A[i] = input.nextInt();
        }
        input.close();

        System.out.println("Strikt disjunkt: " + disjoint(A));


        // Diesen Kommentar für 11.1B hinzufügen
        LinkedList<Integer> solution = disjoint3(A);
        int sum = 0;
        for (Integer item : solution) { sum += item; }
        System.out.print("Strikt 3-disjunkt: " + solution.size() + " " + sum + " | ");

        for (Integer item : solution) {
            System.out.print(item + " ");
        }
        System.out.println();

    }
}

