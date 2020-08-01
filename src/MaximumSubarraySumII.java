// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class MaximumSubarraySumII {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int n;
    static int a;
    static int b;
    static long[] prefix;
    static ArrayDeque<Value> arrayDeque;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        n = nextInt();
        a = nextInt();
        b = nextInt();

        prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            int value = nextInt();
            prefix[i+1] = value + prefix[i];
        }

        arrayDeque = new ArrayDeque<>();

        arrayDeque.add(new Value(0, 0));

        long result = Long.MIN_VALUE;

        for (int i = a; i <= n; i++) {
            long curValue = prefix[i] - arrayDeque.getFirst().value;
            result = Math.max(result, curValue);
            add(i - a + 1);
            while (!arrayDeque.isEmpty() && i - arrayDeque.getFirst().index >= b) arrayDeque.removeFirst();
        }

        out.println(result);

        out.close();
    }

    static void add(int index) {
        while (!arrayDeque.isEmpty()) {
            if (prefix[index] > arrayDeque.getLast().value) break;
            arrayDeque.removeLast();
        }

        arrayDeque.addLast(new Value(prefix[index], index));
    }

    static class Value {
        long value;
        int index;

        Value(long value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}


