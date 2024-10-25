import java.util.Scanner;
import java.util.Arrays;

class B9A2 {
    // Definition eines Knotens im Intervall-Baum
    class Node {
        int l, r; // Intervall [l, r]
        int max; // Maximum der rechten Grenzen im Teilbaum
        int height; // Höhe des Knotens
        Node left, right;

        Node(int l, int r) {
            this.l = l;
            this.r = r;
            this.max = r;
            this.height = 1;
        }
    }

    private Node root;

    public B9A2() {
        root = null;
    }

    public B9A2(int[] array) {
        for (int i = 0; i < array.length; i += 2) {
            add(array[i], array[i + 1]);
        }
    }

    private boolean isEmpty() {
        return root == null;
    }

    // Methode zum Hinzufügen eines Intervalls und zur Balancierung des Baums
    public void add(int l, int r) {
        root = add(root, l, r);
    }

    private Node add(Node node, int l, int r) {
        if (node == null) {
            return new Node(l, r);
        }

        if (l < node.l) {
            node.left = add(node.left, l, r);
        } else {
            node.right = add(node.right, l, r);
        }

        // Aktualisiere die Höhe und das Maximum
        node.height = 1 + Math.max(height(node.left), height(node.right));
        node.max = Math.max(node.max, r);
        if (node.left != null) {
            node.max = Math.max(node.max, node.left.max);
        }
        if (node.right != null) {
            node.max = Math.max(node.max, node.right.max);
        }

        // Balancierung des Baums
        int balance = getBalance(node);

        // Linker Linker Fall
        if (balance > 1 && l < node.left.l) {
            return rotateRight(node);
        }

        // Rechter Rechter Fall
        if (balance < -1 && l > node.right.l) {
            return rotateLeft(node);
        }

        // Linker Rechter Fall
        if (balance > 1 && l > node.left.l) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Rechter Linker Fall
        if (balance < -1 && l < node.right.l) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Hilfsmethode zur Links-Rotation
    private Node rotateLeft(Node z) {
        Node y = z.right;
        Node T2 = y.left;

        y.left = z;
        z.right = T2;

        z.height = Math.max(height(z.left), height(z.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        z.max = Math.max(z.r, Math.max(max(z.left), max(z.right)));
        y.max = Math.max(y.r, Math.max(max(y.left), max(y.right)));

        return y;
    }

    // Hilfsmethode zur Rechts-Rotation
    private Node rotateRight(Node z) {
        Node y = z.left;
        Node T2 = y.right;

        y.right = z;
        z.left = T2;

        z.height = Math.max(height(z.left), height(z.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        z.max = Math.max(z.r, Math.max(max(z.left), max(z.right)));
        y.max = Math.max(y.r, Math.max(max(y.left), max(y.right)));

        return y;
    }

    // Hilfsmethode zur Höhe eines Knotens
    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // Hilfsmethode zur Balance eines Knotens
    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // Hilfsmethode zum Maximum eines Knotens
    private int max(Node node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        return node.max;
    }

    // Methode zur Suche nach einem überschneidenden Intervall
    public boolean search(int l, int r) {
        return search(root, l, r) != null;
    }

    private Node search(Node node, int l, int r) {
        if (node == null) {
            return null;
        }

        if (overlap(node.l, node.r, l, r)) {
            System.out.println("Intersects with: (" + node.l + ", " + node.r + ")");
            return node;
        }

        if (node.left != null && node.left.max >= l) {
            return search(node.left, l, r);
        }

        return search(node.right, l, r);
    }

    // Methode zur Überprüfung, ob sich zwei Intervalle überschneiden
    private boolean overlap(int l1, int r1, int l2, int r2) {
        return l1 <= r2 && l2 <= r1;
    }

    // In-Order Traversierung
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print("(" + node.l + ", " + node.r + ") ");
            inOrder(node.right);
        }
    }

    // Hauptmethode zum Testen
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        assert n > 0 : "Error: length of the Input Array < 1";
        int[] arr = new int[2 * n];

        for (int i = 0; i < 2 * n; i++) {
            arr[i] = input.nextInt();
        }
        System.out.print("Input Array: ");
        System.out.println(Arrays.toString(arr));
        B9A2 bst = new B9A2(arr);
        System.out.println("In-Order Traversierung:");
        bst.inOrder();
        System.out.println();

        String command;
        while (true) {
            System.out.print("Enter command (s to search, q to quit): ");
            command = input.next();
            if (command.equals("s")) {
                int l = input.nextInt();
                int r = input.nextInt();
                if (!bst.search(l, r)) {
                    System.out.println("No interval overlaps!");
                }
            } else if (command.equals("q")) {
                break;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }
        input.close();
    }
}