// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class cereal {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("cereal.in")));

        int n = nextInt(); // Number of cows
        int m = nextInt(); // Number of cereal boxes

        int[] box_distribution = new int[m+1]; // each of the index will be filled with the id of the cow

        HashMap<Integer, int[]> cows = new HashMap<>(); // choice 1, choice 2

        for (int i = 1; i <= n; i++) {
            int f = nextInt();
            int s = nextInt();

            cows.put(i, new int[] {f, s});
        }

        int cur_num = 0;

        int[] results = new int[n];

        for (int i = n; i >= 1; i--) {

            // the cow will take its first spot, but it will knock out the other cow
            // we need to map out what happens to the other cow

            int cow_id = i;
            int choice = cows.get(cow_id)[0];

            while (true) {
                if (box_distribution[choice] == 0) {
                    // this means that the cow can just take the spot
                    box_distribution[choice] = cow_id;
                    cur_num++;
                    break;
                }

                // need to get the details of the cow in this current position

                int other_cow = box_distribution[choice];

                if (other_cow < cow_id) break;

                // we will replace the cow in this position

                box_distribution[choice] = cow_id;

                // need to check what choice this was for the other cow

                int second_choice = cows.get(other_cow)[1];

                if (choice == second_choice) break;

                cow_id = other_cow;
                choice = second_choice;
            }

            results[i-1] = cur_num;
        }

        PrintWriter out = new PrintWriter(new File("cereal.out"));

        for (int i : results) {
            System.out.println(i);
            out.println(i);
        }

        out.close();
    }
}


