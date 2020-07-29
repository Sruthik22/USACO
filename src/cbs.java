// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class cbs {

    static int k;
    static int n;
    static int[][] prefixSums;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new BufferedReader(new FileReader("cbs.in")));

        k = in.nextInt();
        n = in.nextInt();

        prefixSums = new int[k][n];

        int before = 0;

        for (int i = 0; i < k; i++) {
            String s = in.next();

            for (int j = 0; j < n; j++) {
                if (s.charAt(j) == '(') before++;
                else before--;

                prefixSums[i][j] = before;
            }
        }

        int[][] earliestLess = new int[k][n];

        for (int i = 0; i < k; i++) {
            earliestLess[i] = lastLessThan(i);
        }

        int[] earlyCompact = new int[n];

        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < k; j++) {
                max = Math.max(earliestLess[j][i], max);
            }
            earlyCompact[i] = max;
        }

        HashMap<String, Integer> states = new HashMap<>();

        StringBuilder s = new StringBuilder();

        for (int i = 0; i < k; i++) {
            s.append("0");
        }

        states.put(s.toString(), 0);

        int[] counts = new int[n+1];
        int result = 0;

        for (int i = 0; i < n; i++) {

            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < k; j++) {
                sb.append(prefixSums[j][i]);
            }

            String state = sb.toString();

            if (states.containsKey(state)) {
                int pos = states.get(state);

                if (pos > earlyCompact[i]) {
                    result += counts[pos] + 1;
                    counts[i + 1] = counts[pos] + 1;
                }
            }

            states.put(state, i+1);
        }

        PrintWriter out = new PrintWriter(new File("cbs.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static int[] lastLessThan(int index) {
        Stack<Value> stack = new Stack<>();

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int next = prefixSums[index][i];

            while (!stack.isEmpty()) {
                int pos = stack.peek().val;
                if (pos < next) {
                    ans[i] = stack.peek().index;
                    break;
                }

                else {
                    stack.pop();
                }
            }

            if (stack.isEmpty()) ans[i] = -1;

            stack.add(new Value(next, i));
        }

        return ans;
    }

    static class Value {
        int val, index;

        Value(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
}


