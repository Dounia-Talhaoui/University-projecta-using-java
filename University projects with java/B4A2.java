import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

class B4A2 {
    // Hilfsfunktion zum Vertauschen zweier Werte
    public static void swap(int[] data, int i, int j) {
        int h = data[i];
        data[i] = data[j];
        data[j] = h;
    }

    // Hilfsfunktion zum Berechnen von n!
    public static int fact(int n) {
        int fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }

    public static void updatePermutation(int[] numbers, int[] counters) {
        int n = numbers.length;
        int i = 0;
        while (i < n) {
            if (counters[i] < i) {
                if (i % 2 == 0) {
                    swap(numbers, 0, i);
                } else {
                    swap(numbers, i, counters[i]);
                }
                counters[i]++;
                break;
            } else {
                counters[i] = 0;
                i++;
            }
        }
    }

    public static void shufflePermutation(int[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--) {
            int j = ThreadLocalRandom.current().nextInt(i + 1);
            // Tausche numbers[i] mit numbers[j]
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
    }

    public static int insertionSort(int[] numbers) {
        int shifts = 0; // Anzahl der Verschiebungen
        for (int i = 1; i < numbers.length; i++) {
            int key = numbers[i];
            int j = i - 1;
            while (j >= 0 && numbers[j] > key) {
                numbers[j + 1] = numbers[j];
                j--;
                shifts++; // Eine Verschiebung durchgeführt
            }
            numbers[j + 1] = key;
        }
        return shifts;
    }


    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);

        if (n <= 10) {
            // Algorithmus von Heap verwenden, um alle möglichen Permutationen von n Ganzzahlen zu berechnen
            int[] numbers = new int[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = i + 1;
            }

            // Erzeuge ein Array, um den Fortschritt beim Generieren der Permutationen zu verfolgen
            int[] counters = new int[n];

            // Gesamtanzahl der Permutationen berechnen
            int totalPermutations = fact(n);

            int totalShifts = 0; // Summe der Verschiebungen für alle Permutationen
            int worstShifts = 0; // Schlimmste Anzahl von Verschiebungen

            for (int count = 0; count < totalPermutations; count++) {
                // Eine Permutation generieren
                updatePermutation(numbers, counters);
                // Eine Verschiebung durchführen und deren Anzahl ausgeben
                int shifts = insertionSort(numbers);
                // Aktualisiere die Summe der Verschiebungen für alle Permutationen
                totalShifts += shifts;
                // Aktualisiere die schlimmste Anzahl von Verschiebungen
                if (shifts > worstShifts) {
                    worstShifts = shifts;
                }
            }

            // Berechne den Durchschnitt der Verschiebungen
            double averageShifts = (double) totalShifts / totalPermutations;

            // Gib die Ergebnisse aus
            System.out.println("Durchschnittliche Anzahl von Verschiebungen: " + averageShifts);
            System.out.println("Schlimmste Anzahl von Verschiebungen: " + worstShifts);
        } else {
            // shufflePermutation verwenden, um k zufällige Permutationen zu berechnen und zu sortieren
            int k = Integer.parseInt(args[1]);
            int totalShifts = 0; // Summe der Verschiebungen für alle Permutationen

            for (int i = 0; i < k; i++) {
                int[] numbers = new int[n];
                for (int j = 0; j < n; j++) {
                    numbers[j] = j + 1;
                }

                // Zufällige Anordnung des Arrays
                shufflePermutation(numbers);

                // Eine Verschiebung durchführen und deren Anzahl ausgeben
                int shifts = insertionSort(numbers);
                // Aktualisiere die Summe der Verschiebungen für alle Permutationen
                totalShifts += shifts;
            }

            // Berechne den Durchschnitt der Verschiebungen
            double averageShifts = (double) totalShifts / k;

            // Gib die Ergebnisse aus
            System.out.println("Durchschnittliche Anzahl von Verschiebungen fuer " + k + " zufaellige Permutationen: " + averageShifts);
        }
    }



}