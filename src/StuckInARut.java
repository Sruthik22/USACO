import java.util.*;
import java.io.*;

public class StuckInARut {

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

    static LinkedList<Integer>[] linkedLists;
    static LinkedList<Integer>[] reversed;

    static int[] blames;
    static Cow[] cows;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        int n = sc.nextInt();

        cows = new Cow[n];

        for (int i = 0; i < n; i++) {
            char c = sc.next().charAt(0);
            int x = sc.nextInt();
            int y = sc.nextInt();

            cows[i] = new Cow(x, y, c, i);
        }

        IntersectionPoint[] intersectionPoints = new IntersectionPoint[(n-1) * n / 2];

        int index = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                Cow c1 = cows[i];
                Cow c2 = cows[j];

                int time = get_time(c1, c2);
                intersectionPoints[index] = new IntersectionPoint(i, j, time);
                index++;
            }
        }

        Arrays.sort(intersectionPoints);

        linkedLists = new LinkedList[n];
        reversed = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
            reversed[i] = new LinkedList<>();
        }

        for (IntersectionPoint intersectionPoint: intersectionPoints) {
            if (intersectionPoint.time == -1) continue;

            // now we need to check which cow stopped the other, or if one of them even did stop the other

            Cow c1 = cows[intersectionPoint.cow_1_id];
            Cow c2 = cows[intersectionPoint.cow_2_id];

            if (c1.stopped && c2.stopped) continue;

            if (!c1.stopped && !c2.stopped) {
                int who_stopped_the_other = get_who_stopped(c1, c2);
                int other_cow = who_stopped_the_other == c1.id ? c2.id : c1.id;

                if (who_stopped_the_other != -1) {
                    linkedLists[who_stopped_the_other].add(other_cow);
                }
            }

            else if (c1.stopped) {
                boolean stopped = get_if_c2_stopped(c1, c2);

                if (stopped) {
                    linkedLists[c1.id].add(c2.id);
                }
            }

            else {
                boolean stopped = get_if_c2_stopped(c2, c1);

                if (stopped) {
                    linkedLists[c2.id].add(c1.id);
                }
            }
        }

        blames = new int[n];

        // we need to find the root of the tree, so we will reverse the graph and dfs upwards

        for (int i = 0; i < n; i++) {
            LinkedList<Integer> nodes = linkedLists[i];
            for (int j: nodes) {
                reversed[j].add(i);
            }
        }

        visited = new boolean[n];

        root = -1;
        for (int i = 0; i < n; i++) {
            dfs_up(i);

            if (visited[root]) continue;

            visited[root] = true;
            dfs(root, -1);
        }

        for (int i = 0; i < n; i++) {
            pw.println(blames[i]);
        }

        pw.close();
    }

    static int root;

    static void dfs_up(int node) {
        if (reversed[node].size() == 0) {
            root = node;
            return;
        }

        for (int i: reversed[node]) {
            dfs_up(i);
        }
    }

    static void dfs(int node, int parent) {
        for (int i: linkedLists[node]) {
            if (i != parent) {
                dfs(i, node);
                blames[node] += (1+blames[i]);
            }
        }
    }

    static int get_time(Cow c1, Cow c2) {
        int time = 0;

        if (c1.direction == 'E' && c2.direction == 'N') {
            if (c1.start_x <= c2.start_x && c2.start_y <= c1.start_y) {
                // then will intersect
                // x = c2.start_x
                // y = x1.start_y

                int time_1 = c2.start_x - c1.start_x;
                int time_2 = c1.start_y - c2.start_y;

                time = Math.max(time_1, time_2);
            }

            else {
                time = -1;
            }
        }
        else if (c1.direction == 'N' && c2.direction == 'E') {
            if (c2.start_x <= c1.start_x && c1.start_y <= c2.start_y) {
                // then will intersect
                // x = c2.start_x
                // y = x1.start_y

                int time_1 = c1.start_x - c2.start_x;
                int time_2 = c2.start_y - c1.start_y;

                time = Math.max(time_1, time_2);
            }

            else {
                time = -1;
            }
        }
        else if (c1.direction == 'N' && c2.direction == 'N') {
            if (c2.start_x == c1.start_x) {
                // then will intersect
                // x = c2.start_x
                // y = Math.max(c1.start_y, c2.start_y)

                int time_1 = Math.max(c1.start_y, c2.start_y) - c1.start_y;
                int time_2 = Math.max(c1.start_y, c2.start_y) - c2.start_y;

                time = Math.max(time_1, time_2);
            }

            else {
                time = -1;
            }
        }
        if (c1.direction == 'E' && c2.direction == 'E') {
            if (c2.start_y == c1.start_y) {
                // then will intersect
                // x = c2.start_x
                // y = Math.max(c1.start_y, c2.start_y)

                int time_1 = Math.max(c1.start_x, c2.start_x) - c1.start_x;
                int time_2 = Math.max(c1.start_x, c2.start_x) - c2.start_x;

                time = Math.max(time_1, time_2);
            }

            else {
                time = -1;
            }
        }

        return time;
    }

    static boolean get_if_c2_stopped(Cow c1, Cow c2) {
        int time = 0;

        if (c1.direction == 'E' && c2.direction == 'N') {
            if (c1.stop_x >= c2.start_x) {
                cows[c2.id].stop_x = c2.start_x;
                cows[c2.id].stop_y = c1.start_y;
                cows[c2.id].stopped = true;
                return true;
            }
        }
        else if (c1.direction == 'N' && c2.direction == 'E') {
            if (c1.stop_y >= c2.start_y) {
                cows[c2.id].stop_x = c1.start_x;
                cows[c2.id].stop_y = c2.start_y;
                cows[c2.id].stopped = true;
                return true;
            }
        }
        else if (c1.direction == 'N' && c2.direction == 'N') {
            if (c2.start_y < c1.start_y) {
                cows[c2.id].stop_x = c1.start_x;
                cows[c2.id].stop_y = c1.start_y;
                cows[c2.id].stopped = true;
                return true;
            }
        }
        if (c1.direction == 'E' && c2.direction == 'E') {
            if (c2.start_x < c1.start_x) {
                cows[c2.id].stop_x = c1.start_x;
                cows[c2.id].stop_y = c1.start_y;
                cows[c2.id].stopped = true;
                return true;
            }
        }

        return false;
    }

    static int get_who_stopped(Cow c1, Cow c2) {
        if (c1.direction == 'E' && c2.direction == 'N') {
            int time_1 = c2.start_x - c1.start_x;
            int time_2 = c1.start_y - c2.start_y;

            if (time_1 < time_2) {
                // c1 hits c2
                cows[c2.id].stop_x = c2.start_x;
                cows[c2.id].stop_y = c1.start_y;
                cows[c2.id].stopped = true;
                return c1.id;
            }

            if (time_2 < time_1) {
                cows[c1.id].stop_x = c2.start_x;
                cows[c1.id].stop_y = c1.start_y;
                cows[c1.id].stopped = true;
                return c2.id;
            }
        }
        else if (c1.direction == 'N' && c2.direction == 'E') {
            int time_1 = c1.start_x - c2.start_x;
            int time_2 = c2.start_y - c1.start_y;

            if (time_1 < time_2) {
                cows[c1.id].stop_x = c1.start_x;
                cows[c1.id].stop_y = c2.start_y;
                cows[c1.id].stopped = true;
                return c2.id;
            }

            if (time_2 < time_1) {
                cows[c2.id].stop_x = c1.start_x;
                cows[c2.id].stop_y = c2.start_y;
                cows[c2.id].stopped = true;
                return c1.id;
            }
        }
        else if (c1.direction == 'N' && c2.direction == 'N') {
            int time_1 = Math.max(c1.start_y, c2.start_y) - c1.start_y;
            int time_2 = Math.max(c1.start_y, c2.start_y) - c2.start_y;

            if (time_1 < time_2) {
                cows[c2.id].stop_x = c1.start_x;
                cows[c2.id].stop_y = Math.max(c1.start_y, c2.start_y);
                cows[c2.id].stopped = true;
                return c1.id;
            }

            if (time_1 > time_2) {
                cows[c1.id].stop_x = c1.start_x;
                cows[c1.id].stop_y = Math.max(c1.start_y, c2.start_y);
                cows[c1.id].stopped = true;
                return c2.id;
            }
        }
        if (c1.direction == 'E' && c2.direction == 'E') {
            int time_1 = Math.max(c1.start_x, c2.start_x) - c1.start_x;
            int time_2 = Math.max(c1.start_x, c2.start_x) - c2.start_x;

            if (time_1 < time_2) {
                cows[c2.id].stop_x = Math.max(c1.start_x, c2.start_x);
                cows[c2.id].stop_y = c1.start_y;
                cows[c2.id].stopped = true;
                return c1.id;
            }

            if (time_1 > time_2) {
                cows[c1.id].stop_x = Math.max(c1.start_x, c2.start_x);
                cows[c1.id].stop_y = c1.start_y;
                cows[c1.id].stopped = true;
                return c2.id;
            }
        }

        return -1;
    }

    static class IntersectionPoint implements Comparable<IntersectionPoint> {
        int time;
        int cow_1_id;
        int cow_2_id;

        IntersectionPoint(int cow_1_id, int cow_2_id, int time) {
            this.cow_1_id = cow_1_id;
            this.cow_2_id = cow_2_id;
            this.time = time;
        }

        @Override
        public int compareTo(IntersectionPoint o) {
            return this.time - o.time;
        }
    }
    static class Cow {
        int start_x, start_y;
        char direction;
        boolean stopped;
        int id;
        int stop_x, stop_y;

        Cow(int start_x, int start_y, char direction, int id) {
            this.start_x = start_x;
            this.start_y = start_y;
            this.direction = direction;
            this.id = id;
            this.stopped = false;
        }
    }
}