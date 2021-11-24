package fibbonacciHeap;

public class DoubleLinkedList {
    Node head;

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
    }

    @Override
    public String toString()   {
        StringBuilder sb = new StringBuilder();
        Node temp = head;
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

    public static void main(String[] args)
    {
        DoubleLinkedList l_list = new DoubleLinkedList();

        Node p = new Node(90);
        Node p2 = new Node(630);
        l_list.addNode(p);
        l_list.addNode(new Node(2));
        l_list.addNode(new Node(70));
        l_list.addNode(p2);
        DoubleLinkedList l_list2 = new DoubleLinkedList();

        l_list2.addNode(new Node(32));
        l_list2.addNode(new Node(740));
        l_list2.addNode(new Node(6230));

        System.out.println(l_list);
        DoubleLinkedList c = l_list.combine(l_list2);
        System.out.println(c);
        c.remove(630);
        System.out.println(c);
    }
}
