import java.util.*;
import java.io.*;

public class Matching {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int mod = 1000000007;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();

        int[][] grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = nextInt();
            }
        }

        int[] dp = new int[1 << n]; // mask represents the women that are not matched yet

        dp[0] = 1;

        for (int j = 0; j < (1 << n) - 1; j++) {
            int i = Integer.bitCount(j);
            for (int k = 0; k < n; k++) {
                if (grid[i][k] == 1 && (j & (1 << k)) == 0) {
                    int m2 = j ^ (1 << k); // caret is XOR
                    dp[m2] = add_self(dp[m2], dp[j]);
                }
            }
        }

        out.println(dp[(1 << n) - 1]);

        out.close();
    }

    static int add_self(int a, int b) {
        a += b;
        if (a >= mod) a -= mod;

        return a;
    }
}