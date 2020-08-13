// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class feast {

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
        in = new StreamTokenizer(new BufferedReader(new FileReader("feast.in")));

        int t = nextInt();
        int a = nextInt();
        int b = nextInt();

        boolean[][] dp = new boolean[t+1][2];

        Stack<State> states = new Stack<>();

        states.add(new State(0, 0));

        while (!states.isEmpty()) {
            State cur = states.pop();

            dp[cur.fullness][cur.drink] = true;

            if (cur.fullness + a <= t && !dp[cur.fullness + a][cur.drink]) states.add(new State(cur.fullness + a, cur.drink));
            if (cur.fullness + b <= t && !dp[cur.fullness + b][cur.drink]) states.add(new State(cur.fullness + b, cur.drink));

            if (cur.drink == 0) {
                if (!dp[cur.fullness / 2][1]) {
                    states.add(new State(cur.fullness / 2, 1));
                }
            }
        }

        int result = 0;

        for (int i = 0; i <= t; i++) {
            if (dp[i][0] || dp[i][1]) result = i;
        }

        PrintWriter out = new PrintWriter(new File("feast.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class State {
        int fullness, drink;

        State(int fullness, int drink) {
            this.fullness = fullness;
            this.drink = drink;
        }
    }
}


