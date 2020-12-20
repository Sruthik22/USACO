import java.util.*;
import java.io.*;

public class RoomAllocation {

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
        sc = new InputReader(System.in);
        pw = new PrintWriter(System.out);

        int n = sc.nextInt();

        Customer[] customers = new Customer[n];

        for (int i = 0; i < n; i++) {
            customers[i] = new Customer(sc.nextInt(), sc.nextInt(), i);
        }

        Arrays.sort(customers);

        int result = 0;

        TreeMap<Integer, Integer> treeSet = new TreeMap<>();
        HashMap<Integer, ArrayList<Integer>> ids = new HashMap<>();

        int[] room_num = new int[n];

        int cur = 0;

        for (Customer customer: customers) {

            room_num[customer.id] = -1;

            if (treeSet.lowerKey(customer.arrive) != null) {
                int key = treeSet.lowerKey(customer.arrive);
                treeSet.put(key, treeSet.get(key) - 1);
                if (treeSet.get(key) == 0) treeSet.remove(key);
                cur--;

                room_num[customer.id] = ids.get(key).get(ids.get(key).size() - 1);
                ids.get(key).remove(ids.get(key).size() - 1);
            }

            treeSet.putIfAbsent(customer.depart, 0);
            treeSet.put(customer.depart, treeSet.get(customer.depart) + 1);
            cur++;

            if (room_num[customer.id] == -1) {
                room_num[customer.id] = result + 1;
            }

            ids.putIfAbsent(customer.depart, new ArrayList<>());
            ids.get(customer.depart).add(room_num[customer.id]);

            result = Math.max(result, cur);
        }

        pw.println(result);

        for (int i = 0; i < n; i++) {
            pw.print(room_num[i] + " ");
        }

        pw.close();
    }

    static class Customer implements Comparable<Customer> {
        int arrive, depart, id;

        Customer(int arrive, int depart, int id) {
            this.arrive = arrive;
            this.depart = depart;
            this.id = id;
        }

        @Override
        public int compareTo(Customer o) {
            return this.arrive - o.arrive;
        }
    }
}