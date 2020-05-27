// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class hayfeast {

    static StreamTokenizer in;

    static long nextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new FileReader("hayfeast.in")));
        PrintWriter out = new PrintWriter(new File("hayfeast.out"));

        int n = (int) nextLong();
        long m = nextLong();

        long result = Integer.MAX_VALUE;

        long currentFlavor = 0;
        int startIndex = 0;

        Meal[] meals = new Meal[n];

        PriorityQueue<Meal> states = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            // go through all of the meals until the spiciness is at least m
            // until then just add to the priority queue that sorts on spiciness
            // once reached update result, and slide the window

            long flavor = nextLong();
            long spiciness = nextLong();

            Meal meal = new Meal(flavor, spiciness, i);

            states.add(meal);

            meals[i] = meal;

            currentFlavor+=flavor;

            if (currentFlavor >= m) {

                // need to make sure that the head of the priority queue is actually in the current window
                Meal largest;

                do {
                    largest = states.poll();
                } while (largest.index < startIndex);

                // need to add largest back into the priority queue

                states.add(largest);

                result = Math.min(result, largest.spiciness); // want to get the max value (make sure this isn't min)
                // need to remove the spiciness from the meal at start index from the current spiciness
                currentFlavor-= meals[startIndex].flavor;
                startIndex++;
            }
        }

        System.out.println("result is " + result);

        out.println(result);
        out.close();
    }

    static class Meal implements Comparable<Meal>{
        long flavor, spiciness;
        int index;

        Meal (long flavor, long spiciness, int index) {
            this.flavor = flavor;
            this.spiciness = spiciness;
            this.index = index;
        }

        @Override
        public int compareTo(Meal o) {
            if (o.spiciness - this.spiciness == 0) {
                return o.index - this.index;
            }

            else return (int) (o.spiciness - this.spiciness);
        }
    }
}


