package RedBlackTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RBTree {
  Node root;

  public static class Node {
    int key;
    Node left, right, parent;
    boolean color;

    public Node(int key) {
      this.key = key;
      left = right = null;
      color = false;
    }
  }

  private void rotateRight(Node node) {
    Node p = node.parent;
    Node l = node.left;

    // Left node of original root becomes the right node of the left tree
    node.left = l.right;
    // Left tree max becomes the left child of the original root
    if (l.right != null) {
      l.right.parent = node;
    }
    l.right = node;
    node.parent = l;

    // Deal with parent child relationships
    if (p == null) {
      root = l;
    } else if (p.left == node) {
      p.left = l;
    } else if (p.right == node) {
      p.right = l;
    } else {
      throw new IllegalStateException("Node is not a child of its parent");
    }
    if (l != null) {
      l.parent = p;
    }
  }

  private void rotateLeft(Node node) {
    Node p = node.parent;
    Node r = node.right;

    // Left node of original root becomes the right node of the left tree
    node.right = r.left;
    // Left tree max becomes the left child of the original root
    if (r.left != null) {
      r.left.parent = node;
    }
    r.left = node;
    node.parent = r;

    // Deal with parent child relationships
    if (p == null) {
      root = r;
    } else if (p.left == node) {
      p.left = r;
    } else if (p.right == node) {
      p.right = r;
    } else {
      throw new IllegalStateException("Node is not a child of its parent");
    }
    if (r != null) {
      r.parent = p;
    }
  }

  public Node search(int key) {
    Node r = root;
    while (r != null) {
      if (key > r.key) {
        r = r.right;
      } else if (key < r.key) {
        r = r.left;
      } else if (key == r.key) {
        return r;
      }
    }
    return new Node(-1);
  }

  public void insert(int key) {
    Node r = root;
    Node p = null;

    while (r != null) {
      p = r; // keep track of parent
      if (key > r.key) {
        r = r.right;
      } else if (key < r.key) {
        r = r.left;
      }
      else {
        throw new IllegalArgumentException("Key already exists.");
      }
    }

    Node n = new Node(key);
    n.color = false;
    n.parent = p;
    if (p == null) {
      root = n; // Set as root in empty tree
    } else if (key > p.key) {
      p.right = n;
    } else if (key < p.key) {
      p.left = n;
    }

    // ADD FIX TO BALANCE, balance(n)
    balance(n);
  }


  private void balance(Node node) {
    Node parent = node.parent;
    if (parent == null) { // This is the root node, make it black
      node.color = true;
      return;
    }
    if (parent.color == true) {
      return;
    }
    Node grandparent = parent.parent;
    if (grandparent == null) {
      parent.color = true;
      return;
    }
    Node uncle = null;
    if (grandparent.left == parent) {
      uncle = grandparent.right;
    } else if (grandparent.right == parent) {
      uncle = grandparent.left;
    }

    // Case 3: Uncle is red -> recolor parent, grandparent and uncle
    if (uncle != null && uncle.color == false) {
      parent.color = true;
      grandparent.color = false;
      uncle.color = true;
      balance(grandparent);
    } else if (parent == grandparent.left) {
      if (node == parent.right) {
        rotateLeft(parent);
        parent = node;
      }
      rotateRight(grandparent);
      parent.color = true;
      grandparent.color = false;
    } else {
      if (node == parent.left) {
        rotateRight(parent);
        parent = node;
      }
      rotateLeft(grandparent);
      parent.color = true;
      grandparent.color = false;
    }

//    while (z.parent.color == false) {
//      if (z.parent==z.parent.parent.left) {
//        y = z.parent.parent.right;
//      }
//      if (y.color == false) {
//        z.parent.color = true;
//        y.color=true;
//        z.parent.parent.color=false;
//        z = z.parent.parent;
//      } else if (z == z.parent.right) {
//        z = z.parent;
//        rotateLeft(z);
//        z.parent.color=true;
//        z.parent.parent.color=false;
//        rotateRight(z.parent.parent);
//      } else {
//        z = z.parent;
//        rotateRight(z);
//        z.parent.color=true;
//        z.parent.parent.color=false;
//        rotateLeft(z.parent.parent);
//      }
//    }
//    root.color=true;
  }

  public Node predecessor(int key) {
    Node r = root;
    Node s = null;
    if (r == null) {
      return null;
    }
    while (true) {
      if (key > r.key) {
        s = r;
        r = r.right;
      } else if (key < r.key) {
        r = r.left;
      } else {
        Node left = r.left;
        if (left != null) {
          while (left.right != null) {
            left = left.right;
          }
          s = left;
        }
        break;
      }
      if (r == null) {
        return s;
      }
    }

    // return predecessor, if any
    return s;
  }

  public Node successor(int data) {
    Node r = root;
    Node s = null;
    if (r == null) {
      return null;
    }
    while (true) {
      if (data < r.key) {
        s = r;
        r = r.left;
      } else if (data > r.key) {
        r = r.right;
      } else {
        Node right = r.right;
        if (right != null) {
          while (right.left != null) {
            right = right.left;
          }
          s = right;
        }
        break;
      }
      if (r == null) {
        return s;
      }
    }

    // return successor, if any
    return s;
  }

  public Node min() {
    Node r = root;
    while (r.left != null) {
      r = r.left;
    }
    return r;
  }

  public Node max() {
    Node r = root;
    while (r.right != null) {
      r = r.right;
    }
    return r;
  }

  public int getDepth() {
    return depthHelper(root);
  }

  private int depthHelper(Node node) {
    if (node == null)
      return 0;
    else {
      /* compute the depth of each subtree */
      int lDepth = depthHelper(node.left);
      int rDepth = depthHelper(node.right);

      /* use the larger one */
      if (lDepth > rDepth)
        return (lDepth + 1);
      else
        return (rDepth + 1);
    }
  }

  public void printInOrder() {
    printInOrderHelper(root);

  }
  private void printInOrderHelper(Node node) {
    if (node != null) {
      printInOrderHelper(node.left);
      System.out.print(node.key+", ");
      printInOrderHelper(node.right);
    }
  }

  public void printTree() {
    Node x = root;
    printTreeHelper(x, "");
  }

  public void printTreeHelper(Node node, String prefix) {
    if (node == null) return;
    String c;
    if (node.color == false) {
      c = "R";
    } else {
      c = "B";
    }
    System.out.println(prefix + " -- " + node.key + c);
    printTreeHelper(node.right, prefix + "       ");
    printTreeHelper(node.left, prefix + "       ");
  }

  public static void main(String[] args) {
    RBTree t = new RBTree();
    int e;
    try {
      Scanner s = new Scanner(new File("C:\\Users\\njord\\IdeaProjects\\Algorithms_notes\\src\\RedBlackTree\\RBTree_Array.txt"));
      while (s.hasNext()) {
        e = s.nextInt();
        t.insert(e);
      }
      s.close();
    } catch (FileNotFoundException fnf) {
      throw new IllegalArgumentException("RBTree_Array not found");
    }
//    t.insert(3);
//    t.printTree();
//    t.insert(7);
//    t.printTree();
//    t.insert(45);
//    t.printTree();
//    t.insert(3456);
//    t.printTree();
////    t.insert(3);
////    t.printTree();
    Scanner scan = new Scanner(System.in);
    String element = "";
    int keyInput;
    while (true) {
      System.out.println("Enter a command: (print/sort/search/min/max/predecessor/successor/q)");
      while (scan.hasNext()) {
        element = scan.next();
        switch (element) {
          case "q":
          case "Q":
            System.out.println("Quit.");
            return;
          case "print":
            t.printTree();
            break;
          case "sort":
            t.printInOrder();
            System.out.println();
            break;
          case "search":
            System.out.println("Enter Key: ");
            keyInput = scan.nextInt();
            System.out.println(t.search(keyInput).key);
            break;
          case "min":
            System.out.println(t.min().key);
            break;
          case "max":
            System.out.println(t.max().key);
            break;
          case "predecessor":
            System.out.println("Enter Key: ");
            keyInput = scan.nextInt();
            System.out.println(t.predecessor(keyInput).key);
            break;
          case "successor":
            System.out.println("Enter Key: ");
            keyInput = scan.nextInt();
            System.out.println(t.successor(keyInput).key);
            break;
          case "insert":
            System.out.println("Enter Key: ");
            keyInput = scan.nextInt();
            try {
              t.insert(keyInput);
            } catch (IllegalArgumentException ex) {
              System.out.println("Key already exists!");
            }

            break;
          default:
            System.out.println("Unknown command");
        }
        System.out.println("Height of tree: "+t.depthHelper(t.root));
        System.out.println("Enter a command: (print/sort/search/insert/min/max/predecessor/successor/q)");
      }
    }
  }
}


