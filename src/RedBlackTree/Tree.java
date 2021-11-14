package RedBlackTree;

public class Tree {
    Node root;

    public static class Node {
        int key;
        Node left,right;

        public Node(int data) {
            this.key = data;
            left = right = null;
        }
    }

    public static Node insert(Node root, int key) {
        return insertHelper(root, key);
    }

    private static Node insertHelper(Node node, int key) {
        if (node == null)
        {
            node = new Node(key);
            return node;
        }
        if (key < node.key)
            node.left = insertHelper(node.left, key);
        else if (key > node.key)
            node.right = insertHelper(node.right, key);
        return node;
    }

    public static Node min(Node n) {
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }
    public static Node max(Node n) {
        while (n.right != null) {
            n = n.right;
        }
        return n;
    }
    public static Node search(Node root, int key) {
        return searchHelper(root, key);
    }

    public static Node searchHelper(Node node, int key) {
        if (node==null || node.key==key) {
            return node;
        }
        if (node.key < key)
            return searchHelper(node.right, key);

        // Key is smaller than root's key
        return searchHelper(node.left, key);
    }

    public static Node successor(Node root, int key) {
        Node s = null;
        if (root == null) {
            return null;
        }
        while (true) {
            if (key < root.key)
            {
                s = root;
                root = root.left;
            }
            else if (key > root.key) {
                root = root.right;
            }
            else {
                if (root.right != null) {
                    s = min(root.right);
                }
                break;
            }
            if (root == null) {
                return s;
            }
        }

        // return successor, if any
        return s;
    }

    public static Node predecessor(Node root, int key) {
        Node s = null;
        if (root == null) {
            return null;
        }
        while (true) {
            if (key > root.key)
            {
                s = root;
                root = root.right;
            }
            else if (key < root.key) {
                root = root.left;
            }
            else {
                if (root.left != null) {
                    s = max(root.left);
                }
                break;
            }
            if (root == null) {
                return s;
            }
        }

        // return successor, if any
        return s;
    }

    public static void main(String[] args) {
        Node x = null;
        int[] a = new int[]{6,5,2,1,8,9};
        for (int i = 0; i<a.length;i++) {
            x= insert(x,a[i]);
        }
        System.out.println(min(x).key);
        System.out.println(max(x).key);
        System.out.println(successor(x,7).key);
        System.out.println(search(x,0).key);
        System.out.println(predecessor(x,9).key);
    }
}
