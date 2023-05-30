package GraphFramework;

public class SingleSourceSPAlg extends ShortestPathAlgorithm {

    /**
     * save the smallest smallestDistance from source to the vertex
     */
    int[] smallestDistance;
    /**
     * array of string to save the smallestDistance form source to each vertex
     */
    String[] path;

    /**
     * 
     * @param graph graph input
     */
    public SingleSourceSPAlg(Graph graph) {
        this.graph = graph;
    }

    /**
     * 
     * method to compute Dijkstra Algorithm
     */
    public void computeDijkstraAlg() {

        int infinity = 999999;
        // intilize array
        smallestDistance = new int[graph.adjMatrix.length];
        path = new String[graph.adjMatrix.length];

        // update all the vertices smallestDistance as infinity
        for (int i = 0; i < graph.adjMatrix.length; i++) {
            smallestDistance[i] = infinity;
        }

        smallestDistance[0] = 0;// let the source distancde be zero
        path[0] = "A --" + smallestDistance[0] + "--> "; // update it's path twoo

        // Start algorithm
        for (int i = 0; i < graph.adjMatrix.length; i++) {
            // Find the minium smallestDistance among all the vertices adjacent
            // ----
            // this part act like the PQ to find the smallest distance
            int u = -1;
            int minDistance = infinity;
            for (int j = 0; j < graph.adjMatrix.length; j++) {
                if (graph.vertices[j].isVisited != true && smallestDistance[j] < minDistance) {
                    minDistance = smallestDistance[j];
                    u = j; // Min vertix index
                }
            }
            // Once the smallest is found
            graph.vertices[u].isVisited = true;// update isVisited to true

            // Update the adjacent distances
            for (int v = 0; v < graph.adjMatrix.length; v++) {

                if (graph.vertices[v].isVisited != true && graph.adjMatrix[u][v].weight != infinity
                        && graph.adjMatrix[u][v].weight != 0) {
                    // if the distance from this vertex to its adjacent smaller
                    if (smallestDistance[u] + graph.adjMatrix[u][v].weight < smallestDistance[v]) {
                        // update the smallest distance
                        smallestDistance[v] = smallestDistance[u] + graph.adjMatrix[u][v].weight;
                        // Update the path too
                        path[v] = path[u] + (char) (v + 65) + " --" + (smallestDistance[v] - smallestDistance[u])
                                + "--> ";
                    }
                }
            }
        }

    }

    /**
     * 
     * method to print result of smallest distance
     */
    public void printResult() {
        // Print all the distances with the pathes
        System.out.println("\n----- Dijkstra Algorithm -----");
        System.out.println("All the shortest distances from vertex 'A' to other vertices in graph");
        for (int i = 0; i < graph.verticesNo; i++) {

            System.out.println("---> Shortest Distance from 'A' to '" + (char) (i + 65) + "' is " + smallestDistance[i]
                    + ", the Path: " + path[i] + " " + (char) (i + 65) + " " + smallestDistance[i]);
        }

    }

}