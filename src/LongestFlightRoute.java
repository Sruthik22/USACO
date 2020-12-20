import java.util.*;
import java.io.*;

//longest_path
public class LongestFlightRoute {

    static int prev_flight[], dist[], in_degree[];
    static ArrayList<Integer> edge[];
    static ArrayList<Integer> backEdge[];

    static int N, M;

    //does a topological sort
    static void compute() {
        Queue<Integer> q = new ArrayDeque<Integer>();
        for(int i = 0; i < N; i++) {
            if(in_degree[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int node = q.poll();

            for(int next : edge[node]) {
                in_degree[next]--;
                if(in_degree[next] == 0) q.add(next);
            }

            //The below block computes the DP
            int mx = -999999999;
            int mx_node = -1;
            for(int prev : backEdge[node]) {
                if(dist[prev] + 1 > mx) {
                    mx = dist[prev] + 1;
                    mx_node = prev;
                }
            }

            dist[node] = mx;
            if(node == 0) dist[node] = 1;
            prev_flight[node] = mx_node;
        }
    }

    public static void main(String[] args) throws Exception {
        FastIO sc = new FastIO(System.in); //View "General - Fast I/O" for more information about the "FastIO" class

        N = sc.nextInt();
        M = sc.nextInt();

        prev_flight = new int[N];
        dist = new int[N];
        in_degree = new int[N];
        edge = new ArrayList[N];
        backEdge = new ArrayList[N];

        for(int i = 0; i < N; i++) {
            prev_flight[i] = -1;
            dist[i] = -999999999;
            in_degree[i] = 0;
            edge[i] = new ArrayList<Integer>();
            backEdge[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < M; i++) {
            int a = sc.nextInt(), b = sc.nextInt();
            a--; b--;
            in_degree[b]++;
            edge[a].add(b);
            backEdge[b].add(a);
        }

        compute();

        PrintWriter pw = new PrintWriter(System.out);

        ArrayDeque<Integer> answer = new ArrayDeque<Integer>(); //Acts as a Stack
        int temp = N-1;

        boolean contains0 = false;
        if(temp == 0) contains0 = true;

        while(temp != -1 && dist[temp] >= 0) {
            answer.push(temp);
            temp = prev_flight[temp];
            if(temp == 0) contains0 = true;
        }

        if(contains0) {
            pw.println(dist[N-1]);
            while(!answer.isEmpty()) {
                pw.print(answer.peekFirst()+1);
                answer.pop();
                if(!answer.isEmpty()) pw.print(" ");
            }
            pw.println();
        } else {
            pw.println("IMPOSSIBLE\n");
        }
        pw.close();
    }

    //practically a necessity for Java users on CSES
    static class FastIO {

        InputStream dis;
        byte[] buffer = new byte[1 << 17];
        int pointer = 0;

        public FastIO(String fileName) throws Exception {
            dis = new FileInputStream(fileName);
        }

        public FastIO(InputStream is) throws Exception {
            dis = is;
        }

        int nextInt() throws Exception {
            int ret = 0;

            byte b;
            do {
                b = nextByte();
            } while (b <= ' ');
            boolean negative = false;
            if (b == '-') {
                negative = true;
                b = nextByte();
            }
            while (b >= '0' && b <= '9') {
                ret = 10 * ret + b - '0';
                b = nextByte();
            }

            return (negative) ? -ret : ret;
        }

        long nextLong() throws Exception {
            long ret = 0;

            byte b;
            do {
                b = nextByte();
            } while (b <= ' ');
            boolean negative = false;
            if (b == '-') {
                negative = true;
                b = nextByte();
            }
            while (b >= '0' && b <= '9') {
                ret = 10 * ret + b - '0';
                b = nextByte();
            }

            return (negative) ? -ret : ret;
        }

        byte nextByte() throws Exception {
            if (pointer == buffer.length) {
                dis.read(buffer, 0, buffer.length);
                pointer = 0;
            }
            return buffer[pointer++];
        }

        String next() throws Exception {
            StringBuffer ret = new StringBuffer();

            byte b;
            do {
                b = nextByte();
            } while (b <= ' ');
            while (b > ' ') {
                ret.appendCodePoint(b);
                b = nextByte();
            }

            return ret.toString();
        }

    }
}
