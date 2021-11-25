package fibbonacciHeap;


public class FibonacciHeap {
    private DoubleLinkedList rootList;
    private Node min;
    private int n;

    public FibonacciHeap() {
        this.rootList = new DoubleLinkedList();
        min = null;
        n=0;
    }

    public void insert(Node x) {
        x.setDegree(0);
        if (min == null) {
            min = x;
        } else {
            if (x.data < min.data) {
                min = x;
            }
        }
        rootList.addNode(x);
        n = n+1;
    }

    public FibonacciHeap union(FibonacciHeap f2) {
        FibonacciHeap combined = new FibonacciHeap();
        Node m1 = this.min;
        Node m2 = f2.min;
        combined.rootList = this.rootList.combine(f2.rootList);
        if (m1.data < m2.data) {
            combined.min = m1;
        } else {
            combined.min = m2;
        }
        combined.n = this.n + f2.n;
        return combined;
    }

    public Node extractMin() {
        Node z = this.min;
        if (z.data!=Integer.MAX_VALUE) {
            DoubleLinkedList childList = z.child;
            if (childList !=null ) {
                Node[] children = childList.getList();
                for (Node x:children) {
                    childList.remove(x.data);
                    this.rootList.addNode(x);
                }
                rootList.remove(z.data);
            }
            if (z.next == z) {
                this.min = null;
            }
            else {
                consolidate();
            }
            this.n=this.n-1;
        }
        return z;
    }

    private void consolidate() {
        // double golden = (1+Math.sqrt(5)) /2;
        // int dn = (int) Math.floor(Math.log(this.hnodes) / Math.log(golden));
        Node[] a = new Node[100];

        Node[] holding = rootList.getList();
        for (Node w:holding) {
            Node x = w;
            int d = x.degree;
            while (a[d]!=null){
                Node y = a[d];
                if (x.data > y.data) {
                    //rootList.swap(x,y);
                    Node temp = x;
                    x = y;
                    y = temp;
                }
                heapLink(y,x);
                a[d] = null;
                d = d + 1;
            }
            a[d] = x;
        }
        min = null;
//        for (int i = 0; i < a.length; i++) {
//            if (a[i] != null) {
//                if (min == null) {
//                    rootList.addNode(a[i]);
//                    min = a[i];
//                } else {
//                    rootList.addNode(a[i]);
//                    if (a[i].data < min.data) {
//                        min = a[i];
//                    }
//                }
//            }
//        }
    }

    private void heapLink(Node y, Node x) {
        System.out.println(this + " link called for " + x.data+"," +y.data);
        rootList.remove(y.data);
        if (x.child == null) {
            x.child = new DoubleLinkedList();
            x.child.parent=x;
        }
        x.child.addNode(y);
        y.mark=0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node x = rootList.head;
        do {
            sb.append(x.data).append("\n");
            if (x.child != null) {
                sb.append(toStringHelper(x)).append("\n");
            }
            x=x.next;
        } while (x != rootList.head);

        return sb.toString();
    }

    private String toStringHelper(Node x) {
        StringBuilder sb = new StringBuilder();
        Node[] children = x.child.getList();
        for (Node c: children) {
            sb.append("-").append(c.data).append("-").append("\n");
            if (c.child != null) {
                sb.append("  ").append(toStringHelper(c));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        FibonacciHeap f3 = new FibonacciHeap();
//        Node p = new Node(1);
//        DoubleLinkedList d = new DoubleLinkedList();
//        d.addNode(new Node(5));
//        d.addNode(new Node(6));
//        p.child = d;
//        f3.insert(new Node(32));
//        f3.insert(p);
//        f3.insert(new Node(33));
//        f3.insert(new Node(42));
//        f3.insert(new Node(122));
//        f3.insert(new Node(12));
//        System.out.println("ORIGINAL");
//        System.out.println(f3);
//        System.out.println();
//        f3.extractMin();
//        System.out.println(f3 + "NEW");



        FibonacciHeap f3 = new FibonacciHeap();
        Node p = new Node(1);
        DoubleLinkedList d = new DoubleLinkedList();
        d.addNode(new Node(5));
        d.addNode(new Node(6));
        p.child = d;
        f3.insert(new Node(32));
        f3.insert(p);
        f3.insert(new Node(33));
        f3.insert(new Node(222));
        f3.insert(new Node(7));
        f3.insert(new Node(42));
        f3.insert(new Node(122));
        f3.insert(new Node(12));
        System.out.println("ORIGINAL");
        System.out.println(f3);
        System.out.println();
        f3.extractMin();
        System.out.println(f3 + "NEW");
    }
}
