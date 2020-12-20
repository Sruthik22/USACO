import java.util.*;
import java.io.*;

public class lifeguards {

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public float nextFloat() {
            return Float.parseFloat(next());
        }

        public double nextDouble() {
            return Float.parseFloat(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class CPMath {
        static int add(int a, int b) {
            a += b;

            if (a >= mod) a -= mod;

            return a;
        }

        static int sub(int a, int b) {
            a -= b;
            if (a < 0) a += mod;
            return a;
        }

        static int multiply(int a, long b) {
            b = a * b;
            return (int) (b % mod);
        }

        static int divide(int a, int b) {
            return multiply(a, inverse(b));
        }

        static int inverse(int a) {
            return power(a, mod - 2);
        }

        static int power(int a, int b) {
            int r = 1;

            while (b > 0) {
                if (b % 2 == 1) {
                    r = multiply(r, a);
                }

                a = multiply(a, a);
                b /= 2;
            }

            return r;
        }
    }

    static InputReader sc;
    static PrintWriter pw;

    static int mod = (int) (1e9 + 7);

    public static void main(String[] args) throws Exception {
        sc = new InputReader(new FileInputStream("lifeguards.in"));
        pw = new PrintWriter(new File("lifeguards.out"));

        int n = sc.nextInt();

        Point[] points = new Point[2 * n];
        Cow[] cows = new Cow[n];

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            points[i * 2] = new Point(a, true, i);
            points[i * 2 + 1] = new Point(b, false, i);
            cows[i] = new Cow(i);
        }

        Arrays.sort(points);

        HashSet<Integer> cows_working = new HashSet<>();
        int total_time = 0;
        int start = 0;

        for (int i = 0; i < 2 * n; i++) {
            Point cur = points[i];

            if (cur.start) {
                if (cows_working.isEmpty()) {
                    cows[cur.cow].start = cur.x;
                    start = cur.x;
                }

                if (cows_working.size() == 1) {
                    // this means there was a cow by themselves
                    int id = (int) cows_working.toArray()[0];
                    cows[id].time_alone += cur.x - cows[id].start;
                }

                cows_working.add(cur.cow);
            }

            else {
                cows_working.remove(cur.cow);

                if (cows_working.isEmpty()) {
                    cows[cur.cow].time_alone += cur.x - cows[cur.cow].start;
                }

                if (cows_working.size() == 1) {
                    int id = (int) cows_working.toArray()[0];
                    cows[id].start = cur.x;
                }

                if (cows_working.isEmpty()) {
                    total_time += cur.x - start;
                }
            }
        }

        int min_time = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            Cow cur = cows[i];

            min_time = Math.min(min_time, cur.time_alone);
        }

        int result = total_time - min_time;

        pw.println(result);
        pw.close();
    }

    static class Point implements Comparable<Point> {
        int x, cow;
        boolean start;

        Point(int x, boolean start, int cow) {
            this.x = x;
            this.start = start;
            this.cow = cow;
        }

        @Override
        public int compareTo(Point o) {
            return this.x - o.x;
        }
    }
    static class Cow {
        int time_alone;
        int start;
        int id;

        Cow(int id) {
            this.id = id;
        }
    }
}