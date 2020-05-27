// This template code suggested by KT BYTE Computer Science Academy
//  for use in reading and writing files for USACO problems.

//https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class blink {

    private static int[] bulbs;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("blink.in"));
        int n = in.nextInt(); // bulbs
        long b = in.nextLong(); // # time steps to go forward
        bulbs = new int[n];

        for (int i = 0; i < n; i++) {
            bulbs[i] = in.nextInt();
        }

        in.close();

        HashMap<String, Long> pStates = new HashMap<>();

        // key -> string state
        // value -> time index prev seen

        for (long t =0; t<b;t++) {
            pStates.put(Arrays.toString(bulbs), t);
            step();

            String newState = Arrays.toString(bulbs);
            if (pStates.containsKey(newState)) {
                long remaining = b- (t+1);
                long cycleLen = t+1-pStates.get(newState);
                t+= (remaining / cycleLen) * cycleLen;
            }
        }

        PrintWriter out = new PrintWriter(new File("blink.out"));
        for (int i = 0; i< n; i++) {
            System.out.println(bulbs[i]);
            out.println(bulbs[i]);
        }
        out.close();
    }

    static void step() {
        // loop from right to left to make sure we are changing properly
        int lastBulb = bulbs[bulbs.length-1];

        for (int i = bulbs.length-1; i>0; i--) {
            if (bulbs[i-1] == 1) {
                // toggle
                bulbs[i] = (bulbs[i] + 1) % 2;
            }
        }

        if (lastBulb == 1) bulbs[0] = 1-bulbs[0];
    }
}

// java integer 32 bits 2^(32-1)-1, 1 of bits is used for pod/neg
//0 is the other

// sets - collection of elements with no duplicates
//maps - "keys and values"

// tree - sorted O(log n)
// hash - nonsorted O(1) - constant time avg
// hash map

// arrays have to have length in int