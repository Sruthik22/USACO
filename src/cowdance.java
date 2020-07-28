// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class cowdance {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static int n;
    static int t_max;
    static int[] durations;

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("cowdance.in")));

        n = nextInt();
        t_max = nextInt();

        durations = new int[n];

        for (int i = 0; i < n; i++) {
            durations[i] = nextInt();
        }

        int low = -1; // nothing is desired this point and lower
        int high = 10000; // at this point and higher everything is always true

        while (high - low > 1) {
            int mid = (low + high)/2;
            if (check(mid)) high = mid;
            else low = mid;
        }

        int result = high;
        PrintWriter out = new PrintWriter(new File("cowdance.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static boolean check(int k) {
        PriorityQueue<Integer> cows = new PriorityQueue<>();

        // we need to remove the fastest cow, every time and replace -- can achieve with a priority queue

        for (int i = 0; i < Math.min(k, n); i++) {
            cows.add(durations[i]);
        }

        int time = 0;

        for (int i = k; i < n; i++) {
            if (cows.size() == k) {
                time = cows.poll();
                if (time > t_max) return false;
            }

            // need to add the current cow

            cows.add(durations[i] + time);
        }

        int size = cows.size();

        for (int i = 0; i < size; i++) {
            time = cows.poll();
        }

        return time <= t_max;
    }
}


