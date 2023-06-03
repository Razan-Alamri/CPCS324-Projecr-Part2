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

// Graph is a class defines the structure of a graph
public abstract class Graph {

    // Data filed

    // count Number of vertices
    int verticesNo;
    // Number of edges
    int edgeNo;
    // Check if graph is directed or not
    boolean isDigraph;
    // Array to can store and found vertex by Position(ID) (from association
    // relationship)
    public Vertex[] vertices;
    // toltal Number of vertices;
    int AllEdgesNo;

    // Contructors
    public Graph() {

    }

    public Graph(int verticesNo, int EdgeNo, boolean isDigraph) {
        this.AllEdgesNo = EdgeNo;
        this.isDigraph = isDigraph;
        this.verticesNo = verticesNo;
        vertices = new Vertex[verticesNo];
    }

    // Abstract method to create object of Vertex
    public abstract Vertex creatVertex(int ID);

    // Abstract methods to create object of Edge
    public abstract Edge creatEdge(Vertex v, Vertex u, int w);

    public abstract Edge creatEdge(int w);

    public abstract Edge creatEdge();

    /*
     * Methos responsible for creating a graph object with the specified parameters
     * and randomly initializes the vertices’ labels,
     * creating edges that connects the created vertices randomly and
     * assigning them random weights.
     */
    public void makeGraph() {
        // Create n vertices with random labels && edgeNo < AllEdgesNo
        Random random = new Random();

        for (int i = 0; i < verticesNo - 1 && edgeNo < AllEdgesNo; i++) {
            int r = random.nextInt(50) + 1;

            // initializ vertex
            addEdge(i, i + 1, r);
        }

        // calculate the remainig edges to generate it randomly
        int remaning = AllEdgesNo - (verticesNo - 1);

        // Save edges to avoid duplicate edge
        HashSet<String> edgeSet = new HashSet<>();
        // make random graph with remaining edges
        for (int i = 0; i < remaning && edgeNo < AllEdgesNo; i++) {
            // Choose two random vertices
            int ver_Vertex = random.nextInt(verticesNo);
            int target_Vertex = random.nextInt(verticesNo);
            /*
             * ver_Vertex.equals(target_Vertex) -> to check ver and diestination are
             * not the same
             * edgeSet.contains(ver_Vertex+":"+target_Vertex) -> to check there is no
             * edge between them
             */
            if (ver_Vertex == target_Vertex || edgeSet.contains(ver_Vertex + ":" + target_Vertex)) {
                i--;
                continue;
            }
            // Create edge with random weight
            int r = random.nextInt(50) + 1;
            // Call method addEdge to add edges
            addEdge(ver_Vertex, target_Vertex, r);
            // Add edge to set to prevent duplicates
            edgeSet.add(ver_Vertex + ":" + target_Vertex);

            // To add label of vertex
            add_Label(ver_Vertex);
            add_Label(target_Vertex);
        }
    }

    /*
     * Method to reads the edges and vertices from the text file
     * whose name is inputFile
     */
    public void readGraphFromFile(File fileName) throws FileNotFoundException {

        Scanner inpScanner = new Scanner(fileName);
        // Read header information
        inpScanner.next();
        // Read Graph type if 0 is undirected if 1 is directed
        int digraph = inpScanner.nextInt();
        if (digraph == 1) {
            isDigraph = true;
        }
        // Read the number of vertices & edges
        verticesNo = inpScanner.nextInt();
        edgeNo = inpScanner.nextInt();

        // Create vertices
        vertices = new Vertex[verticesNo];
        while (inpScanner.hasNext()) {
            // Read ver vertex
            char s = inpScanner.next().charAt(0);
            // Read tar vertex
            char t = inpScanner.next().charAt(0);
            // Read weight
            int wieght = inpScanner.nextInt();
            // Add edge between ver and destination vertices
            addEdge(s - 65, t - 65, wieght);
            // Add label to s vertex
            add_Label(s);
            // Add label to t vertex
            add_Label(t);
        }
        // Close scanner object
        inpScanner.close();
    }

    /*
     * Method that creates an edge object and passes ver v vertex, tar u
     * vertex
     * and weight as parameters, assigns the tar vertex to the adjacent list
     * of the ver vertex
     */
    public Edge addEdge(int v, int u, int w) {
        // Get or create ver vertex
        if (vertices[v] == null) {
            Vertex ver = creatVertex(v);
            vertices[v] = ver;
        }
        // Get or create destination vertex
        if (vertices[u] == null) {
            Vertex tar = creatVertex(u);
            vertices[u] = tar;
        }
        // Create edge
        Edge edge1 = creatEdge(vertices[v], vertices[u], w);
        // Add edge to the ver adjacent list
        vertices[v].adjList.add(edge1);
        /*
         * Increment the edge count by 1 If it is a undirected graph
         * and by 2 if directed graph
         */

        if (!isDigraph) {
            // Create reverse edge and add it to the target adjacent list
            Edge edge2 = creatEdge(vertices[u], vertices[v], w);
            vertices[u].adjList.add(edge2);
            add_Label(u);
            edgeNo++;
        }
        edgeNo++;
        return edge1;
    }

    // TO print graph
    public void PrintGraph() {
        System.out.println("Adjacency List:");
        for (int i = 0; i < verticesNo; i++) {
            int length = 0;
            System.out.print(vertices[i].displyInfo() + " -> ");
            for (Edge e : vertices[i].adjList) {
                System.out.print(vertices[e.target.ID].displyInfo());
                length += e.getWeight();
            }
            System.out.print(" --- route length: " + length);
            System.out.println();
        }
    }

    // add label from file
    public void add_Label(char lable) {
        vertices[lable - 65].label = lable;
    }

    // add label with uniqe number add_Label_Make
    public void add_Label(int id) {
        if (vertices[id].label == 0) {
            int x = id;
            char a = (char) (x + '0');
            vertices[id].label = a;
        }
    }

    public int getVerticesNo() {
        return verticesNo;
    }

    public void setVerticesNo(int verticesNo) {
        this.verticesNo = verticesNo;
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

    public int getAllEdgesNo() {
        return AllEdgesNo;
    }

    public void setAllEdgesNo(int allEdgesNo) {
        AllEdgesNo = allEdgesNo;
    }

    // Setteers and Getters

}
