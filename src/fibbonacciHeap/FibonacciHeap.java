package fibbonacciHeap;


public class FibonacciHeap {
    private DoubleLinkedList rootList;
    private Node min;
    private int n;

    public FibonacciHeap() {
        this.rootList = new DoubleLinkedList();
        min = new Node(Integer.MAX_VALUE);
        n=0;
    }

    public void insert(Node x) {
        x.setDegree(0);
        if (x.data<min.data) {
            min = x;
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

        }

        return z;
    }

    public static void main(String[] args) {
        FibonacciHeap f1 = new FibonacciHeap();
        f1.insert(new Node(32));
        f1.insert(new Node(21));
        System.out.println(f1.rootList);
        FibonacciHeap f2 = new FibonacciHeap();
        f2.insert(new Node(22));
        f2.insert(new Node(91));
        FibonacciHeap c = f1.union(f2);
        System.out.println(c.rootList);
        System.out.println(c.min.data);
    }
}
