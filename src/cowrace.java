// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;


public class cowrace {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("cowrace.in"));
        int n = in.nextInt();
        int m = in.nextInt();

        int[] bSpeeds = new int[n];
        List< Integer > bTimes = new ArrayList<>(n);
        int[] eSpeeds = new int[m];
        List< Integer > eTimes = new ArrayList<>(m);

        Set<Integer> importantTimes = new TreeSet<>();

        // tree set: no duplicates with order
        int curTime = 0;
        for (int i = 0; i < n; i++) {
            bSpeeds[i] = in.nextInt();
            curTime += in.nextInt();
            bTimes.add(curTime);
            importantTimes.add(curTime);
        }

        curTime = 0;
        for (int i = 0; i < m; i++) {
            eSpeeds[i] = in.nextInt();
            curTime += in.nextInt();
            eTimes.add(curTime);
            importantTimes.add(curTime);
        }

        in.close();

        int result = 0;

        int lead = 0;
        int BESSIE = 1; // BESSIE is in the lead
        int ELSIE = 2; // ELSIE is in the lead

        int bessiePos = 0, elsiePos = 0;
        int bessieSpd = bSpeeds[0], elsieSpd = eSpeeds[0];
        int lastTime = 0;

        int totalTime = bTimes.get(bTimes.size() - 1);

        for (int t : importantTimes) {

            bessiePos += (t-lastTime)*bessieSpd;
            elsiePos += (t-lastTime)*elsieSpd;

            if (bessiePos > elsiePos) {
                if (lead == ELSIE) {
                    result ++;
                }
                lead = BESSIE;
            }

            else if (elsiePos > bessiePos) {
                if (lead == BESSIE) {
                    result ++;
                }
                lead = ELSIE;
            }

            int eIndex = eTimes.indexOf(t);
            if (eIndex != -1 && t != totalTime) {
                elsieSpd = eSpeeds[eIndex+1];
            }

            int bIndex = bTimes.indexOf(t);
            if (bIndex != -1 && t != totalTime) {
                bessieSpd = bSpeeds[bIndex+1];
            }

            lastTime = t;

        }

        PrintWriter out = new PrintWriter(new File("cowrace.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }
}



