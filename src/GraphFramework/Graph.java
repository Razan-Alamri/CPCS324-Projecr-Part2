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
public abstract class Graph {

    // ----------------------------Attributes ----------------------------  
    // deleare edgeNo variable to store edge's number of the graph.
    int edgeNum;
    // decleare boolean variable true
    // -->if the graph is directed graph, false -->if the graph is undirected
    public boolean isDigraph;
    // decleare Vertex array to store the list of vertices of a graph
    public Vertex[] vertices;
    // decleare int type variable to store the total number of edges
    int totalEdges;
    // decleare int type variable to store the total number of vertices
    int totalVertices;

    // ----------------------------Constructors ----------------------------
    /*
     * Default constructor
     */
    public Graph() {

    }

    public Graph(int totalVerticesNum, int totalEdgesNum, boolean isDigraph) {
        this.totalEdges = totalEdgesNum;
        this.isDigraph = isDigraph;
        this.totalVertices = totalVerticesNum;
        vertices = new Vertex[totalVertices];
    }

    // ----------------------------Methods----------------------------
   
   // Abstract method to create object of Vertex
    public abstract Vertex creatVertex(int ID);

    // Abstract method to create object of Edge
    public abstract Edge creatEdge(Vertex v, Vertex u, int w);

    public abstract Edge creatEdge(int w);

    public abstract Edge creatEdge();

    /**
     * This method to make graph 
     * 1- first make the necessary edge with random weight to ensure its connected using addEdge 
     * addEdge will create source and target vertices if it not already created
     * 2- make the remaining edge with random weight using addEdge 
     * and addEdge will create source and target vertices if it not already created)
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
        int sourceVertex, destVertex;

        // Create m edges randomly between vertices
        HashSet<String> edgeSet = new HashSet<>();
        for (int i = 0; i < (totalEdges - totalVertices + 1); i++) {
            sourceVertex = random.nextInt(totalVertices); // to pick random source of the edge
            destVertex = random.nextInt(totalVertices); // to pick random distenation of the edge

            if (sourceVertex == destVertex || edgeSet.contains(sourceVertex + ":" + destVertex)) {
                i--; // do not count this iteration
                continue; // generate another pairs
            }
            int randomWeight = random.nextInt(50) + 1;
            // no edge between these vertices
            // add an edge
            addEdge(sourceVertex, destVertex, randomWeight);
            // Add edge to set to avoid duplicates
            edgeSet.add(sourceVertex + ":" + destVertex);

            // to add label of vertex
            addLabel(sourceVertex);
            addLabel(destVertex);
        }
    }

    /**
     * This method reads the edges and vertices from the text file whose name is
     * specified by the parameter filename and place data in a Graph object.
     */
    public void readGraphFromFile(File fileName) throws FileNotFoundException {
        // create scanner object to read from the file
        Scanner readF = new Scanner(fileName);
        readF.next();
        // read the integer and store it in the digraph variable
        // 0 means flase, 1 means true
        int digraph = readF.nextInt();
        // if it's digraph set isDigraph variable true
        if (digraph == 1) {
            isDigraph = true;
        }
        // read the number of vertices and store it in the totalVertices variable
        totalVertices = readF.nextInt();
        // read the number of edges and store it in the totalEdges variable
        totalEdges = readF.nextInt();

        // initialize the vertices array with number of vertices for the size
        vertices = new Vertex[totalVertices];

        /*
         * loop to read source label and target label and doing some preprocessing
         * then call the addEdge() method to determine the position of the Edge
         * and then processing the returned Edge object to set the label attribute of
         * the source
         * and target attributes that are of the type Vertex
         */
        while (edgeNum < totalEdges) {
            // read source label
            char sourceLabel = readF.next().charAt(0);
            // read target label
            char targetLabel = readF.next().charAt(0);
            // read weight
            int wieght = readF.nextInt();
            // invoke addEdge to add edge between the source and target position
            // and store the return edge object
            // Note: position= label-65
            // Edge edge =
            addEdge(sourceLabel - 65, targetLabel - 65, wieght);
            // call add vertex position method to add the source vertex label
            addLabel(sourceLabel);
            // call add vertex position method to add the target vertex label
            addLabel(targetLabel);
        }
        // Close scanner object
        readF.close();
        ;
    }

    public Edge addEdge(int v, int u, int w) {
        // if the source vertex not already created
        if (vertices[v] == null) {
            // create vertex with v position to be the source
            Vertex source = creatVertex(v);
            // add the source vertex to the vertices list
            vertices[v] = source;
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
        edgeNum++;
        if (!isDigraph) {
            Edge edge2 = creatEdge(vertices[u], vertices[v], w);
            vertices[u].adjList.add(edge2);
            edgeNum++;
        }
        return e;
    }

    /**
     * This method prints the graph after read it and create
     * the List
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

    // add label from file
    public void addLabel(char lable) {
        vertices[lable - 65].label = lable;
    }

    // add label with uniqe number
    public void addLabel(int d) {
        if (vertices[d].label == 0) {
            int b = d;
            char c = (char) (b + '0');
            vertices[d].label = c;
        }

    }

    public int getEdgeNo() {
        return edgeNum;
    }

    public void setEdgeNo(int edgeNum) {
        this.edgeNum = edgeNum;
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
