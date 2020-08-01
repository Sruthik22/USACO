// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class Ch1_RunningOnFumes {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static ArrayDeque<Value> arrayDeque;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("Ch1_RunningOnFumes.in")));
        PrintWriter out = new PrintWriter(new File("Ch1_RunningOnFumes.out"));

        int t = nextInt();

        for (int j = 0; j < t; j++) {
            int n = nextInt();
            int m = nextInt();

            int[] costs = new int[n];

            for (int i = 0; i < n; i++) {
                costs[i] = nextInt();
            }

            arrayDeque = new ArrayDeque<>();

            long[] mins = new long[n];

            mins[0] = 0;
            arrayDeque.add(new Value(0, 0)); // f values - min cost of full tank at pos

            boolean works = true;

            for (int i = 1; i < n; i++) {
                if (arrayDeque.isEmpty()) {
                    works = false;
                    break;
                }

                if (costs[i] != 0) {
                    mins[i] = arrayDeque.getLast().value + costs[i];

                    clear(new Value(i, mins[i]));
                }
                else {
                    mins[i] = -1;
                }

                if (i != n-1 & arrayDeque.getLast().index <= i - m) arrayDeque.removeLast();
            }

            out.print("Case #" + (j+1) + ": ");

            if (works) out.println(arrayDeque.getLast().value);
            else out.println(-1);
        }

        out.close();
    }

    static void clear(Value add) {
        while (!arrayDeque.isEmpty()) {
            Value last = arrayDeque.getFirst();

            if (add.value <= last.value) {
                arrayDeque.removeFirst();
            }

            else break;
        }

        arrayDeque.addFirst(add);
    }

    static class Value {
        int index;
        long value;

        Value(int index, long value) {
            this.index = index;
            this.value = value;
        }
    }
}


