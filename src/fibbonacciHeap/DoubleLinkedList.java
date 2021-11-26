package fibbonacciHeap;

import java.util.ArrayList;
import java.util.List;

public class DoubleLinkedList {
    Node head;
    Node parent;

    public Node Node(Node n) {
        Node r = new Node(n.data);
        r.prev=n.prev;
        r.next=n.next;
        r.parent = n.parent;
        r.mark = n.mark;
        r.child=n.child;
        r.degree=n.degree;
        return r;
    }

    public void addNode(Node n) {
        if (this.head == null) {
            n.next = n.prev = n;
            head = n;
            return;
        }

        Node last = (head).prev;

        n.next = head;

        (head).prev = n;

        // change new_node=>prev to last
        n.prev = last;

        // Make new node next of old last
        last.next = n;

        n.parent = parent;
    }

    @Override
    public String toString()   {
        StringBuilder sb = new StringBuilder();
        Node temp = head;
        if (temp == null) {
            return "";
        }
        while (temp.next != head)
        {
            sb.append(" ").append(temp.data);
            temp = temp.next;
        }
        sb.append(" ").append(temp.data);
        return sb.toString();
    }

    public void toStringReversed() {
        Node last = head.prev;
        Node temp = last;
        while (temp.prev != last)
        {
            System.out.printf("%d ", temp.data);
            temp = temp.prev;
        }
        System.out.printf("%d ", temp.data);
    }

    public Node remove(int value) {
        Node temp = head;
        if (temp.data == value) {
            if (head.next == head) { // One value check

                head = null;
                return temp;
            }
            head.prev.next = head.next;
            head.next.prev = head.prev;
            head = head.next;
            return temp;
        }
        temp = temp.next;
        while (temp != head) {
            if (temp.data == value) {
                temp.prev.next = temp.next;
                temp.next.prev=temp.prev;
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public int countParents() {
        int count = 0;
        Node parent = this.parent;
        while (parent!=null) {
            count++;
            parent = parent.parent;
        }
        return count;
    }

    public DoubleLinkedList combine(DoubleLinkedList d2) {
        Node temp = this.head;
        while (temp.next != this.head)
        {
            int data = temp.data;
            d2.addNode(new Node(data));
            temp = temp.next;
        }
        d2.addNode(temp);
        return d2;
    }

    public void setParent(Node x) {
        this.parent = x;
    }

    public Node[] getList(){
        List<Node> li = new ArrayList<>();
        Node temp = this.head;
        if (temp !=null) {
            li.add(temp);
            while (temp.next != this.head)
            {
                temp = temp.next;
                li.add(temp);
            }
        }
        Node[] nodes = new Node[li.size()];
        nodes = li.toArray(nodes);
        return nodes;
    }

    public void swap(Node x, Node y) {
        Node prevX = x.prev;
        Node nextX = x.next;
        Node prevY = y.prev;
        Node nextY = y.next;
        if (nextX == y) {
            y.prev = prevX;
            prevX.next=y;
            y.next = x;
            x.prev = y;
            x.next = nextY;
            nextY.prev = x;
        } else if (nextY == x) {
            x.prev = prevY;
            prevY.next=x;
            x.next = y;
            y.prev = x;
            y.next = nextX;
            nextX.prev = y;
        } else {
            x.next = nextY;
            x.prev = prevY;
            y.next = nextX;
            y.prev = prevX;
//        System.out.println(x.data+"DATA "+x.prev.data +" " + x.next.data);
//        System.out.println(y.data+"DATA "+y.prev.data +" " + y.next.data);
            nextX.prev = y;
            nextY.prev = x;
            prevX.next = y;
            prevY.next = x;
        }
        if (head == x) {
            head = y;
        } else if (head == y) {
            head = x;
        }
    }

    public static void main(String[] args) {
        Node p1 = new Node(90);
        Node p2 = new Node(630);
        Node p3 = new Node(32);
        DoubleLinkedList l_list2 = new DoubleLinkedList();
        l_list2.addNode(p1);
        l_list2.addNode(p2);
        l_list2.addNode(p3);
        l_list2.addNode(new Node(6230));
        System.out.println(l_list2);
        l_list2.swap(p1,p3);
        System.out.println(l_list2);
    }
}
