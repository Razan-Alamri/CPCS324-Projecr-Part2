/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphFramework;

public class SingleSourceSPAlg extends ShortestPathAlgorithm {
    // ----------------------------Attributes Section----------------------------

    // Decleare the verticesNum variable to store the total vertices number of the
    // graph
    int verticesNum;
    // Decleare verPath array to store the path from the source to the vertex
    String[] verPath = new String[verticesNum];
    // Decleare verDistance array to store the distance from the source to the
    // vertex
    int[] verDistance = new int[verticesNum];
    // Decleare and initialize constant variable to store infinity number
    // (it's much larger than the range of graph vertices weight)
    final int INFINITY = 100000;

    // ----------------------------Constructors Section--------------------------
    /**
     * Constructor with specific parameters
     *
     * @param graph
     */
    public SingleSourceSPAlg(Graph graph) {
        // Call the super constructor
        super(graph);
        // Intialize the verticesNum variable to store the total vertices number of the
        // graph
        verticesNum = graph.adjList.length;
        // determine the size of the verPath array as number of vertices
        verPath = new String[verticesNum];
        // determine the size of the verDistance array as number of vertices
        verDistance = new int[verticesNum];
    }

    // ----------------------------Methods section-------------------------------
    /**
     * This method will use the graph to use Dijkstra algorithm to compute the
     * length of the shortest paths from the chosen source to the rest of the
     * vertices.
     */
    public void computeDijkstraAlg() {
        // Intially, set all the vertices distance as infinity
        for (int i = 0; i < verticesNum; i++) {
            verDistance[i] = INFINITY;
        }

        // Take the vertex with position 0 as source
        // make the distance between the source to itself 0
        verDistance[0] = 0;
        // intialize the source path
        verPath[0] = "A";

        // for loop to go through all vertices
        for (int i = 0; i < verticesNum; i++) {
            // intially set the v=-1 and the min distance as infinity until find vertex with
            // minimum distance
            int v = -1;
            int minDist = INFINITY;
            // find the minimum verDistance among all the vertices
            for (int j = 0; j < verticesNum; j++) {
                // check the vertex is not visited before and distance is less than the minimum
                // distance so far
                if (graph.vertices[j].isVisited != true && verDistance[j] < minDist) {
                    // set distance of vertex as minimum distance
                    minDist = verDistance[j];
                    // and put the vertex position as v
                    v = j;
                }
            }
            // set the vertex v (with minimum distance) as visited
            graph.vertices[v].isVisited = true;
            // Update the distance of the vertices adjacent to v
            for (int u = 0; u < verticesNum; u++) {
                // check if the vertex is not visited before and there is edge between v and u
                if (graph.vertices[u].isVisited != true && graph.adjList[v].get(u) != null) {
                    // check if the distance from the source to v + the distance of the edge between
                    // v + u
                    // is less than the distance from source to vertex u
                    if (verDistance[v] + graph.adjList[v].get(u).weight < verDistance[u]) {
                        // put the distance from the source to v + the distance of the edge between v +
                        // u as distance of u
                        verDistance[u] = verDistance[v] + graph.adjList[v].get(u).weight;
                        // Update the verPath by adding v path to the current u path
                        verPath[u] = verPath[v] + "->" + (char) (u + 65);
                    }
                }
            }
        }
    }

    /**
     * This method will print the graph after find the length of the shortest
     * paths between every pair in the graph using Floyd-Warshall algorithm
     *
     */
    public void printPath() {
        // print all the vertices' distance with their path
        System.out.println("Dijkstra Algorithm: ");
        System.out.println("\n*** From Vertex " + "A" + " to Other Vertices in Graph *** \n");
        for (int i = 0; i < verticesNum; i++) {
            System.out.println("  > From " + "A" + " --> " + (char) (i + 65));
            System.out.println("      The Path is : " + verPath[i] + " && The Cost is : " + verDistance[i]);

        }
    }

}
