/* CPCS324 Project Part 2

 Group members: 
    1- Razan Alamri
    2- Khloud Alsofyani
    3- Leen Ba Galaql
    4- Shatha Binmahfouz
 */
package GraphFramework;

import java.util.*;

public class SingleSourceSPAlg extends ShortestPathAlgorithm {
    /*
     * int[] distance;
     * int[] previous;
     * boolean[] visited;
     * Vertex source;
     */
    // Constructor
    public SingleSourceSPAlg(Graph graph) {
        //
        super(graph);
    }

    /* */
    public void computeDijkstraAlg(Vertex source, Boolean isFile) {
        int[] distance = new int[graph.totalVertices];
        int[] previous = new int[graph.totalVertices];
        boolean[] visited = new boolean[graph.totalVertices];
        // this.source = source;
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(previous, -1);
        Arrays.fill(visited, false);

        distance[source.position] = 0;

        PriorityQueue<Vertex> pq = new PriorityQueue<>(graph.totalVertices, new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                return distance[v1.position] - distance[v2.position];
            }
        });
        pq.add(source);

        while (!pq.isEmpty()) {
            Vertex curr = pq.poll();
            visited[curr.position] = true;
            for (Edge e : curr.adjList) {
                Vertex v = e.target;
                if (!visited[v.position] && distance[curr.position] != Integer.MAX_VALUE &&
                        distance[curr.position] + e.weight < distance[v.position]) {
                    distance[v.position] = distance[curr.position] + e.weight;
                    previous[v.position] = curr.position;
                    pq.remove(v);
                    pq.add(v);
                }
            }
        }
        if (isFile) {
            // Print the result
            System.out.println("Shortest paths from location " + source.label + ":");
            for (int i = 0; i < graph.totalVertices; i++) {
                if (distance[i] == Integer.MAX_VALUE) {
                    System.out.println(source.displyInfo() + " has no path to " + graph.vertices[i].displyInfo());
                    continue;
                } else {
                    System.out.print(source.displyInfo() + " ");
                }
                LinkedList<Integer> path = new LinkedList<>();
                int curr = i;
                while (curr != -1) {
                    path.addFirst(curr);
                    curr = previous[curr];
                }
                for (int j = 0; j < path.size(); j++) {
                    System.out.print(graph.vertices[path.get(j)].displyInfo());
                    if (j < path.size() - 1) {
                        System.out.print(" ");
                    }
                }

                System.out.print("--- route length: " + distance[i]);
                System.out.println();
            }
            System.out.println("----------------------------------------------------------------------------------");
        }

    }
}
