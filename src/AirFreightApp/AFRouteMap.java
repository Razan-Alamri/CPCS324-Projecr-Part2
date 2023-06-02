/* CPCS324 Project Part 2

 Group members: 
    1- Razan Alamri
    2- Khloud Alsofyani
    3- Leen Ba Galaql
    4- Shatha Binmahfouz
 */
package AirFreightApp;

import GraphFramework.Edge;
import GraphFramework.Graph;
import GraphFramework.Vertex;

// AFRouteMap is a subclass of Graph, it inherits all attributes, operations & relationships. 
public class AFRouteMap extends Graph {

    // Contructors
    public AFRouteMap() {
        // Call a super class "Graph"
        super();
    }

    public AFRouteMap(int verticesNo, int edgeNo, boolean isDigraph) {
        // Call a super class "Graph"
        super(verticesNo, edgeNo, isDigraph);
    }

    /*
     * Override abstract method from graph class
     * to create object of Vertex "Location"
     */
    @Override
    public Vertex creatVertex(int ID) {
        return new Location(ID);
    }

    /*
     * Override abstract method from graph class
     * to create object of Edge "Route"
     */
    @Override
    public Edge creatEdge(Vertex v, Vertex u, int w) {
        return new Route(v, u, w);
    }

    @Override
    public Edge creatEdge(int w) {
        return new Route(w);
    }

    @Override
    public Edge creatEdge() {
        return new Route();
    }

}
