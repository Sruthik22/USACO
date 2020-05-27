// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class convention2 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("convention2.in"));
        int n = in.nextInt();

        Cow[] cows = new Cow[n];

        for (int i = 0; i < n; i++) {
            cows[i] = new Cow(in, i);
        }

        Arrays.sort(cows);

        in.close();

        int result = 0;

        for (int i = 0; i < n; i++) {
            Cow current = cows[i];

            int timeToCheckUntil = current.startTime + current.timeSpentAtGrass;

            ArrayList<Cow> cowsWaiting = new ArrayList<>();

            int nextI = i+1;

            while (nextI < n) {
                if (cows[nextI].startTime <= timeToCheckUntil) {
                    cowsWaiting.add(cows[nextI]);
                    nextI++;
                }
                else break;
            }

            if (!cowsWaiting.isEmpty()) {
                Collections.sort(cowsWaiting, Cow.cowSeniorityComparator);

                int calculatedWaiting = timeToCheckUntil;
                int lengthOfCowsWaiting = cowsWaiting.size();

                while (cowsWaiting.size() > 0) {

                    Cow c = cowsWaiting.get(0);

                    result = Math.max(result, calculatedWaiting - c.startTime);

                    calculatedWaiting += c.timeSpentAtGrass;

                    cowsWaiting.remove(c);

                    while (nextI < n) {
                        if (cows[nextI].startTime <= calculatedWaiting) {
                            cowsWaiting.add(cows[nextI]);
                            lengthOfCowsWaiting++;
                            nextI++;
                        }
                        else break;
                    }

                    Collections.sort(cowsWaiting, Cow.cowSeniorityComparator);
                }

                i+= lengthOfCowsWaiting;
            }
        }

        PrintWriter out = new PrintWriter(new File("convention2.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class Cow implements Comparable<Cow> {
        int seniority, startTime, timeSpentAtGrass;

        private Cow (Scanner in, int i) {
            this.seniority = i;
            this.startTime = in.nextInt();
            this.timeSpentAtGrass = in.nextInt();
        }

        @Override
        public int compareTo(Cow o) {
            if (this.startTime == o.startTime) {
                return this.seniority - o.seniority;
            }

            else return this.startTime - o.startTime;
        }

        private static Comparator<Cow> cowSeniorityComparator = Comparator.comparingInt(c -> c.seniority);
    }
  
 /*
 ANALYSIS

 Cow class with 3 variables:
    seniority
    startTime
    timeSpentAtGrass

 straightforward simulation problem

 sort all the cows based on their startTime

 When next cow is called, check the cow after that to check if they are during the waiting period

 if not: then move to the next cow
 if so:
 create a new list
while loop until the next cows starting time is after current starting time + watching time

sort through the cow array by the seniority of the cows

to get the wait time of the last cow, which is the one that matters because we are trying to maximize the longest waiting time
add all of the timeSpentAtGrass

make result = Math.max(result, calculated)

When the cows are going up, more cows can also be added,
 based on the current times, and they will need to be added to the list as well

There will be a total list length counter to be added to i at the end of the process
every cow through the list, the cow will be deleted, and if cows were added, the list would be sorted by seniority again

needs to be a while list to go through the list

MAIN LEARNING LESSONS:

- NEVER TRY TO TAKE A SHORTCUT TO SAVE TIME, SHORTCUT CAN CAUSE UNFORSEEN PROBLEMS
- LOOPING THROUGH AN ARRAYLIST WITH SORTING, AND DELETION JUST DO NORMAL WHILE, NO ITERATOR (Causes problems)

 */
}


