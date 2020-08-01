// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class art2 {

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
        in = new StreamTokenizer(new BufferedReader(new FileReader("art2.in")));
        PrintWriter out = new PrintWriter(new File("art2.out"));

        int n = nextInt();

        EndPoint[] endPoints = new EndPoint[2 * n];

        for (int i = 0; i < 2 * n; i++) {
            endPoints[i] = new EndPoint(i, -1, false);
        }

        for (int i = 0; i < n; i++) {
            int color = nextInt() - 1;

            if (color == -1) continue;

            if (endPoints[color].color == -1) {
                endPoints[color].index = i;
                endPoints[color].color = color;
                endPoints[color].isStart = true;
                endPoints[color + n].index = i;
                endPoints[color + n].color = color;
            }
            else {
                endPoints[color + n].index = i;
            }
        }

        Arrays.sort(endPoints);

        Stack<EndPoint> stack = new Stack<>();
        int result = 0;

        for (EndPoint i : endPoints) {
            if (i.color == -1) continue;
            if (i.isStart) {
                i.depth = stack.size() + 1;
                stack.push(i);
            }

            else {
                EndPoint top = stack.peek();
                if (top.color == i.color) {
                    result = Math.max(result, top.depth);
                    stack.pop();
                }

                else {
                    System.out.println(-1);
                    out.println(-1);
                    out.close();
                    return;
                }
            }
        }

        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class EndPoint implements Comparable<EndPoint> {
        int color;
        int index;
        boolean isStart;
        int depth = 0;

        EndPoint(int index, int color, boolean isStart) {
            this.index = index;
            this.isStart = isStart;
            this.color = color;
        }

        @Override
        public int compareTo(EndPoint o) {
            return this.index - o.index;
        }
    }
}


