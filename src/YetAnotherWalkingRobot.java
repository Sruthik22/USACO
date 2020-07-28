// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class YetAnotherWalkingRobot {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int t = nextInt();

        for (int i = 0; i < t; i++) {
            int n = nextInt();

            String instructions = next();

            Point point = new Point(0, 0);

            HashMap<String, Integer> pointTimes = new HashMap<>();
            pointTimes.put(point.toString(), 0);

            int time = 1;

            int length = Integer.MAX_VALUE;
            int min = 0;
            int max = 0;

            for (char c: instructions.toCharArray()) {
                point.direction(c);
                if (pointTimes.containsKey(point.toString())) {
                    int val = pointTimes.get(point.toString());

                    int curLength = time - val;

                    if (curLength < length) {
                        length = curLength;
                        min = val+1;
                        max = time;
                    }
                }

                pointTimes.put(point.toString(), time);

                time++;
            }

            if (length == Integer.MAX_VALUE) {
                out.println(-1);
            }

            else {
                out.println(min + " " + max);
            }
        }

        out.close();
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void direction(char c) {
            if (c == 'L') left();
            else if (c == 'R') right();
            else if (c == 'U') up();
            else down();
        }

        void up() {
            this.y++;
        }

        void down() {
            this.y--;
        }

        void right() {
            this.x++;
        }

        void left() {
            this.x--;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }
}


