/* CPCS324 Project Part 2

 Group members: 
    1- Razan Alamri
    2- Khloud Alsofyani
    3- Leen Ba Galaql
    4- Shatha Binmahfouz
 */
package AirFreightApp;

import GraphFramework.Edge;
import GraphFramework.Vertex;

// Route it is a subclass of Edge, it inherits all attributes, operations & relationships.
public class Route extends Edge {

    public Route(Vertex v, Vertex u, int w) {
        super(v, u, w);
    }

    public Route(int w) {
        super(w);
    }

    public Route() {
        super();
    }
    /*
     * Override method that responsible for displaying
     * the information of the class attributes
     */

    @Override
    public String displyInfo() {

        return "--- route length: " + getWeight();
    }

}
