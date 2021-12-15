package flows;

import java.util.LinkedList;

public class AdjacencyList {
    private final LinkedList<Edge>[] adjLists;
    private final Vertex[] vertices;
    private final int size;

    public AdjacencyList(int numVertices) {
        this.size = numVertices;
        adjLists = new LinkedList[numVertices];
        vertices = new Vertex[numVertices];
        for (int i = 0; i<numVertices;i++){
            adjLists[i] = new LinkedList();
            vertices[i] = new Vertex(i);
        }
    }

    public void addEdge(int capacity, int source, int destination) {
        adjLists[source].add(new Edge(capacity, source, destination));
    }

    public Vertex getVertex(int index) {
        return vertices[index];
    }

    public LinkedList getEdges(int index) {
        return adjLists[index];
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        for (int i = 0; i<size;i++){
            System.out.println("Vertex " + i + " " + getVertex(i));
            for (int j = 0; j<adjLists[i].size(); j++) {
                System.out.println("Destination: " + adjLists[i].get(j).getDestination() + " Capacity: " + adjLists[i].get(j).getCapacity()+ " Flow: " + adjLists[i].get(j).getFlow());
            }
        }
        return "";
    }
}
