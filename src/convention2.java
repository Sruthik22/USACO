import java.util.*;
import java.io.*;

public class convention2 {

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
        sc = new InputReader(new FileInputStream("convention2.in"));
        pw = new PrintWriter(new File("convention2.out"));

        int n = sc.nextInt();

        Cow[] cows = new Cow[n];

        for (int i = 0; i < n; i++) {
            Cow c = new Cow(i, sc.nextInt(), sc.nextInt());
            cows[i] = c;
        }

        Arrays.sort(cows, new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                return o1.arrival - o2.arrival;
            }
        });

        int result = 0;

        PriorityQueue<Cow> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(cows[0]);
        int current_time = cows[0].arrival;

        int cow_pointer = 1;

        for (int i = 0; i < n; i++) {
            Cow cur = priorityQueue.poll();
            result = Math.max(result, current_time - cur.arrival);
            current_time += cur.sampling_time;

            while (cow_pointer < n && cows[cow_pointer].arrival <= current_time) {
                priorityQueue.add(cows[cow_pointer]);
                cow_pointer++;
            }

            if (priorityQueue.isEmpty() && i != n-1) {
                priorityQueue.add(cows[cow_pointer]);
                current_time = cows[cow_pointer].arrival;
                cow_pointer++;
            }
        }

        pw.println(result);
        pw.close();
    }

    static class Cow implements Comparable<Cow> {
        int priority, arrival, sampling_time;

        Cow(int priority, int arrival, int sampling_time) {
            this.priority = priority;
            this.arrival = arrival;
            this.sampling_time = sampling_time;
        }

        @Override
        public int compareTo(Cow o) {
            return this.priority - o.priority;
        }
    }
}