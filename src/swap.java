// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class swap {

    private static int n;
    private static int m;

    private static Swap[] swaps;
    private static int[] positions;

    private static boolean[] visited;
    private static LinkedList<Integer>[] adjListArray;
    private static HashMap<Integer, ArrayList<Integer>> components;
    private static int[] connectedcomponents;
    private static int[] indexOfIndex;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("swap.in"));
        n = in.nextInt(); // cows
        m = in.nextInt(); // the swaps
        long k = in.nextLong(); // # time steps to go forward

        swaps = new Swap[m];

        for (int i = 0; i < m; i++) {
            swaps[i] = new Swap(in.nextInt()-1, in.nextInt()-1);
        }

        positions = new int[n];

        for (int i = 0; i < n; i++) {
            positions[i] = i;
        }

        in.close();

        step(); // do a single swap to find the transformation matrix

        /*
        * need to find the connected components */

        adjListArray = new LinkedList[n]; // need to create an adjacency list in order to make a graph
        for (int i = 0; i < n; i++) {
            adjListArray[i] = new LinkedList<>();
        }

        for (int i = 0; i < n; i++) {
            adjListArray[i].add(positions[i]);
        }

        // now set up the adjacency list, now need to find the connected components
        connectedcomponents = new int[n];
        components = new HashMap<>();
        indexOfIndex = new int[n];

        int counter = 1;

        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                connectedComponents(i, counter);
                counter++;
            }
        }

        PrintWriter out = new PrintWriter(new File("swap.out"));
        for (int i = 0; i < n; i++) {

            // for each node figure out what position

            ArrayList<Integer> curComponent = components.get(connectedcomponents[i]);

            long remainder = k % curComponent.size();

            int index = indexOfIndex[i];

            int newelementIndex = (int) ((index + remainder) % curComponent.size());

            positions[i] = curComponent.get(newelementIndex) + 1;

            System.out.println(positions[i]);
            out.println(positions[i]);
        }
        out.close();
    }

    private static void connectedComponents(int v, int counter) {

        visited[v] = true;
        connectedcomponents[v] = counter;

        if (components.containsKey(counter)) {
            components.get(counter).add(v);
        }
        else {
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(v);
            components.put(counter, arrayList);
        }

        indexOfIndex[v] = components.get(counter).size()-1;

        for (int x : adjListArray[v]) {
            if(!visited[x]) connectedComponents(x, counter);
        }
    }

    static void step() {

        for (int i = 0; i < m; i++) {
            // go through each of the swaps
            Swap swap = swaps[i];

            int[] temporary = new int[n];
            System.arraycopy(positions, 0, temporary, 0, n);

            for (int j = swap.l; j <= swap.r; j++) {
                // not efficient
                // j needs to go to the symmetric side

                positions[j] = temporary[swap.r-(j-swap.l)];
            }
        }
    }

    static class Swap {
        int l, r;

        Swap(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }
}


