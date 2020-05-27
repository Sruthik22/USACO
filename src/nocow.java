// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class nocow {

    public static void main(String[] args) throws Exception {

        // Set up.
        Scanner stdin = new Scanner(new File("nocow.in"));
        StringTokenizer tok = new StringTokenizer(stdin.nextLine());
        int n = Integer.parseInt(tok.nextToken());
        int k = Integer.parseInt(tok.nextToken())-1;

        // More set up...
        tok = new StringTokenizer(stdin.nextLine());
        int adj = tok.countTokens() - 5;
        TreeMap[] characteristics = new TreeMap[adj];
        String[][] missing = new String[n][adj];
        for (int i=0; i<adj; i++) characteristics[i] = new TreeMap<String,Integer>();

        // Read in data.
        for (int i=0; i<n; i++) {
            for (int j=0; j<4; j++) tok.nextToken();
            for (int j=0; j<adj; j++) {
                missing[i][j] = tok.nextToken();
                characteristics[j].put(missing[i][j],0);
            }
            if (i < n-1) tok = new StringTokenizer(stdin.nextLine());
        }

        // Create a list of characteristics and sort it.
        String[][] lookup = new String[adj][];
        for (int i=0; i<adj; i++) {
            ArrayList<String> myList = new ArrayList<String>();

            for (String x: (Set<String>)characteristics[i].keySet())
                myList.add(x);
            Collections.sort(myList);

            // Create our look up here.
            lookup[i] = new String[myList.size()];
            for (int j=0; j<myList.size(); j++) {
                characteristics[i].put(myList.get(j), j);
                lookup[i][j] = myList.get(j);
            }
        }

        // Get all the missing cows so we can find our adjusted rank.
        int[] numCharacteristics = new int[adj];
        for (int i=0; i<adj; i++) numCharacteristics[i] = characteristics[i].size();
        int[] missID = new int[n];
        for (int i=0; i<n; i++)
            missID[i] = getID(missing[i],characteristics);
        Arrays.sort(missID);
        int ans = search(missID, k);

        // Build the answer from the result array.
        String[] res = getResult(ans,lookup,numCharacteristics);
        String answer = "";
        for (int i=0; i<adj-1; i++)
            answer = answer + res[i] + " ";
        answer = answer + res[adj-1];

        // Write out hte answer.
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocow.out")));
        out.println(answer);
        out.close();
        stdin.close();
    }

    // Peel off the answer, one by one and return the appropriate cow.
    public static String[] getResult(int ans, String[][] lookup, int[] numCharacteristics) {
        String[] res = new String[lookup.length];
        for (int i=res.length-1; i>=0; i--) {
            int index = ans%numCharacteristics[i];
            res[i] = lookup[i][index];
            ans /= numCharacteristics[i];
        }
        return res;
    }

    // Gets the ID of this cow based on its characteristics.
    public static int getID(String[] cow, TreeMap[] characteristics) {
        int total = 0, multiplier = 1;
        for (int i=0; i<cow.length; i++) {
            total = (total*multiplier) +  (Integer)characteristics[i].get(cow[i]);
            multiplier = characteristics[i].size();
        }
        return total;
    }

    // Just does an iterative search knowing we can start at k.
    public static int search(int[] missID, int k) {
        int ans = k;
        while (ans - below(missID, ans) < k || in(missID, ans)) ans++;
        return ans;
    }

    // Returns the number of items in missID less than value.
    public static int below(int[] missID, int value) {
        int i, cnt=0;
        for (i=0; i<missID.length; i++)
            if (missID[i] < value)
                cnt++;
        return cnt;
    }

    // Returns true iff value is in missID.
    public static boolean in(int[] missID, int value) {
        int i, cnt=0;
        for (i=0; i<missID.length; i++)
            if (missID[i] == value)
                return true;
        return false;
    }
}