package AirFreightApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import GraphFramework.DBAllSourceSPAlg;
import GraphFramework.Graph;
import GraphFramework.SingleSourceSPAlg;

public class AirFreightApp {
    public static void main(String[] args) throws FileNotFoundException {

        // Scanner to read input from user
        Scanner input = new Scanner(System.in);
        // Variables section -->initialize all variables
        // n = number of vertexs of the graph
        // m = number of edges of the graph
        // choiceAlogrithm , menuChoice ,choiceCase , sourceNum = user choice
        int n = 0, m = 0, choiceAlogrithm = -1, menuChoice = -1, choiceCase = -1, sourceNum = 0;
        // graph = instant form class graph
        Graph graph;
        // choiceDigraph(isDigraph)= user choice
        String choiceDigraph;
        boolean isDigraph = false;
        // finishTime, startTime = to calculate the running time
        long finishTime, startTime;
        // floydAlg = instant form class AllSourceSPAlg
        DBAllSourceSPAlg floydAlg;
        // dijkstraAlg = instant form class SingleSourceSPAlg
        SingleSourceSPAlg dijkstraAlg;
        // Print menu msg to user
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("\t\t              *** Welcome to *** ");
        System.out.println("\t\t     *** Shortest Path General Problem ***");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("**** Please Choose the Algorithm ****");
        System.out.println("1- Floyd-Warshall Algorithm ");
        System.out.println("2- Dijkstra Algorithm ");
        System.out.print("\n> Enter Your Choice: ");
        // To save user choice (1-Floyd) OR (2-Dijkstra) otherwise it's wrong input
        choiceAlogrithm = input.nextInt();
        // If user enter Invalid choiceAlogrithm not 1 or 2
        // loop until user enter correct input
        while (choiceAlogrithm != 1 && choiceAlogrithm != 2) {
            System.out.println("**** Invalid Input! ****");
            System.out.print("> Enter Your Choice: ");
            choiceAlogrithm = input.nextInt();
        }
        // If user enter 1 or 2 complete the rest of the program

        // Print menu msg to user
        System.out.println("\n-----------------------------------------------------");
        System.out.println("**** Please choose How you Want to Make the Graph ****");
        System.out.println("1- Create a Graph from a File ");
        System.out.println("2- Create a Random Graph ");
        System.out.print("> Enter your choice: ");
        // To save user choice (1-from file) OR (2-random) otherwise it's wrong input
        menuChoice = input.nextInt();
        // If user enter Invalid menuChoice not 1 or 2
        // loop until user enter correct input
        while (menuChoice != 1 && menuChoice != 2) {
            System.out.println("**** Invalid input! ****");
            System.out.print("> Enter your choice: ");
            menuChoice = input.nextInt();
        }
        // If user enter 1 or 2 complete the rest of the program
        System.out.println("\n-----------------------------------------------------");

        // Switch to enter one of the options
        // Case 1 : Create a Graph from a File
        // Case 2 : Create a Random Graph
        switch (menuChoice) {
            ///////////////////////////////////////////////////////////////////////////////////////////
            // Case 1 : Create a Graph from a File
            case 1:
                // Create object from class "File" --> to read graph from file
                File fileInput = new File("InputGraph.txt");
                // First : create object from class "graph"
                graph = new AFRouteMap();
                // Let the graph be directed
                isDigraph = true;
                graph.isDigraph = isDigraph;
                // Second : call method "readGraphFromFile" to read graph from file
                graph.readGraphFromFile(fileInput);
                graph.PrintGraphFile();
                System.out.println("gggggggggg");
                // If the user choice the floyedwarshal algorithm
                if (choiceAlogrithm == 1) {
                    System.out.println("\t      *** Weight Matrix ***");
                    System.out.println("-----------------------------------------------------");
                    // Call printGraphFile to print the intial matrix
                    graph.PrintGraphFile();
                    // Create AllSourceSPAlg object to use floyedwarshal algorithm
                    floydAlg = new DBAllSourceSPAlg(graph);
                    // invoke setNullEdge method to create edge between the vertices that there is
                    // no edge between them
                    floydAlg.setNullEdge();
                    System.out.println("\n-----------------------------------------------------");
                    System.out.println("\t      *** Distance matrix *** ");
                    System.out.println("-----------------------------------------------------\n");
                    // Store the time before invoke the algorithm
                    startTime = System.currentTimeMillis();
                    floydAlg.computeFloyedWarshalAlg(true);
                    finishTime = System.currentTimeMillis();// Store the time after invoke the algorithm
                    // Call printPath to print the the matrix after apply floyedwarshal algorithm
                    System.out.println(
                            "\n *** Which represent The length of the shortest paths *** \n       *** between every pair in the graph ***");
                    System.out.println("\n-----------------------------------------------------");
                    System.out.println(" Run Time for Floyd-Warshall Algorithm " + (finishTime - startTime) + " ms \n");
                    // ----------------------------------------------------------------------------------------------------
                } // If the user choice the dijkstra algorithm
                else {
                    // System.out.print("Please choose the number of the source you want to begin
                    // with (from 0 to 9) : ");
                    // sourceNum = input.nextInt();
                    // while (sourceNum < 0 && sourceNum >= 10) {
                    // System.out.println("**** Invalid input! ****");
                    // System.out.print("> Enter your choice : ");
                    // sourceNum = input.nextInt();
                    // }
                    // System.out.println();
                    System.out.println("\t      *** Weight Matrix ***");
                    System.out.println("-----------------------------------------------------");
                    // Call printGraphFile to print the intial matrix
                    graph.PrintGraphFile();
                    System.out.println("ffffffffffffffffffffff");
                    // Create SingleSourceSPAlg object to use dijkstra algorithm
                    dijkstraAlg = new SingleSourceSPAlg(graph);
                    System.out.println("\n-----------------------------------------------------");
                    System.out.println("         *** Length of the shortest paths ***");
                    System.out.println("-----------------------------------------------------");
                    // Store the time before invoke the algorithm
                    startTime = System.currentTimeMillis();
                    dijkstraAlg.computeDijkstraAlg();
                    finishTime = System.currentTimeMillis();// Store the time after invoke the algorithm
                    // //Call printPath to print the the path from the source to all other vertices
                    dijkstraAlg.printPath();
                    System.out.println(
                            "\n *** Which represent the length from the chosen source ***\n       *** to the rest of the vertices *** ");
                    System.out.println("\n-----------------------------------------------------");
                    System.out.println(" Run Time for Dijkstra Algorithm " + (finishTime - startTime) + " ms \n");
                }
                break;
            ///////////////////////////////////////////////////////////////////////////////////////////
            // Case 2 : Create random graph
            case 2:
                // Ask user if he/she want to do the algorithm with direct graph
                System.out.print("Do you want the graph directed (y/n) ? ");
                choiceDigraph = input.next();
                // System.out.println("");
                // FOR WRONG INPUT
                if (!choiceDigraph.equalsIgnoreCase("y") && !choiceDigraph.equalsIgnoreCase("n")) {
                    System.out.println("\n**** Invalid input! ****");
                    System.out.print("> Enter your choice : ");
                    choiceDigraph = input.next();
                }
                // If the user answer was yes make directed graph
                if (choiceDigraph.equalsIgnoreCase("y")) {
                    isDigraph = true;
                }
                // If the user choice the floyedwarshal algorithm
                if (choiceAlogrithm == 1) {
                    // Ask user to choose one of this cases
                    // n-> means number of vertices in the graph
                    // m-> means number of edges in the graph
                    System.out.println("\n-----------------------------------------------------");
                    System.out.println(
                            "> Available cases (where n represents # of vertices and m represents # of edges ) : ");
                    System.out.println(" 1-  n=2,000 - m=10,000");
                    System.out.println(" 2-  n=3,000 - m=15,000");
                    System.out.println(" 3-  n=4,000 - m=20,000");
                    System.out.println(" 4-  n=5,000 - m=25,000");
                    System.out.println(" 5-  n=6,000 - m=30,000");
                    System.out.print("> Enter a case to test: ");
                    choiceCase = input.nextInt();
                    // FOR WRONG INPUT
                    while (choiceCase < 1 || choiceCase > 6) {
                        System.out.println("**** Invalid input! ****");
                        System.out.print("> Enter a case to test: ");
                        choiceCase = input.nextInt();
                    }
                    // Switch to set n and m based on the user choice case
                    switch (choiceCase) {
                        case 1: {
                            n = 2000;
                            m = 10000;
                            break;
                        }

                        case 2: {
                            n = 3000;
                            m = 15000;
                            break;
                        }

                        case 3: {
                            n = 4000;
                            m = 20000;
                            break;
                        }

                        case 4: {
                            n = 5000;
                            m = 25000;
                            break;
                        }

                        case 5: {
                            n = 6000;
                            m = 30000;
                            break;
                        }
                    }
                    // Make random graph with specific parameter based on the above choice of the
                    // user
                    graph = new AFRouteMap(n, m, isDigraph);
                    graph.makeGraph(n, m);
                    // Create AllSourceSPAlg object to use floyedwarshal algorithm
                    floydAlg = new DBAllSourceSPAlg(graph);
                    // invoke setNullEdge method to create edge between the vertices that there is
                    // no edge between them
                    floydAlg.setNullEdge();
                    // Store the time before invoke the algorithm
                    startTime = System.currentTimeMillis();
                    floydAlg.computeFloyedWarshalAlg(false);
                    finishTime = System.currentTimeMillis();// Store the time after invoke the algorithm
                    // print the running time
                    System.out.println("\n-----------------------------------------------------");
                    System.out.println(" Run time for Floyd-Warshall algorithm " + (finishTime - startTime) + " ms \n");
                    // ----------------------------------------------------------------------------------------------------
                } // If the user choice the Dijkstra algorithm
                else {
                    // Ask user to choose one of this cases
                    // n-> means number of vertices in the graph
                    // m-> means number of edges in the graph
                    System.out.println("\n-----------------------------------------------------");
                    System.out.println(
                            "> Available cases (where n represents # of vertices and m represents # of edges ) : ");
                    System.out.println(" 1-  n=5000   - m=25,000");
                    System.out.println(" 2-  n=10,000 - m=50,000");
                    System.out.println(" 3-  n=15,000 - m=75,000");
                    System.out.println(" 4-  n=20,000 - m=100,000");
                    System.out.println(" 5-  n=25,000 - m=125,000");
                    System.out.print("> Enter a case to test: ");
                    choiceCase = input.nextInt();
                    // FOR WRONG INPUT
                    while (choiceCase < 1 || choiceCase > 6) {
                        System.out.println("**** Invalid input! ****");
                        System.out.print("> Enter a case to test: ");
                        choiceCase = input.nextInt();
                    }

                    System.out.println("\n-----------------------------------------------------");
                    // Switch to set n and m based on the user choice case
                    switch (choiceCase) {
                        case 1: {
                            n = 5000;
                            m = 25000;
                            break;
                        }

                        case 2: {
                            n = 10000;
                            m = 50000;
                            break;
                        }

                        case 3: {
                            n = 15000;
                            m = 75000;
                            break;
                        }

                        case 4: {
                            n = 20000;
                            m = 100000;
                            break;
                        }

                        case 5: {
                            n = 25000;
                            m = 125000;
                            break;
                        }
                    }
                    // Make random graph with specific parameter based on the above choice of the
                    // user
                    graph = new AFRouteMap(n, m, isDigraph);
                    graph.makeGraph(n, m);
                    // Create AllSourceSPAlg object to use dijkstra algorithm
                    dijkstraAlg = new SingleSourceSPAlg(graph);// by default start with 0
                    // Store the time before invoke the algorithm
                    startTime = System.currentTimeMillis();
                    dijkstraAlg.computeDijkstraAlg();
                    finishTime = System.currentTimeMillis();// Store the time after invoke the algorithm
                    // Print the running time
                    System.out.println("\n-----------------------------------------------------");
                    System.out.println(" Run time for Dijkstra algorithm " + (finishTime - startTime) + " ms \n");

                }

                break;

        }
        // Print thanks message
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("\t\t     *** Thank you for Using the Program ***");
        System.out.println("--------------------------------------------------------------------------------");

        input.close();
    }// END MAIN METHOD

}// END THE PROGRAM