import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

public class RoundSubset {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        long[] array = new long[n];
        for (int i = 0; i < n; i++)
            array[i] = scan.nextLong();

        Point[] power_2_5 = new Point[n];
        for (int i = 0; i < n; i++)
            power_2_5[i] = new Point(0, 0);

        int sumPow_2 = 0;
        for (int i = 0; i < n; i++) {
            long temp = array[i];
            while (temp % 2 == 0) {
                power_2_5[i].x++;
                temp /= 2;
            }
            temp = array[i];
            while (temp % 5 == 0) {
                power_2_5[i].y++;
                temp /= 5;
            }

            sumPow_2 += power_2_5[i].x;
        }

        int[][] dp = new int[sumPow_2 + 1][k + 1];
        for (int[] x : dp)
            Arrays.fill(x, -1);
        dp[0][0] = 0;

        for (int i = 0; i < n; i++)
            for (int j = sumPow_2; j >= power_2_5[i].x; j--)
                for (int z = k; z > 0; z--)
                    if (dp[j - power_2_5[i].x][z - 1] >= 0)
                        dp[j][z] = Math.max(dp[j][z], dp[j - power_2_5[i].x][z - 1] + power_2_5[i].y);

        int ans = 0;
        for (int i = 0; i <= sumPow_2; i++)
            ans = Math.max(ans, Math.min(i, dp[i][k]));

        System.out.println(ans);

        scan.close();
    }

}