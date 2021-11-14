package RedBlackTree;

public class RBTree {
  Node root;

  public static class Node {
    int key;
    Node left,right,parent;
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
    while (r!=null) {
      if (key>r.key) {
        r = r.right;
      }
      else if (key<r.key) {
        r = r.left;
      }
      else if (key==r.key){
        return r;
      }
    }
    return null;
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

    // ADD FIX TO BALANCE fixBalance(n)
    balance(n);
  }

  private Node getUncle(Node parent) {
    Node grandparent = parent.parent;
    if (grandparent.left == parent) {
      return grandparent.right;
    } else if (grandparent.right == parent) {
      return grandparent.left;
    } else {
      throw new IllegalStateException("Parent is not a child of its grandparent");
    }
  }

  private void balance(Node node) {
    Node parent = node.parent;

    // Case 1: Parent is null, we've reached the root, the end of the recursion
    if (parent == null) {
      // Uncomment the following line if you want to enforce black roots (rule 2):
      node.color = true;
      return;
    }

    // Parent is black --> nothing to do
    if (parent.color == true) {
      return;
    }

    // From here on, parent is red
    Node grandparent = parent.parent;

    // Case 2:
    // Not having a grandparent means that parent is the root. If we enforce black roots
    // (rule 2), grandparent will never be null, and the following if-then block can be
    // removed.
    if (grandparent == null) {
      // As this method is only called on red nodes (either on newly inserted ones - or -
      // recursively on red grandparents), all we have to do is to recolor the root black.
      parent.color = true;
      return;
    }

    // Get the uncle (may be null/nil, in which case its color is BLACK)
    Node uncle = getUncle(parent);

    // Case 3: Uncle is red -> recolor parent, grandparent and uncle
    if (uncle != null && uncle.color == false) {
      parent.color = true;
      grandparent.color = false;
      uncle.color = true;

      // Call recursively for grandparent, which is now red.
      // It might be root or have a red parent, in which case we need to fix more...
      balance(grandparent);
    }

    // Note on performance:
    // It would be faster to do the uncle color check within the following code. This way
    // we would avoid checking the grandparent-parent direction twice (once in getUncle()
    // and once in the following else-if). But for better understanding of the code,
    // I left the uncle color check as a separate step.

    // Parent is left child of grandparent
    else if (parent == grandparent.left) {
      // Case 4a: Uncle is black and node is left->right "inner child" of its grandparent
      if (node == parent.right) {
        rotateLeft(parent);

        // Let "parent" point to the new root node of the rotated sub-tree.
        // It will be recolored in the next step, which we're going to fall-through to.
        parent = node;
      }

      // Case 5a: Uncle is black and node is left->left "outer child" of its grandparent
      rotateRight(grandparent);

      // Recolor original parent and grandparent
      parent.color = true;
      grandparent.color = false;
    }

    // Parent is right child of grandparent
    else {
      // Case 4b: Uncle is black and node is right->left "inner child" of its grandparent
      if (node == parent.left) {
        rotateRight(parent);

        // Let "parent" point to the new root node of the rotated sub-tree.
        // It will be recolored in the next step, which we're going to fall-through to.
        parent = node;
      }

      // Case 5b: Uncle is black and node is right->right "outer child" of its grandparent
      rotateLeft(grandparent);

      // Recolor original parent and grandparent
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
      if (key > r.key)
      {
        s = r;
        r = r.right;
      }
      else if (key < r.key) {
        r = r.left;
      }
      else {
        Node left = r.left;
        if (left != null) {
          while (left.right != null) {
            left = left.right;
          }
          s=left;
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

  public Node successor(int key) {
    Node r = root;
    Node s = null;
    if (r == null) {
      return null;
    }
    while (true) {
      if (key < r.key)
      {
        s = r;
        r = r.left;
      }
      else if (key > r.key) {
        r = r.right;
      }
      else {
        Node right = r.right;
        if (right != null) {
          while (right.left != null) {
            right = right.left;
          }
          s=right;
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

  public void printTree() {
    Node x = root;
    printTreeHelper(x, "");
  }

  public void printTreeHelper(Node node, String prefix)
  {
    if(node == null) return;

    System.out.println(prefix + " + " + node.key+node.color);
    printTreeHelper(node.left , prefix + " ");
    printTreeHelper(node.right , prefix + " ");
  }

  public static void main(String[] args) {
    RBTree t = new RBTree();
    int[] a = new int[]{6,5,2,1,8,9};//
    for (int i = 0; i<a.length;i++) {
      t.insert(a[i]);
    }
    System.out.println(t.search(6).parent.key);
    System.out.println(t.search(2).parent.key);
    System.out.println(t.search(1).parent.key);
    System.out.println(t.search(8).parent.key);
    System.out.println(t.search(9).parent.key);
    t.printTree();
//    System.out.println(t.min().key);
//    System.out.println(t.predecessor(5).key);
//    System.out.println(t.successor(2).key);
  }
}


