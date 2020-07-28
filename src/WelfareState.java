// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class WelfareState {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();

        int[] balances = new int[n];

        for (int i = 0; i < n; i++) {
            balances[i] = nextInt();
        }

        int q = nextInt();

        ArrayList<Action> actions = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            int code = nextInt();

            if (code == 1) {
                int p = nextInt()-1;
                int x = nextInt();

                actions.add(new Action(code, p, x));
            }

            else {
                int x = nextInt();

                actions.add(new Action(code, x, 0));
            }
        }

        int maxWW = 0;

        boolean[] locked = new boolean[n];

        for (int i = actions.size()-1; i >= 0; i--) {
            Action a = actions.get(i);
            if (a.code == 1) {
                if (locked[a.p1]) continue;
                balances[a.p1] = Math.max(maxWW, a.p2);
                locked[a.p1] = true;
            }
            else {
                maxWW = Math.max(maxWW, a.p1);
            }
        }

        for (int i = 0; i < n; i++) {
            if (!locked[i]) {
                balances[i] = Math.max(balances[i], maxWW);
            }
        }

        for (int i = 0; i < n; i++) {
            out.print(balances[i]);

            if (i != n-1) out.print(" ");
        }

        out.close();
    }

    static class Action {
        int code, p1, p2;

        Action(int code, int p1, int p2) {
            this.code = code;
            this.p1 = p1;
            this.p2 = p2;
        }
    }
}


