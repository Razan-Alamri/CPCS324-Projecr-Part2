/* CPCS324 Project Part 2

 Group members: 
    1- Razan Alamri
    2- Khloud Alsofyani
    3- Leen Ba Galaql
    4- Shatha Binmahfouz
 */
package GraphFramework;

public abstract class Edge {
    /**
     * source vertex of edge
     */
    Vertex source;
    /**
     * target vertex of edge
     */
    Vertex target;
    /**
     * edge weight
     */
    int weight;

    /**
     * empty constructor of class
     */
    public Edge() {

    }

    /**
     * constructor
     * 
     * @param weight weight of edge
     */
    public Edge(int weight) {
        this.weight = weight;
    }

    /**
     * constructor with parameters
     * 
     * @param source source vertex of edge
     * @param target target vertex of edge
     * @param weight edge weight
     */
    public Edge(Vertex source, Vertex target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;

    }

    /**
     * 
     * @return string that print edge
     */
    @Override
    public String toString() {
        return "source " + source.label + "-" + "destenation " + target.label + ": " + weight;
    }
}