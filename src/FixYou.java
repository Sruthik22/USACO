import java.util.*;
import java.io.*;

public class FixYou {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
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

        for (int i = 0; i < n; i++) {
            int x = nextInt();
            int y = nextInt();

            int[][] leftRight = new int[x][y];

            for (int j = 0; j < x; j++) {
                String s = next();

                for (int k = 0; k < y; k++) {
                    leftRight[j][k] = s.charAt(k) == 'R' ? 1 : s.charAt(k) == 'C' ? 2 : 0;
                }
            }

            int result = 0;

            for (int j = 0; j < x; j++) {
                if (leftRight[j][y-1] == 1) result++;
            }

            for (int j = 0; j < y; j++) {
                if (leftRight[x-1][j] == 0) result++;
            }

            out.println(result);
        }

        out.close();
    }
}