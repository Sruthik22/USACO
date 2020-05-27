// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class greedyBasicInterval {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt(); // number of test cases

        for (int i = 0; i < t; i++) {
            int size = in.nextInt();

            int[] start = new int[size];
            int[] end = new int[size];

            Meeting[] meetings = new Meeting[size];

            for (int j = 0; j < size; j++) {
                start[j] = in.nextInt();
            }

            for (int j = 0; j < size; j++) {
                end[j] = in.nextInt();
            }

            for (int j = 0; j < size; j++) {
                meetings[j] = new Meeting(start[j], end[j], j);
            }

            Arrays.sort(meetings);

            int needToBeAfter = 0;

            for (int j = 0; j < size; j++) {
                if (meetings[j].starting >= needToBeAfter) {
                    System.out.print(meetings[j].index+1);
                    if (j != size - 1) System.out.print(" ");
                    needToBeAfter = meetings[j].ending;
                }
            }

            System.out.println();
        }

        in.close();
    }

    private static class Meeting implements Comparable<Meeting> {

        int starting, ending, index;

        public Meeting(int starting, int ending, int index) {
            this.starting = starting;
            this.ending = ending;
            this.index = index;
        }

        @Override
        public int compareTo(Meeting o) {
            return this.ending - o.ending;
        }
    }
}


