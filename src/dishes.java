// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class dishes {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("dishes.in")));
        PrintWriter out = new PrintWriter(new File("dishes.out"));

        int n = nextInt();

        ArrayList<Stack<Integer>> stacks = new ArrayList<>();
        ArrayList<Integer> bottomsOfStacks = new ArrayList<>();

        int highestRemoved = 0;

        for (int i = 0; i < n; i++) {
            int nextDish = nextInt();

            if (nextDish < highestRemoved) {
                System.out.println(i);
                out.println(i);
                out.close();
                return;
            }

            int index = - (Collections.binarySearch(bottomsOfStacks, nextDish) + 1);

            if (index == stacks.size()) {
                Stack<Integer> stack = new Stack<>();
                stack.add(nextDish);
                bottomsOfStacks.add(nextDish);
                stacks.add(stack);
            }

            else {
                while (!stacks.get(index).isEmpty()) {
                    if (stacks.get(index).peek() < nextDish) {
                        int remove = stacks.get(index).pop();
                        highestRemoved = Math.max(highestRemoved, remove);
                    }
                    else break;
                }

                if (stacks.isEmpty()) {
                    bottomsOfStacks.set(index, nextDish);
                }

                stacks.get(index).add(nextDish);
            }
        }

        System.out.println(n);
        out.println(n);
        out.close();
    }
}


