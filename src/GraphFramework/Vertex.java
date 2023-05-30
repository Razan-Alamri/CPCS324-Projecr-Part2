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