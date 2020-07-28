// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class NearestSmallerValues {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();

        Stack<Value> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int next = nextInt();

            while (!stack.isEmpty()) {
                int pos = stack.peek().val;
                if (pos < next) {
                    out.print(stack.peek().index);
                    break;
                }

                else {
                    stack.pop();
                }
            }

            if (stack.isEmpty()) out.print(0);

            stack.add(new Value(next, i+1));

            if (i != n-1) out.print(" ");
        }

        out.close();
    }

    static class Value {
        int val, index;

        Value(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
}


