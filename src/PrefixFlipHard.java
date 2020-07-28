// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class PrefixFlipHard {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            int n = in.nextInt();

            char[] bin1 = in.next().toCharArray();
            char[] bin2 = in.next().toCharArray();

            ArrayDeque<Integer> deque = new ArrayDeque<>();

            for (int j = 0; j < n; j++) {
                if (bin1[j] == '1') deque.add(1);
                else deque.add(0);
            }

            int[] destination = new int[n];

            for (int j = 0; j < n; j++) {
                if (bin2[j] == '1') destination[j] = 1;
                else destination[j] = 0;
            }

            boolean reversed = false;

            ArrayList<Integer> steps = new ArrayList<>();

            for (int j = n-1; j >= 1; j--) {
                if (reversed && deque.peekFirst() != destination[j]) {
                    deque.removeFirst();
                }

                else if (!reversed && deque.peekLast() == destination[j]) {
                    deque.removeLast();
                }

                else {
                    if (deque.peekFirst() != deque.peekLast()) {
                        // need to reverse the "first" value

                        if (reversed) {
                            int val = deque.removeLast();
                            deque.addLast((val + 1) % 2);
                        }

                        else {
                            int val = deque.removeFirst();
                            deque.addFirst((val + 1) % 2);
                        }

                        steps.add(1);
                    }

                    steps.add(j+1);

                    reversed = !reversed;

                    if (reversed) {
                        deque.removeFirst();
                    }
                    else {
                        deque.removeLast();
                    }
                }
            }

            if (reversed && deque.peekFirst() == destination[0]) {
                steps.add(1);
            }

            else if (!reversed && deque.peekFirst() != destination[0]){
                steps.add(1);
            }

            out.print(steps.size() + " ");

            for (int j = 0; j < steps.size(); j++) {
                out.print(steps.get(j));

                if (j != steps.size() - 1) {
                    out.print(" ");
                }
            }

            out.println();
        }

        out.close();
    }
}


