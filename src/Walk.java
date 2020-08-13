import java.util.*;
import java.io.*;

public class Walk {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static long nextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    static int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        long k = nextLong();

        LinkedList<Integer>[] linkedLists = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int next = nextInt();
                if (next == 1) {
                    linkedLists[i].add(j);
                }
            }
        }

        int[] dp = new int[n]; // the number of paths to this vertex

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        for (long i = 0; i < k; i++) {
            int[] newDp = new int[n];
            for (int j = 0; j < n; j++) {
                for (int l : linkedLists[j]) {
                    newDp[l] = add_self(newDp[l], dp[j]);
                }
            }

            dp = newDp;
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            result += dp[i];
        }

        out.println(result);
        out.close();
    }

    static int add_self(int a, int b) {
        a += b;

        if (a >= mod) a -= mod;
        return a;
    }
}