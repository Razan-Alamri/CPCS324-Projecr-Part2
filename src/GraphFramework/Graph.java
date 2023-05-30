package GraphFramework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Graph {
    /**
     * to keep track the number of vertices
     */
    int verticesNo;
    /**
     * to keep track the number of edges
     */
    int edgeNo;
    /**
     * true if its directed graph, false if undirected
     */
    boolean isDigraph;
    /**
     * array of vertices of the graph
     */
    Vertex[] vertices;
    /**
     * adjacency matrix that represent the graph
     */
    Edge[][] adjMatrix;
    /**
     * user input of the number of edges
     */
    int totalNumberOfEdge;
    /**
     * user input of the number of vertices
     */
    int totalNumberOfVertices;

    /**
     * empty constructor of graph
     */
    public Graph() {
    }

    /**
     * constructor with three parameter (number of vertices , number of edges ,
     * boolean to indicate
     * if graph is directed or not
     * 
     * @param verticesNo number of vertices of graph
     * @param edgeNo     number of edges of graph
     * @param isDigraph  if its a directed graph or not
     */
    public Graph(int verticesNo, int edgeNo, boolean isDigraph) {

        this.totalNumberOfVertices = verticesNo;
        this.totalNumberOfEdge = edgeNo;
        this.isDigraph = isDigraph;
        adjMatrix = new Edge[totalNumberOfVertices][totalNumberOfVertices];
        vertices = new Vertex[totalNumberOfVertices];

    }

    // Abstract method to create object of Vertex
    public abstract Vertex creatVertex(int ID);

    // Abstract method to create object of Edge
    public abstract Edge creatEdge(Vertex v, Vertex u, int w);

    public abstract Edge creatEdge(int w);

    public abstract Edge creatEdge();

    /**
     * method that generate graph and ensure that graph is connected
     * 
     * @param totalNumberOfVertices total number of vertices
     * @param totalNumberOfEdge     total number of edges
     */
    public void makeGraph(int totalNumberOfVertices, int totalNumberOfEdge) {

        // --------------------
        // this part to ensure that graph is connected
        // to connect V vetex we ned V-1 edge
        // be connected each vetex to the next vertex adjacent to it , we ensuring that
        // graph is connected
        for (int i = 1; i < totalNumberOfVertices; i++) {
            int randomWeight = (int) (Math.random() * (50)) + 1;// first generate random wight to assign it to the edge
            addEdge(i - 1, i, randomWeight); // then add that edge graph]

        }
        // -----------------------

        // create edges randomaly and add them
        while (edgeNo < totalNumberOfEdge) {// if the needed number of edges is reached >> stop loop
            // randomly choose a source
            int sourceLable = (int) (Math.random() * (totalNumberOfVertices));
            // randomly choose a target
            int targetLabel = (int) (Math.random() * (totalNumberOfVertices));

            if (sourceLable == targetLabel) {// if the source is equal to the
                continue;
            }
            // if they are diffrent
            if (adjMatrix[sourceLable][targetLabel] != null) { // if the graph is undirected i need to check only the
                                                               // source
                continue; // since the target will have the same edge too

            }
            int randomWeight = (int) (Math.random() * (50)) + 1;// generate randomWight
            addEdge(sourceLable, targetLabel, randomWeight); // add the edge to the graph

        }
        // replace null values withe infinity
        updateAllNullsValues();

    }// end of makeGraph method

    /**
     * method that generate graph and read inputs from input file
     * 
     * @param inpuFilet that read graph from
     * @throws java.io.FileNotFoundException reading input file
     */
    public void readFromFile(File inpuFilet) throws FileNotFoundException {
        Scanner input = new Scanner(inpuFilet);// scanner to read from file
        String graphType = input.next();
        if (input.next().equalsIgnoreCase("1"))
            isDigraph = true;
        totalNumberOfVertices = input.nextInt();// read number of vertices
        totalNumberOfEdge = input.nextInt();// read number of edges
        adjMatrix = new Edge[totalNumberOfVertices][totalNumberOfVertices];// intillize the array of edges
        vertices = new Vertex[totalNumberOfVertices]; // intillizen the array of vertices

        // add edges of graph
        while (edgeNo < totalNumberOfEdge) {

            char source = input.next().charAt(0);
            char destination = input.next().charAt(0);
            int weight = input.nextInt();
            addEdge(source - 65, destination - 65, weight);
            addLabel(source);// add label to the source vertex
            addLabel(destination);// add label to the destination vertex

        }
        // replace null values withe infinity
        updateAllNullsValues();

    }

    /**
     * this method used to add edge to the graph
     * 
     * @param v      position number of source
     * @param u      position number of destination
     * @param weight weight of edge
     * @return the new edge created by the
     */
    public Edge addEdge(int v, int u, int weight) {

        if (vertices[v] == null) { // check if the source Vertex is created before or not
            vertices[v] = creatVertex(v); // if not, create new object and specify its position
            verticesNo++; // increment the number of vertices
        }
        if (vertices[u] == null) {// same for the source postion
            vertices[u] = creatVertex(u);
            verticesNo++; // increment the number of vertices
        }

        adjMatrix[v][u] = creatEdge(vertices[v], vertices[u], weight);// create new edge
        edgeNo++;// increment number of edges
        if (!isDigraph) {// if the graph is undirected
            // add the opssite edge too
            adjMatrix[u][v] = creatEdge(vertices[u], vertices[v], weight);
            edgeNo++;// increment number of edges
        }
        return adjMatrix[v][u];
    }

    /**
     * method to print the graph
     */
    public void print_graph() {
        System.out.print("    ");
        // print header of all vertices
        for (int i = 0; i < verticesNo; i++) {
            System.out.printf("%-3s", vertices[i].label);
        } // end of for loop
        System.out.println("");
        System.out.println("---------------------------------");
        // print all edges between vertices
        for (int i = 0; i < verticesNo; i++) {
            System.out.print(vertices[i].label + " : ");
            for (int j = 0; j < verticesNo; j++) {
                if (adjMatrix[i][j].weight != 999999)// if weight doesnt equal to infinity
                    System.out.printf("%-3d", adjMatrix[i][j].weight);// print the value of weight
                else
                    System.out.printf("%-3s", "∞");// otherwise print infinityto indicate there is no edge between these
                                                   // to vertices

            } // end of inner for loop
            System.out.println("");
        } // end of outer for loop
    }// end of method

    /**
     * method to add label to a vertex
     * 
     * @param lable the label of vertex
     */
    public void addLabel(char lable) {
        vertices[lable - 65].label = lable;
    }

    /**
     * method to replace all null value with value that greater than the range of
     * graph weights
     * and replace the distance form a vertex to itself by zero
     */
    public void updateAllNullsValues() {
        // loop to go through all Edges
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix[i].length; j++) {
                if (i == j && adjMatrix[i][j] == null)// if i==j
                    // smallest distance (weight from the vertex to itself)
                    adjMatrix[i][j] = creatEdge(0);

                else if (adjMatrix[i][j] == null) { // if there is no edges
                    adjMatrix[i][j] = creatEdge(999999); // set infinity(a number out of the range of wieghts)
                } // end of eles if

            } // end of inner loop

        } // end of outer loop
    }// end of method
}
