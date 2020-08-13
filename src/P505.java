import java.util.*;
import java.io.*;

public class P505 {

    static int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = in.nextInt();
        int m = in.nextInt();

        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            String row = in.next();
            for (int j = 0; j < m; j++) {
                grid[i][j] = row.charAt(j) == '1' ? 1:0;
            }
        }

        if (n >= 4 && m >= 4) out.println(-1);
        else if (n == 1 || m == 1) out.println(0);

        else {

            if (m > n) {
                int rows = grid.length;
                int cols = grid[0].length;

                int[][] B = new int[cols][rows];

                int i, j;
                for (i = 0; i < cols; i++)
                    for (j = 0; j < rows; j++)
                        B[i][j] = grid[j][i];

                grid = B;
            }

            int row = Math.max(n, m);
            int column = Math.min(n, m);
            column = 1 << column;

            int[][] dp = new int[row][column];

            for (int i = 1; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    dp[i][j] = 1000000000;
                }
            }

            for (int i = 0; i < column; i++) {
                dp[0][i] = changes(Math.min(n, m), toBinary(i, Math.min(n, m)), grid[0]);
            }

            boolean[][] works = new boolean[column][column];

            for (int i = 0; i < column; i++) {
                for (int j = 0; j < column; j++) {
                    works[i][j] = work(i, j, Math.min(n, m));
                }
            }

            for (int i = 1; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    for (int k = 0; k < column; k++) {
                        if (works[k][j]) {
                            int val = changes(Math.min(n, m), toBinary(j, Math.min(n, m)), grid[i]);
                            dp[i][j] = Math.min(dp[i][j], dp[i-1][k] + val);
                        }
                    }
                }
            }

            int result = 1000000000;

            for (int i = 0; i < column; i++) {
                result = Math.min(result, dp[row - 1][i]);
            }

            out.println(result);
        }

        out.close();
    }

    static boolean work(int top, int below, int length) {
        int[] tS = toBinary(top, length);
        int[] bS = toBinary(below, length);

        for (int i = 1; i < length; i++) {
            int prevVal = row_val(tS, bS, i - 1);
            int thisVal = row_val(tS, bS, i);

            if ((prevVal + thisVal) % 2 == 0) return false;
        }

        return true;
    }

    static int changes(int length, int[] bS, int[] row) {
        int result = 0;

        for (int i = 0; i < length; i++) {
            if (bS[i] != row[i]) result++;
        }

        return result;
    }

    static int row_val(int[] tS, int[] bS, int index) {
        return tS[index] + bS[index];
    }

    public static int[] toBinary(int x, int len) {
        final int[] buff = new int[len];

        for (int i = len - 1; i >= 0 ; i--) {
            int mask = 1 << i;
            buff[len - 1 - i] = (x & mask) != 0 ? 1 : 0;
        }

        return buff;
    }

    static int mult_self(long a, long b) {
        long result = a * b;
        result %= mod;
        return (int) result;
    }

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