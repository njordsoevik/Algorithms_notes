package fibbonacciHeap;

public class Node{
    int data;
    Node next;
    Node prev;
    Node child;
    Node parent;
    int degree;
    int mark;

    public Node(int data) {
        this.data = data;
    }

    public void setDegree(int d) {
        this.degree=d;
    }
    public void setMark(int b) {
        this.mark=b;
    }
    public void setChild(Node x) {
        this.child = x;
    }
    public void setParent(Node x) {
        this.parent = x;
    }

}