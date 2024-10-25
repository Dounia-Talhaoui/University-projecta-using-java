import java.util.Scanner;
import java.util.Arrays;

class B8A1 {

    // Attribute für den linken und rechten Teilbaum
    private B8A1 left;
    private B8A1 right;
    // Wert des Knotens
    private int value;
    // Attribut für die Höhe des Teilbaums
    private int height;

     // Konstruktor, um aus einem Array von Integern einen Suchbaum zu bauen
    public B8A1(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array darf nicht leer sein");
        }
        this.value = array[0];  // Initialisierung des Wurzelwerts
        this.height = 1;
        for (int i = 1; i < array.length; i++) {
            add(array[i]);
        }
    }

    public void add(int value) {
        addRecursive(this, value);
    }

    private B8A1 addRecursive(B8A1 node, int value) {
        if (node == null) {
            return new B8A1(value, 1);
        }
        if (value < node.value) {
            node.left = addRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = addRecursive(node.right, value);
        }
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        return node;
    }

    // Hilfskonstruktor für addRecursive
    private B8A1(int value, int height) {
        this.value = value;
        this.height = height;
    }

    // Methode, um die Höhe eines Teilbaums zu bekommen
    private int getHeight(B8A1 node) {
        return node == null ? 0 : node.height;
    }

    // In-Order Traversierung
    // linkes Kind -> Wurzel -> rechtes Kind
    public void inOrder() {
        inOrderTraversal(this);
    }

    private void inOrderTraversal(B8A1 node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.value + " ");
            inOrderTraversal(node.right);
        }
    }

    // Pre-Order Traversierung
    // Wurzel -> linkes Kind -> rechtes Kind
    public void preOrder() {
        preOrderTraversal(this);
    }

    private void preOrderTraversal(B8A1 node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    // Post-Order Traversierung
    // linkes Kind -> rechtes Kind -> Wurzel
    public void postOrder() {
        postOrderTraversal(this);
    }

    private void postOrderTraversal(B8A1 node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.print(node.value + " ");
        }
    }

    // Methode zur Berechnung der Höhe des Baums
    public int getTreeHeight() {
        return getHeight(this);
    }

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

        B8A1 bst = new B8A1(arr);

        System.out.println("Hoehe des Baums: " + bst.height);

        System.out.println("In-Order Traversierung:");
        bst.inOrder();
        System.out.println();
        System.out.println("Pre-Order Traversierung:");
        bst.preOrder();
        System.out.println();
        System.out.println("Post-Order Traversierung:");
        bst.postOrder();
        System.out.println();
    }

}
