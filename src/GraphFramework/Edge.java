/* CPCS324 Project Part 2

 Group members: 
    1- Razan Alamri
    2- Khloud Alsofyani
    3- Leen Ba Galaql
    4- Shatha Binmahfouz
 */
package GraphFramework;

// Edge is a class that represents a graph edge
public abstract class Edge {

    // Data filed

    // Cost of edge
    int weight;
    // Parent vertex (from association relationship)
    Vertex parent;
    // Source vertex (from association relationship)
    Vertex source;
    // Target vertex (from association relationship)
    Vertex target;

    // Contructors
    public Edge() {

    }

    public Edge(int weight) {
        this.weight = weight;
    }

    public Edge(Vertex source, Vertex target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        this.parent = source;
    }

    // Setteers and Getters
    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getTarget() {
        return target;
    }

    public void setTarget(Vertex target) {
        this.target = target;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    // Method for displaying the information of the Edge class attributes.
    public abstract String displyInfo();
}
