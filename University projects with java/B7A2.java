import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class B7A2<K, V> {
    private ArrayList<LinkedList<Pair<K, V>>> hashTable;
    private int size;

    // Constructor
    public B7A2(int m) {
        hashTable = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            hashTable.add(new LinkedList<>());
        }
        size = m;
    }

    // Private inner class to represent key-value pairs
    private static class Pair<K, V> {
        private final K key;
        private final V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    // Private helper method to calculate the index of the linked list based on
    // key's hash code
    private int addressOfList(K key) {
        int hashCode = key.hashCode();
        return hashCode % size;
    }

    // Public method to insert a key-value pair into the hash table
    public void insert(K key, V value) {
        int index = addressOfList(key);
        LinkedList<Pair<K, V>> list = hashTable.get(index);
        list.add(new Pair<>(key, value));
    }

    // Public method to retrieve the value associated with a key
    public V get(K key) {
        int index = addressOfList(key);
        LinkedList<Pair<K, V>> list = hashTable.get(index);
        for (Pair<K, V> pair : list) {
            if (pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null;
    }

    // Public method to remove a key-value pair from the hash table
    public boolean remove(K key) {
        int index = addressOfList(key);
        LinkedList<Pair<K, V>> list = hashTable.get(index);
        for (Pair<K, V> pair : list) {
            if (pair.getKey().equals(key)) {
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

        B7A2<String, String> table = new B7A2<>(n);

        String command;
        while (true) {
            System.out.print("Enter command (i to insert, g to get, r to remove, q to quit): ");
            command = input.next();
            if (command.equals("i")) {
                String key = input.next();
                String value = input.next();
                table.insert(key, value);
                System.out.println("Inserted (" + key + ", " + value + ")");
            } else if (command.equals("g")) {
                String key = input.next();
                String result = table.get(key);
                if (result != null) {
                    System.out.println("Key: " + key + ", Value: " + result);
                } else {
                    System.out.println("Key not found.");
                }
            } else if (command.equals("r")) {
                String key = input.next();
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