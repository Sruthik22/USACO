import java.io.*;
import java.util.*;

public class Applejack {

    static int A = 100000;

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = in.nextInt();

        int[] frequencies = new int[A + 1];

        for (int i = 0; i < n; i++) {
            frequencies[in.nextInt()]++;
        }

        int total4s = 0;
        int total2s = 0;

        for (int i = 0; i <= A; i++) {
            total4s += frequencies[i] / 4;
            total2s += frequencies[i] % 4 / 2;
        }

        int q = in.nextInt();

        for (int i = 0; i < q; i++) {
            char c = in.next().charAt(0);
            int x = in.nextInt();

            total4s -= frequencies[x] / 4;
            total2s -= frequencies[x] % 4 / 2;

            if (c == '-') {
                frequencies[x]--;
            }

            else {
                frequencies[x]++;
            }

            total4s += frequencies[x] / 4;
            total2s += frequencies[x] % 4 / 2;

            out.println(total4s >= 2 || total4s == 1 && total2s >= 2 ? "YES" : "NO");
        }

        out.close();
    }
}


