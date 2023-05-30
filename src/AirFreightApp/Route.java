package AirFreightApp;

import GraphFramework.Edge;
import GraphFramework.Vertex;

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

}
