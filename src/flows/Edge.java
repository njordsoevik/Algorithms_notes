package flows;

public class Edge {
    private final int capacity;
    private int flow;
    private final int source;
    private final int destination;

    public Edge(int capacity, int source, int destination) {
        this.capacity = capacity;
        this.source = source;
        this.destination = destination;
        this.flow = 0;
    }

    public int getSource() {
        return this.source;
    }

    public int getDestination() {
        return this.destination;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getFlow() {
        return this.flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    @Override
    public String toString() {
        return String.format("Source:" + this.source + ", " + "Destination: " + this.destination);
    }
}