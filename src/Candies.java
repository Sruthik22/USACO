import java.util.*;
import java.io.*;

public class Candies {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        int k = nextInt();

        int[] candies = new int[n];

        for (int i = 0; i < n; i++) {
            candies[i] = nextInt();
        }

        int[] dp = new int[k + 1];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            int up_to = candies[i];
            int[] prefixArray = new int[k+1];
            for (int used = k; used >= 0; used--) {
                int temp = dp[used];
                int L = used + 1;
                int R = used + Math.min(up_to, k - used);

                if (L <= R) {
                    prefixArray[L] = add_self(prefixArray[L], temp);

                    if (R + 1 <= k) {
                        prefixArray[R + 1] = remove_self(prefixArray[R + 1], temp);
                    }
                }
            }

            int prefix = 0;
            for (int j = 0; j <= k; j++) {
                prefix = add_self(prefix, prefixArray[j]);
                dp[j] = add_self(dp[j], prefix);
            }
        }

        out.println(dp[k]);

        out.close();
    }

    static int mod = 1000000007;

    static int add_self(int a, int b) {
        a += b;
        if (a >= mod) a -= mod;

        return a;
    }
    static int remove_self(int a, int b) {
        a -= b;
        if (a < 0) a += mod;

        return a;
    }
}