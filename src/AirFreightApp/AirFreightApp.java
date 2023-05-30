package AirFreightApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import GraphFramework.DBAllSourceSPAlg;
import GraphFramework.Graph;
import GraphFramework.SingleSourceSPAlg;

public class AirFreightApp {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);// scanner to read user choices
        // intilize variables
        int verticesNo = 0; // number of vertices
        int edgesNo = 0; // number of edges
        int userChoice = 0; // choice of algorithms
        int algorithmNumber = 0;// choice of algorithms
        int caseNumber = 0; // choice of casses
        // ----------------------
        // print menu and ask user to choice
        System.out.println("-----------------Shortest Path Algorithm---------------");
        System.out.println("\t1-Read Graph from File.");
        System.out.println("\t2-Generate Radnom Graph.");
        // ensure that user enter 1 or 2
        while (userChoice != 1 && userChoice != 2) {
            System.out.print(">Enter your choice: ");// ask user to enter a number
            userChoice = input.nextInt();// read user input

        } // end of while loop
          // ----------
          // read form fild
        if (userChoice == 1) {

            System.out.println("---------Choose one of the Algorithms: ");
            System.out.println("\t1- Dijkstra (Single Source Shortest Path) Algorithm.");
            System.out.println("\t2- Floyed-Warshall (All Source Shortest Path) Algorithm.");
            while (algorithmNumber != 1 && algorithmNumber != 2) {
                System.out.print(">Enter your choice: ");// ask user to enter a number
                algorithmNumber = input.nextInt();// read user input

            } // end of while loop

            File inputFile = new File("InputGraph.txt");
            Graph graph = new AFRouteMap();
            graph.readFromFile(inputFile);
            System.out.println("graph from file: ");
            System.out.println("_________________________________");
            graph.print_graph();
            System.out.println("_________________________________");

            System.out.println("result: ");

            if (algorithmNumber == 1) { // if user choice 1
                // run dijkstra
                SingleSourceSPAlg dijkstra = new SingleSourceSPAlg(graph);
                dijkstra.computeDijkstraAlg();
                dijkstra.printResult();
            } else if (algorithmNumber == 2) {// if user choice 2
                // run floyedWarshall
                DBAllSourceSPAlg floyedWarshall = new DBAllSourceSPAlg(graph);
                floyedWarshall.setReadFromFile(true);
                floyedWarshall.computeFloyedWarshallAlg();

            }
        }
        // -----------------------------------------------------------
        // generate random graph
        if (userChoice == 2) {

            System.out.println("---------Choose one of the Algorithms: ");
            System.out.println("\t1- Dijkstra (Single Source Shortest Path) Algorithm.");
            System.out.println("\t2- Floyed-Warshall (All Source Shortest Path) Algorithm.");
            while (algorithmNumber != 1 && algorithmNumber != 2) {
                System.out.print(">Enter your choice: ");// ask user to enter a number
                algorithmNumber = input.nextInt();// read user input

            } // end of while loop
              // printthe menue of casses
            System.out.println("\tCases (where n represnt # of vertices and m represent # of edges):");
            System.out.println("1-  n =  2,000  , m =   10,000");
            System.out.println("2-  n =  3,000  , m =   15,000");
            System.out.println("3-  n =  4,000  , m =   20,000");
            System.out.println("4-  n =  5,000  , m =   25,000");
            System.out.println("5-  n =  6,000  , m =   30,000");
            System.out.println("6-  n = 10,000  , m =   50,000");
            System.out.println("7-  n = 15,000  , m =   75,000");
            System.out.println("8-  n = 20,000  , m =  100,000");
            System.out.println("9-  n = 25,000  , m =  125,000");

            // let user choose the case
            // ensure that user enter a number between 0 and 10
            while (caseNumber <= 0 || caseNumber > 10) {
                System.out.print(">Enter your choice: ");// aske user to enter a number
                caseNumber = input.nextInt();// read user input
            } // end of while loop

            switch (caseNumber) {
                case 1:
                    verticesNo = 2000;
                    edgesNo = 10000;
                    break;
                case 2:
                    verticesNo = 3000;
                    edgesNo = 15000;
                    break;
                case 3:
                    verticesNo = 4000;
                    edgesNo = 20000;
                    break;
                case 4:
                    verticesNo = 5000;
                    edgesNo = 25000;
                    break;
                case 5:
                    verticesNo = 6000;
                    edgesNo = 30000;
                    break;
                case 6:
                    verticesNo = 10000;
                    edgesNo = 50000;
                    break;
                case 7:
                    verticesNo = 15000;
                    edgesNo = 75000;
                    break;
                case 8:
                    verticesNo = 20000;
                    edgesNo = 100000;
                    break;
                case 9:
                    verticesNo = 25000;
                    edgesNo = 125000;
                    break;

            }// end of switvh
            System.out.print("is the graph directed(yes/no): ");
            boolean digraphBoolean = false;// intiate the varible with false
            String digraph = input.next();// take user input
            if (digraph.equalsIgnoreCase("yes"))// if user enter yes
                digraphBoolean = true;// change the value of digraph to ture < otherwise it will remain false
            // ------------------------------------------------------------
            Graph newGhraph = new AFRouteMap(verticesNo, edgesNo, digraphBoolean); // create new graph object
            newGhraph.makeGraph(verticesNo, edgesNo);

            if (algorithmNumber == 1) {

                long startTime = System.currentTimeMillis();
                SingleSourceSPAlg dijkstra = new SingleSourceSPAlg(newGhraph);
                dijkstra.computeDijkstraAlg();
                long finishTime = System.currentTimeMillis();
                System.out.println("taken time (Dijkstra algorithm): " + (finishTime - startTime) + "ms");

            } else if (algorithmNumber == 2) {

                long startTime = System.currentTimeMillis();
                DBAllSourceSPAlg floyedWarshall = new DBAllSourceSPAlg(newGhraph);
                floyedWarshall.computeFloyedWarshallAlg();
                long finishTime = System.currentTimeMillis();
                System.out.println("taken time (floyd-warshall algorithm): " + (finishTime - startTime) + "ms");
            }

        }
    }

}