import java.util.Scanner;
import java.util.Arrays;

class B9A1 {
    // Definition eines Knotens im AVL-Baum
    class Node {
        int value;
        int height;
        Node left, right;

        Node(int d) {
            value = d;
            height = 1;
        }
    }

    private Node root;

    public B9A1() {
        root = null;
    }

    public B9A1(int[] array) {
        for (int value : array) {
            add(value);
        }
    }

    private boolean isEmpty() {
        return root == null;
    }

    // Eine Methode zum Hinzufügen eines Werts und zur Balancierung des Baums
    public void add(int value) {
        System.out.println("Fuege " + value + " in AVL-Baum ein.");
        root = add(root, value);
    }

    private Node add(Node node, int value) {
        // 1. Normales Einfügen wie in einem binären Suchbaum
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = add(node.left, value);
        } else if (value > node.value) {
            node.right = add(node.right, value);
        } else {
            // Duplikate sind im AVL-Baum nicht erlaubt
            return node;
        }

        // 2. Aktualisiere die Höhe dieses Vorfahrknotens
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 3. Holen Sie die Balance-Faktor dieses Vorfahrknotens, um zu überprüfen,
        // ob dieser Knoten unausgeglichen ist
        int balance = getBalance(node);

        // Wenn der Knoten unausgeglichen ist, gibt es 4 Fälle

        // Linker Linker Fall
        if (balance > 1 && value < node.left.value) {
            System.out.println("Linker Teilbaum von \"" + node.value + "\" hat Hoehe " + height(node.left) + ". Rechter Teilbaum hat Hoehe " + height(node.right) + ".");
            System.out.println("Fuehre Rechts-Rotation durch");
            return rotateRight(node);
        }

        // Rechter Rechter Fall
        if (balance < -1 && value > node.right.value) {
            System.out.println("Rechter Teilbaum von \"" + node.value + "\" hat Hoehe " + height(node.right) + ". Linker Teilbaum hat Hoehe " + height(node.left) + ".");
            System.out.println("Fuehre Links-Rotation durch");
            return rotateLeft(node);
        }

        // Linker Rechter Fall
        if (balance > 1 && value > node.left.value) {
            System.out.println("Fuehre Links-Rotation durch");
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Rechter Linker Fall
        if (balance < -1 && value < node.right.value) {
            System.out.println("Fuehre Rechts-Rotation durch");
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        // return the (unchanged) node pointer
        return node;
    }

    // Hilfsmethode zur Links-Rotation
    private Node rotateLeft(Node z) {
        Node y = z.right;
        Node T2 = y.left;

        // Durchführung der Rotation
        y.left = z;
        z.right = T2;

        // Aktualisierung der Höhen
        z.height = Math.max(height(z.left), height(z.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Gebe die neue Wurzel zurück
        return y;
    }

    // Hilfsmethode zur Rechts-Rotation
    private Node rotateRight(Node z) {
        Node y = z.left;
        Node T2 = y.right;

        // Durchführung der Rotation
        y.right = z;
        z.left = T2;

        // Aktualisierung der Höhen
        z.height = Math.max(height(z.left), height(z.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Gebe die neue Wurzel zurück
        return y;
    }

    // Hilfsmethode zur Höhe eines Knotens
    private int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    // Hilfsmethode zur Balance eines Knotens
    private int getBalance(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    // In-Order Traversierung
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.value + " ");
            inOrder(node.right);
        }
    }

    // Hauptmethode zum Testen
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Initialize the scanner and read the amount of expected integers
        int n = input.nextInt();
        assert n > 0 : "Error: length of the Input Array < 1";
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        input.close();
        System.out.print("Input Array: ");
        System.out.println(Arrays.toString(arr));
        B9A1 bst = new B9A1(arr);
        System.out.println("Hoehe: " + bst.height(bst.root));
        System.out.println("In-Order Traversierung:");
        bst.inOrder();
        System.out.println();
    }
}
