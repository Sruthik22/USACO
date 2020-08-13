import java.util.*;
import java.io.*;

public class Suborrays {

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

        for (int i = 0; i < n; i++) {
            int val = nextInt();

            for (int j = 1; j <= val; j++) {
                out.print(j + " ");
            }

            out.println();
        }

        out.close();
    }
}