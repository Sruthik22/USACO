import java.util.*;
import java.io.*;

public class CyclicPermutations {

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

        long n = nextLong();

        int result = 1;

        for (long i = 1; i <= n; i++) {
            result = mult_self(result, i);
        }

        int part_to_subtract = 1;

        for (long i = 1; i < n; i++) {
            part_to_subtract = mult_self(part_to_subtract, 2);
        }

        out.println(remove_self(result, part_to_subtract));

        out.close();
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