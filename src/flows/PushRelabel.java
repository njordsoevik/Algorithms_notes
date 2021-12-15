package flows;

import java.util.LinkedList;

public class PushRelabel {

    public static void main(String[] args) {
        AdjacencyList g = new AdjacencyList(6);
        g.addEdge(13, 0, 1);
        g.addEdge(10, 0, 2);
        g.addEdge(3, 2, 1);
        g.addEdge(6, 1, 3);
        g.addEdge(7, 1, 5);
        g.addEdge(10, 3, 4);
        g.addEdge(5, 4, 5);

        g.getVertex(0).setHeight(6);

        // Max flow from source
        LinkedList<Edge> es = g.getEdges(0);
        for (int i = 0; i < es.size(); i++) {
            Edge e = es.get(i);
            push(g, e, e.getCapacity());
        }


        System.out.println(g);

        // Check adjacent from source for excess
        int vPush = findExcessVertex(g);
        while (vPush != -1) {

            Vertex vPushVertex = g.getVertex(vPush);
            // Check any outgoing edges
            LinkedList<Edge> edges = g.getEdges(vPush);

            // RELABEL
            // Check all heights, if less than all, increase to min + 1
            int minHeight = Integer.MAX_VALUE;
            boolean relabel = true;
            for (int i = 0; i < edges.size(); i++) {
                Vertex destination = g.getVertex(edges.get(i).getDestination());
                if (destination.getHeight() >= vPushVertex.getHeight()) {
                    minHeight = Math.min(destination.getHeight(), minHeight);
                } else {
                    relabel = false;
                }
            }
            if (relabel) {
                // Relabel
                System.out.println(vPushVertex.getHeight()+" "+vPushVertex.getIndex());
                vPushVertex.setHeight(minHeight + 1);
            }


            // PUSH
            for (int i = 0; i < edges.size(); i++) {
                Edge e = edges.get(i);
                // If there is extra capacity and excess, push
                if (e.getFlow() < e.getCapacity() && vPushVertex.getExcess() > 0) {
                    push(g, e, Math.min(vPushVertex.getExcess(), e.getCapacity() - e.getFlow()));
                }
            }
            break;
        }

        System.out.println(g);

    }

    private static int findExcessVertex(AdjacencyList g) {
        for (int i = g.getSize() - 1; i >= 0; i--) {
            if (g.getVertex(i).getExcess() > 0) {
                return i;
            }
        }
        return -1;
    }


    private static void push(AdjacencyList a, Edge e, int value) {
        // Update vertex excess
        Vertex source = a.getVertex(e.getSource());
        Vertex dest = a.getVertex(e.getDestination());
        // Get flow update difference
        int flowDifference = value - e.getFlow();
        // Add to vertex excess

        if (source.getIndex() != 0) {
            source.setExcess(source.getExcess() - flowDifference);
        }

        if (dest.getIndex() != 0) {
            dest.setExcess(dest.getExcess() + flowDifference);
        }

        // Update edge flow
        e.setFlow(value);


    }
}
