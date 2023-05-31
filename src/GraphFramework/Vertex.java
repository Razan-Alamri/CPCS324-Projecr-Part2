/* CPCS324 Project Part 2

 Group members: 
    1- Razan Alamri
    2- Khloud Alsofyani
    3- Leen Ba Galaql
    4- Shatha Binmahfouz
 */
package GraphFramework;

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