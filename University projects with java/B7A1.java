import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class B7A1 {
    // Hashtabelle als Array von verketteten Listen
    private ArrayList<LinkedList<Pair>> table;
    private int m;

    // Constructor
    public B7A1(int m) {
        this.m = m;
        table = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            table.add(new LinkedList<>());
        }
    }

    // Private inner class to represent key-value pairs
    private static class Pair {
        Integer key;
        Integer value;

        Pair(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    // Private helper method to calculate the index of the linked list based on key
    private int addressOfList(Integer key) {
        return Math.floorMod(key, m);
    }

    // Public method to insert a key-value pair into the hash table
    public void insert(Integer key, Integer value) {
        int index = addressOfList(key);
        LinkedList<Pair> list = table.get(index);
        for (Pair pair : list) {
            if (pair.key.equals(key)) {
                pair.value = value;
                return;
            }
        }
        list.add(new Pair(key, value));
    }

    // Public method to retrieve the value associated with a key
    public Integer get(Integer key) {
        int index = addressOfList(key);
        LinkedList<Pair> list = table.get(index);
        for (Pair pair : list) {
            if (pair.key.equals(key)) {
                return pair.value;
            }
        }
        return null;
    }

    // Public method to remove a key-value pair from the hash table
    public boolean remove(Integer key) {
        int index = addressOfList(key);
        LinkedList<Pair> list = table.get(index);
        for (Pair pair : list) {
            if (pair.key.equals(key)) {
                list.remove(pair);
                return true;
            }
        }
        return false; // Key not found
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Initialize the scanner and read the amount of expected integers
        int n = input.nextInt();
        assert n > 0 : "Error: length of the Input Array < 1";

        B7A1 table = new B7A1(n);

        String command;
        while (true) {
            System.out.print("Enter command (i to insert, g to get, r to remove, q to quit): ");
            command = input.next();
            if (command.equals("i")) {
                int key = input.nextInt();
                int value = input.nextInt();
                table.insert(key, value);
                System.out.println("Inserted (" + key + ", " + value + ")");
            } else if (command.equals("g")) {
                int key = input.nextInt();
                Integer result = table.get(key);
                if (result != null) {
                    System.out.println("Key: " + key + ", Value: " + result);
                } else {
                    System.out.println("Key not found.");
                }
            } else if (command.equals("r")) {
                int key = input.nextInt();
                boolean removed = table.remove(key);
                System.out.println("Key " + key + " removed: " + removed);
            } else if (command.equals("q")) {
                break;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }
        input.close();
    }
}