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
    g.getVertex(0).setExcess(23);

    // Max flow from source
    LinkedList<Edge> es = g.getEdges(0);
    push(g,0);
    push(g,0);
    relabel(g,2);
    push(g,2);
    relabel(g,1);
    push(g,1);

    System.out.println(g);
//
//    // Check adjacent from source for excess
//    int u = findExcessVertex(g);
//    while (u != -1) {
//      Vertex vPushVertex = g.getVertex(u);
//      // Check any outgoing edges
//      if (!push(g, u)) {
//        relabel(g, u);
//      }
//      break;
//    }
  }

  private static void relabel(AdjacencyList g, int u) {
    // RELABEL
    // Check all heights, if less than all, increase to min + 1
    int minHeight = Integer.MAX_VALUE;
    boolean relabel = true;
    LinkedList<Edge> edges = g.getEdges(u);
    Vertex relabelVertex = g.getVertex(u);

    for (int i = 0; i < edges.size(); i++) {
      Vertex destination = g.getVertex(edges.get(i).getDestination());
      if (destination.getHeight() >= relabelVertex.getHeight()) {
        minHeight = Math.min(destination.getHeight(), minHeight);
      } else {
        relabel = false;
      }
    }
    if (relabel) {
      // Relabel
      relabelVertex.setHeight(minHeight + 1);
    }
  }

  private static int findExcessVertex(AdjacencyList g) {
    for (int i = g.getSize() - 1; i >= 0; i--) {
      if (g.getVertex(i).getExcess() > 0) {
        return i;
      }
    }
    return -1;
  }


  private static boolean push(AdjacencyList g, int u) {

    LinkedList<Edge> edges = g.getEdges(u);
    boolean push = false;

    // PUSH
    for (int i = 0; i < edges.size(); i++) {
      Edge e = edges.get(i);
      // If there is extra capacity and excess, push
      if (g.getVertex(e.getDestination()).getHeight() < g.getVertex(e.getSource()).getHeight()) {
        if (e.getFlow() < e.getCapacity() && g.getVertex(e.getSource()).getExcess() > 0) {
          int value = Math.min(g.getVertex(e.getSource()).getExcess(), e.getCapacity()-e.getFlow());

          Vertex source = g.getVertex(e.getSource());
          Vertex dest = g.getVertex(e.getDestination());

          // Get flow update difference
          int flowDifference = value - e.getFlow();

          source.setExcess(source.getExcess() - flowDifference);
          dest.setExcess(dest.getExcess() + flowDifference);
          e.setFlow(value);
          return true;
        }
      }

    }

    return false;

  }
}