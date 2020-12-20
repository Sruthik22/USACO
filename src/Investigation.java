import java.util.*;
import java.io.*;

public class Investigation {

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
    static long[] distance;

    public static void main(String[] args) throws Exception {
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        int n = sc.nextInt();
        int m = sc.nextInt();
        LinkedList<Edge>[] linkedLists = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            linkedLists[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int c = sc.nextInt();

            linkedLists[a].add(new Edge(b, c));
        }

        distance = new long[n];
        Arrays.fill(distance, Long.MAX_VALUE);

        PriorityQueue<Element> priorityQueue = new PriorityQueue<>();
        distance[0] = 0;
        priorityQueue.add(new Element(0));

        int[] numRoutes = new int[n];
        int[] minFlights = new int[n];
        int[] maxFlights = new int[n];

        numRoutes[0] = 1;

        boolean[] visited = new boolean[n];

        while(!priorityQueue.isEmpty()) {
            Element element = priorityQueue.poll();

            if (visited[element.node]) continue;
            visited[element.node] = true;

            for (Edge edge: linkedLists[element.node]) {
                if (visited[edge.node]) continue;
                if (distance[edge.node] == distance[element.node] + edge.weight) {
                    numRoutes[edge.node] = CPMath.add(numRoutes[edge.node], numRoutes[element.node]);
                    minFlights[edge.node] = Math.min(minFlights[edge.node], minFlights[element.node] + 1);
                    maxFlights[edge.node] = Math.max(maxFlights[edge.node], maxFlights[element.node] + 1);
                    priorityQueue.add(new Element(edge.node));
                }

                if (distance[edge.node] > distance[element.node] + edge.weight) {
                    distance[edge.node] = distance[element.node] + edge.weight;
                    numRoutes[edge.node] = numRoutes[element.node];
                    minFlights[edge.node] = minFlights[element.node] + 1;
                    maxFlights[edge.node] = maxFlights[element.node] + 1;
                    priorityQueue.add(new Element(edge.node));
                }
            }
        }



        pw.print(distance[n-1]);
        pw.print(" ");
        pw.print(numRoutes[n-1]);
        pw.print(" ");
        pw.print(minFlights[n-1]);
        pw.print(" ");
        pw.print(maxFlights[n-1]);
        pw.close();
    }

    static class Edge {
        int node;
        long weight;

        Edge(int node, long weight) {
            this.node = node;
            this.weight = weight;
        }
    }
    static class Element implements Comparable<Element> {
        int node;

        Element(int node) {
            this.node = node;
        }

        @Override
        public int compareTo(Element o) {
            return Long.compare(distance[this.node], distance[o.node]);
        }
    }
}


