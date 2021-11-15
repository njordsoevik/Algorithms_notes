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

    public void insert(String data, Integer count) {
        Node n = new Node(data, count);
        n.next = null;
        if (data.equals("no")){
            if (this.head == null) {
                this.head = n;
            } else {
                Node last = this.head;
                if (last.data.equals(data)) {
                    last.count += count;
                    return;
                }
                while (last.next != null) {
                    if (last.next.data.equals(data)){
                        last.next.count += count;
                        return;
                    }
                    last = last.next;
                }
                last.next = n;
            }
        } else {
            if (this.head == null) {
                this.head = n;
            } else {
                Node last = this.head;
                if (last.data.equals(data)) {
                    last.count += count;
                    return;
                }
                while (last.next != null) {
                    if (last.next.data.equals(data)){
                        last.next.count += count;
                        return;
                    }
                    last = last.next;
                }

                last.next = n;
            }
        }

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

    public String printList() {
        StringBuilder b = new StringBuilder();
        Node currNode = this.head;
        while (currNode != null) {
            b.append("| ").append(currNode.data).append(" (").append(currNode.count).append(") |");
            currNode = currNode.next;
        }
        return b.toString();
    }

    public void delete(String key) {
        Node currNode = this.head;
        Node prev = null;

        if (currNode != null && currNode.data == key) {
            this.head = currNode.next;
            return;
        }
        while (currNode != null && currNode.data != key) {
            prev = currNode;
            currNode = currNode.next;
        }
        if (currNode != null) {
            prev.next = currNode.next;
        }
    }

    public static void main(String[] args) {
        /* Start with the empty list. */
        LinkedListHash list = new LinkedListHash();

        list.insert("we",0);
        list.insert("are", 8);
        list.insert("re",0);

        list.insert("c",1);
        list.printList();
        System.out.println("\n");

        list.delete("we");
        list.delete("werf");
        list.printList();
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
