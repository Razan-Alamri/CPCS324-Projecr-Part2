/* CPCS324 Project Part 2

 Group members: 
    1- Razan Alamri
    2- Khloud Alsofyani
    3- Leen Ba Galaql
    4- Shatha Binmahfouz
 */
package GraphFramework;

public abstract class Edge {
    
    //---------------------- Attributes ---------------------------------------

    Vertex src;
    Vertex target;
    Vertex parent;
    int weight;

        //---------------------- Constructures ---------------------------------------
    //Default
    public Edge() {

    }

    public Edge(int weight) {
        this.weight = weight;
    }

    public Edge(Vertex src, Vertex target, int weight) {
        this.src = src;
        this.target = target;
        this.weight = weight;
        this.parent = src;
    }

            //---------------------- Methods ---------------------------------------

    public Vertex getSource() {
        return src;
    }

    public void setSource(Vertex source) {
        this.src = source;
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
