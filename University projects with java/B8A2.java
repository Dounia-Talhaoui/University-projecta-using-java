import java.util.Scanner;
import java.util.Arrays;

class B8A2 {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        int value;
        Node left, right, parent;
        boolean color;

        Node(int value) {
            this.value = value;
            this.color = RED; // Neue Knoten sind zunächst rot
        }
    }

    private Node root;

    public B8A2(int[] array) {
        for (int i = 0; i < array.length; i++) {
            this.add(array[i]);
        }
    }

    // Methode zum Hinzufügen eines Werts
    public void add(int value) {
        Node newNode = new Node(value);
        insert(root, newNode);
        fixInsert(newNode);
        System.out.println("Fuege " + value + " in den Rot-Schwarz-Baum ein.");
    }

    // Hilfsmethode zum Einfügen eines neuen Knotens
    private void insert(Node root, Node newNode) {
        if (this.root == null) {
            this.root = newNode;
            this.root.color = BLACK; // Wurzel ist immer schwarz
        } else {
            Node current = root;
            Node parent = null;
            while (current != null) {
                parent = current;
                if (newNode.value < current.value) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
            newNode.parent = parent;
            if (newNode.value < parent.value) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }
    }

    // Balancierung nach dem Einfügen
    private void fixInsert(Node node) {
        while (node != root && node.parent != null && node.parent.color == RED) {
            Node grandparent = node.parent.parent;
            if (grandparent == null) break; // Überprüfe, ob grandparent null ist
            if (node.parent == grandparent.left) {
                Node uncle = grandparent.right;
                if (uncle != null && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    grandparent.color = RED;
                    node = grandparent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        rotateLeft(node);
                        System.out.println("Fuehre Links-Rotation durch bei Knoten: " + node.value);
                    }
                    if (node.parent != null) node.parent.color = BLACK;
                    grandparent.color = RED;
                    rotateRight(grandparent);
                    System.out.println("Fuehre Rechts-Rotation durch bei Knoten: " + grandparent.value);
                }
            } else {
                Node uncle = grandparent.left;
                if (uncle != null && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    grandparent.color = RED;
                    node = grandparent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotateRight(node);
                        System.out.println("Fuehre Rechts-Rotation durch bei Knoten: " + node.value);
                    }
                    if (node.parent != null) node.parent.color = BLACK;
                    grandparent.color = RED;
                    rotateLeft(grandparent);
                    System.out.println("Fuehre Links-Rotation durch bei Knoten: " + grandparent.value);
                }
            }
        }
        root.color = BLACK;
    }


    // Links-Rotation
    private void rotateLeft(Node node) {
        Node temp = node.right;
        node.right = temp.left;
        if (temp.left != null) {
            temp.left.parent = node;
        }
        temp.parent = node.parent;
        if (node.parent == null) {
            root = temp;
        } else if (node == node.parent.left) {
            node.parent.left = temp;
        } else {
            node.parent.right = temp;
        }
        temp.left = node;
        node.parent = temp;
    }

    // Rechts-Rotation
    private void rotateRight(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        if (temp.right != null) {
            temp.right.parent = node;
        }
        temp.parent = node.parent;
        if (node.parent == null) {
            root = temp;
        } else if (node == node.parent.right) {
            node.parent.right = temp;
        } else {
            node.parent.left = temp;
        }
        temp.right = node;
        node.parent = temp;
    }

    // In-Order Traversierung mit Ausgabe der Farben
    public void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print("(" + node.value + ", " + (node.color == RED ? "rot" : "schwarz") + ") ");
            inOrderTraversal(node.right);
        }
    }

    // Gibt die Höhe des Baums zurück
    public int height(Node node) {
        if (node == null) {
            return 0;
        } else {
            int leftHeight = height(node.left);
            int rightHeight = height(node.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // Hauptmethode
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

        B8A2 tree = new B8A2(arr);

        System.out.println("Hoehe: " + tree.height(tree.root));
        System.out.print("In-Order Traversierung: ");
        tree.inOrderTraversal(tree.root);
    }
}