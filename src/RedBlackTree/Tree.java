package RedBlackTree;

public class Tree {
    Node root;

    class Node {
        int key;
        Node left,right;

        public Node(int data) {
            this.key = data;
            left = right = null;
        }
    }

    Tree() {
        root = null;
    }

    public void insert(int key) {
        insertHelper(root, key);
    }

    private Node insertHelper(Node node, int key) {
        if (node == null)
        {
            node = new Node(key);
            return node;
        }
        if (key < node.key)
            node.left = insertHelper(node.left, key);
        else if (key > root.key)
            node.right = insertHelper(node.right, key);
        return node;
    }

    public int min() {
        Node n = root;
        while (n.left != null) {
            n = n.left;
        }
        return n.key;
    }
    public int max() {
        Node n = root;
        while (n.right != null) {
            n = n.right;
        }
        return n.key;
    }
    public Node search(int key) {

    }
    public Node searchHelper(Node node, int key) {
        if
    }

    public static void main(String[] args) {
        Tree t = new Tree();
    }
}
