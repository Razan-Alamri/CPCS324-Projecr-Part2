/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphFramework;

import AirFreightApp.Route;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DBAllSourceSPAlg extends ShortestPathAlgorithm {
    // ----------------------------Attributes Section----------------------------

    // Decleare the verticesNum variable to store the total vertices number of the
    // graph
    int verticesNum;
    // Decleare and initialize constant variable to store infinity number
    // (it's much larger than the range of graph vertices weight)
    final int INFINITY = 100000;
    private int[] dist;
    private boolean[] visited;
    private int[][] graph;
    private int source;

    // ----------------------------Constructors Section----------------------------
    /**
     * Constructor with specific parameters
     *
     * @param graph
     */
    public DBAllSourceSPAlg(Graph graph) {
        // Call the super constructor
        super(graph);
        // Intialize the verticesNum variable to store the total vertices number of the
        // graph
        verticesNum = graph.totalVertices;
        

    }

// ----------------------------Methods section----------------------------
    /**
     * This method will use the graph to use Floyd-Warshall algorithm to compute
     * the distance matrix that indicates the length of the shortest paths
     * between every pair in the graph
     */
    public void computeDijkstraBasedSPAlg(boolean fromFile) {

        // Initialize the distance array with infinity for all vertices except the source vertex
        dist = new int[verticesNum];
        visited = new boolean[verticesNum];
        for (int i = 0; i < verticesNum; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        dist[source] = 0;

        // Create a priority queue to store the vertices according to their distances
        PriorityQueue<Integer> pq = new PriorityQueue<>(verticesNum, new Comparator<Integer>() {
            public int compare(Integer u, Integer v) {
                return dist[u] - dist[v];
            }
        });
        pq.offer(source);

        while (!pq.isEmpty()) {
            // Select the vertex with the minimum distance
            int u = pq.poll();
            visited[u] = true;

            // Update the distances of adjacent vertices
            for (int v = 0; v < verticesNum; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    pq.offer(v);
                }
            }
        }
    }

    /**
     * This method will print the graph after find the length of the shortest
     * paths between every pair in the graph using Floyd-Warshall algorithm
     *
     */


    /**
     * The purpose of this method to ensure there is edge between all pair of
     * vertices, if there is pair with no edge between them simply this method
     * will add edge with infinity weight (which is number larger than the range
     * of weight of vertices), if the pair is diagonal that means i=j the edge
     * will be with 0 weight (always the distance between the vertex and itself
     * is 0)
     */
}
