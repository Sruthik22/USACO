// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.io.*;

public class findtrue {

    public static void main(String[] args) throws FileNotFoundException {
        int answer = 123456;

        boolean[] desired = new boolean[1000000];
        // is this value desired? (index)

        for (int i = answer; i < desired.length; i++) {
            desired[i] = true;
        }

        /*
        Always possible to binary search the array when there is a boundary between 'good' values and 'bad' values.
        */

        int low = -1; // nothing is desired this point and lower
        int high = 1000000; // at this point and higher everything is always true

        while (high - low > 1) {
            int mid = (low + high)/2;
            if (desired[mid]) high = mid;
            else low = mid;
        }

        int result = high;
        System.out.println(result);
    }
  
 /*
 ANALYSIS
 
 */
}


