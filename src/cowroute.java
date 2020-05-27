
// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class cowroute {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("cowroute.in"));
        int a = in.nextInt(); // starting city
        int b = in.nextInt(); //
        int n = in.nextInt(); //

        int [] costs = new int[n];
        int [] lens = new int[n];
        int [][] routes = new int[n][];

        for (int i = 0; i < n; i++) {
            costs[i] = in.nextInt();
            lens[i] = in.nextInt();
            routes[i] = new int [lens[i]];

            for (int j = 0; j < lens[i]; j++) {
                routes[i][j] = in.nextInt();
            }
        }

        in.close();

        int[] costsToX = new int[10001];
        // id # of a city
        // value: cost to that city from A or 0 if no routes

        for (int i = 0; i < n; i++) {
            boolean started = false;

            for (int j = 0; j < lens[i]; j++) {
                int city = routes[i][j];
                if (started) costsToX[city] = costsToX[city] == 0 ? costs[i] : Math.min(costsToX[city], costs[i]);
                if (city == a) started = true;
            }
        }

        int result = 0;
        PrintWriter out = new PrintWriter(new File("cowroute.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}

/* ANALYSIS
created 2d array: 
row index: which route is it?
col index: order of cities
value: the city reached at the point in that route

loop through all routes, look for start and end (in correct order), 
then compare the cost with the variable cost

For the second problem:

Seperate the problems into to two parts, to create a effective cost array ()

And then try to create a merged route with effective costs
*
*/