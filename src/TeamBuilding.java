import java.util.*;
import java.io.*;

public class TeamBuilding {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("team.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("team.out")));
        final int MOD = 1000000009;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        int[] b = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        long[][] dp = new long[n+1][m+1];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], 1);
        }
        Arrays.sort(a);
        Arrays.sort(b);

        while(k-- > 0) {

            long[][] next = new long[n + 1][m + 1];

            for(int i = 0; i < a.length; i++) {
                for(int j = 0; j < b.length; j++) {
                    if(a[i] <= b[j]) continue;
                    next[i+1][j+1] += dp[i][j];
                }
            }

            dp = next;

            for(int i = 0; i < dp.length; i++) {
                for(int j = 0; j < dp[i].length; j++) {
                    if(i > 0) {
                        dp[i][j] += dp[i-1][j];
                    }
                    if(j > 0) {
                        dp[i][j] += dp[i][j-1];
                        if(i > 0) {
                            dp[i][j] -= dp[i-1][j-1];
                        }
                    }
                    dp[i][j] %= MOD;
                    dp[i][j] += MOD;
                    dp[i][j] %= MOD;
                }
            }
        }

        long result = dp[n][m] % MOD;

        pw.println(result);
        pw.close();
    }
}


