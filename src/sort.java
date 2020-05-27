// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class sort {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("sort.in")));

        int n = nextInt();

        Value[] values = new Value[n];

        for (int i = 0; i < n; i++) {
            values[i] = new Value(nextInt(), i);
        }

        Arrays.sort(values);

        int result = 0;

        for (int i = 0; i < n; i++) {
            int move = values[i].origIndex - i;
            result = Math.max(result, move);
        }

        result++;

        PrintWriter out = new PrintWriter(new File("sort.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Value implements Comparable<Value> {
        int val, origIndex;

        Value (int val, int origIndex) {
            this.val = val;
            this.origIndex = origIndex;
        }

        @Override
        public int compareTo(Value o) {
            return this.val - o.val;
        }
    }
}


