// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

//http://www.usaco.org/index.php?page=viewproblem2&cpid=527

import java.util.*;
import java.io.*;

public class cow {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("cow.in"));

        int n = in.nextInt();

        List<Integer> c_positions = new ArrayList<>();
        List<Integer> o_positions = new ArrayList<>();
        List<Integer> w_positions = new ArrayList<>();

        String s = in.next();

        for (int i = 0; i < n; i++) {
            switch (s.charAt(i)) {
                case 'C':
                    c_positions.add(i);
                    break;
                case 'O':
                    o_positions.add(i);
                    break;
                case 'W':
                    w_positions.add(i);
                    break;
            }
        }

        in.close();

        int c_index = c_positions.size()-1;
        int w_index = 0;
        long result = 0;

        boolean b1 = false;
        boolean b2 = false;

        boolean wBreak = false;

        for (int i:o_positions) {
            // find all of the indexes after this in the o_positions

            b1 = false;
            wBreak = false;
            b2 = false;

            while (c_positions.get(c_index) > i) {
                c_index--;

                b1 = true;

                if (c_index < 0) {
                    c_index = c_positions.size()-1;
                    wBreak = true;
                    break;
                }
            }

            if (wBreak) continue;

            while (!b1) {
                if ((c_index+1 <= c_positions.size() - 1) && c_positions.get(c_index + 1) < i) {
                    c_index++;
                }

                else {
                    b1 = true;
                }
            }

            while (w_positions.get(w_index) < i) {
                w_index++;

                b2 = true;

                if (w_index > w_positions.size()-1) {
                    wBreak = true;
                    break;
                }
            }

            if (wBreak) break;

            while (!b2) {
                if (w_index-1 >= 0 && w_positions.get(w_index - 1) > i) {
                    w_index--;
                }

                else {
                    b2 = true;
                }
            }

            result += (c_index+1)*(w_positions.size()-w_index);
        }


        PrintWriter out = new PrintWriter(new File("cow.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }


}


