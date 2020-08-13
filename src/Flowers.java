import java.util.*;
import java.io.*;

public class Flowers {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        Flower[] flowers = new Flower[n];

        for (int i = 0; i < n; i++) {
            flowers[i] = new Flower(nextInt(), 0);
        }

        for (int i = 0; i < n; i++) {
            flowers[i].beauty = nextInt();
        }

        Flower[] dp = new Flower[n];

        TreeSet<Flower> treeSet = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            dp[i] = flowers[i];

            if (treeSet.lower(dp[i]) != null) {
                dp[i].beauty = flowers[i].beauty + treeSet.lower(dp[i]).beauty;
            }

            if (treeSet.lower(dp[i]) != null && treeSet.lower(dp[i]).beauty >= dp[i].beauty) continue;
            else {
                treeSet.add(dp[i]);
                while (treeSet.higher(dp[i]) != null && treeSet.higher(dp[i]).beauty <= dp[i].beauty) {
                    treeSet.remove(treeSet.higher(dp[i]));
                }
            }
        }

        long result = 0;

        for (int i = 0; i < n; i++) {
            result = Math.max(result, dp[i].beauty);
        }

        out.println(result);

        out.close();
    }

    static class Flower implements Comparable<Flower>{
        int height;
        long beauty;

        Flower(int height, long beauty) {
            this.height = height;
            this.beauty = beauty;
        }

        @Override
        public int compareTo(Flower o) {
            return this.height - o.height;
        }
    }
}