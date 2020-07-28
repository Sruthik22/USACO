// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class Mentors {

    static StreamTokenizer in;

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws Exception {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = nextInt();
        int k = nextInt();

        Programmer[] programmers = new Programmer[n];

        for (int i = 0; i < n; i++) {
            int skill = nextInt();

            programmers[i] = new Programmer(i, skill);
        }

        int[] subs = new int[n];

        for (int i = 0; i < k; i++) {
            int p1 = nextInt() - 1;
            int p2 = nextInt() - 1;

            // we need to add one with the greater skill

            if (programmers[p1].skill > programmers[p2].skill) {
                subs[p1]++;
            }

            else if (programmers[p1].skill < programmers[p2].skill) {
                subs[p2]++;
            }
        }

        Arrays.sort(programmers);

        int[] results = new int[n];

        for (int i = 0; i < n; i++) {
            int id = programmers[i].id;
            int skill = programmers[i].skill;
            int index = findFirstOccurrence(programmers, skill);
            int num = index - subs[id];

            results[id] = num;
        }

        for (int i = 0; i < n; i++) {
            out.print(results[i]);

            if (i != n-1) {
                out.print(" ");
            }
        }

        out.close();
    }

    static class Programmer implements Comparable<Programmer> {
        int skill, id;

        Programmer(int id, int skill) {
            this.id = id;
            this.skill = skill;
        }

        @Override
        public int compareTo(Programmer o) {
            if (this.skill == o.skill) {
                return this.id - o.id;
            }
            return this.skill - o.skill;
        }
    }

    public static int findFirstOccurrence(Programmer[] programmers, int x)
    {
        // search space is A[left..right]
        int left = 0;
        int right = programmers.length - 1;

        // initialize the result by -1
        int result = -1;

        // iterate till search space contains at-least one element
        while (left <= right)
        {
            // find the mid value in the search space and
            // compares it with key value
            int mid = (left + right) / 2;

            // if key is found, update the result and
            // go on searching towards right (higher indices)
            if (x == programmers[mid].skill) {
                result = mid;
                right = mid - 1;
            }

            // if key is less than the mid element, discard right half
            else if (x < programmers[mid].skill) {
                right = mid - 1;
            }

            // if key is more than the mid element, discard left half
            else {
                left = mid + 1;
            }
        }

        // return the leftmost index or -1 if the element is not found
        return result;
    }
}


