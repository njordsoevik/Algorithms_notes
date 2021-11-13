package hashTable;

// Java program to implement
// a Singly Linked List
public class LinkedListHash {

    Node head; // head of list

    static class Node {
        String data;
        Integer count;
        Node next;


        Node(String d, Integer count) {
            this.data = d;
            this.count = count;
            next = null;
        }
        Node(String d) {
            this(d,0);
        }
    }

    public LinkedListHash insert(String data, Integer count) {
        Node n = new Node(data, count);
        n.next = null;

        if (this.head == null) {
            this.head = n;
        } else {
            Node last = this.head;
            while (last.next != null) {
                last = last.next;
            }

            last.next = n;
        }

        // Return the list by head
        return this;
    }

    public Integer get(String key) {
        Node node = this.head;
        while (node != null) {
            if (node.data == key) {
                return node.count;
            }
            node = node.next;
        }
        // Return the list by head
        return null;
    }

    public void printList() {
        Node currNode = this.head;
        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.next;
        }
    }

//    public LinkedListHash deleteByKey(int key) {
//        Node currNode = this.head;
//        Node prev = null;
//
//        if (currNode != null && currNode.data == key) {
//            this.head = currNode.next;
//
//            return this;
//        }
//        while (currNode != null && currNode.data != key) {
//            prev = currNode;
//            currNode = currNode.next;
//        }
//        if (currNode != null) {
//            prev.next = currNode.next;
//        }
//        return this;
//    }

    public static void main(String[] args) {
        /* Start with the empty list. */
        LinkedListHash list = new LinkedListHash();

        list = list.insert("1",0);
        list = list.insert("2", 8);
        list = list.insert("3",0);
        list = list.insert("4",1);
        list = list.insert("5",0);
        list = list.insert("6",0);
        list = list.insert("7",0);
        list = list.insert("8",0);

        list.printList();
        System.out.println("\n");

//        list.deleteByKey("1");
//
//        list.printList();
//        System.out.println("\n");
//
//        list.deleteByKey("4");
//        System.out.println("\n");
//
//        list.printList();
//
//        list.deleteByKey(10);
//        System.out.println("\n");

    }

}
