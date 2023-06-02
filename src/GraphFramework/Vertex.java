/* CPCS324 Project Part 2

 Group members: 
    1- Razan Alamri
    2- Khloud Alsofyani
    3- Leen Ba Galaql
    4- Shatha Binmahfouz
 */
package GraphFramework;

import java.util.LinkedList;

public class Vertex {
    /**
     * label of vertex
     */
    char label;
    /**
     * boolean to determine if the vertex is visited or not
     */
    boolean isVisited;
    /**
     * to indicate the position of vertex
     */
    int position;
    // Decleare the Linked list variable adjList to store the adjacent vertices to
    // this vertex(have edge)
    LinkedList<Edge> adjList;

    /**
     * empty constructor
     */
    public Vertex() {

    }

    /**
     * 
     * @param position of a vertex
     */
    public Vertex(int position) {
        this.position = position;
        adjList = new LinkedList<>();
    }

    /**
     * 
     * method to get position of vertex
     * 
     * @return the position number
     */
    public int getVertPos() {
        return position;
    }

}