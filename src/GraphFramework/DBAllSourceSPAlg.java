package GraphFramework;

public class DBAllSourceSPAlg extends ShortestPathAlgorithm {
    /**
     * to indicate if user read graph from file or not
     */
    private boolean isReadFromFile;

    /**
     * 
     * @param graph graph input
     */
    public DBAllSourceSPAlg(Graph graph) {
        this.graph = graph;
    }

    /**
     * 
     * method to compute FloyedWarshall Algorithm and find all shortest path
     * algorithms
     */
    public void computeFloyedWarshallAlg() {
        // Get number of vertices
        int numOfVertices = graph.verticesNo;
        // Start the algorithm
        for (int k = 0; k < numOfVertices; k++) {
            for (int i = 0; i < numOfVertices; i++) {
                for (int j = 0; j < numOfVertices; j++) {
                    // if the sum of the transitive weights smqaller than current weight
                    // update the weight
                    graph.adjMatrix[i][j].weight = Math.min(graph.adjMatrix[i][j].weight,
                            (graph.adjMatrix[i][k].weight + graph.adjMatrix[k][j].weight));

                } // end of j llop
            } // end of i lopp
            if (isReadFromFile) {// if user has been read graph from file
                System.out.println("\niteation = " + (k + 1));// print the result of each iteration
                graph.print_graph();
            }
        } // end of k loop
    }

    public void setReadFromFile(boolean isReadFromFile) {
        this.isReadFromFile = isReadFromFile;
    }

}