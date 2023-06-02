/* CPCS324 Project Part 2

 Group members: 
    1- Razan Alamri
    2- Khloud Alsofyani
    3- Leen Ba Galaql
    4- Shatha Binmahfouz
 */
package AirFreightApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import GraphFramework.*;

// AirFreightApp is a class. It is the starting point of the program and contains the main method.
public class AirFreightApp {

    /*
     * main(): this function should be responsible for running the readGraphFromFile
     * method for requirement
     * 1 and running the make graph function for requirement 2 to initialize the
     * graph and invoking the Dijkstra
     * -based all source shortest path algorithm and displaying the returned result
     * and the measured running
     * time.
     */
    public static void main(String[] args) throws FileNotFoundException {

        // To read input from user
        Scanner input = new Scanner(System.in);
        // To store running time of each algorithm
        double start_Time, end_Time;

        // Print Hedar massege
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("-------                 Welcome to Air Freight Application                 -------");
        System.out.println("----------------------------------------------------------------------------------");

        System.out.println(
                "\n This program to Compute the shortest path between every pair of locations using two algorithms:");
        System.out.println("\t1- Dijkstra algorithm");
        System.out.println("\t2- Dijkstra-based shortest path algorithm");
        System.out.println("\n----------------------------------------------------------------------------------");

        // To disply menu of test cases can do
        System.out.println("\n<<  Test Type  >>\n");
        System.out.println(" 1:  Read The Graph From File");
        System.out.println(" 2:  Create a Random Graph");
        System.out.print("\n> From these choices, please enter your choice: ");

        // To save number of test Type choice
        int test_Type = input.nextInt();

        // Create a new graph object
        Graph map = new AFRouteMap();

        // To get test case
        switch (test_Type) {
            case 1:
                // Create a new graph object
                map = new AFRouteMap();
                File InputFile = new File("InputGraph.txt");
                map.readGraphFromFile(InputFile);
                /*
                 * ********************************************************************
                 * edit print way
                 ********************************************************************
                 */
                map.PrintGraphFile();

                break;
            case 2:
                // To disply menu of test cases can do
                System.out.println("\n<<  Test Cases  >>\n");
                System.out.println(" 1:  n = 2000 and  m = 10000");
                System.out.println(" 2:  n = 3000 and  m = 15000");
                System.out.println(" 3:  n = 4000 and  m = 20000");
                System.out.println(" 4:  n = 5000 and  m = 25000");
                System.out.println(" 5:  n = 6000 and  m = 30000");
                System.out.println();
                System.out.println("< NOTE: n is the number of Locations and m is the number of Routes >");
                System.out.print("\n> From these cases, please enter your choice: ");
                // To save number of test Case choice
                int test_Case = input.nextInt();

                // Ask user about graph type (directed / undirected)
                Boolean isDigraph = false;
                System.out.print("Do you want the graph directed (Y/N) ? ");
                String graphType = input.next();

                // If Invalid input
                if (!graphType.equalsIgnoreCase("Y") && !graphType.equalsIgnoreCase("N")) {
                    System.out.println("\n<   Invalid input!  >");
                    System.out.print("> Please enter your choice again : ");
                    graphType = input.next();
                }
                // If directed graph
                if (graphType.equalsIgnoreCase("Y")) {
                    isDigraph = true;
                }

                // To get test case
                switch (test_Case) {
                    case 1:
                        // Create a new graph object
                        map = new AFRouteMap(10, 20, isDigraph);
                        map.makeGraph();
                        map.PrintGraphFile();
                        break;

                    case 2:
                        // Create a new graph object
                        map = new AFRouteMap(3000, 15000, isDigraph);
                        map.makeGraph();
                        break;

                    case 3:
                        // Create a new graph object
                        map = new AFRouteMap(4000, 20000, isDigraph);
                        map.makeGraph();
                        break;

                    case 4:
                        // Create a new graph object
                        map = new AFRouteMap(5000, 25000, isDigraph);
                        map.makeGraph();
                        break;

                    case 5:
                        // Create a new graph object
                        map = new AFRouteMap(6000, 30000, isDigraph);
                        map.makeGraph();
                        break;

                    default:
                        System.out.println("<   Invalid input!  >");
                        break;

                }
                break;
            default:
                System.out.println("<   Invalid input!  >");
                break;
        }

        // Apply two algoritms and compute the run time for each algorithm
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println();

        // Computing the shortest path for a specified source using Dijkstra algorithm
        SingleSourceSPAlg Dijkstra_Alg = new SingleSourceSPAlg(map);
        // Start time
        start_Time = System.currentTimeMillis();
        // ********************************************************** call alg2 */
        Dijkstra_Alg.computeDijkstraAlg();
        // End time
        end_Time = System.currentTimeMillis();
        // Prtint run time
        System.out.println("Dijkstra algorithm's run time is  " + (end_Time - start_Time) + " ms.\n");

        // Computing the shortest path for all source using Dijkstra-based algorithm
        DBAllSourceSPAlg DijkstraAll_Alg = new DBAllSourceSPAlg(map);
        // Start time
        start_Time = System.currentTimeMillis();
        // ********************************************************** call alg2 */
        // DijkstraAll_Alg.computeDijkstraBasedSPAlg();
        // End time
        end_Time = System.currentTimeMillis();
        // Prtint run time
        System.out
                .println("Dijkstra-based all shortest path algorithm's run time is  " + (end_Time - start_Time)
                        + " ms.\n");

        // Print Hedar massege
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("-------             Thank You For Using Air Freight Application            -------");
        System.out.println("----------------------------------------------------------------------------------");

        // Close scanner
        input.close();

    }
}