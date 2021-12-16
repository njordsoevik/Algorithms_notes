package flows;

import java.util.LinkedList;

public class PushRelabel {

  public static void main(String[] args) {
    int numberVertices;
    AdjacencyList g;
    // EX 1
//    numberVertices = 4;
//
//    g = new AdjacencyList(numberVertices);
//    g.addEdge(13, 0, 1);
//    g.addEdge(10, 0, 2);
//    g.addEdge(3, 1, 3);
//    g.addEdge(6, 2, 3);
//
//    g.getVertex(0).setHeight(numberVertices);
//
//    // EX 2
//    numberVertices = 4;
//
//    g = new AdjacencyList(numberVertices);
//    g.addEdge(13, 0, 1);
//    g.addEdge(4, 1, 2);
//    g.addEdge(10, 0, 2);
//    g.addEdge(3, 1, 3);
//    g.addEdge(6, 2, 3);
//
//    g.getVertex(0).setHeight(numberVertices);

    // EX 3
    numberVertices = 5;

    g = new AdjacencyList(numberVertices);
    g.addEdge(13, 0, 1);
    g.addEdge(4, 1, 2);
    g.addEdge(10, 0, 2);
    g.addEdge(3, 1, 3);
    g.addEdge(6, 2, 3);
    g.addEdge(5, 3, 4);

    g.getVertex(0).setHeight(numberVertices);



    //g.getVertex(0).setExcess(23);

    // Max flow from source
    //push(g,0);
    //push(g,0);


    LinkedList<Edge> es = g.getEdges(0);
    for (int i = 0; i<es.size();i++) {
      Edge e = es.get(i);
      // Set flow to capacity
      e.setFlow(e.getCapacity());
      // Set inverse flow to capacity
      g.getInverse(e).setFlow((-1)*e.getCapacity());
      // Set destination excess to capacity
      g.getVertex(e.getDestination()).setExcess(e.getCapacity());
    }

    int u = findExcessVertex(g);
    while (u != -1) {
      System.out.println("Excess vertex picked: " + u);
      if (!push(g, u)) {
        System.out.println("Relabeling" + u);
        relabel(g, u);
      }
      System.out.println(g);
      u = findExcessVertex(g);
    }
    System.out.println(g);
  }

  private static void relabel(AdjacencyList g, int u) {
    // RELABEL
    // Check all heights, if less than all, increase to min + 1
    int minHeight = Integer.MAX_VALUE;
    LinkedList<Edge> edges = g.getEdges(u);
    Vertex relabelVertex = g.getVertex(u);

    for (int i = 0; i < edges.size(); i++) {
      Edge e = edges.get(i);
      Vertex destination = g.getVertex(e.getDestination());
      if (destination.getHeight() >= relabelVertex.getHeight() && e.getFlow() < e.getCapacity()) {
        minHeight = Math.min(destination.getHeight(), minHeight);
      }
    }
      relabelVertex.setHeight(minHeight + 1);
  }

  private static int findExcessVertex(AdjacencyList g) {
    for (int i = 0; i < g.getSize() - 1; i++) {
      if (g.getVertex(i).getExcess() > 0) {
        return i;
      }
    }
    return -1;
  }


  private static boolean push(AdjacencyList g, int u) {
    LinkedList<Edge> edges = g.getEdges(u);

    // PUSH
    for (int i = 0; i < edges.size(); i++) {
      Edge e = edges.get(i);
      // If there is extra capacity and excess, push
      if (g.getVertex(e.getDestination()).getHeight() < g.getVertex(e.getSource()).getHeight()) {
        if (e.getFlow() < e.getCapacity() && g.getVertex(e.getSource()).getExcess() > 0) {

          // Value to increase flow
          int value = Math.min(g.getVertex(e.getSource()).getExcess(), e.getCapacity()-e.getFlow());

          Vertex source = g.getVertex(e.getSource());
          Vertex dest = g.getVertex(e.getDestination());

          // Get flow update difference

          source.setExcess(source.getExcess() - value);
          if (dest.getIndex() != 0) {
            dest.setExcess(dest.getExcess() + value);
          }

          g.getInverse(e).setFlow(g.getInverse(e).getFlow()-value);
          e.setFlow(e.getFlow()+value);
          return true;
        }
      }

    }

    return false;

  }
}