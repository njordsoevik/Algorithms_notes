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
        if (this.min.data!=Integer.MAX_VALUE) {
            DoubleLinkedList childList = z.child;
            if (childList != null) {
                Node childOriginal = z.child.head;
                this.rootList.addNode(new Node(childOriginal.data));
                Node child = childOriginal.next;
                while (child != null && child != childOriginal) {
                    this.rootList.addNode(new Node(child.data));
                    child = child.next;
                }
            }
//            System.out.println("here");
            rootList.remove(z.data);
//            System.out.println(z.data);
//            System.out.println("min");
//            System.out.println(rootList);
//            System.out.println("here");
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
        Node[] a = new Node[100];
        Node[] holding = rootList.getList();
        for (Node x:holding) {
            System.out.println(x.data + " x data");
            int d = x.degree;
            while (a[d]!=null){
                System.out.println("while loop");
                Node y = a[d];
                if (x.data > y.data) {
                    System.out.println("x > y check");
                    Node nextX = x.next;
                    Node prevX = x.prev;
                    x.next = y.next;
                    x.prev = y.prev;
                    y.prev=prevX;
                    y.next=nextX;
                    System.out.println(this);
                }
//                heapLink(y,x);
                a[d] = null;
                d = d + 1;
//                System.out.println(d);
            }
            a[d] = x;
        }
//        min = null;
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
        rootList.remove(y.data);
        if (x.child == null) {
            x.child = new DoubleLinkedList();
        }
        x.child.addNode(y);
        x.child.parent=x;
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
        Node c = x.child.head;
        do {
            sb.append("-").append(c.data).append("-");
            if (c.child !=null) {
                sb.append(toStringHelper(c)).append("\n");
            }
            c = c.next;
        } while (c != x.child.head);
        return sb.toString();
    }

    public static void main(String[] args) {
        FibonacciHeap f3 = new FibonacciHeap();
        f3.insert(new Node(32));
        f3.insert(new Node(42));
        f3.insert(new Node(122));
        f3.insert(new Node(12));
        f3.insert(new Node(62));
        System.out.println("ORIGINAL");
        System.out.println(f3);
        System.out.println("Extracting MIN:");
        System.out.println(f3.extractMin());
        System.out.println(f3);
//        System.out.println(f3.min);
//        f3.insert(new Node(42));
//        System.out.println(f3.min.data);
    }
}
