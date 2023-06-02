/* CPCS324 Project Part 2

 Group members: 
    1- Razan Alamri
    2- Khloud Alsofyani
    3- Leen Ba Galaql
    4- Shatha Binmahfouz
 */
package GraphFramework;

import java.io.*;
import java.util.*;

//import AirFreightApp.AFRouteMap;

public abstract class Graph {

    // ----------------------------Attributes Section----------------------------
    // deleare verticesNo variable number of vertices of the graph.
    // It should be incremented whenever a method a new object is added to the
    // vertex list.
    // int verticesNo;
    // deleare edgeNo variable to store edge's number of the graph.
    // It should be incremented by one in case add edge in directed graph and by two
    // if it is an undirected graph.
    int edgeNo;
    // decleare boolean variable true
    // -->if the graph is directed graph, false -->if the graph is undirected
    public boolean isDigraph;
    // decleare variable to store the list of vertices of a graph
    public Vertex[] vertices;
    // decleare variable to store the total number of edges
    int totalEdges;
    // decleare variable to store the total number of vertices
    int totalVertices;

    // ----------------------------Constructors section----------------------------
    /**
     * Default constructor
     */
    public Graph() {

    }

    public Graph(int totalVerticesNo, int totalEdgesNo, boolean isDigraph) {
        this.totalEdges = totalEdgesNo;
        this.isDigraph = isDigraph;
        this.totalVertices = totalVerticesNo;
        vertices = new Vertex[totalVertices];
    }

    // ----------------------------Methods section----------------------------
    /**
     * This method pass the source and target to create edge between them and
     * add it to the adjMatrix[v][u] (and adjMatrix[u][v] if its undirected
     * graph)
     *
     * @param v -- source vertex label
     * @param u -- target vertex label
     * @param w -- weight of the edge to be created
     * @return Edge object was created between v and u (source and target)
     */

    // Abstract method to create object of Vertex
    public abstract Vertex creatVertex(int ID);

    // Abstract method to create object of Edge
    public abstract Edge creatEdge(Vertex v, Vertex u, int w);

    public abstract Edge creatEdge(int w);

    public abstract Edge creatEdge();

    /**
     * This method to make graph 1- first make the nessecary edge with random
     * weight to ensure its connected using addEdge (and addEdge will create
     * source and target vertices if it not already created) 2- make the
     * remaining edge with random weight using addEdge (and addEdge will create
     * source and target vertices if it not already created)
     */
    public void makeGraph() {
        Random random = new Random(); // to pick a random vertix
        // (minimum edges = nv -1)
        for (int i = 0; i < totalVertices - 1; i++) {
            // i = current vertex, i + 1 = next vertix
            int randomWeight = random.nextInt(50) + 1;
            addEdge(i, i + 1, randomWeight);
        }

        // make random graph with remaining edges
        // (remaining edges = ne - (nv -1))

        int srcVertex, desVertex;

        // Create m edges randomly between vertices
        HashSet<String> edgeSet = new HashSet<>();
        for (int i = 0; i < (totalEdges - totalVertices + 1); i++) {
            srcVertex = random.nextInt(totalVertices); // pick random source of the edge
            desVertex = random.nextInt(totalVertices); // pick random distenation of the edge

            if (srcVertex == desVertex || edgeSet.contains(srcVertex + ":" + desVertex)) {
                i--; // do not count this iteration
                continue; // generate another pairs
            }
            int randomWeight = random.nextInt(50) + 1;
            // no edge between these vertices
            // add an edge
            addEdge(srcVertex, desVertex, randomWeight);
            // Add edge to set to prevent duplicates
            edgeSet.add(srcVertex + ":" + desVertex);

            // to add label of vertex
            addLabel(srcVertex);
            addLabel(desVertex);
        }
    }

    /**
     * This method reads the edges and vertices from the text file whose name is
     * specified by the parameter filename and place data in a Graph object.
     */
    public void readGraphFromFile(File fileName) throws FileNotFoundException {
        // create scanner object to read from the file
        Scanner readFile = new Scanner(fileName);
        readFile.next();
        // read the integer and store it in the digraph variable
        // 0 means flase, 1 means true
        int digraph = readFile.nextInt();
        // if it's digraph set isDigraph variable true
        if (digraph == 1) {
            isDigraph = true;
        }
        // read the number of vertices and store it in the totalVertices variable
        totalVertices = readFile.nextInt();
        // read the number of edges and store it in the totalEdges variable
        totalEdges = readFile.nextInt();

        // initialize the vertices array with number of vertices as size
        vertices = new Vertex[totalVertices];

        /*
         * loop to read source label and target label and doing some preprocessing
         * then call the addEdge() method to determine the position of the Edge
         * and then processing the returned Edge object to set the label attribute of
         * the source
         * and target attributes that are of the type Vertex
         */
        while (edgeNo < totalEdges) {
            // read source label
            char srcLabel = readFile.next().charAt(0);
            // read target label
            char targetLabel = readFile.next().charAt(0);
            // read weight
            int wieght = readFile.nextInt();
            // invoke addEdge to add edge between the source and target position
            // and store the return edge object
            // Note: position= label-65
            // Edge edge =
            addEdge(srcLabel - 65, targetLabel - 65, wieght);
            // call add vertex position method to add the source vertex label
            addLabel(srcLabel);
            // calladd vertex position method to add the target vertex label
            addLabel(targetLabel);
        }
        // Close scanner
        readFile.close();
        ;
    }

    public Edge addEdge(int v, int u, int w) {
        // if the source vertex not already created
        // System.out.println(vertices[v].label);
        if (vertices[v] == null) {
            // create vertex with v position to be the source
            Vertex src = creatVertex(v);
            // add the source vertex to the vertices list
            vertices[v] = src;
        }
        // if the target vertex not already created
        if (vertices[u] == null) {
            // create vertex with u position to be the target
            Vertex target = creatVertex(u);
            // add the target vertex to the vertices list
            vertices[u] = target;
        }
        Edge e = creatEdge(vertices[v], vertices[u], w);
        vertices[v].adjList.add(e);
        /*
         * Increment the edge count by 1 If it is a undirected graph
         * and by 2 if directed graph
         */
        edgeNo++;
        if (!isDigraph) {
            Edge e2 = creatEdge(vertices[u], vertices[v], w);
            vertices[u].adjList.add(e2);
            edgeNo++;
        }
        return e;
    }

    /**
     * This method will print the graph after read it from the file and create
     * the matrix
     */
    public void PrintGraph() {
        System.out.println("Adjacency List:");
        for (int i = 0; i < totalVertices; i++) {
            int length = 0;
            System.out.print(vertices[i].displyInfo() + " -> ");
            for (Edge e : vertices[i].adjList) {
                System.out.print(vertices[e.target.position].displyInfo());
                length += e.getWeight();
            }
            System.out.print(" --- route length: " + length);
            System.out.println();
        }
    }

    // For add label from file
    public void addLabel(char lable) {
        vertices[lable - 65].label = lable;
    }

    // For add label with uniqe number
    public void addLabel(int d) {
        if (vertices[d].label == 0) {
            int b = d;
            char c = (char) (b + '0');
            vertices[d].label = c;
        }

    }

    public int getEdgeNo() {
        return edgeNo;
    }

    public void setEdgeNo(int edgeNo) {
        this.edgeNo = edgeNo;
    }

    public boolean isDigraph() {
        return isDigraph;
    }

    public void setDigraph(boolean isDigraph) {
        this.isDigraph = isDigraph;
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }

    public int getTotalEdges() {
        return totalEdges;
    }

    public void setTotalEdges(int totalEdges) {
        this.totalEdges = totalEdges;
    }

    public int getTotalVertices() {
        return totalVertices;
    }

    public void setTotalVertices(int totalVertices) {
        this.totalVertices = totalVertices;
    }
}
