package SkipList;

import java.util.Random;

public class SkipList {
  Node head;
  Node tail;

  private final int NEG_INF = Integer.MIN_VALUE;
  private final int POS_INF = Integer.MAX_VALUE;
  private int height;
  private Random rand;
  private int length;

  public SkipList() {
    head = new Node(NEG_INF);
    tail = new Node(POS_INF);
    head.next = tail;
    tail.prev = head;
    rand = new Random();
    height = 0;
    length = 0;
  }

  public class Node {
    public Node next;
    public Node below;
    public Node above;
    public Node prev;
    public int data;

    Node(int data) {
      this.data = data;
    }
  }

  private int flipCoin() {
    int count = 0;
    while (this.rand.nextBoolean() == true) {
      count++;
    }
    return count;
  }

  public Node search(int key) {
    Node n = head;
    while (n.below != null) {
      n = n.below;
      while (key >= n.next.data) {
        n = n.next;
      }
    }
    return n;
  }

  public void insert(int data) {
    Node position = search(data);
    Node q;

    int num = -1;
    int l = -1;

    if (data == position.data) {
      return;
    } else {
      do {
        l++;
        num++;

        increaseLevel(l);

        q = position;
        while (position.above == null) {
          position = position.prev;
        }

        position = position.above;
        q = insertAfter(position, q, data);

      } while (rand.nextBoolean() == true);
    }
  }

  private Node insertAfter(Node position, Node q, int data) {
    Node newNode = new Node(data);
    Node nodeBeforeNewNode = position.below.below;

    // Set before and after references
    newNode.next = q.next;
    newNode.prev = q;
    q.next.prev = newNode;
    q.next = newNode;

    // Set above and below references
    if (nodeBeforeNewNode != null) {
      while (true) {
        if (nodeBeforeNewNode.next.data != data) {
          nodeBeforeNewNode = nodeBeforeNewNode.next;
        } else {
          break;
        }
      }

      newNode.below = nodeBeforeNewNode.next;
      nodeBeforeNewNode.next.above = newNode;
    }
    if (position != null) {
      if (position.next.data == data) {
        newNode.above = position.next;
      }
    }
    return newNode;
  }

  private void increaseLevel(int l) {
    if (l >= height) {
      height++;
      addLevel();
    }
  }

  private void addLevel() {
    Node newHead = new Node(NEG_INF);
    Node newTail = new Node(POS_INF);

    newHead.next = newTail;
    newHead.below = head;
    newTail.prev = newHead;
    newTail.below = tail;

    head.above = newHead;
    tail.above = newTail;
    head = newHead;
    tail = newTail;
  }

  public Node remove(int data) {
    Node remove = search(data);
    if (remove.data != data) {
      return null;
    }

    removeReferences(remove);

    while (remove != null) {
      removeReferences(remove);

      if (remove.above != null) {
        remove = remove.above;
      } else {
        break;
      }
    }
    return remove;
  }

  private void removeReferences(Node remove) {
    Node after = remove.next;
    Node before = remove.prev;

    before.next = after;
    after.prev = before;
  }

  @Override
  public String toString() {
    StringBuilder b = new StringBuilder();
    Node node = head;
    Node rowStart = node;
    int h = height;

    while (rowStart != null) {
      b.append("Height: " + h + "\n");
      while (node != null) {
        b.append(" | ").append(node.data).append(" | ");
        node = node.next;
      }
      rowStart = rowStart.below;
      node = rowStart;
      h--;
      b.append("\n");
    }
    return b.toString();
  }

  public static void main(String[] args) {
    SkipList s = new SkipList();
    s.insert(6);
    s.insert(15);
    s.insert(3);
    System.out.println(s);
    s.remove(6);
    System.out.println(s);
    s.insert(24);
    System.out.println(s);
    System.out.println(s.search(3));
  }


}
