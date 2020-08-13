import java.util.*;
import java.io.*;

public class Deque {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = nextInt();
        }

        long[][] dp = new long[n][n];

        for (int L = n-1; L >= 0; L--) {
            for (int R = L; R < n; R++) {
                if (L == R) dp[L][R] = nums[L];
                else dp[L][R] = Math.max(nums[L] - dp[L+1][R], nums[R] - dp[L][R-1]);
            }
        }

        out.println(dp[0][n-1]);

        out.close();
    }
}