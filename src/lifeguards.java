// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class lifeguards {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("lifeguards.in"));
        int n = in.nextInt();

        State[] states = new State[n*2];

        for (int i = 0; i < n; i++) {
            int start = in.nextInt();
            int end = in.nextInt();

            states[i*2] = new State(start, i);
            states[i*2+1] = new State(end, i);
        }

        Arrays.sort(states);

        in.close();

        int result = 0;

        int totalTime = 0;

        TreeSet<Integer> current = new TreeSet<>(); // indexes of the cows
        int[] alone = new int[n]; // each index corresponds to amount of time alone

        int last = 0;

        for (int i = 0; i < 2*n; i++) {
            State s = states[i];

            // another is being added to one that was alone
            if (current.size() == 1) {
                alone[current.first()] += s.time - last;
            }

            if (!current.isEmpty()) {
                totalTime += s.time - last;
            }

            // one is being removed
            if (current.contains(s.index)) {
                current.remove(s.index);
            }

            else {
                current.add(s.index);
            }

            last = s.time;
        }

        for (int i : alone) {
            result = Math.max(result, totalTime - i);
        }

        PrintWriter out = new PrintWriter(new File("lifeguards.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    private static class State implements Comparable<State>{
        int time, index;

        State(int time, int index) {
            this.time = time;
            this.index = index;
        }

        @Override
        public int compareTo(State o) {
            return this.time - o.time;
        }
    }

    // tree set to hold current cows
    // array to hold the cows that are alone
    // variable to hold the total time for the entire array
    // above variable is later used to subtract from the least time in the alone array

}


