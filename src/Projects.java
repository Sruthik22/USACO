import java.util.*;
import java.io.*;

public class Projects {

    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
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

    static PrintWriter pw;

    static int mod = (int) (1e9 + 7);

    static ArrayList<Pair>[] arrayLists;

    public static void main(String[] args) throws Exception {
        Reader sc = new Reader();
        pw = new PrintWriter(System.out);

        int n = sc.nextInt();

        HashSet<Integer> hashSet = new HashSet<>();
        ArrayList<Integer> points = new ArrayList<>();

        Pair[] pairs = new Pair[n];

        for (int i = 0; i < n; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt() + 1;
            long value = sc.nextLong();

            Pair p = new Pair(start, value, end);
            pairs[i] = p;

            if (!hashSet.contains(start)) {
                points.add(start);
                hashSet.add(start);
            }
            if (!hashSet.contains(end)) {
                points.add(end);
                hashSet.add(end);
            }
        }

        assert points.size() != hashSet.size();
        assert points.size() < 2e5 + 5;

        Collections.sort(points);

        arrayLists = new ArrayList[points.size()];

        for (int i = 0; i < points.size(); i++) {
            arrayLists[i] = new ArrayList<>();
        }

        for (int i = 0; i < pairs.length; i++) {
            Pair p = pairs[i];

            int sI = Collections.binarySearch(points, p.start);
            int sE = Collections.binarySearch(points, p.end);

            p.start = sI;
            p.end = sE;

            arrayLists[sE].add(p);
        }

        long[] dp = new long[points.size()];

        for (int i = 0; i < points.size(); i++) {
            if (i != 0) dp[i] = dp[i - 1];

            for (Pair p: arrayLists[i]) {
                dp[i] = Math.max(dp[i], dp[p.start] + p.value);
            }
        }

        pw.println(dp[points.size() - 1]);
        pw.close();
    }

    static class Pair {
        int start, end;
        long value;

        Pair(int start, long value, int end) {
            this.start = start;
            this.value = value;
            this.end = end;
        }
    }
}


