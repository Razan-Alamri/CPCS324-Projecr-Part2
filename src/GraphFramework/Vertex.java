/* CPCS324 Project Part 2

 Group members: 
    1- Razan Alamri
    2- Khloud Alsofyani
    3- Leen Ba Galaql
    4- Shatha Binmahfouz
 */
package GraphFramework;

import java.util.LinkedList;

public abstract class Vertex {

    //------------------- Attributes ---------------------
    // label of vertex
    char label;
    // boolean to determine if the vertex is visited or not
    boolean isVisited;
    // to indicate the position of vertex
    int position;
    // Decleare the Linked list variable adjList to store the adjacent vertices after have an edge
    LinkedList<Edge> adjList;

    //------------------------------------ Constructure ------------------------------
    //Default
    public Vertex() {

    }

    /**
     * @param position of a vertex
     */
    public Vertex(int position) {
        this.position = position;
        adjList = new LinkedList<>();
    }
//-------------------------------- Methods -----------------------------------
    // Check if this vertices are adjacent

    public boolean isAdjacent(Vertex adj) {
        for (Edge edge : adjList) {
            if (edge.target == adj) {
                return true;
            }
        }
        return false;
    }

    public char getLabel() {
        return label;
    }

    public void setLabel(char label) {
        this.label = label;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public LinkedList<Edge> getAdjList() {
        return adjList;
    }

    public void setAdjList(LinkedList<Edge> adjList) {
        this.adjList = adjList;
    }

    // Method is responsible for displaying the information of the class attributes.
    public abstract String displyInfo();
}
