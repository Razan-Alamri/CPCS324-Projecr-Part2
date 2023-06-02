/* CPCS324 Project Part 2

 Group members: 
    1- Razan Alamri
    2- Khloud Alsofyani
    3- Leen Ba Galaql
    4- Shatha Binmahfouz
 */
package GraphFramework;

public class DBAllSourceSPAlg extends ShortestPathAlgorithm {

    // Constructor
    public DBAllSourceSPAlg(Graph graph) {
        //
        super(graph);
    }

    /* */
    public void computeDijkstraBasedSPAlg(Boolean isFile) {
        //
        SingleSourceSPAlg DijkstraAlg = new SingleSourceSPAlg(graph);
        // Print the result
        System.out.println("Shortest paths from all location:\n");
        //
        for (int i = 0; i < graph.totalVertices; i++) {
            //
            DijkstraAlg.computeDijkstraAlg(graph.vertices[i], isFile);
        }
    }
}