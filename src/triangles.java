import java.util.*;
import java.io.*;

public class triangles {

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

    static HashMap<Integer, ArrayList<Integer>> xs;
    static HashMap<Integer, ArrayList<Integer>> ys;
    static HashMap<Point, Integer> precomputeX;
    static HashMap<Point, Integer> precomputeY;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(new FileInputStream("triangles.in"));
        pw = new PrintWriter(new File("triangles.out"));

        int n = sc.nextInt();

        xs = new HashMap<>();
        ys = new HashMap<>();

        Point[] points = new Point[n];

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            points[i] = new Point(x, y);

            if (xs.containsKey(x)) xs.get(x).add(y);
            else {
                ArrayList<Integer> holder = new ArrayList<>();
                holder.add(y);
                xs.put(x, holder);
            }

            if (ys.containsKey(y)) ys.get(y).add(x);
            else {
                ArrayList<Integer> holder = new ArrayList<>();
                holder.add(x);
                ys.put(y, holder);
            }
        }

        precomputeX = new HashMap<>();
        precomputeY = new HashMap<>();

        for (int y: ys.keySet()) {
            precomputeX(y);
        }

        for (int x: xs.keySet()) {
            precomputeY(x);
        }


        int result = 0;

        for (int i = 0; i < n; i++) {
            Point p = points[i];
            result = CPMath.add(result, CPMath.multiply(precomputeX.get(p), precomputeY.get(p)));
        }

        pw.println(result);
        pw.close();
    }

    static void precomputeX(int y) {
        ArrayList<Integer> points = ys.get(y);
        Collections.sort(points);

        int[] distances = new int[points.size() - 1];

        for (int i = 1; i < points.size(); i++) {
            int cur = points.get(i);
            int last = points.get(i-1);
            distances[i-1] = cur - last;
        }

        Point[] pointInPoints = new Point[points.size()];

        for (int i = 0; i < points.size(); i++) {
            pointInPoints[i] = new Point(points.get(i), y);
        }

        int total = 0;
        int prev = 0;

        for (int i = 1; i < points.size(); i++) {
            prev = CPMath.add(prev, distances[i - 1]);
            total = CPMath.add(total, prev);
        }

        precomputeX.put(pointInPoints[0], total);

        for (int i = 1; i < points.size(); i++) {
            int toSubRight = points.size() - i;
            total = CPMath.sub(total, CPMath.multiply(toSubRight, distances[i-1]));
            precomputeX.put(pointInPoints[i], total);
        }

        total = 0;
        prev = 0;

        for (int i = points.size() - 2; i >= 0; i--) {
            prev = CPMath.add(prev, distances[i]);
            total = CPMath.add(total, prev);
        }

        precomputeX.put(pointInPoints[points.size() - 1], total);

        for (int i = points.size() - 2; i >= 0; i--) {
            int toSubLeft = i + 1;
            total = CPMath.sub(total, CPMath.multiply(toSubLeft, distances[i]));
            precomputeX.put(pointInPoints[i], CPMath.add(precomputeX.get(pointInPoints[i]), total));
        }
    }
    static void precomputeY(int x) {
        ArrayList<Integer> points = xs.get(x);
        Collections.sort(points);

        int[] distances = new int[points.size() - 1];

        for (int i = 1; i < points.size(); i++) {
            int cur = points.get(i);
            int last = points.get(i-1);
            distances[i-1] = cur - last;
        }

        Point[] pointInPoints = new Point[points.size()];

        for (int i = 0; i < points.size(); i++) {
            pointInPoints[i] = new Point(x, points.get(i));
        }

        int total = 0;
        int prev = 0;

        for (int i = 1; i < points.size(); i++) {
            prev = CPMath.add(prev, distances[i - 1]);
            total = CPMath.add(total, prev);
        }

        precomputeY.put(pointInPoints[0], total);

        for (int i = 1; i < points.size(); i++) {
            int toSubRight = points.size() - i;
            total = CPMath.sub(total, CPMath.multiply(toSubRight, distances[i-1]));
            precomputeY.put(pointInPoints[i], total);
        }

        total = 0;
        prev = 0;

        for (int i = points.size() - 2; i >= 0; i--) {
            prev = CPMath.add(prev, distances[i]);
            total = CPMath.add(total, prev);
        }

        precomputeY.put(pointInPoints[points.size() - 1], total);

        for (int i = points.size() - 2; i >= 0; i--) {
            int toSubLeft = i + 1;
            total = CPMath.sub(total, CPMath.multiply(toSubLeft, distances[i]));
            precomputeY.put(pointInPoints[i], CPMath.add(precomputeY.get(pointInPoints[i]), total));
        }
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}


