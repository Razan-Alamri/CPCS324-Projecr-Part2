/* CPCS324 Project Part 2

 Group members: 
    1- Razan Alamri
    2- Khloud Alsofyani
    3- Leen Ba Galaql
    4- Shatha Binmahfouz
 */
package GraphFramework;

import java.util.*;

/* SingleSourceSPAlg class represents the implementation of the algorithm responsible for computing the
    shortest path for a specified source, which is located in the computeDijkstraAlg()method.
 */
public class SingleSourceSPAlg extends ShortestPathAlgorithm {

    // Constructor
    public SingleSourceSPAlg(Graph graph) {
        // Call a super class "ShortestPathAlgorithm"
        super(graph);
    }

    // For computing the shortest path for a specified source.
    public void computeDijkstraAlg(Vertex source, Boolean isFile) {

        // Initialize the necessary data structures
        // Array to hold the distances from source to each vertex
        int[] distance = new int[graph.totalVertices];
        // Array to hold the previous vertex on the shortest path from
        // source to each vertex
        int[] previous = new int[graph.totalVertices];
        // Array to keep track of visited vertices
        boolean[] visited = new boolean[graph.totalVertices];
        // Set all distances to infinity initially
        Arrays.fill(distance, Integer.MAX_VALUE);
        // Set all previous vertices to -1 initially
        Arrays.fill(previous, -1);
        // Set all vertices as unvisited initially
        Arrays.fill(visited, false);
        // Set the distance to the source vertex as 0
        distance[source.position] = 0;

        // Create a priority queue to store unvisited vertices and order them by their
        // distance from the source vertex
        PriorityQueue<Vertex> pq = new PriorityQueue<>(graph.totalVertices, new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                return distance[v1.position] - distance[v2.position];
            }
        });
        // Add the source vertex to the priority queue
        pq.add(source);

        // Run the Dijkstra's algorithm loop
        while (!pq.isEmpty()) {
            // Select the unvisited vertex with the smallest distance from the priority
            // queue
            Vertex curr = pq.poll();
            // Mark the current vertex as visited
            visited[curr.position] = true;
            // Iterate over the adjacent edges of the current vertex
            for (Edge e : curr.adjList) {
                // Get the adjacent vertex
                Vertex v = e.target;
                if (!visited[v.position] && distance[curr.position] != Integer.MAX_VALUE &&
                        distance[curr.position] + e.weight < distance[v.position]) {
                    /*
                     * If the adjacent vertex is unvisited and a shorter path to it is found through
                     * the current vertex,
                     * update its distance and previous vertex information and add it back to the
                     * priority queue
                     */
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
                    // If there is no path from the source vertex to the current vertex, print a
                    // message indicating so
                    System.out.println(source.displyInfo() + " has no path to " + graph.vertices[i].displyInfo());
                    continue;
                } else {
                    // If there is a path from the source vertex to the current vertex, print the
                    // path and its length
                    System.out.print(source.displyInfo() + " ");
                }
                LinkedList<Integer> path = new LinkedList<>();
                int curr = i;
                while (curr != -1) {
                    // Add the current vertex to the front of the path
                    path.addFirst(curr);
                    // Get the previous vertex on the shortest path to the current vertex
                    curr = previous[curr];
                }
                for (int j = 0; j < path.size(); j++) {
                    // Print the vertices on the path
                    System.out.print(graph.vertices[path.get(j)].displyInfo());
                    if (j < path.size() - 1) {
                        System.out.print(" ");
                    }
                }
                // Print the length of the path
                System.out.print("--- route length: " + distance[i]);
                System.out.println();
            }
            System.out.println("----------------------------------------------------------------------------------");
        }
    }
}
