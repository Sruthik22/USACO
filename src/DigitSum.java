import java.util.*;
import java.io.*;

public class DigitSum {

    static int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        String s = in.next();
        int d = in.nextInt();
        int length = s.length();

        int[][] dp = new int[2][d]; // 0 - is smaller, 1 is not yet
        dp[1][0] = 1;

        for (int i = 0; i < length; i++) {
            int[][] latestDP = new int[2][d];
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < d; k++) {
                    int prevSum = Math.floorMod(k - j, d);
                    latestDP[0][k] = add_self(latestDP[0][k], dp[0][prevSum]);
                }
            }
            int digit = Character.getNumericValue(s.charAt(i));
            for (int j = 0; j < digit; j++) {
                for (int k = 0; k < d; k++) {
                    latestDP[0][k] = add_self(latestDP[0][k], dp[1][Math.floorMod(k - j, d)]);
                }
            }
            for (int k = 0; k < d; k++) {
                latestDP[1][k] = add_self(latestDP[1][k], dp[1][Math.floorMod(k - digit, d)]);
            }
            dp = latestDP;
        }

        out.println(sub_self(add_self(dp[0][0], dp[1][0]), 1));
        out.close();
    }

    static int add_self(long a, long b) {
        a += b;
        if (a >= mod) a -= mod;
        return (int) a;
    }

    static int sub_self(long a, long b) {
        a -= b;
        if (a < 0) a += mod;
        return (int) a;
    }
}