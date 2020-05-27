// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class highcard {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("highcard.in"));

        int n = in.nextInt();
        ArrayList<Integer> bessieCards = new ArrayList<>();
        ArrayList<Integer> elsieCards = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int j = in.nextInt();
            elsieCards.add(j);
        }

        in.close();

        Collections.sort(elsieCards);

        for (int i = 1; i <= 2*n; i++) {
            if (Collections.binarySearch(elsieCards, i) >= 0) continue;
            bessieCards.add(i);
        }

        int result = 0;

        for (int i = n-1; i >= 0; i--) {

            int elsieCard = elsieCards.get(i);

            if (elsieCard > bessieCards.get(i)) bessieCards.remove(0);

            else {
                int insertion = -(Collections.binarySearch(bessieCards, elsieCard) + 1);

                bessieCards.remove(insertion);
                result++;
            }
        }

        PrintWriter out = new PrintWriter(new File("highcard.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}


