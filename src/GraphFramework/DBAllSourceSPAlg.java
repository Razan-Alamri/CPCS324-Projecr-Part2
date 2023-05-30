package GraphFramework;

import AirFreightApp.Route;

public class DBAllSourceSPAlg extends ShortestPathAlgorithm {
    // ----------------------------Attributes Section----------------------------

    // Decleare the verticesNum variable to store the total vertices number of the
    // graph
    int verticesNum;
    // Decleare and initialize constant variable to store infinity number
    // (it's much larger than the range of graph vertices weight)
    final int INFINITY = 100000;

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
    public void computeFloyedWarshalAlg(boolean fromFile) {
        // First loop: to compute the transitive closure in two cases:
        // 1)If the transitive closure edges weight less than the edge weight
        // 2)If there is no edge between i and j vertices
        for (int k = 0; k < verticesNum; k++) {
            // Second loop: represent the row of the matrix
            for (int i = 0; i < verticesNum; i++) {
                // Third loop: represent the column of the matrix
                for (int j = 0; j < verticesNum; j++) {
                    // Put the minimum between the transitive closure edges weight and the edge
                    // weight
                    graph.adjMatrix[i][j].weight = Math.min(graph.adjMatrix[i][j].weight,
                            (graph.adjMatrix[i][k].weight + graph.adjMatrix[k][j].weight));
                } // end third loop
            } // end second loop
            if (fromFile)
                printPath();
        } // end third loop
    }

    /**
     * This method will print the graph after find the length of the shortest
     * paths between every pair in the graph using Floyd-Warshall algorithm
     *
     */
    public void printPath() {
        System.out.print("   ");
        for (int i = 0; i < verticesNum; i++) {
            System.out.printf("%-4s", (char) (i + 65));
        }
        System.out.println();

        for (int i = 0; i < verticesNum + 1; i++) {
            System.out.print("----");
        }
        System.out.println();

        for (int i = 0; i < verticesNum; i++) {
            System.out.print((char) (i + 65) + "| ");
            for (int j = 0; j < verticesNum; j++) {
                if (j == verticesNum - 1) {
                    if (graph.adjMatrix[i][j].weight == INFINITY) {
                        System.out.print("∞ ");
                    } else {
                        System.out.printf("%-2d ", graph.adjMatrix[i][j].weight);
                    }
                    continue;
                }
                if (graph.adjMatrix[i][j].weight == INFINITY) {
                    System.out.print("∞ ,");
                } else {
                    System.out.printf("%-2d, ", graph.adjMatrix[i][j].weight);
                }
            }
            System.out.println();
        }
    }

    /**
     * The purpose of this method to ensure there is edge between all pair of
     * vertices, if there is pair with no edge between them simply this method
     * will add edge with infinity weight (which is number larger than the range
     * of weight of vertices), if the pair is diagonal that means i=j the edge
     * will be with 0 weight (always the distance between the vertex and itself
     * is 0)
     */
    public void setNullEdge() {
        // First loop: represent the row of the matrix
        for (int i = 0; i < graph.adjMatrix.length; i++) {
            // Second loop: represent the column of the matrix
            for (int j = 0; j < graph.adjMatrix[i].length; j++) {
                // If the pair has no edge between them create one
                if (graph.adjMatrix[i][j] == null) {
                    // if the pair is diagonal create edge with weight 0
                    if (i == j) {
                        graph.adjMatrix[i][j] = new Route(graph.vertices[i], graph.vertices[j], 0);
                    } // if the pair is not diagonal create edge with weight infinity
                    else {
                        graph.adjMatrix[i][j] = new Route(graph.vertices[i], graph.vertices[j], INFINITY);
                    } // end if else statement
                } // end if statement
            } // end second loop
        } // end first loop
    }

}