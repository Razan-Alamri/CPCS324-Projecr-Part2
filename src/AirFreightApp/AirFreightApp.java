package AirFreightApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import GraphFramework.DBAllSourceSPAlg;
import GraphFramework.Graph;
import GraphFramework.SingleSourceSPAlg;

public class AirFreightApp {
    public static void main(String[] args) throws FileNotFoundException {

        // To read input from user
        Scanner input = new Scanner(System.in);
        // To store running time of each algorithm
        double start_Time, end_Time;

        // Print Hedar massege
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("-------                   Welcome to Air Freight Program                   -------");
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

                // To get test case
                switch (test_Case) {
                    case 1:
                        // Create a new graph object
                        map = new AFRouteMap();
                        map.makeGraph(200, 10000);
                        break;

                    case 2:
                        // Create a new graph object
                        map = new AFRouteMap();
                        map.makeGraph(3000, 15000);
                        break;

                    case 3:
                        // Create a new graph object
                        map = new AFRouteMap();
                        map.makeGraph(4000, 20000);
                        break;

                    case 4:
                        // Create a new graph object
                        map = new AFRouteMap();
                        map.makeGraph(5000, 25000);
                        break;

                    case 5:
                        // Create a new graph object
                        map = new AFRouteMap();
                        map.makeGraph(6000, 30000);
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

        // Compute the minimum spanning tree using Kruskal algorithm
        start_Time = System.currentTimeMillis();
        // ********************************************************** call alg2 */

        end_Time = System.currentTimeMillis();
        // ************************************************************ edit ptint
        System.out.println("Kruskal's Algorithm's run time is  " + (end_Time - start_Time) + " ms.\n");

        start_Time = System.currentTimeMillis();
        // ********************************************************** call alg2 */
        // Compute the minimum spanning tree using Prim's algorithm

        end_Time = System.currentTimeMillis();
        // ************************************************************ edit ptint
        System.out.println("Min-heap based Prim Algorithm's run time is  " + (end_Time - start_Time) + " ms.\n");

        // Print Hedar massege
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("-------              Thank You For Using Air Freight Program              -------");
        System.out.println("----------------------------------------------------------------------------------");
    }
}