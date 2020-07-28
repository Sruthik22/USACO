// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class AirConditioner {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int q = nextInt();

        for (int i = 0; i < q; i++) {
            int n = nextInt();
            int m = nextInt();

            Customer[] customers = new Customer[n];

            for (int j = 0; j < n; j++) {
                int t = nextInt();
                int l = nextInt();
                int h = nextInt();

                Customer customer = new Customer(t, l, h);
                customers[j] = customer;
            }

            Arrays.sort(customers);

            boolean works = true;
            int mn = m;
            int mx = m;
            int k = 0;

            for (int j = 0; j < n; j++) {
                Customer cur = customers[j];

                int time = cur.t - k;

                mn -= time;
                mx += time;

                if (mn > cur.h || mx < cur.l) {
                    works = false;
                    break;
                }

                else {
                    mx = Math.min(mx, cur.h);
                    mn = Math.max(mn, cur.l);
                }

                k = cur.t;
            }

            if (works) {
                out.println("YES");
            }

            else {
                out.println("NO");
            }
        }

        out.close();
    }

    static class Customer implements Comparable<Customer> {
        int t, l, h;

        Customer(int t, int l, int h) {
            this.t = t;
            this.l = l;
            this.h = h;
        }

        @Override
        public int compareTo(Customer o) {
            return this.t - o.t;
        }
    }
}