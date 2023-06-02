/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphFramework;

import java.util.*;

public class SingleSourceSPAlg extends ShortestPathAlgorithm {
    // Constructor with specific parameter

    public SingleSourceSPAlg(Graph graph) {
        super(graph);

    }

    public void computeDijkstraAlg() {
        // Initialize the distance and visited arrays
        int[][] distance = new int[graph.totalVertices][graph.totalVertices];
        boolean[] visited = new boolean[graph.totalVertices];
        for (int i = 0; i < graph.totalVertices; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
            visited[i] = false;
        }

        // Perform Dijkstra algorithm from each vertex
        for (int i = 0; i < graph.totalVertices; i++) {
            final int sourceIndex = i;
            Vertex source = graph.vertices[sourceIndex];
            distance[sourceIndex][source.position] = 0;

            PriorityQueue<Vertex> pq = new PriorityQueue<>(graph.totalVertices, new Comparator<Vertex>() {
                @Override
                public int compare(Vertex v1, Vertex v2) {
                    return distance[sourceIndex][v1.position] - distance[sourceIndex][v2.position];
                }
            });
            pq.add(source);

            while (!pq.isEmpty()) {
                Vertex curr = pq.poll();
                visited[curr.position] = true;
                for (Edge e : curr.adjList) {
                    Vertex v = e.target;
                    if (!visited[v.position] && distance[sourceIndex][curr.position] != Integer.MAX_VALUE &&
                            distance[sourceIndex][curr.position] + e.weight < distance[sourceIndex][v.position]) {
                        distance[sourceIndex][v.position] = distance[sourceIndex][curr.position] + e.weight;
                        pq.remove(v);
                        pq.add(v);
                    }
                }
            }

            // Reset visited array for next iteration
            Arrays.fill(visited, false);
        }

        // Print the result
        System.out.println("Shortest paths from all vertices:");
        for (int i = 0; i < graph.totalVertices; i++) {
            Vertex source = graph.vertices[i];
            System.out.println("Shortest paths from vertex " + source.label + ":");
            for (int j = 0; j < graph.totalVertices; j++) {
                System.out.println(source.label + " -> " + graph.vertices[j].label + " : " + distance[i][j]);
            }
        }
    }
}