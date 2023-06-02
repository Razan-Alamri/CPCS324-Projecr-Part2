/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphFramework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

//import AirFreightApp.AFRouteMap;

public abstract class Graph {

    // ----------------------------Attributes Section----------------------------
    // deleare verticesNo variable number of vertices of the graph.
    // It should be incremented whenever a method a new object is added to the
    // vertex list.
    int verticesNo;
    // deleare edgeNo variable to store edge's number of the graph.
    // It should be incremented by one in case add edge in directed graph and by two
    // if it is an undirected graph.
    int edgeNo;
    // decleare boolean variable true
    // -->if the graph is directed graph, false -->if the graph is undirected
    public boolean isDigraph;
    // decleare 2D array to store matrix this matrix will represent the edge between
    // pair of two vertex
    // Edge[][] adjMatrix;
    // decleare variable to store the list of vertices of a graph
    Vertex[] vertices;
    // decleare variable to store the total number of edges
    int totalEdges;
    // decleare variable to store the total number of vertices
    int totalVertices;

    LinkedList<Edge>[] adjList;

    // ----------------------------Constructors section----------------------------
    /**
     * Default constructor
     */
    public Graph() {

    }

    /**
     * Constructor with specific parameter
     *
     * @param totalVerticesNo -- number of vertices of graph
     * @param totalEdgesNo    -- number of edges between the vertices
     * @param isDigraph       -- its directed graph or undirected
     */
    public Graph(int totalVerticesNo, int totalEdgesNo, boolean isDigraph) {
        this.totalEdges = totalEdgesNo;
        this.isDigraph = isDigraph;
        this.totalVertices = totalVerticesNo;
        // adjMatrix = new Edge[totalVerticesNo][totalVerticesNo];// intially null
        vertices = new Vertex[totalVertices];
        adjList = new LinkedList[totalVerticesNo];
        for (int i = 0; i < totalVerticesNo; i++) {
            adjList[i] = new LinkedList<>();
        }

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

    public Edge addEdge(int v, int u, int w) {
        // if the source vertex not already created
        // System.out.println(vertices[v].label);
        if (vertices[v] == null) {
            // create vertex with v position to be the source
            Vertex src = creatVertex(v);
            // add the source vertex to the vertices list
            vertices[v] = src;
            // increament vertices number by one
            verticesNo++;
        }
        // if the target vertex not already created
        if (vertices[u] == null) {
            // create vertex with u position to be the target
            Vertex target = creatVertex(u);
            // add the target vertex to the vertices list
            vertices[u] = target;
            // increament vertices number by one
            verticesNo++;
        }
        Edge e = creatEdge(vertices[v], vertices[u], w);
        adjList[v].add(e);
        edgeNo++;
        if (!isDigraph) {
            Edge e2 = creatEdge(vertices[u], vertices[v], w);
            adjList[u].add(e2);
            edgeNo++;
        }
        return e;
    }

    /**
     * This method to make graph 1- first make the nessecary edge with random
     * weight to ensure its connected using addEdge (and addEdge will create
     * source and target vertices if it not already created) 2- make the
     * remaining edge with random weight using addEdge (and addEdge will create
     * source and target vertices if it not already created)
     *
     * @param totalVertices
     * @param totalEdges
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
            int a = srcVertex;
            int b = desVertex;
            char c = (char) (a + '0');
            char d = (char) (b + '0');
            addLabel(srcVertex);
            addLabel(desVertex);
            System.out.println(c + "  lllll   " + d);

        }
        updateAllNullsValues();
    }

    /**
     * This method reads the edges and vertices from the text file whose name is
     * specified by the parameter filename and place data in a Graph object.
     *
     * @param fileName
     * @throws FileNotFoundException
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
        // initialize the matrix with number of vertices as number of row
        // and for each row the number of column it will be number of vertice
        adjList = new LinkedList[totalVertices];
        for (int i = 0; i < totalVertices; i++) {
            adjList[i] = new LinkedList<Edge>();
        }
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
            Edge edge = addEdge(srcLabel - 65, targetLabel - 65, wieght);
            // call add vertex position method to add the source vertex label
            addLabel(srcLabel);
            // calladd vertex position method to add the target vertex label
            addLabel(targetLabel);
        }
        ;
    }

    /**
     * This method will print the graph after read it from the file and create
     * the matrix
     */

    public void PrintGraphFile() {
        System.out.println("Adjacency List:");
        for (int i = 0; i < totalVertices; i++) {
            System.out.print(vertices[i].label + " -> ");
            for (Edge e : adjList[i]) {
                System.out.print(vertices[e.target.position].label + "(" + e.weight + ") ");
            }
            System.out.println();
        }
    }

    /**
     * This method to add vertex label
     *
     * @param vLabel : is the label of the vertex
     * @return true if add the label, and false if its already added
     */
    /*
     * public boolean addVertLabel(char vLabel) {
     * // if the label is equal 0 (default character value)
     * if (vertices[vLabel - 65].label == 0) {
     * // store the label
     * vertices[vLabel - 65].label = vLabel;
     * // return true-->the label added sucessfully
     * return true;
     * }
     * // the label not added because it already there
     * return false;
     * }
     */

    public void addLabel(char lable) {
        vertices[lable - 65].label = lable;
    }

    public void addLabel(int d) {
        if (vertices[d].label == 0) {
            int b = d;
            char c = (char) (b + '0');
            vertices[d].label = c;
        }

    }

    /**
     * method to replace all null value with value that greater than the range of
     * graph weights
     * and replace the distance form a vertex to itself by zero
     */
    public void updateAllNullsValues() {
        // loop to go through all Edges
        for (int i = 0; i < adjList.length; i++) {
            for (int j = 0; j < adjList[i].size(); j++) {
                if (i == j && adjList[i].get(j) == null)// if i==j
                    // smallest distance (weight from the vertex to itself)

                    adjList[i].add(creatEdge(0));

                else if (adjList[i].get(j) == null) { // if there is no edges
                    adjList[i].add(creatEdge(999999)); // set infinity(a number out of the range of wieghts)
                } // end of eles if

            } // end of inner loop

        } // end of outer loop
    }// end of method
}
